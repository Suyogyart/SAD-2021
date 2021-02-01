<!DOCTYPE html>
<html lang="en">

<head>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Employee</title>
    <link href="..\css\bootstrap.min.css" rel="stylesheet">
</head>

<body>
    <br>
    <div class="container">
        <div class="row">
            <div class="col-lg-4"></div>
            <div class="col-lg-4">
                <h2>Edit Employee</h2>
                <form action="employee" method="POST">
                    <div class=" form-group">
                        <label for="name">Name</label>
                        <input type="text" name="name" id="name" class="form-control" placeholder="" value="${employee.name}">
                    </div>
                    <div class="form-group">
                        <label for="gender">Gender</label>
                        <select class="form-control" name="gender" id="gender" value="${employee.gender}">
                            <c:forEach items="${genders}" var="gender">

                              <c:choose>
                                 <c:when test="${employee.gender == gender}">
                                     <option value="${gender}" selected="selected">${gender}</option>
                                 </c:when>
                                 <c:otherwise>
                                     <option>${gender}</option>
                                 </c:otherwise>
                              </c:choose>

                            </c:forEach>
                        </select>
                    </div>
                    <div class=" form-group">
                        <label for="address">Address</label>
                        <input type="text" name="address" id="address" class="form-control" placeholder="" value="${employee.address}">
                    </div>
                    <div class=" form-group">
                        <label for="salary">Salary</label>
                        <input type="text" name="salary" id="salary" class="form-control" placeholder="" value="${employee.salary}">
                    </div>
                    <div class=" form-group">
                        <label for="value">Value</label>
                        <input type="text" name="value" id="value" class="form-control" placeholder="" value="${employee.value}">
                    </div>
                    <div class="form-group">
                        <label for="positionlevel">Position Level</label>
                        <select class="form-control" name="positionLevel" id="positionlevel">
                            <c:forEach items="${positionLevels}" var="level">

                                <c:choose>
                                    <c:when test="${employee.positionLevel == level}">
                                        <option value="${level}" selected="selected">${level}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option>${level}</option>
                                    </c:otherwise>
                                </c:choose>

                            </c:forEach>
                        </select>
                    </div>
                    <br>
                    <button type="submit" class="btn btn-success btn-block">Update</button>
                </form>
            </div>
            <div class="col-lg-4"></div>
        </div>
    </div>

</body>

</html>