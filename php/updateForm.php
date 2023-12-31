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

// Initialize variables
$idToUpdate = $nameToUpdate = "";

// Check if the form is submitted and contains valid data
if ($_SERVER["REQUEST_METHOD"] == "POST" && isset($_POST['action']) && $_POST['action'] == 'update' && isset($_POST['id'])) {
    $idToUpdate = $_POST['id'];

    // Fetch the existing data based on the ID
    $selectSql = "SELECT * FROM names WHERE id = ?";
    $selectStmt = $conn->prepare($selectSql);
    $selectStmt->bind_param("i", $idToUpdate);
    $selectStmt->execute();
    $selectResult = $selectStmt->get_result();

    if ($selectResult->num_rows > 0) {
        $row = $selectResult->fetch_assoc();
        $nameToUpdate = $row["name"];
        // You can add other fields as needed
    } else {
        echo "Record not found.";
        $conn->close();
        exit();
    }

    $selectStmt->close();
}

// Close the database connection
$conn->close();
?>

<!DOCTYPE html>
<html>
<head>
    <title>Update Form</title>
</head>
<body>

<h2>Update Record</h2>

<form action="update.php" method="post">
    <input type="hidden" name="id" value="<?php echo $idToUpdate; ?>">
    <label for="name">Name:</label>
    <input type="text" name="name" value="<?php echo $nameToUpdate; ?>" required>
    <br>
    <!-- Add other fields as needed -->
    <input type="submit" value="Update">
</form>

</body>
</html>
