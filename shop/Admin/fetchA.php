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
?>
<html>
<head>
	<title>EDIT Data</title>
	
</head>

<body>
	<div class="container" style="width: 800px; margin-top: 100px;">
		<h3 align="center">GuchuSys Solutions| Admin panel</h3>
		<a href="index.php?=add-record" class="btn btn-info">Add Record</a><br><br>
		<div class="row">
             <table id="example" class="display" style="width:100%">
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Code</th>
                <th>Image</th>
                <th>Price</th>
                <th>Edit</th>
                <th>Delete</th>
                
            </tr>
        </thead>
        <tbody>
        	<?php
        	      include("dbcontroller.php");
                  $query ="SELECT * FROM orders";
                  $sql = mysqli_query($conn,$query);
                  while($row = mysqli_fetch_array($sql))
                  {

        	?>
            <tr>
                <td><?php echo $row["id"];?></td>
                <td><?php echo $row["name"];?></td>
                <td><?php echo $row["code"];?></td>
                <td><?php echo $row["image"];?></td>
                <td><?php echo $row["price"];?></td>
                <td><a href="edit.php?id=<?php echo $row['id']; ?>" class="btn btn-info">EDIT</a></td>
              <td>  <a href="delete.php?id=<?php echo $row['id'];?>" class="btn btn-danger" onClick="return confirm('Are you sure you want to delete?')">DELETE</a></td>
                 
            </tr>
            <?php } ?>
            
        </tbody>
        
    </table>

		</div>
	</div>
</body>
</html>

<script type="text/javascript">
	$(document).ready(function() {
    $('#example').DataTable();
} );
</script>