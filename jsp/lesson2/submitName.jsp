<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Personalized Greeting</title>
</head>
<body>
    <center>
        <%
            // Retrieve the name from the request
            String name = request.getParameter("name");

            // Check if the name is provided
            if (name != null && !name.isEmpty()) {
                // Display a personalized greeting
        %>
                <h2>Hello, <%= name %>! Welcome to our website.</h2>
        <%
            }
        %>
    </center>
</body>
</html>
