-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: test2
-- ------------------------------------------------------
-- Server version	8.2.0

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
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `tracked_task` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_6dotkott2kjsp8vw4d0m25fb7` (`email`),
  UNIQUE KEY `UK_r43af9ap4edm43mmtq01oddj6` (`username`),
  KEY `FKde7umpr8v0xteg58i6ea8fppo` (`tracked_task`),
  CONSTRAINT `FKde7umpr8v0xteg58i6ea8fppo` FOREIGN KEY (`tracked_task`) REFERENCES `tasks` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'tuna3@gmail.com','tuna3','$2a$10$HHSMbSJyhyPk0GhRHzLkJOel60HTbnNqN4Zu2yns2bghVs6/z.9.C','tuna',102),(2,'admin@gmail.com','admin','$2a$10$gqHrslMttQWSsDSVRTK1OehkkBiXsJ/a4z2OURU./dizwOQu5Lovu','admin',NULL),(3,'ahmet@123.com','ahmet','$2a$10$B.wt0nLquCtWgMuItCRdd.vtfreB.HHc0caqTFwqBEmiuGep5he4m','ahmet',252),(4,'john@123.com','john','$2a$10$dVfgFQBiKLfokdlEJd/TbOVnhLekPiOWSK73LtqBNfYY2qpS82rjW','john',NULL),(5,'abc@abc.com','abc','$2a$10$Y9pG4eleNqs1bJ0gCErI5ufAKDarVCIPMFDzmaQm5e3JahWqZV1tG','abc',NULL),(6,'zeyno@gmail.com','zeyno','$2a$10$q4mOiLD.EaNEedIeye4KMuYQZph.6W1WtjRTU47Cu62kPYagjRW3u','zeyno',NULL),(10,'emine@gmail.com','emine','$2a$10$pPhzL2.gDPmnQOexVooj4O4NILSeA9PNOUy1yU5k5SwoDaXrXFRjG','emine',102),(11,'testuser@gmail.com','testuser','$2a$10$tjB5PAhQEno.bk2Nfxjdb.CsJM8GKYga3m2w94M2ERPTkmOdSZR42','testuser',NULL),(12,'mehmet','mehmet','$2a$10$8SMTN/eeND0rREz0PpfW2.a/qKBeZQ3LCL9y6kSRSVB4oFyqN9kZ.','mehmet',NULL),(13,'emine','tunahan','$2a$10$954awCwJ7LwMr08S6Uk1W.vvWy0ycXEZ.SgyLQImw7hKq8IlcjA7q','tunahan',402);
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

-- Dump completed on 2025-04-19 16:17:33
