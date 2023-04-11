DROP DATABASE IF EXISTS todo;

CREATE DATABASE  IF NOT EXISTS `todo` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `todo`;
DROP TABLE IF EXISTS `task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `task` (
                        `id` int(11) NOT NULL AUTO_INCREMENT,
                        `description` varchar(100) NOT NULL,
                        `status` int(11) NOT NULL,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

LOCK TABLES `task` WRITE;
/*!40000 ALTER TABLE `task` DISABLE KEYS */;
INSERT  IGNORE INTO `task` VALUES (1,'aaa',1),(2,'bbb',2),(3,'ccc',0),(4,'ddd',1),(5,'eee',2),(6,'fff',0),(7,'ggg',1),(8,'hhh',2),(9,'jjj',0),(10,'kkk',1),(11,'lll',2),(12,'mmm',0),(13,'nnn',1),(14,'ooo',2),(15,'ppp',0);
/*!40000 ALTER TABLE `task` ENABLE KEYS */;
UNLOCK TABLES;