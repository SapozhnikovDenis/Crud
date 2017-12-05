package client;

import service.ServiceSOAP;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

public class Client {
    public static void main(String[] args) throws MalformedURLException {
        URL url = new URL("http://localhost:8080/web/service?wsdl");
        QName qname = new QName("http://service/", "ServiceSOAPImplService");
        Service service = Service.create(url, qname);
        ServiceSOAP serviceSOAP = service.getPort(ServiceSOAP.class);
        serviceSOAP.add("nickname", "pass", "name", "name" , "10.10.2010");
    }
}
