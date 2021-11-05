-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 06, 2021 at 07:14 PM
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
  `password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `admin_login_information`
--

INSERT INTO `admin_login_information` (`id`, `username`, `password`) VALUES
(1, 'ADMIN', '73acd9a5972130b75066c82595a1fae3');

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `id` int(8) NOT NULL,
  `name` varchar(255) NOT NULL,
  `code` varchar(255) NOT NULL,
  `image` text NOT NULL,
  `price` double(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`id`, `name`, `code`, `image`, `price`) VALUES
(1, 'Gout Remedie', '3DcAM01', 'product-images/GOUT.jfif', 1500.00),
(2, 'Hay Fever Remedie', 'USB02', 'product-images/HAYFEVER.png', 800.00),
(3, 'Flu Oranges ', 'wristWear03', 'product-images/ORANGES.jpg', 300.00),
(4, 'Carrot Mix for Alergies', 'LPN45', 'product-images/carrot.jfif', 800.00),
(7, 'Berry Mix Remedie', 'Lp123', 'product-images/mixberry.jpg', 15000.00),
(8, 'Summer Mix', 'fruit212', 'product-images/SUMERFRUITS.jpg', 50.00),
(9, 'WetCold Mix', 'Med12', 'product-images/WETCOLD.jfif', 85.00),
(10, 'Charcoal', 'c12313214214412115', 'charcoal.jpg', 14.00),
(11, 'HEADACHE RELIEF', 'GRP121231231', 'GOUT - Copy.jfif', 1500.00),
(12, 'SWEET REST', 'S12312312', 'LITCHI.jfif', 1520.00),
(13, 'herb mix', 'her11213123', 'HAYFEVER - Copy.png', 2000.00);

-- --------------------------------------------------------

--
-- Table structure for table `remedies`
--

CREATE TABLE `remedies` (
  `id` int(11) NOT NULL,
  `name` varchar(200) NOT NULL,
  `used_for` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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
-- Indexes for table `remedies`
--
ALTER TABLE `remedies`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin_login_information`
--
ALTER TABLE `admin_login_information`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `id` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `remedies`
--
ALTER TABLE `remedies`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
