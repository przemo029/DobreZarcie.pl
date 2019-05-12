<%-- 
    Document   : Rejuzyt
    Created on : 2019-02-10, 12:09:00
    Author     : mateu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Rejestracja</title>
    </head>
    <body>
    <h2>Podaj dane do rejestracji</h2>
        <form action="Rejuzyt">
            <p>Login: <input type=text size=20 name=login></p>
            <p>Haslo: <input type=text size=20 name=haslo></p>
            <p>Potwierdz haslo: <input type=text size=20 name=haslo1></p>
            
            <input type="submit" name= "action" value="UTWORZ KONTO" />
            <input type="submit" name= "action" value="POWROT" />
        </form>
    </body>
</html>
