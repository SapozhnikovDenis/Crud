package client;

import service.ServiceSOAP;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.URL;
import java.util.List;

public class Client  {
    public static void main(String[] args) throws Exception {
        URL url = new URL("http://localhost:8080/service?wsdl");
        QName qname = new QName("http://service/", "ServiceSOAPImplService");

        Service service = Service.create(url, qname);

        ServiceSOAP serviceSOAP = service.getPort(ServiceSOAP.class);

        serviceSOAP.add("client", "123", "client", "client" , "10.10.2010");
        serviceSOAP.update("client", "456", "clientos", "clientos" , "01.01.1020");
        List list = serviceSOAP.select();
        for (Object o: list) System.out.println(o.toString());
        serviceSOAP.delete("client");
    }
}
