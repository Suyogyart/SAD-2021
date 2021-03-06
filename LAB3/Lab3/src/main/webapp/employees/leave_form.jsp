<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="ISO-8859-1" name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <title>Leave Form</title>
</head>
<body>
<div class="container">
    <div class="row">

        <div class="col-4">
            <div class="row" style="padding: 5px">
                <h3>Apply for Leave</h3>
                <br><br>
            </div>
            <div class="row">

                <form class="form-group" action="leave" method="post">
                    <div class = form-group>
                        <label for="leaveType">Select Leave Type</label>
                        <select class="form-control" name="leaveType" id="leaveType">
                            <c:forEach items="${leaveTypes}" var="leaveType">
                                <option value="${leaveType}">${leaveType}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="start">From:</label>
                        <input class="form-control" type="date" name="start" id="start" min="${minDate}" required>
                    </div>

                    <div class="form-group">
                        <label for="end">To:</label>
                        <input class="form-control" type="date" name="end" id="end" required>
                    </div>

                    <div class="form-group">
                        <label for="remarks">Remarks:</label>
                        <input class="form-control" type="text" name="remarks" id="remarks">
                    </div>

                    <br>
                    <button class="btn btn-primary" type="submit">Apply</button>
                </form>
            </div>
        </div>

        <div class="col-8">
            <div class="row" style="padding: 5px">
                <h3>Your Leaves</h3>
                <br><br>
            </div>
            <div class="row">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th scope="col">Leave Type</th>
                        <th scope="col">Start</th>
                        <th scope="col">End</th>
                        <th scope="col">Remarks</th>
                        <th scope="col">Approved?</th>
                        <th scope="col">Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${leaves}" var="leave">
                        <tr>
                            <td>${leave.leaveType}</td>
                            <td>${leave.start}</td>
                            <td>${leave.end}</td>
                            <td>${leave.remarks}</td>
                            <td>${leave.approved}</td>
                            <td>
                                <form method="post" action="leave/delete/${leave.id}">
                                    <button class="btn btn-danger" type="submit">Delete</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>

                    </tbody>
                </table>
            </div>
        </div>
    </div>


</div>

</body>
</html>