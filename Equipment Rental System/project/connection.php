<?php
    // Enable error reporting for mysqli
    mysqli_report(MYSQLI_REPORT_ERROR | MYSQLI_REPORT_STRICT);
    // Hostname
    $host = "localhost";

    // Username
    $user = "root";

    // Password
    $pass = "";

    // Database Name
    $db   = "equipment_rental_system";


    $con = new mysqli("localhost","root","","equipment_rental_system");
    if ($con->connect_error) {
        die("Connection failed: " . $con->connect_error);
    }