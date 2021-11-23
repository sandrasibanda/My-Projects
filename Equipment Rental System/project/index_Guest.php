<?php
    // Connect to the database
    require_once "connection.php";
    
      
    // Delete Table data
    if (isset($_GET["del"])) {
        $id = preg_replace('/\D/', '', $_GET["del"]); //Accept numbers only
        if ($stmt = $con->prepare("DELETE FROM `items` WHERE `id`=?")) {
            $stmt->bind_param("i", $id);
            $stmt->execute();
            $stmt->close();
            $msg = '<div class="msg msg-delete">Contact details deleted successfully.</div>';
        } else {
            die('prepare() failed: ' . htmlspecialchars($con->error));
        }
    }

    // Display Table data
    $tabledata = "";
    $tabledata2 = "";
    $sqlsearch = "";
    if (isset($_POST["btnSearch"])) {
        $keywords = $con->real_escape_string($_POST["txtSearch"]);
        $searchTerms = explode(' ', $keywords);
        $searchTermBits = array();
        foreach ($searchTerms as $key => &$term) {
            $term = trim($term);
            $searchTermBits[] = " `item_code` LIKE '%$term%' OR `bin_number` LIKE '%$term%' OR `category` LIKE '%$term%' OR `size` LIKE '%$term%' OR `colour` LIKE '%$term%'";
        }
        $sqlsearch = " WHERE " . implode(' AND ', $searchTermBits);
    }

    if ($stmt = $con->prepare("SELECT * FROM `items` $sqlsearch")) 
    
        $stmt->execute();
        $result = $stmt->get_result();
        if($result->num_rows > 0) 
        {
            while ($row = $result->fetch_assoc())
            {
                $tabledata .= '<tr>
                                <td>'.$row["item_code"].'</td>
                                <td>'.$row["category"].'</td>
                                <td>'.$row["description"].'</td>
                                <td>'.$row["size"].'</td>
                                <td>'.$row["colour"].'</td>
                                <td>'.$row["bin_number"].'</td>
                                <td>
                                </td>
                            </tr>';
            }
        } else {
            $tabledata= '<tr><td colspan="8" style="text-align: center; padding:30px 0;">Nothing to display</td></tr>';
        }

        $stmt->close();

    // Close database connection
    $con->close();
  ?>
 <!DOCTYPE html>
 <html lang="en">
 <head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Equipment Rental | Home</title>
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
            <a href="" class="btnCreate" title="Create new contact">GUEST PORTAL</a>
        </div>
        <div class="wrapper">
            <form action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]); ?>" method="post">
                <input type="text" name="txtSearch" value="<?php if(isset($keywords)){ echo $keywords; }?>" title="Input keywords here" required>
                <button type="submit" name="btnSearch" class="btnSearch" title="Search keywords">Search</button>|
                <a href="index_Guest.php" class="btnReset" title="Reset search">Reset</a> |
                
                <a href="mylogin.php" class="btnReset" title="Login">Login existing account</a> |
                <a href="register.php" class="btnReset" title="SignUp">SignUp for account</a>
            </form>
        </div>
        <div class="wrapper">
            <table>
                <thead>
                    <tr>
                        <th>Item Code</th>
                        <th>Category</th>
                        <th>Description</th>
                        <th>Size</th>
                        <th>Colour</th>
                        <th>Bin Number</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <?php
                        echo $tabledata;
                    ?>
                </tbody>
            </table>
        </div>
        
    </main>
  </body>
</html>