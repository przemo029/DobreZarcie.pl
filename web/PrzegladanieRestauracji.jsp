<%-- 
    Document   : PrzegladanieRestauracji
    Created on : 2019-02-08, 00:25:54
    Author     : Przemek
--%>


<%@page import="java.util.List"%>
<%@page import="pl.polsl.proj.model.Restauracja"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Servlet PrzeglądRestauracji</title>
    </head>
    <body>
        <h2>Przegląd restauracji</h2>
        
       <% List<Restauracja> rs = (List<Restauracja>) request.getAttribute("result");%>
      
       
        <table style='border-collapse:separate;border-spacing:15px'><tr><th>Adres</th><th>Kategoria</th><th>NIP</th><th>NR. Konta</th><th>Nr. Tel</th><th>Nazwa</th></tr>
                    <% for (Restauracja rs1 : rs) { %>
            <tr><td>  
                    <%= rs1.getAdres()%> </td><td>
                    <%= rs1.getKat()%></td><td>
                    <%= rs1.getNip()%></td><td>
                    <%= rs1.getNr_konta()%></td><td>
                    <%= rs1.getTel()%></td><td>
                    <%= rs1.getNazwa()%></td></tr>
             <%}%>
        </table>

        <form action="PrzegladanieRestauracji">
            <input type="hidden" name="login" value="<%= request.getParameter("login") %>" />
            <input type="hidden" name="haslo" value="<%= request.getParameter("haslo") %>" />
            <input type="submit" name= "action" value="WROC" />
        </form>
    </body>
</html>