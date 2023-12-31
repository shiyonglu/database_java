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

// Handle update action
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $idToUpdate = $_POST['id'];
    $nameToUpdate = $_POST['name'];
    // You can add other fields as needed

    // Update the record in the database
    $updateSql = "UPDATE names SET name = ? WHERE id = ?";
    $updateStmt = $conn->prepare($updateSql);
    $updateStmt->bind_param("si", $nameToUpdate, $idToUpdate);

    if ($updateStmt->execute()) {
        echo "Record with ID $idToUpdate updated successfully.";
    } else {
        echo "Error updating record: " . $updateStmt->error;
    }

    $updateStmt->close();

    // Redirect back to database.php
    header("Location: database.php");
    exit();
}

// Close the database connection
$conn->close();
?>
