<html> 
<head>
<title>Passing the input value to a session variable</title>
</head>
<body> 
<% 
String uname=request.getParameter("inputname"); <!-- retrieve the value of the inputname parameter from request -->
out.print("Welcome "+ uname);
session.setAttribute("sessname",uname);         <!-- store the value of the inputname parameter into session attribute sessname, which can be retrieved by other jsp pages -->
%> 
<a href="output.jsp">Check Output Page Here </a>
</body> 
</html>
