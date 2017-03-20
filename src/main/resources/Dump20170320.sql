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
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bids`
--

LOCK TABLES `bids` WRITE;
/*!40000 ALTER TABLE `bids` DISABLE KEYS */;
INSERT INTO `bids` VALUES (8,55,144,20.00,'2017-03-07 22:49:32'),(9,55,144,30.00,'2017-03-07 22:49:37'),(10,55,144,35.00,'2017-03-07 22:49:44'),(11,55,144,41.00,'2017-03-07 22:49:50'),(12,55,144,48.00,'2017-03-07 23:19:18'),(13,55,144,85.00,'2017-03-07 23:19:24'),(14,55,144,51.00,'2017-03-07 23:19:35'),(15,55,144,85.10,'2017-03-08 10:43:13'),(16,56,152,55.00,'2017-03-11 18:07:23'),(17,55,152,86.00,'2017-03-11 18:12:59'),(18,56,149,60.00,'2017-03-11 19:00:51'),(19,56,152,61.00,'2017-03-12 12:09:43');
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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
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
  `category_id` int(15) NOT NULL,
  `date_finish` datetime NOT NULL,
  `state` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id_items`),
  KEY `fk_owner_idx` (`owner_id`),
  KEY `fk_category_id` (`category_id`),
  CONSTRAINT `fk_category_id` FOREIGN KEY (`category_id`) REFERENCES `category` (`id_category`),
  CONSTRAINT `fk_owner` FOREIGN KEY (`owner_id`) REFERENCES `users` (`id_users`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `items`
--

LOCK TABLES `items` WRITE;
/*!40000 ALTER TABLE `items` DISABLE KEYS */;
INSERT INTO `items` VALUES (55,144,'Accessories1','Accessories1',10.00,NULL,'2017-03-11 15:32:25.673765',9,'2017-03-31 21:27:57','Active'),(56,144,'Accessories2','Accessories2',30.00,120.00,'2017-03-11 19:35:40.226093',9,'2017-03-13 17:48:36','INACTIVE');
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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (8,152,56,0);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `persistent_logins`
--

DROP TABLE IF EXISTS `persistent_logins`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `persistent_logins` (
  `username` varchar(64) NOT NULL,
  `series` varchar(64) NOT NULL,
  `token` varchar(64) NOT NULL,
  `last_used` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`series`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persistent_logins`
--

LOCK TABLES `persistent_logins` WRITE;
/*!40000 ALTER TABLE `persistent_logins` DISABLE KEYS */;
INSERT INTO `persistent_logins` VALUES ('Admin','GlUVt2gLQVjDzylKYIzTZw==','Sskz4+EBgjKSMH5+Oje7lg==','2017-03-20 19:43:14');
/*!40000 ALTER TABLE `persistent_logins` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pictures`
--

DROP TABLE IF EXISTS `pictures`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pictures` (
  `id_item` int(15) NOT NULL,
  `path` varchar(45) NOT NULL,
  KEY `id_item_idx` (`id_item`),
  CONSTRAINT `id_item` FOREIGN KEY (`id_item`) REFERENCES `items` (`id_items`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pictures`
--

LOCK TABLES `pictures` WRITE;
/*!40000 ALTER TABLE `pictures` DISABLE KEYS */;
INSERT INTO `pictures` VALUES (55,'/144/144_55_0.jpg'),(55,'/144/144_55_1.jpg'),(55,'/144/144_55_2.jpg'),(55,'/144/144_55_3.jpg'),(56,'/144/144_56_0.jpg'),(56,'/144/144_56_1.jpg');
/*!40000 ALTER TABLE `pictures` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `profile`
--

DROP TABLE IF EXISTS `profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `profile` (
  `id` int(15) NOT NULL AUTO_INCREMENT,
  `type` varchar(30) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `type` (`type`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profile`
--

LOCK TABLES `profile` WRITE;
/*!40000 ALTER TABLE `profile` DISABLE KEYS */;
INSERT INTO `profile` VALUES (2,'ADMIN'),(4,'ANONIM'),(3,'DBA'),(1,'USER');
/*!40000 ALTER TABLE `profile` ENABLE KEYS */;
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
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(50) NOT NULL,
  `validate_email` tinyint(1) NOT NULL DEFAULT '0',
  `picture` varchar(50) DEFAULT NULL,
  `state` varchar(30) DEFAULT NULL,
  `country` varchar(45) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `zip` varchar(45) DEFAULT NULL,
  `street` varchar(45) DEFAULT NULL,
  `home_number` varchar(45) DEFAULT NULL,
  `title` varchar(3) DEFAULT NULL,
  PRIMARY KEY (`id_users`),
  UNIQUE KEY `id_users_UNIQUE` (`id_users`),
  UNIQUE KEY `login_UNIQUE` (`login`)
) ENGINE=InnoDB AUTO_INCREMENT=155 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (144,'Dmitry','$2a$10$xmjYDmOXNQCxQpR9NfZByul3jlxniiDzapbRkPecTIi8PQP.1US.6','Dmitry','Shainogo','0675009780@ukr.net',0,'C:\\AuctionImages\\144\\144_avat.jpg','Active','Ukraine','Kiev','04114','Vishgorodskaja','123-456-789','0'),(149,'Admin','$2a$06$MDVWtKpk1vmgqqab6og6.OYG029AZurUFQHdFzPUwAgvkgKtrI8LG','Admin','Admin','info.auction2017@gmail.com',1,NULL,'Active',NULL,NULL,NULL,NULL,NULL,'0'),(150,'aadmin','$2a$10$lsSmh6dPWXcLLiVxqtE9qeVtFK2kShepVKCor99Z/aXmmXTvFVRDe','','','0675009780@ukr.net',0,'C:\\AuctionImages\\150\\150_avat.jpg','Deleted','','','','','','0'),(152,'Dmitryq','$2a$06$qmVxGkomXKBiVtUEkrjpf..rEUt8cXa0aZEOsGi62tZ2knGNPXDJi','','','0675009780@ukr.net',1,'C:\\AuctionImages\\152\\152_avat.jpg','Active','','','','','','0');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_profile`
--

DROP TABLE IF EXISTS `users_profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users_profile` (
  `id_users` int(15) NOT NULL,
  `id_profile` int(15) NOT NULL,
  KEY `users_profile_profile_id_fk` (`id_profile`),
  KEY `users_profile_users_id_users_fk` (`id_users`),
  CONSTRAINT `users_profile_profile_id_fk` FOREIGN KEY (`id_profile`) REFERENCES `profile` (`id`),
  CONSTRAINT `users_profile_users_id_users_fk` FOREIGN KEY (`id_users`) REFERENCES `users` (`id_users`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_profile`
--

LOCK TABLES `users_profile` WRITE;
/*!40000 ALTER TABLE `users_profile` DISABLE KEYS */;
INSERT INTO `users_profile` VALUES (144,1),(149,2),(152,1);
/*!40000 ALTER TABLE `users_profile` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-03-20 21:52:49
