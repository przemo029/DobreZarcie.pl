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
import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
 * @author DobreZarcieTeam
 */
public class DodawanieRestauracji extends HttpServlet {

    int i = 0;
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
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession(true);
        Connection connection = null;
        PrintWriter out = response.getWriter();
        boolean error = false;
        boolean nr_konta_error = false;
        boolean nip_error = false;
        boolean empty_field = false;
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
        i = restaurator.getID_res();
        
        try {
            /* TODO output your page here. You may use following sample code. */
            //Class.forName(this.getInitParameter("sterownik"));
            //connection = DriverManager.getConnection(this.getInitParameter("url"), this.getInitParameter("uzytkownik"), this.getInitParameter("haslo"));

            String action = request.getParameter("action");

            if (action != null) {
                switch (action) {
                    case "DODAJ": {
                        //sprawdzenie czy odczytano
                        String rest = request.getParameter("rest"); //odbior parametru przekazaneo przez pole tekstowe

                        String adres = request.getParameter("adres"); //odbior parametru przekazaneo przez pole tekstowe

                        String tel = request.getParameter("tel"); //odbior parametru przekazaneo przez pole tekstowe

                        String kat = request.getParameter("kat"); //odbior parametru przekazaneo przez pole tekstowe

                        String nip = request.getParameter("nip"); //odbior parametru przekazaneo przez pole tekstowe
                        if (nip.length() != 10) {
                            nip_error = true;
                            error = true;
                        }

                        String nr_konta = request.getParameter("nr_konta"); //odbior parametru przekazaneo przez pole tekstowe
                        if (nr_konta.length() != 26) {
                            nr_konta_error = true;
                            error = true;
                        }
                        if(!error)
                        {
                            Restauracja restauracja = new Restauracja(rest,adres,tel,kat,nip,nr_konta,restaurator);
                            databaseManagement.dodajEncje(new Object[]{restauracja}, utx);
                        }

                       
                        /*try {
                            Statement stat = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                            ResultSet rs = stat.executeQuery("SELECT * FROM Restauracje");
                            int rows = 1;
                            //zliczenie liczby rekordow, w celu poprawnego indeksowania
                            if (rs.last()) {
                                rows = rs.getRow();
                                rows++;
                            }
                            //wprowadzenie danych do tabeli
                            if (rest == "" || adres == "" || tel == "" || kat == "" || nip == "" || nip == "" || nr_konta == "") {
                                empty_field = true;
                            } else if (!error) {
                                Statement statement = connection.createStatement();
                                statement.executeUpdate("INSERT INTO restauracje VALUES ("
                                        + rows + ","
                                        + 1 + ",'"
                                        + adres + "','"
                                        + kat + "','"
                                        + nip + "','"
                                        + nr_konta + "','"
                                        + tel + "',"
                                        + 1.0 + ",'"
                                        + rest + "'"
                                        + ")");
                            }
                        } catch (SQLException e) {
                            out.println("SQL exception: " + e.getMessage());
                        }
*/
                        break;
                    }
                    case "POWROT": {
                        request.getRequestDispatcher("/EkranRest.jsp").forward(request, response);
                        break;
                    }
                    default: {
                        break;
                    }
                }
            }

            request.getSession().setAttribute("emp_field", empty_field);
            request.getSession().setAttribute("nip_err", nip_error);
            request.getSession().setAttribute("nr_konta_err", nr_konta_error);
            String url = "/DodawanieRestauracji.jsp";
            // utworzenie obiektu RequestDispatcher
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
            // wysłanie obiektu request do naszego DodawanieRestauracji.jsp
            dispatcher.forward(request, response);
            request.getRequestDispatcher("/DodawanieRestauracji.jsp").forward(request, response);

        } catch (IOException | ServletException | HeuristicMixedException | HeuristicRollbackException | NotSupportedException | RollbackException | SystemException e) {
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
