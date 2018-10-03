# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.5.5-10.1.26-MariaDB)
# Database: Students
# Generation Time: 2018-07-30 22:26:57 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table Admin
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Admin`;

CREATE TABLE `Admin` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `Name` varchar(25) DEFAULT NULL,
  `Password` varchar(25) DEFAULT NULL,
  `Type` enum('admin') DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `Admin` WRITE;
/*!40000 ALTER TABLE `Admin` DISABLE KEYS */;

INSERT INTO `Admin` (`id`, `Name`, `Password`, `Type`)
VALUES
	(1,'Dafne','12345','admin'),
	(3,'Erico','er321','admin');

/*!40000 ALTER TABLE `Admin` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table Course
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Course`;

CREATE TABLE `Course` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `Name` varchar(15) DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `FK` (`id`),
  KEY `FOGK` (`Name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `Course` WRITE;
/*!40000 ALTER TABLE `Course` DISABLE KEYS */;

INSERT INTO `Course` (`id`, `Name`)
VALUES
	(30,'chines'),
	(1,'english'),
	(3,'french'),
	(6,'italian'),
	(20,'polish'),
	(4,'portuguse'),
	(2,'spanish');

/*!40000 ALTER TABLE `Course` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table Student
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Student`;

CREATE TABLE `Student` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `Name` varchar(30) DEFAULT NULL,
  `Address` varchar(30) DEFAULT NULL,
  `Nationality` varchar(25) DEFAULT NULL,
  `Password` varchar(25) DEFAULT NULL,
  `Courses` varchar(15) DEFAULT '',
  `Type` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK` (`Courses`),
  CONSTRAINT `student_ibfk_1` FOREIGN KEY (`Courses`) REFERENCES `Course` (`Name`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `Student` WRITE;
/*!40000 ALTER TABLE `Student` DISABLE KEYS */;

INSERT INTO `Student` (`id`, `Name`, `Address`, `Nationality`, `Password`, `Courses`, `Type`)
VALUES
	(1,'Fernando','19, PhilipSburgh Avenue','Brazilian','1234','english','student'),
	(2,'Andres','20, Parnell Street','Colombian','an123','english','student');

/*!40000 ALTER TABLE `Student` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
