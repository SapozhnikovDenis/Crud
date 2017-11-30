package service;


import beans.ClientManager;
import entity.User;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public class Service {
    private Logger log = Logger.getLogger(Service.class);
    @EJB private ClientManager clientManager;

    @WebMethod
    public boolean add(String nickname, String password,
                                String firstName, String lastName, String birthday) {

        return clientManager.add(nickname, password, firstName, lastName, birthday);
    }

    @WebMethod
    public boolean update(String nickname, String password,
                       String firstName, String lastName, String birthday) {

        return clientManager.update(nickname, password, firstName, lastName, birthday);
    }

    @WebMethod
    public boolean delete(String nickname) {
        return clientManager.delete(nickname);
    }

    @WebMethod
    public List<User> select() {
        return clientManager.select();
    }
}
