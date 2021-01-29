<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Show Users in Table</title>
    <link href="css\bootstrap.min.css" rel="stylesheet">
</head>

<body>
    <div class="container">
        <div class="row">
            <div class="col-lg-6">
                <table class="table-hover">
                    <thead>
                        <tr>
                            <th scope="col">UID</th>
                            <th scope="col">Name</th>
                            <th scope="col">Nationality</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr class="table-light">
                            <td>${user.uid}</td>
                            <td>${user.name}</td>
                            <td>${user.nationality}</td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div class="col-lg-4"></div>
            <div class="col-lg-4"></div>
        </div>
    </div>

    
    <!-- <table border="1" cellpadding="5">
        <caption>
            <h2>List of users</h2>
        </caption>
        <tr>
            <th>UID</th>
            <th>Name</th>
            <th>Nationality</th>
        </tr>
        <c:forEach var="user" items="${listOfUsers.rows}">
            <tr>
                <td>${user.uid}</td>
                <td>${user.name}</td>
                <td>${user.nationality}</td>
            </tr>
        </c:forEach>
    </table> -->
</body>

</html>