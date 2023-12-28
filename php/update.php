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
$id = $_GET['id'];
$name = '';

// Fetch existing data for the given ID
$sql = "SELECT * FROM names WHERE id = ?";
$stmt = $conn->prepare($sql);
$stmt->bind_param("i", $id);

if ($stmt->execute()) {
    $result = $stmt->get_result();
    if ($result->num_rows > 0) {
        $row = $result->fetch_assoc();
        $name = $row['name'];
    } else {
        echo "Record not found.";
        exit();
    }
} else {
    echo "Error retrieving record: " . $stmt->error;
    exit();
}

// Handle form submission for updating data
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $newName = $_POST['name'];

    // Update the record in the database
    $updateSql = "UPDATE names SET name = ? WHERE id = ?";
    $updateStmt = $conn->prepare($updateSql);
    $updateStmt->bind_param("si", $newName, $id);

    if ($updateStmt->execute()) {
        echo "Record updated successfully. Redirecting back to database.php...";
        header("Refresh: 2; URL=database.php"); // Redirect after 2 seconds
        exit();
    } else {
        echo "Error updating record: " . $updateStmt->error;
    }

    $updateStmt->close();
}

// Close the database connection
$conn->close();
?>

<!DOCTYPE html>
<html>
<head>
    <title>Update Record</title>
</head>
<body>

<h2>Update Record</h2>

<form action="updateTable.php?id=<?php echo $id; ?>" method="post">
    <label for="name">New Name:</label>
    <input type="text" name="name" value="<?php echo $name; ?>" required>
    <br>
    <input type="submit" value="Update">
</form>

</body>
</html>
