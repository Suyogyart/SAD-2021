<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="ISO-8859-1" name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <title>Admin Home</title>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <h3>Leave Requests</h3>
                <br><br>
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th scope="col">Emp ID</th>
                        <th scope="col">Full Name</th>
                        <th scope="col">Leave Type</th>
                        <th scope="col">Remarks</th>
                        <th scope="col">Start</th>
                        <th scope="col">End</th>
                        <th scope="col" colspan="2">Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${leaves}" var="leave">
                        <tr>
                            <th scope="row">${leave.emp.id}</th>
                            <td>${leave.emp.name.fname} ${leave.emp.name.mname} ${leave.emp.name.lname}</td>
                            <td>${leave.leaveType}</td>
                            <td>${leave.remarks}</td>
                            <td>${leave.start}</td>
                            <td>${leave.end}</td>
                            <td>
                                <form method="post" action="leaveRequests/approve/${leave.id}">
                                    <button class="btn btn-success" name="approve" type="submit">Approve</button>
                                </form>
                            </td>
                            <td>
                                <form method="post" action="leaveRequests/decline/${leave.id}">
                                    <button class="btn btn-danger" name="decline" type="submit">Decline</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>

                    </tbody>
                </table>

            </div>
        </div>

    </body>
</html>