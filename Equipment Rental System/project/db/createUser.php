<?php
    // Delete Table data
    if (isset($_POST["btnSave"])) {
        // Connect to the database
        require_once "connection.php";

        $username    = $con->real_escape_string($_POST["txtUsername"]);
        $password   = $con->real_escape_string($_POST["txtPassword"]);
       
        

        if ($stmt = $con->prepare("INSERT INTO `admin`(`username`, `password`) VALUES (?, ?)")) {
            $stmt->bind_param("ss",  $username,$password);
            $stmt->execute();
            $stmt->close();
            $msg = '<div class="msg msg-create">User Login details saved successfully.</div>';
            header('Location: login.php');
        } else {
            $msg = '<div class="msg">Prepare() failed: '.htmlspecialchars($con->error).'</div>';
        }

        // Close database connection
        $con->close();
    }
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Equipment Rental | New </title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <?php if(isset($msg)){ echo $msg; }?>
    <main class="container">
        <div class="wrapper">
            <h1>Equipment Rental System</h1>
            <h2>&#187; for all equipment services &#171;</h2>
        </div>
        <div class="wrapper">
            <div class="title create">
                <h2>Create New Equipment Rental</h2>
                <hr>
            </div>
            <form action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]); ?>" method="post" class="frmCreate">
                <input type="text" name="txtUsername" placeholder="Enter username" required>
                <input type="text" name="txtPassword" placeholder="Enter password" required>
                
                <div class="btnWrapper">
                    <button type="submit" name="btnSave" title="Save contact details">Save</button>
                    <a href="index.php" class="btnHome" title="Return back to homepage">Home</a>
                </div>
            </form>
        </div>
    </main>
</body>
</html>