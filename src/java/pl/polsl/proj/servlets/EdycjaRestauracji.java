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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import pl.polsl.proj.model.Restauracja;
import pl.polsl.proj.model.Restaurator;
import pl.polsl.proj.service.DatabaseManagement;

/**
 *
 * @author ASUS
 */
public class EdycjaRestauracji extends HttpServlet {

    int ID = 0;

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
            throws ServletException, IOException, NotSupportedException, SystemException, RollbackException, HeuristicMixedException, HeuristicRollbackException {
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
        
        Restaurator restaurator = databaseManagement.pobierzRestauratora(login, haslo);
        List<Restauracja> restauracje = databaseManagement.pobierzRestauracje(restaurator);
        int liczbaRestauracji = 0;
        if(restauracje==null)
        {
        } else {
            for (Restauracja rest : restauracje) {
                liczbaRestauracji++;
            }
        }
        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "NASTEPNA":
                    if (ID < liczbaRestauracji) {
                        ID++;
                    }
                    break;
                case "POPRZEDNIA":
                    if (ID > 0) {
                        ID--;
                    }
                    break;
                case "ZMIEN":
                    if(restauracje!=null)
                    {
                        String adres = request.getParameter("adres");
                        if (adres.isEmpty()) adres=restauracje.get(ID).getAdres();
                        String nip = request.getParameter("nip");
                        if (nip.isEmpty()) nip=restauracje.get(ID).getNip();
                        String konto = request.getParameter("konto");
                        if (konto.isEmpty()) konto=restauracje.get(ID).getNr_konta();
                        String tel = request.getParameter("tel");
                        if (tel.isEmpty()) tel=restauracje.get(ID).getTel();
                        String kat = request.getParameter("kat");
                        if (kat.isEmpty()) kat=restauracje.get(ID).getKat();
                        String nazwa = request.getParameter("nazwa");
                        if (nazwa.isEmpty()) nazwa=restauracje.get(ID).getNazwa();
                        databaseManagement.aktualizujRestauracje(restauracje.get(ID),adres, nip, konto, tel, kat, nazwa, emf, utx);
                    } else {
                    }
                    break;
                case "WROC":
                    request.getRequestDispatcher("/EkranRest.jsp").forward(request, response);
                    break;
                default:
                    break;
            }
        }
        request.setAttribute("liczba_rest", liczbaRestauracji);
        String url = "/EdycjaRestauracji.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);

        if(restauracje.size()!=0)
        {
        Restauracja restauracja = restauracje.get(ID);
        request.setAttribute("result", restauracja);
        }
        dispatcher.forward(request, response);
        request.getRequestDispatcher("/EdycjaRestauracji.jsp").forward(request, response);

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
        try {
            processRequest(request, response);
        } catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException ex) {
            Logger.getLogger(EdycjaRestauracji.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException ex) {
            Logger.getLogger(EdycjaRestauracji.class.getName()).log(Level.SEVERE, null, ex);
        }
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
