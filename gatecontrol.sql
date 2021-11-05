-- MySQL dump 10.13  Distrib 8.0.13, for Win64 (x86_64)
--
-- Host: localhost    Database: gatecontrol
-- ------------------------------------------------------
-- Server version	8.0.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `securityguard`
--

DROP TABLE IF EXISTS `securityguard`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `securityguard` (
  `SecurityID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) NOT NULL,
  `LastName` varchar(45) NOT NULL,
  `Shift` varchar(45) NOT NULL,
  `ContactNumber` varchar(45) NOT NULL,
  `AppPassword` varchar(45) NOT NULL,
  `Role` varchar(45) NOT NULL,
  PRIMARY KEY (`SecurityID`),
  UNIQUE KEY `SecurityID_UNIQUE` (`SecurityID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `securityguard`
--

LOCK TABLES `securityguard` WRITE;
/*!40000 ALTER TABLE `securityguard` DISABLE KEYS */;
INSERT INTO `securityguard` VALUES (1,'JAMES','KATENDE','Afternoon','098282828','OPfyjsij','SUPERVISOR'),(2,'MDUDUZI ','MABASO','Morning','0928220282','RRfgfxt','SECURITY'),(3,'INNOCENT ','NGWENYA','Afternoon','0827263377','NSlBjsDf','SECURITY'),(4,'PAMELA','MSIZA','Evening','0827261511','URxnEf','SECURITY'),(5,'MBONGENI','NGEMA','Evening','078 890 3467','Sljrf','SECURITY');
/*!40000 ALTER TABLE `securityguard` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `visitors`
--

DROP TABLE IF EXISTS `visitors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `visitors` (
  `VisitorID` int(11) NOT NULL AUTO_INCREMENT,
  `Date` varchar(45) NOT NULL,
  `TimeIn` varchar(45) NOT NULL,
  `Name` varchar(45) NOT NULL,
  `FromCompany` varchar(45) NOT NULL,
  `VehicleRegistrationNumber` varchar(45) NOT NULL,
  `ReasonForVisit` varchar(45) NOT NULL,
  `PersonToSee` varchar(45) NOT NULL,
  `TelephoneNumber` varchar(45) NOT NULL,
  PRIMARY KEY (`VisitorID`),
  UNIQUE KEY `VisitorID_UNIQUE` (`VisitorID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `visitors`
--

LOCK TABLES `visitors` WRITE;
/*!40000 ALTER TABLE `visitors` DISABLE KEYS */;
INSERT INTO `visitors` VALUES (1,'2020-06-16','6:40:00 ','ANELE NDLOVU','UJK LOGISTICS','BP90JY GP','DELIVERY','RECEPTION','011 890 6789'),(2,'2020-06-16','6:40:58 ','JADE LEWSI','ADA','JADE01 GP','MEETING','MR MDLULI','087 678 9008'),(3,'2020-06-16','6:41:55 ','MAWANDE JALI','DOH','PQ78KMGP','MEETING','HR','011 678 9098'),(4,'2020-06-16','6:42:53 ','LIAM MATTHEWS','TMO ACCOUNTANTS','MATTHEWS12 GP','MEETING','CEO ','011 228 3390'),(5,'2020-06-16','6:44:31 ','MTHOMBENI LWANELE','RISE & SHINE CLEANERS','GH 96 RX GP','CLEANING SERVICES','RECEPTION','011339 0986');
/*!40000 ALTER TABLE `visitors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'gatecontrol'
--

--
-- Dumping routines for database 'gatecontrol'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-16  9:11:15
