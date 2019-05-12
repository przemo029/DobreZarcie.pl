/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.proj.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Map;
import javax.annotation.Resource;
import javax.persistence.Cache;
import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.PersistenceUnitUtil;
import javax.persistence.Query;
import javax.persistence.SynchronizationType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.metamodel.Metamodel;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.UserTransaction;
import pl.polsl.proj.model.Restaurator;
import pl.polsl.proj.service.DatabaseManagement;

/**
 *
 * @author mateu
 */
public class Logres extends HttpServlet {

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

        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "POWROT":
                    request.getRequestDispatcher("/index.jsp").forward(request, response);
                    break;
                case "ZALOGUJ": {
                    String login = request.getParameter("login");
                    String haslo = request.getParameter("haslo");
                 
                    Restaurator rest = databaseManagement.pobierzRestauratora(login, haslo);
                    if (rest == null) {
                        request.getRequestDispatcher("/index.jsp").forward(request, response);
                    } else {
                        request.setAttribute("login", login);
                        request.setAttribute("haslo", haslo);
                        request.getRequestDispatcher("/EkranRest.jsp").forward(request, response);
                    }
                }
                default:
                    break;
            }
        }

        String url = "/Logres.jsp";
        // utworzenie obiektu RequestDispatcher
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        // wysłanie obiektu request do naszego DodawanieRestauracji.jsp

        dispatcher.forward(request, response);
        request.getRequestDispatcher("/Logres.jsp").forward(request, response);

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
