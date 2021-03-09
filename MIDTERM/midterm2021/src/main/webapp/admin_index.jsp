<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous"> -->
    <title>Admin Home</title>
</head>
<body>

<h3>Admin</h3>

<a href="/admin/register">Register new Employee</a>

<br><br>

<table class="table" border="2">
    <thead>
    <tr>
        <th scope="col">User ID</th>
        <th scope="col">Employee Name</th>
        <th scope="col">Level</th>
        <th scope="col">Base Salary</th>
        <th scope="col" colspan="2">Actions</th>
    </tr>
    </thead>
    <tbody>

        <c:forEach items="${users}" var="user">
            <tr>
                <th scope="row">${user.id}</th>
                <td>${user.emp.name}</td>
                <td>${user.emp.level}</td>
                <td>${user.emp.baseSalary_}</td>
                <td>
                    <form action="/admin/edit/${user.id}" method="get">
                        <input type="submit" value="Edit">
                    </form>
                </td>
                <td>
                    <form action="/admin/delete/${user.id}" method="post">
                        <input type="submit" value="Delete">
                    </form>
                </td>
            </tr>
        </c:forEach>

    </tbody>
</table>

<br><br>
<a href="/admin/calculateSalary">Calculate Salary Generated</a>

<br><br>
<a href="/logout">Logout</a>

</body>
</html>