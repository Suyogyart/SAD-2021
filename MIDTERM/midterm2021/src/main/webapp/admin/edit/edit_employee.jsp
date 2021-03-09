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

<form action="/admin/edit" method="post">

    <label for="id">Name: </label>
    <input name="id" type="text" id="id" value="${data.employee.id}" readonly>
    <br><br>

    <label for="name">Name: </label>
    <input name="name" type="text" id="name" value="${data.employee.name}" required>
    <br><br>

    <label for="level">Level: </label>
    <select name="level" id="level">
        <c:forEach items="${data.levels}" var="level">
            <c:choose>
                <c:when test="${data.employee.level == level}">
                    <option value="${level}" selected="selected">${level}</option>
                </c:when>
                <c:otherwise>
                    <option>${level}</option>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </select>
    <br><br>

    <label for="baseSalary">Base Salary: </label>
    <input name="baseSalary_" type="text" id="baseSalary" value="${data.employee.baseSalary_}" required>
    <br><br>

    <input type="submit" value="Update">

    <br><br>
</form>

</body>
</html>