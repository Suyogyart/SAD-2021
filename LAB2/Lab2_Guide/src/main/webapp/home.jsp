<%@ page language="java" contentType="text/html; ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1" name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <title>Home Page</title>
</head>
<body>
    <div class="container">
        <div class="row pt-5">
            <div class="col-6 offset-3">
                <h2>Your Roles</h2>

                <c:forEach var="role" items="${user.roles}">
                    <li><c:out value="${role.name}" /><br></li>
                </c:forEach>

                <div class="row pt-4"></div>

                <div class="alert alert-success">
                    You are logged in as <b>${user.username}</b>. <a href="/logout">Logout</a>
                </div>

            </div>
        </div>
    </div>

</body>
</html>