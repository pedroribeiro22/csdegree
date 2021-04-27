package pt.uminho.tutorial4_project;

import pt.uminho.tutorial4_project.beans.GameBean;
import pt.uminho.tutorial4_project.beans.GameBeanImpl;

import java.io.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;
    private GameBean bean;

    public void init() {
        try {
            InitialContext initialContext = new InitialContext();
            GameBean bean = (GameBean) initialContext.lookup("java:GameBean");
        } catch (NamingException e) {
            e.printStackTrace();
        }

        message = bean.sayHello();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}