<?php
    // Delete Table data
    if (isset($_POST["btnSave"])) {
        // Connect to the database
        require_once "connection.php";

        $item_code    = $con->real_escape_string($_POST["txtitem_code"]);
        $category   = $con->real_escape_string($_POST["txtCategory"]);
        $description = $con->real_escape_string($_POST["txtDescription"]);
        $size = $con->real_escape_string($_POST["txtSize"]);
        $colour = $con->real_escape_string($_POST["txtColour"]);
        $bin_number = $con->real_escape_string($_POST["txtbinNumber"]);

        if ($stmt = $con->prepare("INSERT INTO `items`(`item_code`, `category`, `description`, `size`, `colour`, `bin_number`) VALUES (?, ?, ?, ?, ?, ?)")) {
            $stmt->bind_param("ssssss",  $item_code,$category, $description, $size , $colour, $bin_number);
            $stmt->execute();
            $stmt->close();
            $msg = '<div class="msg msg-create">Equipment details saved successfully.</div>';
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
                <input type="text" name="txtitem_code" placeholder="Name" required>
                <input type="text" name="txtCategory" placeholder="Equipment Category" required>
                <input type="text" name="txtDescription" placeholder="Equipment Description" required>
                <input type="text" min="0" name="txtSize" placeholder="Enter Equipment Size (kg)" required>
                <input type="text" name="txtColour" placeholder="Equipment Colour" required>
                <input type="text" name="txtbinNumber" placeholder="Equipment Bin Number" required>
                <div class="btnWrapper">
                    <button type="submit" name="btnSave" title="Save contact details">Save</button>
                    <a href="index.php" class="btnHome" title="Return back to homepage">Home</a>
                </div>
            </form>
        </div>
    </main>
</body>
</html>