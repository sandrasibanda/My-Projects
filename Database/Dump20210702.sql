-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: localhost    Database: burial_society_database
-- ------------------------------------------------------
-- Server version	8.0.12

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `beneficiaries`
--

DROP TABLE IF EXISTS `beneficiaries`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `beneficiaries` (
  `IDNum` varchar(13) NOT NULL,
  `LName` varchar(45) NOT NULL,
  `FName` varchar(45) NOT NULL,
  `Relationship` varchar(45) NOT NULL,
  `PM_ID_Num` varchar(13) NOT NULL,
  KEY `fk_Principal_idx` (`PM_ID_Num`),
  CONSTRAINT `fk_Principal` FOREIGN KEY (`PM_ID_Num`) REFERENCES `principal_member` (`id_num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `beneficiaries`
--

LOCK TABLES `beneficiaries` WRITE;
/*!40000 ALTER TABLE `beneficiaries` DISABLE KEYS */;
/*!40000 ALTER TABLE `beneficiaries` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `covers`
--

DROP TABLE IF EXISTS `covers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `covers` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CoverAmount` varchar(45) NOT NULL,
  `Category` varchar(45) NOT NULL,
  `Premium` varchar(45) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID_UNIQUE` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `covers`
--

LOCK TABLES `covers` WRITE;
/*!40000 ALTER TABLE `covers` DISABLE KEYS */;
INSERT INTO `covers` VALUES (1,'15000','1 - 5 years','120'),(2,'15000','1 - 9 years','170'),(3,'15000','1 - 13 years','240'),(4,'20000','1 - 5 years','200'),(5,'20000','1 - 9 years','290'),(6,'20000','1 - 13 years','390'),(7,'25000','1 - 5 years','270'),(8,'25000','1 - 9 years','360'),(9,'25000','1 - 13 years','490');
/*!40000 ALTER TABLE `covers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `principal_member`
--

DROP TABLE IF EXISTS `principal_member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `principal_member` (
  `ID_Num` varchar(13) NOT NULL,
  `LName` varchar(45) NOT NULL,
  `FName` varchar(45) NOT NULL,
  `DOB` varchar(45) NOT NULL,
  `Address` varchar(45) NOT NULL,
  `Tel` varchar(45) NOT NULL,
  `Email` varchar(45) NOT NULL,
  `CoverID` int(11) NOT NULL,
  `Login` int(11) NOT NULL,
  PRIMARY KEY (`ID_Num`),
  KEY `fk_Cover` (`CoverID`),
  KEY `fk_Login` (`Login`),
  CONSTRAINT `fk_Cover` FOREIGN KEY (`CoverID`) REFERENCES `covers` (`id`),
  CONSTRAINT `fk_Login` FOREIGN KEY (`Login`) REFERENCES `users_login` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `principal_member`
--

LOCK TABLES `principal_member` WRITE;
/*!40000 ALTER TABLE `principal_member` DISABLE KEYS */;
/*!40000 ALTER TABLE `principal_member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_login`
--

DROP TABLE IF EXISTS `users_login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users_login` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Username` varchar(45) NOT NULL,
  `Password` varchar(45) NOT NULL,
  `Role` varchar(45) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_login`
--

LOCK TABLES `users_login` WRITE;
/*!40000 ALTER TABLE `users_login` DISABLE KEYS */;
INSERT INTO `users_login` VALUES (2,'Patricia','7@##','Client'),(3,'Portia','Ijrnywn','Administrator'),(4,'Shikwamba','XmnpfsyBj','Client'),(5,'Vuyo','67890','Client');
/*!40000 ALTER TABLE `users_login` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-07-02 10:20:12
