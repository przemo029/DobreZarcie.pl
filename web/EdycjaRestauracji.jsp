<%-- 
    Document   : EdycjaRestauracji
    Created on : 2019-02-07, 02:21:51
    Author     : Przemek
--%>

<%@page import="pl.polsl.proj.model.Restauracja"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Servlet EdycjaRestauracji</title>
    </head>
    <body>
        <h2>Zmiana danych restauracji</h2>
        <form action="EdycjaRestauracji">
            <% Integer licz_rest = (Integer) request.getAttribute("liczba_rest");%>
            <c:choose>
                <c:when test="${licz_rest ==0}">
                    <div>
                        Brak restauracji
                    </div>
                </c:when>
                <c:otherwise>
                    <% Restauracja restauracja = (Restauracja) request.getAttribute("result"); %>
                    NAZWA:  <%= restauracja.getNazwa() %>
                    &emsp;<input type=text size=30 name=nazwa>
                    </div>
                    <br>
                    <br>
                    <div>
                        ADRES:  <%= restauracja.getAdres() %>
                        &emsp;<input type=text size=30 name=adres>
                    </div>
                    <br>
                    <div>
                        NUMER TELEFONU:<%= restauracja.getTel() %>
                        &emsp;<input type=text size=30 name=tel>
                    </div>
                    <br>
                    <div>
                        KATEGORIA:<%= restauracja.getKat() %>
                        &emsp;<input type=text size=30 name=kat>
                    </div>
                    <br>
                    <div>
                        NIP: <%= restauracja.getNip() %>
                        &emsp;<input type=text size=30 name=nip>
                    </div>
                    <br>
                    <div>
                        NUMER KONTA: <%= restauracja.getNr_konta() %>
                        &emsp;<input type=text size=30 name=konto>
                    </div>
                    <br>
                </c:otherwise>
            </c:choose>
            <input type="hidden" name="login" value="<%= request.getParameter("login") %>" />
            <input type="hidden" name="haslo" value="<%= request.getParameter("haslo") %>" />
            <input type="submit" name= "action"  value="POPRZEDNIA" />
            <input type="submit" name= "action" value="ZMIEN" />
            <input type="submit" name= "action" value="WROC" />
            <input type="submit" name= "action" value="NASTEPNA" />
        </form>
    </body>
