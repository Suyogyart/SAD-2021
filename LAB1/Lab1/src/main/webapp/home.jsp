<!DOCTYPE html>
<html lang="en">

<head>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Employee Home</title>
        <link href=".\css\bootstrap.min.css" rel="stylesheet">
</head>

<body>
    <div class="container" align="center">
        <h2>List of Employees</h2>
        <div>
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Gender</th>
                        <th>Address</th>
                        <th>Position Level</th>
                        <th>Salary</th>
                        <th>Value</th>
                        <th>Net Value</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${employeeList}" var="emp">
                        <tr id="dataRow" class="table-light">
                            <td scope="row">
                                <c:out value="${emp.eid}" />
                            </td>
                            <td>
                                <c:out value="${emp.name}" />
                            </td>
                            <td>
                                <c:out value="${emp.gender}" />
                            </td>
                            <td>
                                <c:out value="${emp.address}" />
                            </td>
                            <td align="center">
                                <c:out value="${emp.positionLevel}" />
                            </td>
                            <td>
                                <c:out value="${emp.salary}" />
                            </td>
                            <td>
                                <c:out value="${emp.value}" />
                            </td>
                            <td class="netvalue">
                                <c:out value="${emp.netValue}" />
                            </td>
                            <td>
                                <a href = "edit/${emp.eid}" class="btn btn-primary">Edit</a>  <a href = "delete/${emp.eid}" class="btn btn-danger">Delete</a>

                                <!--
                                <form action="delete/${emp.eid}" method="delete">
                                    <button type="submit" class="btn btn-danger">Delete</button>
                                </form> -->
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <div>
            <form action="add" method="GET">
                <button type="submit" class="btn btn-success">Add New Employee</button>
            </form>
        </div>
    </div>
</body>

</html>