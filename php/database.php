
<!DOCTYPE html>
<html>
<head>
    <title>Names List</title>
</head>
<body>

<h2>Names Table</h2>

<?php
$servername = "localhost";
$username = "john";
$password = "test1234";
$dbname = "web_app";

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);

// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

$sql = "SELECT * FROM names";
$result = $conn->query($sql);

if ($result->num_rows > 0) {
    echo "<table border='1'><tr><th>ID</th><th>Name</th><th>Date Added</th></tr>";
    while($row = $result->fetch_assoc()) {
        echo "<tr><td>".$row["id"]."</td><td>".$row["name"]."</td><td>".$row["date_added"]."</td></tr>";
    }
    echo "</table>";
} else {
    echo "0 results";
}

$conn->close();
?>

<br>
<br>
<br>

<form action="insert.php" method="post">
    <label for="name">Name:</label>
    <input type="text" name="name" required>
    <br>
    <input type="submit" value="Insert Name">
</form>

</body>
</html>
