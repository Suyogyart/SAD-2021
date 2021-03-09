<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add new Address</title>
</head>
<body>

<h2>Add Address</h2>
<br>

<form action="/user/addAddress" method="post">

    <label for="city">City: </label>
    <input name="city" type="text" id="city" required>
    <br><br>

    <label for="street">Street: </label>
    <input name="street" type="text" id="street" required>
    <br><br>

    <label for="houseNo">House No: </label>
    <input name="houseNo" type="text" id="houseNo" required>
    <br><br>

    <label for="zipcode">Zipcode: </label>
    <input name="zipcode" type="text" id="zipcode" required>
    <br><br>

    <input type="submit" value="Add">

    <br><br>
</form>

</body>
</html>