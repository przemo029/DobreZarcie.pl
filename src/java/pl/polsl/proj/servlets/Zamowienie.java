/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.proj.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author mateu
 */
public class Zamowienie extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession(true);
        Connection connection = null;
        PrintWriter out = response.getWriter();
        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "POWROT":
                    request.getRequestDispatcher("/index.jsp").forward(request, response);
                    break;
                case "WYBIERZ":
                    Boolean pasuje = false;
                    try {
                        Class.forName(this.getInitParameter("sterownik"));
                        connection = DriverManager.getConnection(this.getInitParameter("url"), this.getInitParameter("uzytkownik"), this.getInitParameter("haslo"));
                        Statement statement = connection.createStatement();
                        ResultSet rs = statement.executeQuery("SELECT * FROM RESTAURACJE");
                        request.setAttribute("result", rs);
                        //sprawdzanie czy istnieje podana restauracja
                        int wybor = Integer.parseInt(request.getParameter("wybor"));

                        do {

                            if (wybor == (rs.getInt(1))) {
                                pasuje = true;
                            }
                        } while (rs.next());

                        session.setAttribute("pasuje", pasuje);
                        rs.close();
                    } catch (ClassNotFoundException | SQLException | NullPointerException e) {
                        out.println(e.getMessage());
                    }
                    request.getRequestDispatcher("/Zamowienie2.jsp").forward(request, response);
                    break;
                default:
                    break;
            }
        }

        String url = "/Zamowienie.jsp";
        // utworzenie obiektu RequestDispatcher
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        // wysłanie obiektu request do naszego DodawanieRestauracji.jsp
        dispatcher.forward(request, response);
        request.getRequestDispatcher(url).forward(request, response);

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
