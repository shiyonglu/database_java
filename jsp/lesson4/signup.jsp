<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Signup Form</title>
</head>
<body>

<h2>Signup Form</h2>

<%
    // Check if the form is submitted
    if (request.getMethod().equalsIgnoreCase("post")) {
        // Retrieve user input from the form
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        // Store individual pieces of user information in the session
        session.setAttribute("username", username);
        session.setAttribute("password", password);
        session.setAttribute("email", email);
%>
        <p>Thank you for signing up, <%= username %>!</p>
        <p>Email: <%= email %></p>
<%
    } else {
        // Display the form
%>
        <form method="post" action="signup.jsp">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required><br>

            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required><br>

            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required><br>

            <input type="submit" value="Signup">
        </form>
<%
    }
%>

<a href="signin.html">Sign in</a>

</body>
</html>
