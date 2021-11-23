<?php
session_start();
if (empty($_SESSION['uname'])) {
    // The username session key does not exist or it's empty.
    header('location: index_Guest.php');
    exit;
}