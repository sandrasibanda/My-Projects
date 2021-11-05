<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Registration</title>
<link rel="stylesheet" href="style0.css" />
</head>
<body>
<?php

$host = "localhost";
	 $user = "root";
	 $password = "";
	 $database = "ishop";
	 $conn = mysqli_connect($host,$user,$password,$database);
     
	require('dbcontroller.php');
    // If form submitted, insert values into the database.
    if (isset($_REQUEST['username'])){
		$username = stripslashes($_REQUEST['username']); 
		$password = stripslashes($_REQUEST['password']);
		

		
        $query = "INSERT into `user_login` ('username', 'password') VALUES ('$username', '".md5($password)."'')";
        $result = mysqli_query($conn,$query);
        if($result){
            echo "REGISTRATION SUCCESS! <a href='login.php'>CLICK TO Login</a>";
        }
    }else{
?>
<center><h1>ISHOP-LOGIN TERMINAL</h1>
<h2>Registration</h2>
<form name="registration" action="" method="post">
<input type="text" name="username" placeholder="Username" required /><br/>
<input type="password" name="password" placeholder="Password" required /><br/>
<input type="submit" name="submit" value="Register" /><br/><br/>
<label><a href="login.php">CANCEL</a></label>
</form>
<br /><br /><br /><br />
<a href="#">SA1 HPRXS3B161 2021</a>
</div>
<?php } ?>
</body>
</html>
