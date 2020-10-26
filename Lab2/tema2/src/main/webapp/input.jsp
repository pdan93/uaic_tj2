<%-- 
    Document   : input
    Created on : Oct 26, 2020, 11:22:13 AM
    Author     : x
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Introduce new languages</h1>
        <form action="" method="post">
            <label>Language</label>
            <%  
String presetlanguage=(String)request.getAttribute("presetlanguage");  
String operationcaptcha=(String)request.getAttribute("operationcaptcha"); 
%>
            <input type="text" name="language" value="<%=presetlanguage %>" />
            <br />
            <label>Word</label>
            <input type="text" name="word" />
            <br />
            <label>Definition</label>
            <input type="text" name="definition" />
            <br />
            <label>What is <%=operationcaptcha %>?</label>
            <input type="text" name="captcha" />
            <br />
            
            <input type="submit" />
        </form>
    </body>
</html>
