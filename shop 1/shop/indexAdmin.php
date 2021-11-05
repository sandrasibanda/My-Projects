<?php
session_start();
$con = mysqli_connect("localhost","root","","ishop");
$status = "";


if(isset($_POST['submitDATA']))
{

    $name =$_POST['name'];
    $code = $_POST['code'];
    $price = $_POST['price'];

   //getting the image from the field
  //$product_image = $_FILES['image']['name'];
  $filename = $_FILES['imageUpload']['name'];
  $filetmpname = $_FILES['imageUpload']['tmp_name'];
  $folder = "product-images/";
  move_uploaded_file($filetmpname, $folder. $filename);
  $myfile = $folder.$filename;

    $insertsql = "INSERT INTO `orders` (`name`, `code`, `image`, `price`) VALUES ('$name', '$code', '$myfile', '$price')";
    $result = mysqli_query($con,$insertsql);
    if($result==true){
     $status = "New product Inserted Successfully.</br></br><a href='index.php'>View  In Store</a>";
    }
	else {
	echo "<script>alert('UNABLE TO UPLOAD PRODUCT!');</script>";
    } 
}

?>
<!DOCTYPE html>
<HTML>
<HEAD>
<TITLE>GuchuSys Solutions</TITLE>
<link href="style.css" type="text/css" rel="stylesheet" />
<link href="style1.css" type="text/css" rel="stylesheet" />
</HEAD>
<BODY>
<div class="header">
          <h1>GuchuSys Solutions</h1>
          <p font-size='25px'>Helps Improve Health!</p>
          <?php echo "<h2>Welcome " . $_SESSION['username'] . "</h2>"; ?>
      </div>

	<div class="navbar" align='center'>
     <a href="index.php">HOME</a>
     <a href="indexCart.php">cart</a>
     
     <a href="logout.php"   class="right">Sign Out of Your Account</a>
     </div>
<div>
<h1>Add NEW PRODUCT</h1>
<form name="form" method="post" action="" enctype="multipart/form-data"> 
<p style="color:#FF0000;"><?php echo $status; ?></p>
<input type="hidden" name="new" value="1" />
<p><input type="text" name="name" placeholder="Enter Product Name" required /></p>
<p><input type="text" name="code" placeholder="Enter Procuct Code" required /></p>
<p>PRICE: <br><input type="number" min = "1" max="10000" name="price"  required /></p>
<p>PICTURE:<br><input type="file" name="imageUpload" required /></p>
<p><input type="submit" name="submitDATA" value="UPLOAD TO STORE"/></p>
<hr>
<h1>REMOVE PRODUCT FROM STORE</h1>
</form>
<form name="form" method="post" action="delete.php">
<p style="color:#FF0000;"><?php echo $status; ?></p>
<p><input type="text" name="deletebyname" placeholder="ENTER PRODUCT NAME TO DELETE?" /></p>
<p><input type="submit" name="DELETEdata" value="REMOVE FROM STORE"/></p>
</form>
<h1>UPDATE PRODUCT FROM STORE</h1>
</form>
<form name="form" method="post" action="delete.php">
<p style="color:#FF0000;"><?php echo $status; ?></p>
<p><input type="text" name="search product" placeholder="ENTER PRODUCT NAME TO update!" /></p>
<p><input type="submit" name="updatedata" value="UPDATE STORE"/></p>
</form>
</BODY>
</HTML>