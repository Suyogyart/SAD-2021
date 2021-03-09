<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Show User Address</title>
</head>
<body>

<h3>Username: ${user.username}</h3>

<a href="/user/addAddress">Add new address</a>

<br><br>

<table class="table" border="2">
    <thead>
    <tr>
        <th scope="col">ID</th>
        <th scope="col">City</th>
        <th scope="col">Street</th>
        <th scope="col">House No.</th>
        <th scope="col">Zipcode</th>
        <th scope="col" colspan="2">Actions</th>
    </tr>
    </thead>
    <tbody>

        <c:forEach items="${addresses}" var="address">
            <tr>
                <th scope="row">${address.id}</th>
                <td>${address.city}</td>
                <td>${address.street}</td>
                <td>${address.houseNo}</td>
                <td>${address.zipcode}</td>
                <td>
                    <form action="/user/editAddress/${address.id}" method="get">
                        <input type="submit" value="Edit">
                    </form>
                </td>
                <td>
                    <form action="/user/deleteAddress/${address.id}" method="post">
                        <input type="submit" value="Delete">
                    </form>
                </td>
            </tr>
        </c:forEach>

    </tbody>
</table>

<br><br>
<a href="/logout">Logout</a>

</body>
</html>