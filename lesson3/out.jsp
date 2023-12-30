<html> 
<head>
<title>Output page: Fetching the value from session</title>
</head>
<body> 
<% 
String name=(String)session.getAttribute("sessname"); 
out.print("Hello User: You have entered the name: "+name); 
%> 
</body> 
</html>
