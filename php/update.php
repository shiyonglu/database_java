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
if ($_SERVER["REQUEST_METHOD"] == "GET" && isset($_GET['action']) && $_GET['action'] == 'update' && isset($_GET['id'])) {
    $idToUpdate = $_GET['id'];
    
    // Fetch the current data for the selected ID
    $selectSql = "SELECT * FROM names WHERE id = ?";
    $selectStmt = $conn->prepare($selectSql);
    $selectStmt->bind_param("i", $idToUpdate);
    $selectStmt->execute();
    $result = $selectStmt->get_result();
    $row = $result->fetch_assoc();
    
    $nameToUpdate = $row['name'];  // Assuming you want to update the 'name' field

    // Display the form for updating
    echo "<h2>Update Name</h2>";
    echo "<form action='updateTable.php' method='post'>";
    echo "<input type='hidden' name='id' value='$idToUpdate'>";
    echo "Name: <input type='text' name='new_name' value='$nameToUpdate' required>";
    echo "<br><input type='submit' value='Update'>";
    echo "</form>";

    // Handle the form submission for update
    if ($_SERVER["REQUEST_METHOD"] == "POST") {
        $newName = $_POST['new_name'];

        $updateSql = "UPDATE names SET name = ? WHERE id = ?";
        $updateStmt = $conn->prepare($updateSql);
        $updateStmt->bind_param("si", $newName, $idToUpdate);

        if ($updateStmt->execute()) {
            echo "Record with ID $idToUpdate updated successfully.";
        } else {
            echo "Error updating record: " . $updateStmt->error;
        }

        $updateStmt->close();
    }
}

// Close the database connection
$conn->close();
?>
