<?php
session_start();
if(session_destroy()) // Destroying All Sessions
{
    echo"<script>Alert('Logged out Success!');</script>";
header("Location: login.php"); // Redirecting To Home Page
}
?>