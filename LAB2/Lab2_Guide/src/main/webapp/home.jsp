<%@ page language="java" contentType="text/html; ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<body>
    <h1>User Roles</h1>

    <h3>Your Roles</h3>
    <c:forEach var="role" items="${user.roles}">
        <li><c:out value="${role.name}" /><br></li>
    </c:forEach>
    <a href="/logout">Logout</a>
</body>
</html>