<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Hello JSP</title>
</head>
<body>
    <h1>Hello, World! This is a JSP page.</h1>
    <%-- You can include Java code within scriptlet tags <% %> --%>
    <% String name = "John"; %>
    <p>Welcome, <%= name %>!</p>
</body>
</html>
