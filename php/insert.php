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

// Process the form submission
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $name = $_POST["name"];

    // Insert data into the names table
    $sql = "INSERT INTO names (name, date_added) VALUES (?, NOW())";
    $stmt = $conn->prepare($sql);
    $stmt->bind_param("s", $name);

    if ($stmt->execute()) {
        echo "Name '$name' inserted successfully.";
    } else {
        echo "Error inserting name: " . $stmt->error;
    }

    $stmt->close();

    // Redirect back to database.php
    header("Location: database.php");
    exit();
}

$conn->close();
?>
