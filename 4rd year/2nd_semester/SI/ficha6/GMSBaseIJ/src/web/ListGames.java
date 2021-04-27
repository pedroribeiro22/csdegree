/*
 * ListGames
 * ruicouto in 10/abr/2017
 */
package web;

import business.GMSFacade;
import business.Game;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ruicouto
 */
@WebServlet(name = "ListGames", urlPatterns = {"/ListGames"})
public class ListGames extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        int pagination_page = 1;
        if(request.getParameterMap().containsKey("page")) {
            pagination_page = Integer.parseInt(request.getParameter("page"));
        }
        String username = "Carlos";
        String page = "all_games";
        System.out.println("Testing...");
        List<Game> list = GMSFacade.listGames();
        System.out.println(list.toString());
        int pag_size = 5;
        int max_pages = list.size() / pag_size;
        if(pagination_page > max_pages) pagination_page = max_pages;
        int lower_bound = 5 * (pagination_page - 1);
        int upper_bound = 5 * pagination_page;
        if(upper_bound > list.size()) upper_bound = list.size();
        request.setAttribute("games", list.subList(lower_bound, upper_bound));
        request.setAttribute("username", username);
        request.setAttribute("page", page);
        request.setAttribute("currpage", pagination_page);
        request.setAttribute("max_pages", max_pages);
        getServletConfig().getServletContext()
                .getRequestDispatcher("/WEB-INF/Template.jsp").forward(request,response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
