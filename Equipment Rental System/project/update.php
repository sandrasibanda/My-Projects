<?php
    // Connect to the database
    require_once "connection.php";
    
    // Get contact details
    if (isset($_GET["id"])) {
        $id = preg_replace('/\D/', '', $_GET["id"]); //Accept numbers only
    } else {
        header("Location: index.php?p=update&err=no_id");
    }

    // Update contact details
    if (isset($_POST["btnUpdate"])) {
        $item_code    = $con->real_escape_string($_POST["txtitem_code"]);
        $category   = $con->real_escape_string($_POST["txtCategory"]);
        $description = $con->real_escape_string($_POST["txtDescription"]);
        $size = $con->real_escape_string($_POST["txtSize"]);
        $colour = $con->real_escape_string($_POST["txtColour"]);
        $bin_number = $con->real_escape_string($_POST["txtbinNumber"]);

        if ($stmt = $con->prepare("UPDATE `items` SET `item_code`= ?, `category`= ?, `description`=? , `size`= ? ,`colour` = ?,`bin_number` = ? WHERE `id`=?")) {
            $stmt->bind_param("ssssssi", $item_code, $category, $description, $size, $colour, $bin_number, $id);
            $stmt->execute();
            $stmt->close();
            $msg = '<div class="msg msg-update">Contact details updated successfully.</div>';
        } else {
            $msg = '<div class="msg">Prepare() failed: '.htmlspecialchars($con->error).'</div>';
        }
    }

    
    if ($stmt = $con->prepare("SELECT `item_code`, `category`, `description`, `size`, `colour`, `bin_number` FROM `items` WHERE `id`=? LIMIT 1")) {
        $stmt->bind_param("i", $id);
        $stmt->execute();
        $stmt->bind_result($item_code, $category, $description , $size, $colour, $bin_number);
        $stmt->fetch();
        $stmt->free_result();
        $stmt->close();
    } else {
        die('prepare() failed: ' . htmlspecialchars($con->error));
    }
    
    // Close database connection
    $con->close();
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Equipment Rental System | UPDATE</title>
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
            <div class="title update">
                <h2>Update Equipment  </h2>
                <hr>
            </div>
            <form action="<?=$_SERVER['PHP_SELF']."?id=".$id;?>" method="post" class="frmUpdate">
            <input type="text" name="txtitem_code" value="<?php echo $item_code?>" required>
                <input type="text" name="txtCategory" value="<?php echo $category?>" required>
                <input type="text" name="txtDescription" value="<?php echo $description?>" required>
                <input type="text"  name="txtSize" value="<?php echo $size?>" required>
                <input type="text" name="txtColour" value="<?php echo $colour?>" required>
                <input type="text" name="txtbinNumber" value="<?php echo $bin_number?>" required>
                <div class="btnWrapper">
                    <button type="submit" name="btnUpdate" class="btnUpdate" title="Update Equipment details">Update</button>
                    <a href="index.php" class="btnHome" title="Return back to homepage">Home</a>
                </div>
            </form>
        </div>
    </main>
</body>
</html>