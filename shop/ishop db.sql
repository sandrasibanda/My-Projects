-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 07, 2021 at 02:53 PM
-- Server version: 10.4.17-MariaDB
-- PHP Version: 8.0.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ishop`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin_login_information`
--

CREATE TABLE `admin_login_information` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `Full_name` varchar(250) NOT NULL,
  `Address` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `admin_login_information`
--

INSERT INTO `admin_login_information` (`id`, `username`, `password`, `Full_name`, `Address`) VALUES
(1, 'ADMIN', '73acd9a5972130b75066c82595a1fae3', 'ADMIN', 'ADMIN_GUCHU'),
(2, 'JANE', 'dfa6ee1dee4bcd95e43f1431258d3aee', 'Jane White', 'New York 25 flat street'),
(4, 'FRED', 'ffb61f7d3dad0c9901007888548591f8', 'Fred Gray', '78 Soweto Steet '),
(5, 'JOHN', 'e2577c04131c5b0c7e7580f978322b31', '', ''),
(11, 'zac', '0c22828099b789d62a96fc1f87928f43', 'zac black', '6 nice street'),
(12, 'Henry', '450ed2e34e0bf66f0aed387cf4585341', 'HENRY GREEN', '9 NEW STREET'),
(13, 'JAX', 'fab966bb80bf0c8397d2bbce0e2b41c6', 'JAX STREET', '67 HOME STREET'),
(14, 'THEMBI', '5a989adae02e05546b764b42be926efd', 'THEMBI DENMARK', '7 HIGH STREET');

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `id` int(8) NOT NULL,
  `name` varchar(255) NOT NULL,
  `code` varchar(255) NOT NULL,
  `image` varchar(250) NOT NULL,
  `price` double(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`id`, `name`, `code`, `image`, `price`) VALUES
(1, 'Gout Remedie', '3DcAM01', 'product-images/GOUT.jfif', 1500.00),
(2, 'Hay Fever Remedie', 'h123', 'product-images/HAYFEVER.png', 800.00),
(44, 'Berry Mix', 'bm123', 'product-images/BERRIES.jfif', 150.00),
(45, 'Fever Oranges', 'f123', 'product-images/ORANGES.jpg', 140.00);

-- --------------------------------------------------------

--
-- Table structure for table `user_login`
--

CREATE TABLE `user_login` (
  `id` int(11) NOT NULL,
  `username` varchar(250) NOT NULL,
  `password` varchar(250) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user_login`
--

INSERT INTO `user_login` (`id`, `username`, `password`) VALUES
(1, 'SARA', '7f3c1ae38e1687965056c3834c48b343 '),
(5, 'cv@cv.cv', '81dc9bdb52d04dc20036dbd8313ed055'),
(6, 'JANE', 'dfa6ee1dee4bcd95e43f1431258d3aee'),
(7, 'JANE', 'dfa6ee1dee4bcd95e43f1431258d3aee'),
(8, 'JANE', 'dfa6ee1dee4bcd95e43f1431258d3aee');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin_login_information`
--
ALTER TABLE `admin_login_information`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `product_code` (`code`);

--
-- Indexes for table `user_login`
--
ALTER TABLE `user_login`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin_login_information`
--
ALTER TABLE `admin_login_information`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `id` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=47;

--
-- AUTO_INCREMENT for table `user_login`
--
ALTER TABLE `user_login`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
