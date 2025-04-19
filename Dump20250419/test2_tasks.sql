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
-- Table structure for table `tasks`
--

DROP TABLE IF EXISTS `tasks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tasks` (
  `id` bigint NOT NULL,
  `due_date` date DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `finalsp` int DEFAULT NULL,
  `analyst_id` int DEFAULT NULL,
  `developer_id` int DEFAULT NULL,
  `event_id` bigint DEFAULT NULL,
  `is_starred` bit(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK79xq68w2vf505njniub38evee` (`analyst_id`),
  KEY `FK79r3ikp3utt1gvnk7j2s3asvs` (`developer_id`),
  KEY `FKso3gaw278lqccjow3yxkck6wx` (`event_id`),
  CONSTRAINT `FK79r3ikp3utt1gvnk7j2s3asvs` FOREIGN KEY (`developer_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FK79xq68w2vf505njniub38evee` FOREIGN KEY (`analyst_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKso3gaw278lqccjow3yxkck6wx` FOREIGN KEY (`event_id`) REFERENCES `events` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tasks`
--

LOCK TABLES `tasks` WRITE;
/*!40000 ALTER TABLE `tasks` DISABLE KEYS */;
INSERT INTO `tasks` VALUES (53,'2024-11-12','testing123','todo',5,3,1,6,_binary '\0'),(102,'2024-11-13','abc','todo',8,2,10,6,_binary ''),(152,'2024-11-15','eventtest','overdue',5,1,1,8,_binary '\0'),(252,'2024-11-12','testTask','done',6,3,1,202,_binary ''),(352,'2024-11-13','testTask123','todo',4,5,1,202,_binary '\0'),(402,'2025-04-21','metoyuttn','todo',5,13,1,152,_binary '');
/*!40000 ALTER TABLE `tasks` ENABLE KEYS */;
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
