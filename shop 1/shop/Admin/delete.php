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
  $id = $_GET["id"];
  $result = mysqli_query($conn, "DELETE FROM oders WHERE id=$id");
  header("location:fetchA.php");
?>
