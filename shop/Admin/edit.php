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
<?php
 
if(isset($_POST['update']))
{    
    $id = $_POST['id'];
    
   $name = $row['name'];
    $code = $row['code'];
    $image = $row['image'];
	$price = $row ['price'];;    
    
    
        $result = mysqli_query($conn, "UPDATE orders SET name='$name',code='$code',image='$image', price='$price' WHERE id=$id");
        
        //redirectig to the display page. In our case, it is index.php
        header("Location: fetchA.php");
    
}
?>
<?php
//error_reporting(0);
//getting id from url
$id = $_GET['id'];
 
//selecting data associated with this particular id
$result = mysqli_query($conn, "SELECT * FROM orders WHERE id=$id");
 
while($row = mysqli_fetch_array($result))
{
    $name = $row['name'];
    $code = $row['code'];
    $image = $row['image'];
	$price = $row ['price'];
}
?>
<html>
<head>
	<title>Update Data</title>

</head>

<body>
	<div class="container" style="width: 800px; margin-top: 100px;">
		<div class="row">
    <h3 align="center">GuchuSys Solutions| Admin panel</h3>
    
			<div class="col-sm-6"> 
	
	<form action="" method="post" name="form1">
		<div class="form-group">
				
				<input type="hidden" name="id" class="form-control" value="<?php echo $id;?>">
					
				
				
				
				

		</div>
		<div class="form-group">
				<label>Name</label>
				<input type="text" name="name" class="form-control" value="<?php echo $name;?>">
			
		</div>
			   <div class="form-group">
				<label>Code</label>
				<<input type="text" name="code" class="form-control" value="<?php echo $code;?>">
			</div>
			 <div class="form-group">
				<label>Image</label>
				<input type="file" >name="image" class="form-control" value="<?php echo $image;?>"
			  </div>

			  <div class="form-group">
				<label>PRICE</label>
				<input type="text" name="price" class="form-control" value="<?php echo $price;?>">
			  </div>
				<div class="form-group">
				<input type="submit" name="update" value="Update" class="btn btn-primary btn-block" name="update">
			
		
	</form>

</div>
</div>
</div>
</body>
</html>

