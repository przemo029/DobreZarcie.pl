<%-- 
    Document   : Zamowienie2
    Created on : 2019-02-10, 20:22:03
    Author     : Adam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Przyjecie zamowienia</title>
    </head>
    <body>

        <%-- POWINNO dzialac gdy bedzie dobre polaczenie z baza xd
        <% 
            Boolean pasuje = (Boolean) session.getAttribute("pasuje");
            if (pasuje)
            {  %>
                Oczekiwanie na aktualizacje...
        <%    } else
            { %>
                Wybrana restauracja nie istnieje :( 
         <%   }
        %>
        --%>        

        Oczekiwanie na polaczenie z baza...

        <form action="Zamowienie2">
            <input type="submit" name= "action" value="POWROT" />
        </form>  



    </body>
</html>
