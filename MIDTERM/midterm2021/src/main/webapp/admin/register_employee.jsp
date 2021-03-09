<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register New Employee</title>
</head>
<body>

<h2>Employee Registration Form</h2>
<br>

<form action="/admin/register" method="post">

    <label for="username">Username: </label>
    <input name="username" type="text" id="username" required>
    <br><br>

    <label for="password">Password: </label>
    <input name="password" type="password" id="password" required>
    <br><br>

    <label for="role">Role: </label>
    <select name="role" id="role">
        <c:forEach items="${data.roles}" var="role">
            <option value="${role}">${role}</option>
        </c:forEach>
    </select>
    <br><br>

    <label for="name">Name: </label>
    <input name="name" type="text" id="name" required>
    <br><br>

    <label for="level">Level: </label>
    <select name="level" id="level">
        <c:forEach items="${data.levels}" var="level">
            <option value="${level}">${level}</option>
        </c:forEach>
    </select>
    <br><br>

    <label for="dob">Birthday: </label>
    <input name="birthday_" type="date" id="dob" required>
    <br><br>

    <label for="city">City: </label>
    <input name="city" type="text" id="city" required>
    <br><br>

    <label for="street">Street : </label>
    <input name="street" type="text" id="street" required>
    <br><br>

    <label for="houseNo">House No: </label>
    <input name="houseNo" type="text" id="houseNo" required>
    <br><br>

    <label for="zipcode">Zipcode: </label>
    <input name="zipcode" type="text" id="zipcode" required>
    <br><br>

    <label for="baseSalary">Base Salary: </label>
    <input name="baseSalary_" type="text" id="baseSalary" required>
    <br><br>

    <input type="submit" value="Register">

    <br><br>
</form>

</body>
</html>