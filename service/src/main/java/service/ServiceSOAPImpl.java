package service;


import org.apache.log4j.Logger;

import javax.ejb.*;
import javax.inject.Inject;
import javax.jws.WebService;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.xml.ws.Endpoint;
import java.util.LinkedList;
import java.util.List;

@WebService(endpointInterface = "service.ServiceSOAP")
public class ServiceSOAPImpl implements ServiceSOAP{
    private Logger log = Logger.getLogger(ServiceSOAPImpl.class);
    private static Logger loger = Logger.getLogger(ServiceSOAPImpl.class);
//    private ClientManager clientManager;

//    public static void main(String[] args) {
//        loger.debug("1");
//        Endpoint.publish("http://localhost:9999/service", new ServiceSOAPImpl());
//        loger.debug("2");
//    }

    @Override
    public boolean add(String nickname, String password,
                                String firstName, String lastName, String birthday) {
//        try {
//            clientManager = (ClientManager) new InitialContext().lookup("java: ejb/ domain / TestService");
//        } catch (NamingException e) {
//            log.error(e);
//        }
        log.debug("add " + nickname);
//        log.debug(clientManager + "not save");
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

//    @Override
//    public List<User> select() {
//        log.debug("select");
//        return new LinkedList<>();//kek.clientManager.select();
//    }
}
