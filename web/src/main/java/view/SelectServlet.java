package view;


import beans.ClientManager;
import entity.User;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;

@WebServlet("/select")
public class SelectServlet extends HttpServlet {
    private Logger log = Logger.getLogger(SelectServlet.class);
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    @EJB
    private ClientManager clientManager;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("user select DB");
        PrintWriter out = resp.getWriter();
        List<User> list = clientManager.select();
        out.print("<button type=\"submit\" name=\"action\" onclick=\"location.href='http://localhost:8080/pages/index.jsp'\">acton</button><br>");
        for (User iter: list) {
            out.print("<tr><th>" + iter.getId() +"</th><th>" + iter.getNickname() +
                    "</th><th>" + iter.getPassword() + "</th><th>" + iter.getFirstName()
                    +"</th><th>" + iter.getLastName() + "</th><th>" + dateFormat.format(iter.getBirthday()) + "</th></tr>");
        }
        out.close();
    }
}
