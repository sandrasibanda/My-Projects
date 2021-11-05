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
    if (isset($_POST['username'])){
		
		$username = stripslashes($_REQUEST['username']); // removes backslashes
		$username = mysqli_real_escape_string($conn,$username); //escapes special characters in a string
		$password = stripslashes($_REQUEST['password']);
		$password = mysqli_real_escape_string($conn,$password);
		
	//Checking is user existing in the database or not
        $query = "SELECT * FROM `admin_login_information` WHERE username='$username' and password='".md5($password)."'";
		$result = mysqli_query($conn,$query) or die(mysqli_error());
		$rows = mysqli_num_rows($result);
        if($rows==1){
			$_SESSION['username'] = $username;
			header("Location: index.php"); // Redirect user to index.php
            }else{
				echo "<div class='form'><h3>Username/password is incorrect.</h3><br/>Click here to <a href='login.php'>Login</a></div>";
				}
    }else{
?>

<div class="form">
	<center><h1>GuchuSys-LOGIN TERMINAL</h1>
<h2>Log In</h2>
<form action="" method="post" name="login">
<input type="text" name="username" placeholder="Username" required />
<br><br>
<input type="password" name="password" placeholder="Password" required />
<br><br>
<input name="submit" type="submit" value="Login" />
</form>
<p>Not registered yet? <a href='reg.php'>Register Here</a></p>
</center>

<?php } ?>


</body>
</html>
