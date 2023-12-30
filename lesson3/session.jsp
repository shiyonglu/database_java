<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html> 
<head>
<title>Passing the input value to a session variable</title>
</head>
<body> 

<%
String uname=request.getParameter("inputname"); 
out.print("Welcome "+ uname);
session.setAttribute("sessname",uname);         
%> 

<a href="output.jsp">Check Output Page Here </a>
</body> 
</html>
