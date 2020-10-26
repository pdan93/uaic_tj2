<%-- 
    Document   : error.jsp
    Created on : Oct 26, 2020, 11:54:04 AM
    Author     : x
--%>

<%@page import="fish.payara.tema2.ErrorBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Error Page</title>
    </head>
    <body>
        <h1>There has been an error</h1>
        <p>
            <%  
ErrorBean errorbean=(ErrorBean)request.getAttribute("errorbean");  
out.print("Welcome, "+errorbean.getErrorMessage());  
%>
        </p>
    </body>
</html>
