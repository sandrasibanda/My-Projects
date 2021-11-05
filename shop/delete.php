<?php
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "ishop";

// Create connection
$conn = mysqli_connect($servername, $username, $password, $dbname);
// Check connection
if (!$conn) {
    die("Connection failed: " . mysqli_connect_error());
}
$name=$_POST['deletebyname'];
// sql to delete a record
$sql = "DELETE FROM `orders` WHERE `name` = '$name' ";

if (mysqli_query($conn, $sql)==true) {
    echo"<script>alert('PRODUCT REMOVED Successfully!');</script>";
    header("Location: index.php");
   
    
} else {
    echo "Error deleting record: " . mysqli_error($conn);
}

mysqli_close($conn);
?>