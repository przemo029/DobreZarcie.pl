<%-- 
    Document   : index
    Created on : 2019-02-07, 02:08:10
    Author     : Przemek
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>DOBRE ZARCIE.PL</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <h2>Witaj w ekranie logowania</h2>
        <form action="EkranStartowy">
            <input type="submit" name= "action" value="Zaloguj - jestem restauratorem" />
            <input type="submit" name= "action" value="Zaloguj - jestem uzytkownikiem" />
            <input type="submit" name= "action" value="Zaloguj - jestem konsultantem" />
            <input type="submit" name= "action" value="Zamow" />
            <h3>Jesteś tu pierwszy raz?</h3> 
        <input type="submit" name= "action" value="Zarejestruj - jestem restauratorem" />
        <input type="submit" name= "action" value="Zarejestruj - jestem uzytkownikiem" />
        </form>
      
    </body>
</html>
