/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.proj.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Vector;
import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.servlet.RequestDispatcher;
import javax.transaction.UserTransaction;
import pl.polsl.proj.model.Restauracja;
import pl.polsl.proj.model.Restaurator;
import pl.polsl.proj.service.DatabaseManagement;

/**
 *
 * @author Mateusz Godziek
 * @version 1.0
 */
public class PrzegladanieRestauracji extends HttpServlet {

   
     /**
     * EntityManagerFactory injection field, used for creating EntityManager
     * objects.
     */
    @PersistenceUnit
    private EntityManagerFactory emf;
    /**
     * UserTransaction injection field, used for managing transactions when
     * interacting with database.
     */
    @Resource
    private UserTransaction utx;

    private DatabaseManagement databaseManagement;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession(true);
        Connection connection = null;
        PrintWriter out = response.getWriter();
        
        databaseManagement = (DatabaseManagement) request.getSession().getAttribute("databaseManagement");
        if (databaseManagement == null) {
            databaseManagement = new DatabaseManagement();
            request.getSession().setAttribute("databaseManagement", databaseManagement);
        }
        String login = request.getParameter("login");
        String haslo = request.getParameter("haslo");
        request.setAttribute("login", login);
        request.setAttribute("haslo", haslo);
        
        Restaurator restaurator = databaseManagement.pobierzRestauratora(login, haslo);
       
        List<Restauracja> restauracje = databaseManagement.pobierzRestauracje(restaurator);
        
             
        try {                 
            String action = request.getParameter("action");

            if (action != null && action.equals("WROC")) {
                request.getRequestDispatcher("/EkranRest.jsp").forward(request, response);
            }

            String url = "/PrzegladanieRestauracji.jsp";
          
                request.setAttribute("result", restauracje);
                // utworzenie obiektu RequestDispatcher
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
                // wysłanie obiektu request do naszego DodawanieRestauracji.jsp
                dispatcher.forward(request, response);
                request.getRequestDispatcher("/PrzegladanieRestauracji.jsp").forward(request, response);
            
            

        } catch (IOException e) {
            out.println(e.getMessage());
        }
        
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
    public void doGet(HttpServletRequest request, HttpServletResponse response)
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
