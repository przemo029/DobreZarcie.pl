<%-- 
    Document   : DodawanieRestauracji
    Created on : 2019-02-07, 02:12:41
    Author     : Przemek
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Servlet DodawanieRestauracji</title>
    </head>
    <body>

        <form action="DodawanieRestauracji">
            <h2>Dodawanie restauracji</h2>
            <% Boolean emp_field = (Boolean) request.getSession().getAttribute("emp_field");%>
            <c:if test="${emp_field}">
                &emsp;Nie wypełniłeś wszystkich pól!<br/>
            </c:if>
            <p>Nazwa restauracji: <input type=text size=20 name=rest></p>
            <p>Adres:&emsp;&emsp;&emsp;&emsp;&nbsp;&nbsp;&nbsp; <input type=text size=20 name=adres></p>
            <p>Numer telefonu: &nbsp;&nbsp;&nbsp;<input type=text size=20 name=tel></p>
            <p>Kategoria: &emsp;&emsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type=text size=20 name=kat></p>
            <p>NIP: &emsp;&emsp;&emsp;&nbsp;&nbsp;&nbsp;&emsp;&nbsp;&nbsp;&nbsp;<input type=text size=20 name=nip></p>
                <% Boolean nip_err = (Boolean) request.getSession().getAttribute("nip_err");%>
                <c:if test="${nip_err}">
                &emsp;-pole NIP musi zawierac 10 cyfr!<br/>
            </c:if>
            <p>Numer konta: &emsp;&nbsp;&nbsp;&nbsp;<input type=text size=20 name=nr_konta></p>
                <% Boolean nr_konta_err = (Boolean) request.getSession().getAttribute("nr_konta_err");%>
                <c:if test="${nr_konta_err}">
                &emsp;-pole Numer konta musi zawierac 26 cyfr!<br/>
            </c:if>
            <input type="hidden" name="login" value="<%= request.getParameter("login") %>" />
            <input type="hidden" name="haslo" value="<%= request.getParameter("haslo") %>" />
            <input type="submit" name= "action" value="DODAJ" />
            <input type="submit" name= "action" value="POWROT" />
        </form>

    </body>
</html>
