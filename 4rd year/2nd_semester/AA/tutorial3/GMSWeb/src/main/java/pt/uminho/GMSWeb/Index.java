package pt.uminho.GMSWeb;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "index", value = "/Index")
public class Index extends HttpServlet {
    private String message;

    public void init() {
        message = "Index";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // response.setContentType("text/html");

        // Hello
        //  PrintWriter out = response.getWriter();
        // out.println("<html><body>");
        // out.println("<h1>" + message + "</h1>");
        // out.println("</body></html>");
        Object obj = "hello";
        request.setAttribute("obj", obj);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    public void destroy() {
    }
}