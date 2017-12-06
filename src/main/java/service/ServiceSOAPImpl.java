package service;


import beans.ClientManager;
import entity.User;
import org.apache.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.*;
import javax.jws.WebService;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.LinkedList;
import java.util.List;

@WebService(endpointInterface = "service.ServiceSOAP")
public class ServiceSOAPImpl implements ServiceSOAP{
    private Logger log = Logger.getLogger(ServiceSOAPImpl.class);

    ClientManager clientManager;

    @PostConstruct
    private void initClientManager() {
        try {
            clientManager = (ClientManager)
                    new InitialContext().lookup("ClientManager");
        } catch (NamingException e) {
            log.error(e);
        }
    }

    @Override
    public boolean add(String nickname, String password,
                                String firstName, String lastName, String birthday) {
        log.debug("add " + nickname);
        return clientManager.add(nickname, password, firstName, lastName, birthday);
    }

    @Override
    public boolean update(String nickname, String password,
                       String firstName, String lastName, String birthday) {
        log.debug("update " + nickname);
        return clientManager.update(nickname, password, firstName, lastName, birthday);
    }

    @Override
    public boolean delete(String nickname) {
        log.debug("delete " + nickname);
        return clientManager.delete(nickname);
    }

    @Override
    public List<User> select() {
        log.debug("select");
        return clientManager.select();
    }
}
