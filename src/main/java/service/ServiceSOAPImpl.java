package service;


import beans.ClientManager;
import entity.User;
import org.apache.log4j.Logger;

import javax.ejb.*;
import javax.jws.WebService;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.LinkedList;
import java.util.List;

@WebService(endpointInterface = "service.ServiceSOAP")
public class ServiceSOAPImpl implements ServiceSOAP{
    private Logger log = Logger.getLogger(ServiceSOAPImpl.class);
    @EJB
    ClientManager clientManager;
    {
        try {
            clientManager = (ClientManager)
                    new InitialContext().lookup("java:global/Crud-2.0/Crud/kek!beans.ClientManager");
            log.debug(clientManager);
        } catch (NamingException e) {
            log.error(e);
        }
    }

    @Override
    public boolean add(String nickname, String password,
                                String firstName, String lastName, String birthday) {
       // Kek kek = new Kek();
        log.debug("add " + nickname);
        log.debug(clientManager + " dsds");
        return true;//clientManager.add(nickname, password, firstName, lastName, birthday);
    }

    @Override
    public boolean update(String nickname, String password,
                       String firstName, String lastName, String birthday) {
        log.debug("update " + nickname);
        return true;//kek.clientManager.update(nickname, password, firstName, lastName, birthday);
    }

    @Override
    public boolean delete(String nickname) {
        log.debug("delete " + nickname);
        return true;//kek.clientManager.delete(nickname);
    }

    @Override
    public List<User> select() {
        log.debug("select");
        return new LinkedList<>();//kek.clientManager.select();
    }
}
