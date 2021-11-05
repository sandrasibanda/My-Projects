<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>GuchuSys| Login</title>
<link rel="stylesheet" href="style0.css" />
</head>
<body>
<?php
	 $host = "localhost";
	 $user = "root";
	 $password = "";
	 $database = "ishop";
	 $conn = mysqli_connect($host,$user,$password,$database);

	session_start();
    // If form submitted, insert values into the database.
    if (isset($_POST['submit'])){
		
		$username = $_POST['username']; // removes backslashes
		
		$password = $_POST['password'];
		$passEcrypt= md5($password);

		$f_name =$_POST["full_name"];
		$u_addres =$_POST["u_address"];
		
	//Checking is user existing in the database or not
        $query = "INSERT INTO `admin_login_information` (`username`, `password`, `full_name`, `Address`) VALUES('$username','$passEcrypt', '$f_name', '$u_addres')";
		if (mysqli_query($conn, $query))
		{
			header("Location: login.php"); // Redirect user to login.php
			echo"<script>Alert('New record created successfully!');</script>";
            }else{
				echo "<div class='form'><h3>Sorry, you can not register.Please check the details you entered.</h3><br/>Click here to <a href='login.php'>Login</a></div>";
				}
    }else{
?>
<div class="form">
	<center><h1>GuchuSys-Register TERMINAL</h1>
<h2>Register here</h2>
<form action="" method="post" name="login">
<input type="text" name="username" placeholder="Username" required />
<br><br>
<input type="password" name="password" placeholder="Password" required />
<br><br>

<br><br>
<input type="text" name="full_name" placeholder="Enter Full Name" required />
<br><br>
<br><br>
<input type="text" name="u_address" placeholder="Enter Home Address" required />
<br><br>
<input name="submit" type="submit" value="Login" />
</form>
<p>Already have account ? <a href='login.php'>login Here</a></p>
</center>

<?php } ?>


</body>
</html>
