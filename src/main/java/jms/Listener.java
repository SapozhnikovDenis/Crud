package jms;

import entity.User;
import org.apache.log4j.Logger;

import javax.ejb.MessageDriven;
import javax.jms.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;

@MessageDriven(mappedName="QueueCrud")
public class Listener implements MessageListener {
    private static final Logger log = Logger.getLogger(Listener.class);

    @Override
    public void onMessage(Message msg) {
        try {
            BytesMessage bm = (BytesMessage)msg;
            byte b[] = new byte[(int) bm.getBodyLength()];
            bm.readBytes(b);
            log.debug(bm.getStringProperty("clientType"));
            readXML(new ByteArrayInputStream(b));
        } catch (JMSException e) {
            log.debug("JMS is fall", e);
        }
    }

    private void readXML(ByteArrayInputStream bais) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(User.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            User user = (User) jaxbUnmarshaller.unmarshal(bais);
            log.debug("message - " + user.toString());
        } catch (JAXBException e) {
            log.debug("JAXB is fall", e);
        }
    }
}
