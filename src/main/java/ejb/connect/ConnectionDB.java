package ejb.connect;

import javax.ejb.Local;
import java.sql.Connection;

@Local
public interface ConnectionDB {
    Connection getConnection();
}
