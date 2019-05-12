<%-- 
    Document   : EkranRest
    Created on : 2019-02-21, 03:24:38
    Author     : Przemek
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Okno restauratora</title>
    </head>
    <body>
        <h2>Witaj Restauratorze!</h2>
        
        <form action="EkranRest">
            <input type="hidden" name="login" value="<%= request.getParameter("login") %>" />
            <input type="hidden" name="haslo" value="<%= request.getParameter("haslo") %>" />
            <input type="submit" name= "action" value="Dodaj restauracje" />
            <input type="submit" name= "action" value="Edytuj restauracje" />
            <input type="submit" name= "action" value="Przegladaj restauracje" />
            <input type="submit" name= "action" value="Wyloguj" />
        </form>
    </body>
</html>
