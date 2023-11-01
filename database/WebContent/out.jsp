<html> 
<head>
<title>Output page: Fetching the value from session</title>
</head>
<body> 
<% 
String name=(String)session.getAttribute("sessname"); <!-- actually this line can be in any jsp file to retrieve the value of sessname -->
out.print("Hello User: You have entered the name: "+name); 
%> 
</body> 
</html>
