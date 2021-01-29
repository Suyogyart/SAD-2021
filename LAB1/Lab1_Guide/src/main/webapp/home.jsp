<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add User</title>
    <link href="css\bootstrap.min.css" rel="stylesheet">
</head>

<body>
    <div class="container">

        <div class="row">
            <div class="col-lg-3"></div>
            <div class="col-lg-6">
                <h2>Add User</h2>
                <form action="addUser">
                    <div class="form-group">
                        <label for="uid">User Id:</label>
                        <input type="text" name="uid" class="form-control" id="uid">
                    </div>
                    <div class="form-group">
                        <label for="name">Name:</label>
                        <input type="text" name="name" class="form-control" id="name">
                    </div>
                    <div class="form-group">
                        <label for="nationality">Nationality:</label>
                        <input type="text" name="nationality" class="form-control" id="nationality">
                    </div>
                    <button type="submit" class="btn btn-success btn-lg btn-block">Add</button>
                </form>
                <br>
                <h2>Get User by ID:</h2>
                <form action="getUser">
                    <div class="form-group">
                        <label for="uid">User Id:</label>
                        <input type="text" name="uid" class="form-control" id="uid">
                    </div>
                    <button type="submit" class="btn btn-primary btn-lg btn-block">Get</button>
                </form>
            </div>
            <div class="col-lg-3"></div>
        </div>


    </div>

</body>

</html>