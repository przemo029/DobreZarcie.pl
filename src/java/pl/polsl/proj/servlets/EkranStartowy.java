/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.proj.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author DobreZarcieTeam
 */
@WebServlet(name = "EkranStartowy", urlPatterns = {"/EkranStartowy"})
public class EkranStartowy extends HttpServlet{
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
       String action = request.getParameter("action");
       
       if ("Zaloguj - jestem restauratorem".equals(action))
        {
            response.sendRedirect("/DobreZarcie/Logres");
            
        } else if ("Zaloguj - jestem uzytkownikiem".equals(action))
        {
             response.sendRedirect("/DobreZarcie/Loguzyt");
            
        } else if ("Zaloguj - jestem konsultantem".equals(action))
        {
            response.sendRedirect("/DobreZarcie/Logkonsul");
        
        }
        else if ("Zamow".equals(action))
        {
            response.sendRedirect("/DobreZarcie/Zamowienie");
            
        } else if ("Zarejestruj - jestem restauratorem".equals(action))
        {
            response.sendRedirect("/DobreZarcie/Rejres");
        
        } else if ("Zarejestruj - jestem uzytkownikiem".equals(action))
        {
            response.sendRedirect("/DobreZarcie/Rejuzyt");
        
        }
       
       
//        if ("Dodaj restauracje".equals(action))
//        {
//            response.sendRedirect("/DobreZarcie/DodawanieRestauracji");
//            
//        } else if ("Przegladaj restauracje".equals(action))
//        {
//             response.sendRedirect("/DobreZarcie/PrzegladanieRestauracji");
//            
//        } else if ("Zmien restauracje".equals(action))
//        {
//            response.sendRedirect("/DobreZarcie/EdycjaRestauracji");
//        
//        
//        }
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
