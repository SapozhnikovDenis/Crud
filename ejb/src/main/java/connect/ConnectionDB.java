package connect;

import javax.ejb.Local;
import javax.ejb.Remote;
import java.sql.Connection;

@Local
public interface ConnectionDB {
    Connection getConnection();
}
