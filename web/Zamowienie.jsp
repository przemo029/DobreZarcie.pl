<%-- 
    Document   : Zamowienie
    Created on : 2019-02-10, 12:09:14
    Author     : mateu
--%>

<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Zamowienie</title>
    </head>
    <body>

         <h2>Dostepne restauracje...</h2>
     
          <%--      POWINNO dzialac gdy bedzie dobre polaczenie z baza xd
        <% ResultSet rs = (ResultSet) request.getAttribute("result");%>
        <table style='border-collapse:separate;border-spacing:15px'><tr><th>ID_RESTAURACJI</th><th>Adres</th><th>Kategoria</th><th>Nr. Tel</th></tr>
                    <% while (rs.next()) {%>
            <tr><td>  <%= rs.getInt("ID_RESTAURACJI")%> </td>
                <td>
                    <%= rs.getString("ADRES")%> </td><td>
                    <%= rs.getString("KATEGORIA")%></td><td>
                    <%= rs.getString("NR_TELEFONU")%></td></tr>
             <%}%>
        </table>        
         --%> 


       <form action="Zamowienie">
            <p>Wybierz restauracje <input type=text size=1 name=wybor></p>
            <input type="submit" name= "action" value="WYBIERZ" />
            <input type="submit" name= "action" value="POWROT" />
        </form>  


    </body>
</html>
