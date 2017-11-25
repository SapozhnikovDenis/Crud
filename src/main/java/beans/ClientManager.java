package beans;

import connect.ConnectionDB;
import entity.User;

import javax.ejb.Local;
import javax.ejb.Remote;
import java.util.List;

@Local
public interface ClientManager {

    boolean add(String nickname, String password,
                       String firstName, String lastName, String birthday);

    boolean update(String nickname, String password,
                          String firstName, String lastName, String birthday);

    boolean delete(String nickname);

    List<User> select();
}
