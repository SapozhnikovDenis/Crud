package connect.impl;

import connect.ConnectionDB;
import org.apache.log4j.Logger;

import javax.ejb.Singleton;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@Singleton
public class ConnectionDBImpl implements ConnectionDB {
    private Logger log = Logger.getLogger(ConnectionDBImpl.class);
    private Connection connection;

    public ConnectionDBImpl() {}

    private void initializeConnection() {
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:mem:test", "", "");
            createTable();
        } catch (SQLException | ClassNotFoundException e) {
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

    public Connection getConnection() {
        if (connection == null) initializeConnection();
        return connection;
    }
}
