<%@ page language="java" contentType="text/html; ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
    <title>Registration</title>
    <meta charset="ISO-8859-1" name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
    <div class="container">
        <div class="row pt-5">
            <div class="col-6 offset-3">
                <h2>Register</h2><br>

                <form:form method="POST" modelAttribute="user">
                    <spring:bind path="username">
                        <div class="form-group">
                            <form:input type="text" path="username" class="form-control" placeholder="Username" autofocus="true" />
                            <form:errors path="username" class="text-danger" />
                        </div>
                    </spring:bind>
                    <spring:bind path="password">
                        <div class="form-group">
                            <form:input type="password" path="password" class="form-control" placeholder="Password" />
                            <form:errors path="password" class="text-danger" />
                        </div>
                    </spring:bind>
                    <spring:bind path="passwordConfirm">
                        <div class="form-group">
                            <form:input type="password" path="passwordConfirm" class="form-control" placeholder="Confirm your password" autofocus="true" />
                            <form:errors path="passwordConfirm" class="text-danger" />
                        </div>
                    </spring:bind>
                    <spring:bind path="email">
                        <div class="form-group">
                            <form:input type="text" path="email" class="form-control" placeholder="Email" autofocus="true" />
                            <form:errors path="email" class="text-danger" />
                        </div>
                    </spring:bind>
                    <spring:bind path="roles">
                        <div class="form-group">
                            Role (this is only for testing!): <br>
                            <form:checkbox path="roles" value="1" />
                            ROLE_ADMIN<br>

                            <form:checkbox path="roles" value="2" />
                            ROLE_USER<br>

                            <form:checkbox path="roles" value="3" />
                            ROLE_PREMIUM_USER<br>
                        </div>
                    </spring:bind>
                    <input class = "btn btn-primary btn-block" type="submit" name="submit" value="Register">
                </form:form>
                <div class="pt-4">
                    Already have account? <a href="/login">Login</a>
                </div>
            </div>
        </div>
    </div>
</body>

</html>