/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.proj.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import pl.polsl.proj.model.Restauracja;
import pl.polsl.proj.model.Restaurator;
import pl.polsl.proj.service.DatabaseManagement;

/**
 *
 * @author Przemek
 */
public class EkranRest extends HttpServlet {

    private DatabaseManagement databaseManagement;
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
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(true);
        databaseManagement = (DatabaseManagement) request.getSession().getAttribute("databaseManagement");
        if (databaseManagement == null) {
            databaseManagement = new DatabaseManagement();
            request.getSession().setAttribute("databaseManagement", databaseManagement);
        }
        String action = request.getParameter("action");
        String login = request.getParameter("login");
        String haslo = request.getParameter("haslo");
        request.setAttribute("login", login);
        request.setAttribute("haslo", haslo);

        if ("Dodaj restauracje".equals(action)) {
            
            request.getRequestDispatcher("/DodawanieRestauracji.jsp").forward(request, response);

        } else if ("Edytuj restauracje".equals(action)) 
        {
            Restaurator restaurator = databaseManagement.pobierzRestauratora(login, haslo);
            List<Restauracja> restauracje = databaseManagement.pobierzRestauracje(restaurator);
            if (!restauracje.isEmpty()){
            request.getRequestDispatcher("/EdycjaRestauracji").forward(request, response);
            }else{
                request.getRequestDispatcher("/EkranRest.jsp").forward(request, response);
            }

        } else if ("Przegladaj restauracje".equals(action)) 
        {
            request.getRequestDispatcher("/PrzegladanieRestauracji").forward(request, response);
            
        } else if ("Wyloguj".equals(action)) {
            
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }

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
    }
}
