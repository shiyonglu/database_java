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
if ($_SERVER["REQUEST_METHOD"] == "GET" && isset($_GET['action']) && $_GET['action'] == 'delete' && isset($_GET['id'])) {
    $idToDelete = $_GET['id'];
    $deleteSql = "DELETE FROM names WHERE id = ?";
    $deleteStmt = $conn->prepare($deleteSql);
    $deleteStmt->bind_param("i", $idToDelete);

    if ($deleteStmt->execute()) {
        echo "Record with ID $idToDelete deleted successfully.";
    } else {
        echo "Error deleting record: " . $deleteStmt->error;
    }

    $deleteStmt->close();
}

// Fetch records from the database
$sql = "SELECT * FROM names";
$result = $conn->query($sql);
?>

<!DOCTYPE html>
<html>
<head>
    <title>Database</title>
</head>
<body>

<h2>Names Table</h2>

<table border='1'>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Date Added</th>
        <th>Action</th>
        <th>Update</th>
    </tr>
    <?php
    if ($result->num_rows > 0) {
        while ($row = $result->fetch_assoc()) {
            $id = $row["id"];
            $name = $row["name"];
            $dateAdded = $row["date_added"];
    ?>
    <tr>
        <td><?php echo $id; ?></td>
        <td><?php echo $name; ?></td>
        <td><?php echo $dateAdded; ?></td>
        <td>
            <form action="delete.php" method="get">
                <input type="hidden" name="id" value="<?php echo $id; ?>">
                <input type="hidden" name="action" value="delete">
                <button type="submit">Delete</button>
            </form>
        </td>
        <td>
            <form action="update.php" method="get">
                <input type="hidden" name="id" value="<?php echo $id; ?>">
                <input type="hidden" name="action" value="update">
                <button type="submit">Update</button>
            </form>
        </td>
    </tr>
    <?php
        }
    } else {
        echo "<tr><td colspan='5'>0 results</td></tr>";
    }
    ?>
</table>

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

<?php
// Close the database connection
$conn->close();
?>
