CREATE DATABASE  IF NOT EXISTS `clothes` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `clothes`;
-- MySQL dump 10.13  Distrib 5.7.12, for Win32 (AMD64)
--
-- Host: localhost    Database: clothes
-- ------------------------------------------------------
-- Server version	5.7.19

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categories` (
  `categoryID` int(11) NOT NULL,
  `categoryName` varchar(70) NOT NULL,
  PRIMARY KEY (`categoryID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (1,'Formal Wear'),(2,'Casual Wear'),(3,'Corporate Wear'),(4,'Everyday Wear'),(5,'Sports Wear');
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productlines`
--

DROP TABLE IF EXISTS `productlines`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `productlines` (
  `productLine` varchar(25) NOT NULL,
  `description` varchar(250) NOT NULL,
  PRIMARY KEY (`productLine`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productlines`
--

LOCK TABLES `productlines` WRITE;
/*!40000 ALTER TABLE `productlines` DISABLE KEYS */;
INSERT INTO `productlines` VALUES ('Hoodie','Our hoodie product is made up of 100& cotton available for all customers. It comes also with variety of size, colors and designs.'),('Polo Shirt','A close fitting pullover often knit shirt with short or long sleeves and turn over color.'),('Shorts','Quick driying microfiber fabric and 100% cotton mesh linning salt water and chrine resistant and UV protected braiding draw cord.'),('Slacks','Soft and silky feeling when worn. Made from the most luxurious comfortable and absorbent fabric. It traps air and provides warmth in the winter and blissfully cool and breathable in the summer.'),('T-Shirt','Fabric shirt with t shape from body and sleeves. It is made of light, inexpensive fabric and are easy to clean.');
/*!40000 ALTER TABLE `productlines` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `products` (
  `productCode` varchar(15) NOT NULL,
  `productName` varchar(70) NOT NULL,
  `genderType` char(1) NOT NULL,
  `materialType` varchar(125) NOT NULL,
  `productSize` varchar(15) NOT NULL,
  `productLine` varchar(15) NOT NULL,
  `supplierID` int(11) NOT NULL,
  `categoryID` int(11) NOT NULL,
  PRIMARY KEY (`productCode`),
  KEY `fk_products_category_idx` (`categoryID`),
  KEY `fk_products_suppliers1_idx` (`supplierID`),
  KEY `fk_products_productlines1_idx` (`productLine`),
  CONSTRAINT `categoryID` FOREIGN KEY (`categoryID`) REFERENCES `categories` (`categoryID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `productLine` FOREIGN KEY (`productLine`) REFERENCES `productlines` (`productLine`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `supplierID` FOREIGN KEY (`supplierID`) REFERENCES `suppliers` (`supplierID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES ('P01','NikaNike','F','Silk','XS','Slacks',104,1),('P02','IvanRun','M','Nylon','L','T-Shirt',103,2),('P03','AngeHood','U','Cotton','M','Hoodie',102,3),('P04','IvyRun','F','Linen Fabric','S','Shorts',101,4),('P05','MikeNike','M','Nylon','L','Polo Shirt',105,5);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `suppliers`
--

DROP TABLE IF EXISTS `suppliers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `suppliers` (
  `supplierID` int(11) NOT NULL,
  `supplierName` varchar(125) NOT NULL,
  `supplierLocation` varchar(250) NOT NULL,
  PRIMARY KEY (`supplierID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `suppliers`
--

LOCK TABLES `suppliers` WRITE;
/*!40000 ALTER TABLE `suppliers` DISABLE KEYS */;
INSERT INTO `suppliers` VALUES (101,'Branden Salumbidez','Rm. 206 Padilla Delos Reyes Building, 232 Juan Luna Street, Binondo, Manila, Philippines'),(102,'Shyann Sanderson','55 Pershing Subic Bay, Freeport Zone Zambales, Zambales, Philippines'),(103,'Lincoln Diwata','855 T. M. Kalaw Street Ermita 1004, Manila, Philippines'),(104,'Hannah Viridiana','Unit 78 Gracepark Commercial Complex 4th Avenue 1403, Caloocan City, Philippines'),(105,'Frasco Magbanua','2/F Adamson Centre Building 121 Alfaro Street Salcedo Village 1200, Makati City, Manila, Philippines');
/*!40000 ALTER TABLE `suppliers` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-12-12  6:05:12
