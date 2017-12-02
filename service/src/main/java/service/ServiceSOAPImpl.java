package service;


import beans.ClientManager;
import beans.impl.ClientManagerImpl;
import entity.User;
import org.apache.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.*;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.jws.WebService;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebListener;
import java.util.LinkedList;
import java.util.List;

@WebService(endpointInterface = "service.ServiceSOAP")
public class ServiceSOAPImpl implements ServiceSOAP{
    private Logger log = Logger.getLogger(ServiceSOAPImpl.class);
    @EJB
    private ClientManager clientManager;

    @Override
    public boolean add(String nickname, String password,
                                String firstName, String lastName, String birthday) {
        log.debug("add " + nickname);
        log.debug(clientManager + "not save");
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
