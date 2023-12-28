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

// Handle delete action
if (isset($_GET['action']) && $_GET['action'] == 'delete' && isset($_GET['id'])) {
    $id = $_GET['id'];
    $deleteSql = "DELETE FROM names WHERE id = ?";
    $deleteStmt = $conn->prepare($deleteSql);
    $deleteStmt->bind_param("i", $id);

    if ($deleteStmt->execute()) {
        echo "Record with ID $id deleted successfully.";
    } else {
        echo "Error deleting record: " . $deleteStmt->error;
    }

    $deleteStmt->close();
}

// Redirect back to database.php
header("Location: database.php");
exit();

$conn->close();
?>

