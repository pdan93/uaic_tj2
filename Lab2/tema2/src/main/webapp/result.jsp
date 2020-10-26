<%-- 
    Document   : result
    Created on : Oct 26, 2020, 12:08:41 PM
    Author     : x
--%>

<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.Map"%>
<%@page import="fish.payara.tema2.DictionaryBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%  
DictionaryBean dictionarybean=(DictionaryBean)request.getAttribute("dictionarybean");  
%>
<h1>Here is the words for the language <% out.print(dictionarybean.getLanguage());   %></h1>
    <ul>
        
<%
Map<String, String> entries = dictionarybean.getEntries();

for(Entry<String, String> entry : entries.entrySet()) {
%>

<li><strong><%=entry.getKey()%></strong> =  <%=entry.getValue() %></li>

<%
}
%>
    </ul>
    </body>
</html>
