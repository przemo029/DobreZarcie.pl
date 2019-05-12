<%-- 
    Document   : Logkonsul
    Created on : 2019-02-10, 12:08:15
    Author     : mateu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Logowanie</title>
    </head>
    <body>
    <h2>Podaj dane do logowania</h2>
        <form action="Logkonsul">
            <p>Login: <input type=text size=20 name=login></p>
            <p>Haslo: <input type=text size=20 name=haslo></p>
            
            <input type="submit" name= "action" value="ZALOGUJ" />
            <input type="submit" name= "action" value="POWROT" />
        </form>
    </body>
</html>