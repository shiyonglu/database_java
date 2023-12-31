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
if ($_SERVER["REQUEST_METHOD"] == "POST" && isset($_POST['action']) && $_POST['action'] == 'delete' && isset($_POST['id'])) {
    $idToDelete = $_POST['id'];
    $deleteSql = "DELETE FROM names WHERE id = ?";
    $deleteStmt = $conn->prepare($deleteSql);
    $deleteStmt->bind_param("i", $idToDelete);

    if ($deleteStmt->execute()) {
        echo "Record with ID $idToDelete deleted successfully.";
    } else {
        echo "Error deleting record: " . $deleteStmt->error;
    }

    $deleteStmt->close();

    // Redirect back to database.php
    header("Location: database.php");
    exit();
}

// Close the database connection
$conn->close();
?>
