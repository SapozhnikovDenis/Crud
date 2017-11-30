package client;

import service.ServiceSOAP;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.URL;

public class Client  {
    public static void main(String[] args) throws Exception {
        URL url = new URL("http://localhost:8080/service?wsdl");
        QName qname = new QName("http://service/", "ServiceSOAPImplService");

        Service service = Service.create(url, qname);

        ServiceSOAP serviceSOAP = service.getPort(ServiceSOAP.class);

        System.out.println(serviceSOAP.add("keklolkek", "keklolkek", "keklolkek", "keklolkek" , "10.10.2010"));
    }
}
