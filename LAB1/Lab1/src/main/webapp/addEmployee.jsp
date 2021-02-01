<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Add new employee</title>
  <link href="css\bootstrap.min.css" rel="stylesheet">
</head>

<body>
  <br>
  <div class="container">
    <div class="row">
      <div class="col-lg-4"></div>
      <div class="col-lg-4">
        <h2>Add New Employee</h2>
        <form action="employee" method="POST">
          <div class=" form-group">
            <label for="name">Name</label>
            <input type="text" name="name" id="name" class="form-control" placeholder="">
          </div>
          <div class="form-group">
            <label for="gender">Gender</label>
            <select class="form-control" name="gender" id="gender">
              <option value="Male">Male</option>
              <option value="Female">Female</option>
            </select>
          </div>
          <div class=" form-group">
            <label for="address">Address</label>
            <input type="text" name="address" id="address" class="form-control" placeholder="">
          </div>
          <div class=" form-group">
            <label for="salary">Salary</label>
            <input type="text" name="salary" id="salary" class="form-control" placeholder="">
          </div>
          <div class=" form-group">
            <label for="value">Value</label>
            <input type="text" name="value" id="value" class="form-control" placeholder="">
          </div>
          <div class="form-group">
            <label for="positionlevel">Position Level</label>
            <select class="form-control" id="positionlevel" name="positionLevel">
              <option value="1">1</option>
              <option value="2">2</option>
              <option value="3">3</option>
              <option value="4">4</option>
            </select>
          </div>
          <br>
          <button type="submit" class="btn btn-success btn-block">Add</button>
        </form>
      </div>
      <div class="col-lg-4"></div>
    </div>
  </div>

</body>

</html>