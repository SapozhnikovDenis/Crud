package client;

import service.ServiceSOAP;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.io.IOException;
import java.net.URL;

@WebServlet(urlPatterns = "/client")
public class Client extends HttpServlet {
    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        res.getWriter().print("asa");
        URL url = new URL("http://localhost:8080/service?wsdl");
        QName qname = new QName("http://service/", "ServiceSOAPImplService");
        Service service = Service.create(url, qname);
        ServiceSOAP serviceSOAP = service.getPort(ServiceSOAP.class);
        serviceSOAP.add("nickname", "pass", "name", "name" , "10.10.2010");
    }
}
