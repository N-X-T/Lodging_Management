-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: nguyenquangchien
-- ------------------------------------------------------
-- Server version	8.0.32

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
-- Table structure for table `contract_service`
--

DROP TABLE IF EXISTS `contract_service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contract_service` (
  `uuid` varchar(255) NOT NULL,
  `register_amount` int NOT NULL,
  `serivce_name` varchar(255) DEFAULT NULL,
  `contract_id` varchar(255) DEFAULT NULL,
  `service_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`uuid`),
  KEY `FKbus8la4c7apu7qnm60520jcty` (`contract_id`),
  KEY `FK55jd03xxnslogvdj3wpuy2yjt` (`service_id`),
  CONSTRAINT `FK55jd03xxnslogvdj3wpuy2yjt` FOREIGN KEY (`service_id`) REFERENCES `services` (`uuid`),
  CONSTRAINT `FKbus8la4c7apu7qnm60520jcty` FOREIGN KEY (`contract_id`) REFERENCES `contracts` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contract_service`
--

LOCK TABLES `contract_service` WRITE;
/*!40000 ALTER TABLE `contract_service` DISABLE KEYS */;
INSERT INTO `contract_service` VALUES ('78fcbfd3-ccbc-4f29-b697-db43dade8378',2,'Vệ sinh riêng','8a814af4-b334-4221-adbe-35b25c367709','664514c7-f89d-47ea-8ed1-164db2859785'),('cc649ef1-7b0e-4ba1-901b-7ca93028c070',2,'Gửi xe','8a814af4-b334-4221-adbe-35b25c367709','1efb800c-2411-4141-9ef6-9f61caa45ef4');
/*!40000 ALTER TABLE `contract_service` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contracts`
--

DROP TABLE IF EXISTS `contracts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contracts` (
  `uuid` varchar(255) NOT NULL,
  `deposit` int NOT NULL,
  `from_date` date DEFAULT NULL,
  `rent_cost_per_month` int NOT NULL,
  `status` bit(1) NOT NULL,
  `to_date` date DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `signer` varchar(255) DEFAULT NULL,
  `room_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`uuid`),
  UNIQUE KEY `UK_dqivkd5xvy5suvt169ld5p15f` (`signer`),
  UNIQUE KEY `UK_l45n3uex7576dtlo4bgfln2ii` (`room_id`),
  CONSTRAINT `FKju1b0xobla9t8oexrb8lpi8jq` FOREIGN KEY (`room_id`) REFERENCES `rooms` (`uuid`),
  CONSTRAINT `FKohqw60cvc5lv66ewkfb8ws0wh` FOREIGN KEY (`signer`) REFERENCES `residents` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contracts`
--

LOCK TABLES `contracts` WRITE;
/*!40000 ALTER TABLE `contracts` DISABLE KEYS */;
INSERT INTO `contracts` VALUES ('8a814af4-b334-4221-adbe-35b25c367709',1000000,'2024-01-31',5000000,_binary '','2024-03-01','long-term','a45939ae-0b7c-48f2-bfba-c92f03ee4504','0cbd1e9d-9c6d-489b-a0e5-5ee620ccc4e1');
/*!40000 ALTER TABLE `contracts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feedbacks`
--

DROP TABLE IF EXISTS `feedbacks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `feedbacks` (
  `uuid` varchar(255) NOT NULL,
  `content` varchar(255) DEFAULT NULL,
  `create_time` datetime(6) DEFAULT NULL,
  `room` int NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `resident_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`uuid`),
  KEY `FK7tstvnlcpf2w0qfgrwha931oq` (`resident_id`),
  CONSTRAINT `FK7tstvnlcpf2w0qfgrwha931oq` FOREIGN KEY (`resident_id`) REFERENCES `residents` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feedbacks`
--

LOCK TABLES `feedbacks` WRITE;
/*!40000 ALTER TABLE `feedbacks` DISABLE KEYS */;
INSERT INTO `feedbacks` VALUES ('09e760e0-2413-4a60-979d-05f97a091c10','hehe','2024-01-16 21:27:54.864000',0,'pending','hehe','a45939ae-0b7c-48f2-bfba-c92f03ee4504'),('ddf4e6b7-3f83-4131-90fd-796948b792bb','hehe','2024-01-16 20:36:05.815000',0,'pending','hehe','a45939ae-0b7c-48f2-bfba-c92f03ee4504');
/*!40000 ALTER TABLE `feedbacks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invoices`
--

DROP TABLE IF EXISTS `invoices`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `invoices` (
  `uuid` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `time` date DEFAULT NULL,
  `contract_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`uuid`),
  KEY `FKeads7q9fktwtsgdwmp1x16eqc` (`contract_id`),
  CONSTRAINT `FKeads7q9fktwtsgdwmp1x16eqc` FOREIGN KEY (`contract_id`) REFERENCES `contracts` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoices`
--

LOCK TABLES `invoices` WRITE;
/*!40000 ALTER TABLE `invoices` DISABLE KEYS */;
INSERT INTO `invoices` VALUES ('3cf0b94e-4ab6-463f-a246-5823e1f246e8','Hóa đơn tháng 01 - 2024','success','2024-01-16','8a814af4-b334-4221-adbe-35b25c367709'),('9eb61866-59c9-4cb2-a44b-a0970d499c09','Hóa đơn tháng 12 - 2023','new','2023-12-13','8a814af4-b334-4221-adbe-35b25c367709');
/*!40000 ALTER TABLE `invoices` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchases`
--

DROP TABLE IF EXISTS `purchases`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `purchases` (
  `uuid` varchar(255) NOT NULL,
  `amount` int NOT NULL,
  `note` varchar(255) DEFAULT NULL,
  `time_record` datetime DEFAULT NULL,
  `room_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`uuid`),
  KEY `FKfrmuvqqtv9k1sjaj5ui8wyxyj` (`room_id`),
  CONSTRAINT `FKfrmuvqqtv9k1sjaj5ui8wyxyj` FOREIGN KEY (`room_id`) REFERENCES `rooms` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchases`
--

LOCK TABLES `purchases` WRITE;
/*!40000 ALTER TABLE `purchases` DISABLE KEYS */;
/*!40000 ALTER TABLE `purchases` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `residents`
--

DROP TABLE IF EXISTS `residents`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `residents` (
  `uuid` varchar(255) NOT NULL,
  `citizen_id` varchar(255) DEFAULT NULL,
  `date_of_birth` date DEFAULT NULL,
  `first_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `last_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `owner` bit(1) NOT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `room` int NOT NULL,
  `contract_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`uuid`),
  UNIQUE KEY `UK_d5bqx4j713t9uhqvk49e3t5xm` (`contract_id`),
  CONSTRAINT `FKlkrc20aolid9fm1tahw5fmt5x` FOREIGN KEY (`contract_id`) REFERENCES `contracts` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `residents`
--

LOCK TABLES `residents` WRITE;
/*!40000 ALTER TABLE `residents` DISABLE KEYS */;
INSERT INTO `residents` VALUES ('a45939ae-0b7c-48f2-bfba-c92f03ee4504','000000000','2024-01-02','Thắng','Nguyễn',_binary '','0818358449',0,NULL);
/*!40000 ALTER TABLE `residents` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rooms`
--

DROP TABLE IF EXISTS `rooms`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rooms` (
  `uuid` varchar(255) NOT NULL,
  `number` int NOT NULL,
  `number_of_ac` int NOT NULL,
  `number_of_bed` int NOT NULL,
  `number_of_desk` int NOT NULL,
  `number_of_fridge` int NOT NULL,
  `type_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`uuid`),
  KEY `FKpo09o377969rruwrslijf7gtq` (`type_id`),
  CONSTRAINT `FKpo09o377969rruwrslijf7gtq` FOREIGN KEY (`type_id`) REFERENCES `roomtypes` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rooms`
--

LOCK TABLES `rooms` WRITE;
/*!40000 ALTER TABLE `rooms` DISABLE KEYS */;
INSERT INTO `rooms` VALUES ('0cbd1e9d-9c6d-489b-a0e5-5ee620ccc4e1',1,1,2,1,1,'1655c746-a6de-45cf-899c-a5a26941daa7'),('7c1ea06a-0eea-4804-a698-295dac2f2e69',22,1,2,0,0,'d4f6bb85-d83c-44d1-9d19-9eb1ef9ec63c');
/*!40000 ALTER TABLE `rooms` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roomtypes`
--

DROP TABLE IF EXISTS `roomtypes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roomtypes` (
  `uuid` varchar(255) NOT NULL,
  `area` float NOT NULL,
  `default_number_of_ac` int NOT NULL,
  `default_number_of_bed` int NOT NULL,
  `default_number_of_desk` int NOT NULL,
  `default_number_of_fridge` int NOT NULL,
  `default_rent_cost` int NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roomtypes`
--

LOCK TABLES `roomtypes` WRITE;
/*!40000 ALTER TABLE `roomtypes` DISABLE KEYS */;
INSERT INTO `roomtypes` VALUES ('1655c746-a6de-45cf-899c-a5a26941daa7',40,1,2,1,1,5000000,'VIP'),('4881e750-4d83-425b-b320-ed3f589434a6',25,1,2,1,1,3000000,'LUX'),('d4f6bb85-d83c-44d1-9d19-9eb1ef9ec63c',20,1,2,0,0,2000000,'STA');
/*!40000 ALTER TABLE `roomtypes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service_index`
--

DROP TABLE IF EXISTS `service_index`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service_index` (
  `uuid` varchar(255) NOT NULL,
  `index_price` int DEFAULT NULL,
  `time_record` datetime DEFAULT NULL,
  `room_id` varchar(255) DEFAULT NULL,
  `service_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`uuid`),
  KEY `FKaog5xc9dishn865bgmy9wpdjs` (`room_id`),
  KEY `FK22jar1m79qsxygeud75yywloc` (`service_id`),
  CONSTRAINT `FK22jar1m79qsxygeud75yywloc` FOREIGN KEY (`service_id`) REFERENCES `services` (`uuid`),
  CONSTRAINT `FKaog5xc9dishn865bgmy9wpdjs` FOREIGN KEY (`room_id`) REFERENCES `rooms` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service_index`
--

LOCK TABLES `service_index` WRITE;
/*!40000 ALTER TABLE `service_index` DISABLE KEYS */;
INSERT INTO `service_index` VALUES ('2ddd10e2-86af-47f5-b440-7f1bc1989b34',100,'2024-01-12 14:32:09','0cbd1e9d-9c6d-489b-a0e5-5ee620ccc4e1','fddb21bf-62b9-4e79-a035-8947bd6ebffa');
/*!40000 ALTER TABLE `service_index` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `services`
--

DROP TABLE IF EXISTS `services`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `services` (
  `uuid` varchar(255) NOT NULL,
  `calculation_method` varchar(255) DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `price` int NOT NULL,
  `type` varchar(255) DEFAULT NULL,
  `unit` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `services`
--

LOCK TABLES `services` WRITE;
/*!40000 ALTER TABLE `services` DISABLE KEYS */;
INSERT INTO `services` VALUES ('0574bd96-ec49-477d-a581-7aeeaec72772','sub-index','Nước',20000,'default','mét khối'),('1efb800c-2411-4141-9ef6-9f61caa45ef4','once','Gửi xe',3000,'option','xe/tháng'),('664514c7-f89d-47ea-8ed1-164db2859785','once','Vệ sinh riêng',100000,'option','phòng/tháng'),('a1e19072-c98c-45fe-b38b-412afeb31b2a','once','Internet',50000,'default','phòng/tháng'),('d06ddf97-d6ce-48d8-8730-de3cbe397260','sum','Giặt là',12000,'default','kilogram (kg)'),('e2f30f97-e0e3-413d-b69d-72363c3668af','once','Dịch vụ chung',1000,'default','phòng/tháng'),('e6d8b5d7-f973-49bf-a995-a8131fdad8c1','once','Rác thải',20000,'default','người/tháng'),('fddb21bf-62b9-4e79-a035-8947bd6ebffa','sub-index','Điện',3500,'default','kilowatt-hour (kWh)');
/*!40000 ALTER TABLE `services` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `uuid` varchar(255) NOT NULL,
  `admin_id` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `resident_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`uuid`),
  UNIQUE KEY `UK_r43af9ap4edm43mmtq01oddj6` (`username`),
  UNIQUE KEY `UK_b4ru4w87o5p68gmlfea2tyrox` (`resident_id`),
  CONSTRAINT `FKs636ei7elg4xt3gucywf0llb0` FOREIGN KEY (`resident_id`) REFERENCES `residents` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('722e238b-015a-41f7-93cd-90e41d519e05',NULL,'user','resident','user','a45939ae-0b7c-48f2-bfba-c92f03ee4504'),('8cf79b49-2056-4017-8df1-1a29b8e62b48',NULL,'admin','admin','admin',NULL);
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

-- Dump completed on 2024-01-16 21:57:21
