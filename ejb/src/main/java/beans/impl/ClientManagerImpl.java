package beans.impl;

import beans.ClientManager;
import connect.ConnectionDB;
import entity.User;
import org.apache.log4j.Logger;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.*;
import java.sql.*;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

@Stateless
public class ClientManagerImpl implements ClientManager {
    private Logger log = Logger.getLogger(ClientManagerImpl.class);
    @Inject
    private ConnectionDB connectionDB;

    @Resource(name = "jms/crud")
    private ConnectionFactory connectionFactory;

    @Resource(name = "QueueCrud")
    private Destination destination;

    public ClientManagerImpl() {
        log.debug("constructor");
    }

    private boolean validate(String nickname, String password,
                             String firstName, String lastName, String birthday) {
        if (nickname.length() < 5) return false;
        if (password.length() < 1) return false;
        if (firstName.length() < 1) return false;
        if (lastName.length() < 1) return false;
        try {
            String split[] = birthday.split("[.]");
            if (Integer.parseInt(split[0]) < 0 || Integer.parseInt(split[0]) > 31) return false;
            if (Integer.parseInt(split[1]) < 0 || Integer.parseInt(split[1]) > 13) return false;
            if (Integer.parseInt(split[2]) < 0 || Integer.parseInt(split[2]) > 9999) return false;
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            log.debug("invalid not passed the validation");
            return false;
        }
        log.debug("user passed the validation");
        return true;
    }

    @Override
    public boolean add(String nickname, String password,
                       String firstName, String lastName, String birthday) {

        if (!validate(nickname, password, firstName, lastName, birthday)) return false;

        try {
            PreparedStatement ps = connectionDB.getConnection().prepareStatement("INSERT INTO USERS" +
                    " (nickname, password, firstname, lastname, birthday) VALUES (?,?,?,?,?);");
            ps.setString(1, nickname);
            ps.setString(2, password);
            ps.setString(3, firstName);
            ps.setString(4, lastName);
            ps.setDate(5, convertDate(birthday));
            ps.executeUpdate();
            if (!sendHistory(nickname, "add")) {
                log.error("User not add in DB");
                return false;
            }
            else
                log.debug("user add in DB");
        } catch (SQLException e) {
            log.error("insert is fall");
            return false;
        }
        return true;
    }
    @Override
    public boolean update(String nickname, String password,
                          String firstName, String lastName, String birthday) {
        if (!validate(nickname, password, firstName, lastName, birthday)) return false;
        try {
            PreparedStatement ps = connectionDB.getConnection().prepareStatement("UPDATE USERS SET " +
                    "password = ?, firstname = ?, lastname = ?, birthday = ?" +
                    "WHERE nickname = ?;");
            ps.setString(1, password);
            ps.setString(2, firstName);
            ps.setString(3, lastName);
            ps.setDate(4, convertDate(birthday));
            ps.setString(5, nickname);
            ps.executeUpdate();
            if (!sendHistory(nickname, "update")) {
                log.error("user not update in DB");
                return false;
            }
            else
                log.debug("user update in DB");
        } catch (SQLException e) {
            log.error("update is fall");
            return false;
        }
        return true;
    }
    @Override
    public boolean delete(String nickname) {
        try {
            if (!sendHistory(nickname, "delete")) {
                log.error("user not delete in DB");
                return false;
            }
            Statement st = connectionDB.getConnection().createStatement();
            st.execute("DELETE FROM USERS WHERE nickname = '" + nickname + "';");
            log.debug("user delete DB");
            st.close();
        } catch (SQLException e) {
            log.error("delete is fall", e);
            return false;
        }
        return true;
    }

    @Override
    public List<User> select() {
        List<User> users = new LinkedList<>();
        try {
            Statement st = connectionDB.getConnection().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM USERS;");
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setNickname(rs.getString("nickname"));
                user.setPassword(rs.getString("password"));
                user.setFirstName(rs.getString("firstname"));
                user.setLastName(rs.getString("lastname"));
                user.setBirthday(rs.getDate("birthday"));
                users.add(user);
            }
            log.debug("user select DB");
            st.close();
        } catch (SQLException e) {
            log.error("select is fall");
        }
        return users;
    }

    private Date convertDate(String birthday) {
        String split[] = birthday.split("[.]");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Integer.parseInt(split[2]), Integer.parseInt(split[1]) - 1,
                Integer.parseInt(split[0]));
        return new Date(calendar.getTimeInMillis());
    }

    private boolean sendHistory(String nickname, String action){
            try {
                byte[] xmlInByte = createXML(findUserInDB(nickname));
                javax.jms.Connection connectionJMS = connectionFactory.createConnection();
                Session session = connectionJMS.createSession(true, Session.AUTO_ACKNOWLEDGE);
                MessageProducer producer = session.createProducer(destination);
                BytesMessage message = session.createBytesMessage();

                message.setStringProperty("action", action);
                message.writeBytes(xmlInByte);
                producer.send(message);

                log.debug("message sent");
                session.close();
                connectionJMS.close();
            } catch (JMSException|SQLException|IllegalArgumentException e) {
                log.error("error sending message");
                return false;
            }
            return true;
    }

    private User findUserInDB(String nickname) throws SQLException {
        User user = new User();
        Statement st = connectionDB.getConnection().createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM USERS WHERE nickname = '" + nickname + "';");
        while (rs.next()) {
            user.setId(rs.getLong("id"));
            user.setNickname(rs.getString("nickname"));
            user.setPassword(rs.getString("password"));
            user.setFirstName(rs.getString("firstname"));
            user.setLastName(rs.getString("lastname"));
            user.setBirthday((java.util.Date)rs.getDate("birthday"));
        }
        st.close();
        if (user.getNickname() == null)
            throw new IllegalArgumentException();
        return user;
    }

    private byte[] createXML(User user) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(User.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(user, baos);
        } catch (JAXBException e) {
            log.debug("JAXB is fall", e);
        }
        return baos.toByteArray();
    }
}
