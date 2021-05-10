<%@ page language="java" contentType="text/html; ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1" name="viewport" content="width=device-width, initial-scale=1">
<%--    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">--%>
<%--    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>--%>
<%--    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>--%>
<%--    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>--%>
    <link rel="stylesheet" href="/css/styles.css">
    <title>Product Listing - Click Buy</title>
</head>
<body>
    <h3>Product Listing - Click Buy</h3>

    <table>
        <tr>
            <th>ID</th>
            <th>Product Name</th>
            <th>Price</th>
            <th>Stock</th>
            <th>Order Quantity</th>
            <th>Want to buy?</th>
        </tr>

        <c:forEach items="${products}" var="product">

            <form method="get" action="/buy/${product.id}">
                <tr class="${product.stock == 0 ? 'red-bg' : ''}">
                    <td>${product.id}</td>
                    <td>${product.name}</td>
                    <td>${product.price}</td>
                    <td>${product.stock}</td>
                    <td>
<%--                        <input type="number" name="quantity" min="1" max="${product.stock}">--%>
                            <select name="quantity" id="quantity">
                                <c:forEach items="${product.quantities}" var="quantity">
                                    <option value="${quantity}">${quantity}</option>
                                </c:forEach>
                            </select><br><br>

                    </td>
                    <td>

                            <input type="submit" value="Buy">

                    </td>
                </tr>
            </form>
        </c:forEach>

    </table>

</body>
</html>