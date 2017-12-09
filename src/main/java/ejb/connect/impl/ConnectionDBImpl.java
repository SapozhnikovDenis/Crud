package ejb.connect.impl;

import ejb.connect.ConnectionDB;
import org.apache.log4j.Logger;

import javax.ejb.Singleton;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@Singleton
public class ConnectionDBImpl implements ConnectionDB {
    private Logger log = Logger.getLogger(ConnectionDBImpl.class);
    private Connection connection;

    private void initializeConnection() {
        try {
            File file = new File(ConnectionDB.class.getClassLoader().getResource("datasource.xml").getFile());
            JAXBContext jaxbContext = JAXBContext.newInstance(Connect.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Connect connect = (Connect) jaxbUnmarshaller.unmarshal(file);
            Class.forName(connect.getDriver());
            connection = DriverManager.getConnection(connect.getUrl(),connect.getUser(),connect.getPassword());
            createTable();
        } catch (Exception e) {
            log.error("connection falled down",e);
        }
    }

    private void createTable() throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("CREATE TABLE USERS(id BIGINT PRIMARY KEY auto_increment, " +
                "nickname VARCHAR (20) UNIQUE NOT NULL, password VARCHAR (255) NOT NULL, " +
                "firstname VARCHAR (255) NOT NULL, lastname VARCHAR (255) NOT NULL, birthday DATE NOT NULL);");

        statement.execute("CREATE TABLE HISTORY(id BIGINT PRIMARY KEY auto_increment, " +
                "action VARCHAR (20), user_id BIGINT NOT NULL, nickname VARCHAR (20) NOT NULL, time DATE);");
    }

    @Override
    public Connection getConnection() {
        if (connection == null) initializeConnection();
        return connection;
    }
}
