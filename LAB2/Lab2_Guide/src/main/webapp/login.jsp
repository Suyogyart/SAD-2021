<%@ page language="java" contentType="text/html; ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1" name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <title>Login</title>
</head>
<body>
    <div class="container">
        <div class="row pt-5">
            <div class="col-6 offset-3">
                <h2>Login</h2><br>

                ${SPRING_SECURITY_LAST_EXCEPTION.message}
                <br/>
                <form action="/login" method="POST">

                    <div class="form-group">
                        <input class="form-control" type="text" name="username" placeholder="Username">
                    </div>

                    <div class="form-group">
                        <input class="form-control" type="password" name="password" placeholder="Password">
                    </div>

                    <input class="btn btn-primary btn-block" type="submit" name="submit" value="Login">

                </form>
                <div class="pt-4">
                    Don't have account yet? <a href="/register">Register</a>
                </div>
            </div>
        </div>
    </div>
</body>
</html>