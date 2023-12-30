<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign In Form</title>
</head>
<body>

<h2>Sign In Form</h2>

<%
    // Check if the form is submitted
    if (request.getMethod().equalsIgnoreCase("post")) {
        // Retrieve user input from the form
        String enteredUsername = request.getParameter("username");
        String enteredPassword = request.getParameter("password");

        // Retrieve user information from the session
        String storedUsername = (String) session.getAttribute("username");
        String storedPassword = (String) session.getAttribute("password");

        // Check if the provided username and password match the stored information
        if (enteredUsername.equals(storedUsername) && enteredPassword.equals(storedPassword)) {
%>
            <p>Successful sign-in for <%= enteredUsername %>!</p>
<%
        } else {
%>
            <p>Failed sign-in. Invalid username or password.</p>
            <!-- Display the form again for another attempt -->
            <form method="post" action="signin.jsp">
                <label for="username">Username:</label>
                <input type="text" id="username" name="username" required><br>

                <label for="password">Password:</label>
                <input type="password" id="password" name="password" required><br>

                <input type="submit" value="Sign In">
            </form>
<%
        }
    } else {
        // Display the initial sign-in form
%>
        <form method="post" action="signin.jsp">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required><br>

            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required><br>

            <input type="submit" value="Sign In">
        </form>
<%
    }
%>

</body>
</html>
