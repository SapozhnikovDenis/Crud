package jms;



import connect.ConnectionDB;
import entity.User;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.BytesMessage;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.sql.*;

@MessageDriven(mappedName="QueueCrud")
public class Auditor implements MessageListener {
    private static final Logger log = Logger.getLogger(Auditor.class);
    @EJB(mappedName = "connect")
    private ConnectionDB connectionDB;

    @Override
    public void onMessage(Message msg) {
        try {
            BytesMessage bm = (BytesMessage)msg;
            byte b[] = new byte[(int) bm.getBodyLength()];
            bm.readBytes(b);
            User user = readXML(new ByteArrayInputStream(b));
            String action = bm.getStringProperty("action");
            writeInDB(user, action);
            writeHistoryInLog();
        } catch (JMSException e) {
            log.debug("JMS is fall", e);
        }
    }

    private User readXML(ByteArrayInputStream bais) {
        User user = null;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(User.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            user = (User) jaxbUnmarshaller.unmarshal(bais);
        } catch (JAXBException e) {
            log.debug("JAXB is fall", e);
        }
        return user;
    }

    private boolean writeInDB(User user, String action) {
        try {
            PreparedStatement ps = connectionDB.getConnection().prepareStatement("INSERT INTO HISTORY " +
                    " (action, user_id, nickname, time) VALUES (?,?,?,?);");
            ps.setString(1,action);
            ps.setLong(2,user.getId());
            ps.setString(3, user.getNickname());
            ps.setDate(4, new Date(new java.util.Date().getTime()));
            ps.executeUpdate();
            log.debug("history add in DB");
        } catch (SQLException e) {
            log.error("history is fall", e);
            return false;
        }
        return true;
    }

    //test method
    private void writeHistoryInLog() {
        try {
            Statement st = connectionDB.getConnection().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM HISTORY;");
            while (rs.next()) {
                log.debug(rs.getLong("id") + " " + rs.getString("action") +
                        " " + rs.getLong("user_id") + " " + rs.getString("nickname"));
            }
            st.close();
        } catch (SQLException e) {
            log.error("history is fall", e);
        }
    }
}
