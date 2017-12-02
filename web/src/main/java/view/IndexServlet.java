package view;

import beans.ClientManager;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Enumeration;

@WebServlet(urlPatterns = "/index")
public class IndexServlet extends HttpServlet {
    private Logger log = Logger.getLogger(IndexServlet.class);
    @EJB
    private ClientManager clientManager;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("---------------");
        log.debug("user do request");

        String nickname = "" + req.getParameter("nickname");
        String password = "" + req.getParameter("password");
        String firstName = "" +  req.getParameter("firstName");
        String lastName = "" + req.getParameter("lastName");
        String birthday = "" + req.getParameter("birthday");

        boolean completed = true;
        Enumeration flds = req.getParameterNames();
        while (flds.hasMoreElements()) {
            String str = (String) flds.nextElement();
            if (str.equals("add")) completed = clientManager.add(nickname, password, firstName, lastName, birthday);
            else if (str.equals("delete")) completed = clientManager.delete(nickname);
            else if (str.equals("update")) completed = clientManager.update(nickname, password, firstName, lastName, birthday);
            else if (str.equals("select")) {
                log.debug("servlet redirect request in select");
                req.getRequestDispatcher("pages/select.jsp").forward(req, resp);
            }
        }
        if (completed) req.setAttribute("completed", "Operation completed");
        else req.setAttribute("completed", "Operation not completed");

        req.getRequestDispatcher("pages/index.jsp").forward(req, resp);
    }
}
