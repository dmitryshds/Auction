-- MySQL dump 10.13  Distrib 5.7.9, for Win32 (AMD64)
--
-- Host: localhost    Database: auction
-- ------------------------------------------------------
-- Server version	5.7.11-log

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
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `address` (
  `user_id` int(15) NOT NULL,
  `country` varchar(45) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `street` varchar(45) DEFAULT NULL,
  `home_number` varchar(45) DEFAULT NULL,
  UNIQUE KEY `user_id_UNIQUE` (`user_id`),
  CONSTRAINT `FK_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id_users`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (46,'UK9121','LONDON9121',NULL,NULL),(47,'UK91211','LONDON91211',NULL,NULL),(48,'test1','test211',NULL,NULL),(50,'test1','test211',NULL,NULL);
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bids`
--

DROP TABLE IF EXISTS `bids`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bids` (
  `id_bid` int(30) NOT NULL AUTO_INCREMENT,
  `item_id` int(15) NOT NULL,
  `user_id` int(15) NOT NULL,
  `bid` decimal(10,2) NOT NULL,
  `bid_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_bid`),
  UNIQUE KEY `id_bid_UNIQUE` (`id_bid`),
  KEY `fk_itemid_idx` (`item_id`),
  KEY `fk_userid_idx` (`user_id`),
  CONSTRAINT `fk_itemid` FOREIGN KEY (`item_id`) REFERENCES `items` (`id_items`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_userid` FOREIGN KEY (`user_id`) REFERENCES `users` (`id_users`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bids`
--

LOCK TABLES `bids` WRITE;
/*!40000 ALTER TABLE `bids` DISABLE KEYS */;
INSERT INTO `bids` VALUES (1,1,50,20.00,'2017-01-26 11:02:59');
/*!40000 ALTER TABLE `bids` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `id_category` int(15) NOT NULL AUTO_INCREMENT,
  `type` varchar(50) NOT NULL,
  PRIMARY KEY (`id_category`),
  UNIQUE KEY `type_UNIQUE` (`type`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (9,'Accessories'),(2,'Bonistics'),(8,'Cameras'),(7,'Copies'),(3,'Faleristics'),(5,'Numismatics'),(6,'Philately'),(4,'Uniformistika');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category_item`
--

DROP TABLE IF EXISTS `category_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category_item` (
  `category_id` int(15) NOT NULL,
  `item_id` int(15) NOT NULL,
  KEY `fk_category_id_idx` (`category_id`),
  KEY `fk_item_id_idx` (`item_id`),
  CONSTRAINT `fk_category_id` FOREIGN KEY (`category_id`) REFERENCES `category` (`id_category`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_item_id` FOREIGN KEY (`item_id`) REFERENCES `items` (`id_items`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category_item`
--

LOCK TABLES `category_item` WRITE;
/*!40000 ALTER TABLE `category_item` DISABLE KEYS */;
INSERT INTO `category_item` VALUES (2,1),(5,2),(5,3),(5,4),(5,5),(5,6),(5,7),(5,8),(5,9),(5,10),(5,11),(5,12),(5,13),(5,14),(6,15),(6,16),(6,17),(6,18),(6,19),(6,20),(6,21),(6,22),(6,23),(6,24);
/*!40000 ALTER TABLE `category_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `items`
--

DROP TABLE IF EXISTS `items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `items` (
  `id_items` int(25) NOT NULL AUTO_INCREMENT,
  `owner_id` int(15) NOT NULL,
  `name` varchar(45) NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  `initial_price` decimal(10,2) NOT NULL,
  `buynow_price` decimal(10,2) DEFAULT NULL,
  `date_start` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  `pictures` varchar(100) DEFAULT NULL,
  `date_finish` datetime NOT NULL,
  PRIMARY KEY (`id_items`),
  KEY `fk_owner_idx` (`owner_id`),
  CONSTRAINT `fk_owner` FOREIGN KEY (`owner_id`) REFERENCES `users` (`id_users`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `items`
--

LOCK TABLES `items` WRITE;
/*!40000 ALTER TABLE `items` DISABLE KEYS */;
INSERT INTO `items` VALUES (1,48,'EUR3',NULL,10.00,NULL,'2017-01-26 08:48:16.757000',NULL,'2017-02-07 00:34:57'),(2,48,'sdzrgt','ryh',20.00,30.00,'2017-01-29 14:38:56.518000',NULL,'2017-01-30 15:38:12'),(3,45,'EUR5',NULL,10.00,NULL,'2017-01-29 14:31:09.015000',NULL,'2017-02-10 06:17:49'),(4,46,'EUR55',NULL,10.00,NULL,'2017-01-29 14:32:53.108000',NULL,'2017-02-10 06:19:33'),(5,47,'MONETA 1',NULL,1.00,NULL,'2017-01-29 14:46:49.773000',NULL,'2017-02-10 06:33:30'),(6,47,'MONETA 2',NULL,2.00,NULL,'2017-01-29 14:47:07.508000',NULL,'2017-02-10 06:33:48'),(7,47,'MONETA 3',NULL,3.00,NULL,'2017-01-29 14:47:20.038000',NULL,'2017-02-10 06:34:00'),(8,47,'MONETA 4',NULL,4.00,NULL,'2017-01-29 14:47:31.479000',NULL,'2017-02-10 06:34:11'),(9,47,'MONETA 5',NULL,5.00,NULL,'2017-01-29 14:47:42.924000',NULL,'2017-02-10 06:34:23'),(10,47,'MONETA 6',NULL,6.00,NULL,'2017-01-29 14:47:54.734000',NULL,'2017-02-10 06:34:35'),(11,47,'MONETA 7',NULL,7.00,NULL,'2017-01-29 14:48:06.267000',NULL,'2017-02-10 06:34:46'),(12,47,'MONETA 8',NULL,8.00,NULL,'2017-01-29 14:48:17.710000',NULL,'2017-02-10 06:34:58'),(13,47,'MONETA 9',NULL,9.00,NULL,'2017-01-29 14:48:30.593000',NULL,'2017-02-10 06:35:11'),(14,47,'MONETA 10',NULL,10.00,NULL,'2017-01-29 14:48:41.631000',NULL,'2017-02-10 06:35:22'),(15,50,'MARKA 1',NULL,10.00,NULL,'2017-01-29 14:49:37.202000',NULL,'2017-02-10 06:36:17'),(16,50,'MARKA 2',NULL,10.00,NULL,'2017-01-29 14:49:46.526000',NULL,'2017-02-10 06:36:27'),(17,50,'MARKA 3',NULL,10.00,NULL,'2017-01-29 14:49:54.032000',NULL,'2017-02-10 06:36:34'),(18,50,'MARKA 4',NULL,10.00,NULL,'2017-01-29 14:50:01.977000',NULL,'2017-02-10 06:36:42'),(19,50,'MARKA 5',NULL,10.00,NULL,'2017-01-29 14:50:10.060000',NULL,'2017-02-10 06:36:50'),(20,50,'MARKA 6',NULL,10.00,NULL,'2017-01-29 14:50:17.516000',NULL,'2017-02-10 06:36:58'),(21,50,'MARKA 7',NULL,10.00,NULL,'2017-01-29 14:50:25.243000',NULL,'2017-02-10 06:37:05'),(22,50,'MARKA 8',NULL,10.00,NULL,'2017-01-29 14:50:33.063000',NULL,'2017-02-10 06:37:13'),(23,50,'MARKA 9',NULL,10.00,NULL,'2017-01-29 14:50:40.219000',NULL,'2017-02-10 06:37:20'),(24,50,'MARKA 10',NULL,10.00,NULL,'2017-01-29 14:50:47.603000',NULL,'2017-02-10 06:37:28');
/*!40000 ALTER TABLE `items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders` (
  `order_id` int(15) NOT NULL AUTO_INCREMENT,
  `winner_id` int(15) NOT NULL,
  `product_id` int(15) NOT NULL,
  `paid` tinyint(1) NOT NULL,
  PRIMARY KEY (`order_id`),
  KEY `FK_winner_id_idx` (`winner_id`),
  KEY `FK_product_id_idx` (`product_id`),
  CONSTRAINT `FK_product_id` FOREIGN KEY (`product_id`) REFERENCES `items` (`id_items`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_winner_id` FOREIGN KEY (`winner_id`) REFERENCES `users` (`id_users`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,50,1,0);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id_users` int(15) NOT NULL AUTO_INCREMENT,
  `login` varchar(45) NOT NULL,
  `password` varchar(128) NOT NULL,
  `psw_salt` tinyblob NOT NULL,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(50) NOT NULL,
  `validate_email` tinyint(1) NOT NULL DEFAULT '0',
  `picture` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_users`),
  UNIQUE KEY `id_users_UNIQUE` (`id_users`),
  UNIQUE KEY `login_UNIQUE` (`login`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (45,'login912','pass5912','','FIRST712','LAST7','12345912@gmail.com',0,NULL),(46,'login9121','pass59121','','FIRST7121','LAST7','123459121@gmail.com',0,NULL),(47,'login91211','pass59121','','FIRST7121','LAST7','1234591211@gmail.com',0,NULL),(48,'test','test','','test','test','test@gmail.com',0,NULL),(50,'test1','test','','test','test','test1@gmail.com',0,NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-02-11 17:08:43
