<?php
session_start();
include("auth.php"); //include auth.php file on all secure pages
require_once("dbcontroller.php");
$db_handle = new DBController();
if(!empty($_GET["action"])) {
switch($_GET["action"]) {
	case "add":
		if(!empty($_POST["quantity"])) {
			$productByCode = $db_handle->runQuery("SELECT * FROM orders WHERE code='" . $_GET["code"] . "'");
			$itemArray = array($productByCode[0]["code"]=>array('name'=>$productByCode[0]["name"], 'code'=>$productByCode[0]["code"], 'quantity'=>$_POST["quantity"], 'price'=>$productByCode[0]["price"], 'image'=>$productByCode[0]["image"]));
			
			if(!empty($_SESSION["cart_item"])) {
				if(in_array($productByCode[0]["code"],array_keys($_SESSION["cart_item"]))) {
					foreach($_SESSION["cart_item"] as $k => $v) {
							if($productByCode[0]["code"] == $k) {
								if(empty($_SESSION["cart_item"][$k]["quantity"])) {
									$_SESSION["cart_item"][$k]["quantity"] = 0;
								}
								$_SESSION["cart_item"][$k]["quantity"] += $_POST["quantity"];
							}
					}
				} else {
					$_SESSION["cart_item"] = array_merge($_SESSION["cart_item"],$itemArray);
					echo"<script>Alert('Added To Cart!');</script>";
				}
			} else {
				$_SESSION["cart_item"] = $itemArray;
			}
			
		}
	break;
	case "remove":
		if(!empty($_SESSION["cart_item"])) {
			foreach($_SESSION["cart_item"] as $k => $v) {
					if($_GET["code"] == $k)
						unset($_SESSION["cart_item"][$k]);				
					if(empty($_SESSION["cart_item"]))
						unset($_SESSION["cart_item"]);
			}
		}
	break;
	case "empty":
		unset($_SESSION["cart_item"]);
	break;	
}
}
?>
<HTML>
<HEAD>
<TITLE>GuchuSys Solutions</TITLE>
<link href="style.css" type="text/css" rel="stylesheet" />
<link href="style1.css" type="text/css" rel="stylesheet" />
<style>
      .product-item {
        width: 250px;
        height: 115px;
      }
      img {
        width: 100%;
        height: 100%;
        object-fit: cover;
      }
    </style>

</HEAD>
<BODY>
<div class="header">
          <h1>GuchuSys Solutions</h1>
          <p font-size='25px'> Helps Improve Health</p>
      </div>

	<div class="navbar" align='center'>
     <a href="index.php">HOME</a>
     <a href="indexAdmin.php">Admin</a>
	 <?php
		$count=0;
		if(isset($_SESSION["cart_item"]))
		{
			$count=count($_SESSION["cart_item"]);
		}
		else
		{
			$count=0;
		}
	 ?>
	 <a href="indexCart.php">My Cart (<?php echo $count; ?>) </a>
     
     <a href="logout.php"   class="right">Sign Out of Your Account</a>
     </div>


<div id="product-grid">
	<div class="txt-heading">Products / Remedies</div>
	<?php
	$product_array = $db_handle->runQuery("SELECT * FROM orders ORDER BY id ASC");
	if (!empty($product_array)) { 
		foreach($product_array as $key=>$value){
	?>
		<div class="product-item">
			<form method="post" action="index.php?action=add&code=<?php echo $product_array[$key]["code"]; ?>">
			<div class="product-image"><img src="<?php echo $product_array[$key]["image"]; ?>"></div>
			<div class="product-tile-footer">
			<div class="product-title"><?php echo $product_array[$key]["name"]; ?></div>
			<div class="product-price"><?php echo "R".$product_array[$key]["price"]; ?></div>
			<div class="cart-action">
			<input type="number" min="1" max="100" ondrop="return false;" class="product-quantity" name="quantity" value="1" size="2" />
			<input type="submit" value="Add to Cart" class="btnAddAction" onclick="alert('Item Added Successfully!')" />
			</div>
			<br>
			</div>
			
			</form>
			<br>
			<br>
		</div>

	<?php
		}
	}
	?>
</div>

</BODY>
</HTML>