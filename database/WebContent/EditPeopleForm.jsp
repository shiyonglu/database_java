<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>People Application</title>
</head>
<body>
    <center>
        <h1>People Management</h1>
        <h2>
            <a href="new">Add New People</a>
            &nbsp;&nbsp;&nbsp;
            <a href="list">List All People</a>
             
        </h2>
    </center>
    <div align="center">
            <form action="update" method="post">
        <table border="1" cellpadding="5">
            <caption>
                <h2>Edit an Existing People</h2>
            </caption>
                    <input type="hidden" name="id" value="<c:out value='${people.id}' />" />
            <tr>
                <th>Name: </th>
                <td>
                    <input type="text" name="name" size="45"
                            value="<c:out value='${people.name}' />"
                        />
                </td>
            </tr>
            <tr>
                <th>Address: </th>
                <td>
                    <input type="text" name="address" size="45"
                            value="<c:out value='${people.address}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Status: </th>
                <td>
                    <input type="text" name="status" size="5"
                            value="<c:out value='${people.status}' />"
                    />
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Save" />
                </td>
            </tr>
        </table>
        </form>
    </div>   
</body>
</html>
