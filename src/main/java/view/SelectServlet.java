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
import java.util.List;

@WebServlet("/select")
public class SelectServlet extends HttpServlet {
    private Logger log = Logger.getLogger(IndexServlet.class);
    @EJB
    private ClientManager cm;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        out.print("<html>");
        out.print("<head> <title>Select</title> </head>");
        out.print("<center><h1>Users list</h1>");

        List<User> list = cm.select();

        out.print("<table border=\"1\" width=\"50%\" cellpadding=\"5\">");
        out.print("<tr><th>Id</th><th>Nickname</th><th>Password</th><th>First name</th>" +
                "<th>Last name</th><th>Birthday</th></tr>");
        for (User iter: list) {
            out.print("<tr><th>" + iter.getId() +"</th><th>" + iter.getNickname() +
                    "</th><th>" + iter.getPassword() + "</th><th>" + iter.getFirstName()
                    +"</th><th>" + iter.getLastName() + "</th><th>" + iter.getBirthday() + "</th></tr>");
        }
        out.close();
    }
}
