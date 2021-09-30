-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.0.45-community-nt


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema creckett
--

CREATE DATABASE IF NOT EXISTS creckett;
USE creckett;

--
-- Definition of table `admin_info`
--

DROP TABLE IF EXISTS `admin_info`;
CREATE TABLE `admin_info` (
  `USER_NAME` varchar(255) NOT NULL,
  `PASSWORD` varchar(255) default NULL,
  PRIMARY KEY  (`USER_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admin_info`
--

/*!40000 ALTER TABLE `admin_info` DISABLE KEYS */;
INSERT INTO `admin_info` (`USER_NAME`,`PASSWORD`) VALUES 
 ('amitmeshram','726f6e616c646f39');
/*!40000 ALTER TABLE `admin_info` ENABLE KEYS */;


--
-- Definition of table `amount_left`
--

DROP TABLE IF EXISTS `amount_left`;
CREATE TABLE `amount_left` (
  `ID` bigint(20) NOT NULL auto_increment,
  `LEFT_AMOUNT` int(11) default NULL,
  `MATCH_OVER` int(11) default NULL,
  `SESSION_ID` int(11) default NULL,
  `MARKET_ID` bigint(20) default NULL,
  `MATCH_ID` bigint(20) default NULL,
  `USER_ID` bigint(20) default NULL,
  PRIMARY KEY  (`ID`),
  KEY `FK39E6038E5C828C79` (`MARKET_ID`),
  KEY `FK39E6038E892EC97D` (`MATCH_ID`),
  KEY `FK39E6038E8FC631D9` (`USER_ID`),
  CONSTRAINT `FK39E6038E8FC631D9` FOREIGN KEY (`USER_ID`) REFERENCES `user` (`ID`),
  CONSTRAINT `FK39E6038E5C828C79` FOREIGN KEY (`MARKET_ID`) REFERENCES `market` (`ID`),
  CONSTRAINT `FK39E6038E892EC97D` FOREIGN KEY (`MATCH_ID`) REFERENCES `match_master` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `amount_left`
--

/*!40000 ALTER TABLE `amount_left` DISABLE KEYS */;
INSERT INTO `amount_left` (`ID`,`LEFT_AMOUNT`,`MATCH_OVER`,`SESSION_ID`,`MARKET_ID`,`MATCH_ID`,`USER_ID`) VALUES 
 (1,539,10,1,1,1,2),
 (2,484,10,1,1,1,1),
 (3,511,1,2,2,2,2),
 (4,491,1,2,2,2,1),
 (5,502,7,1,3,3,3),
 (6,486,7,1,3,3,2),
 (7,473,6,2,4,4,2),
 (8,494,6,2,4,4,3),
 (10,525,6,1,6,5,4),
 (11,485,6,1,6,5,2),
 (12,500,0,1,7,7,2),
 (14,483,3,2,9,6,2),
 (17,512,3,2,9,6,5),
 (18,482,3,2,9,6,4),
 (19,500,0,1,7,7,5);
/*!40000 ALTER TABLE `amount_left` ENABLE KEYS */;


--
-- Definition of table `archived_amount_left`
--

DROP TABLE IF EXISTS `archived_amount_left`;
CREATE TABLE `archived_amount_left` (
  `ID` bigint(20) NOT NULL auto_increment,
  `LEFT_AMOUNT` int(11) default NULL,
  `MATCH_OVER` int(11) default NULL,
  `SESSION_ID` int(11) default NULL,
  `MARKET_ID` bigint(20) default NULL,
  `MATCH_ID` bigint(20) default NULL,
  `USER_ID` bigint(20) default NULL,
  PRIMARY KEY  (`ID`),
  KEY `FKA554C6B15C828C79` (`MARKET_ID`),
  KEY `FKA554C6B1892EC97D` (`MATCH_ID`),
  KEY `FKA554C6B18FC631D9` (`USER_ID`),
  CONSTRAINT `FKA554C6B18FC631D9` FOREIGN KEY (`USER_ID`) REFERENCES `user` (`ID`),
  CONSTRAINT `FKA554C6B15C828C79` FOREIGN KEY (`MARKET_ID`) REFERENCES `market` (`ID`),
  CONSTRAINT `FKA554C6B1892EC97D` FOREIGN KEY (`MATCH_ID`) REFERENCES `match_master` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=126 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `archived_amount_left`
--

/*!40000 ALTER TABLE `archived_amount_left` DISABLE KEYS */;
INSERT INTO `archived_amount_left` (`ID`,`LEFT_AMOUNT`,`MATCH_OVER`,`SESSION_ID`,`MARKET_ID`,`MATCH_ID`,`USER_ID`) VALUES 
 (1,500,0,1,1,1,1),
 (2,500,0,1,1,1,2),
 (3,500,1,1,1,1,2),
 (4,500,1,1,1,1,1),
 (5,505,2,1,1,1,2),
 (6,489,2,1,1,1,1),
 (7,515,3,1,1,1,2),
 (8,496,3,1,1,1,1),
 (9,491,4,1,1,1,1),
 (10,515,4,1,1,1,2),
 (11,485,5,1,1,1,1),
 (12,520,5,1,1,1,2),
 (13,524,6,1,1,1,2),
 (14,475,6,1,1,1,1),
 (15,534,7,1,1,1,2),
 (16,485,8,1,1,1,1),
 (17,539,8,1,1,1,2),
 (18,490,9,1,1,1,1),
 (19,500,0,1,2,2,2),
 (20,500,0,1,2,2,1),
 (21,505,1,1,2,2,2),
 (22,489,1,1,2,2,1),
 (23,501,2,1,2,2,2),
 (24,489,2,1,2,2,1),
 (25,506,3,1,2,2,2),
 (26,481,3,1,2,2,1),
 (27,486,4,1,2,2,1),
 (28,516,4,1,2,2,2),
 (29,496,5,1,2,2,1),
 (30,521,5,1,2,2,2),
 (31,516,6,1,2,2,2),
 (32,496,6,1,2,2,1),
 (33,500,0,1,3,3,2),
 (34,500,0,1,3,3,3),
 (35,490,1,1,3,3,2),
 (36,505,1,1,3,3,3),
 (37,486,2,1,3,3,2),
 (38,510,2,1,3,3,3),
 (39,496,3,1,3,3,2),
 (40,515,3,1,3,3,3),
 (41,508,4,1,3,3,3),
 (42,513,5,1,3,3,3),
 (43,486,4,1,3,3,2),
 (44,491,5,1,3,3,2),
 (45,509,5,1,3,3,3),
 (46,497,6,1,3,3,3),
 (47,491,6,1,3,3,2),
 (48,500,0,1,4,4,2),
 (49,500,0,1,4,4,3),
 (50,500,1,1,4,4,3),
 (51,494,1,1,4,4,2),
 (52,489,2,1,4,4,2),
 (53,500,2,1,4,4,3),
 (54,489,3,1,4,4,2),
 (55,495,3,1,4,4,3),
 (56,480,4,1,4,4,2),
 (57,500,4,1,4,4,3),
 (58,484,5,1,4,4,2),
 (59,510,5,1,4,4,3),
 (60,494,6,1,4,4,2),
 (61,503,6,1,4,4,3),
 (62,514,7,1,4,4,2),
 (63,481,7,1,4,4,3),
 (64,524,8,1,4,4,2),
 (65,476,8,1,4,4,3),
 (66,529,9,1,4,4,2),
 (67,470,9,1,4,4,3),
 (68,503,10,1,4,4,2),
 (69,480,10,1,4,4,3),
 (70,487,11,1,4,4,2),
 (71,490,11,1,4,4,3),
 (72,482,12,1,4,4,2),
 (73,495,12,1,4,4,3),
 (74,476,13,1,4,4,2),
 (75,510,13,1,4,4,3),
 (76,491,1,2,4,4,2),
 (77,520,1,2,4,4,3),
 (78,492,2,2,4,4,2),
 (79,530,2,2,4,4,3),
 (80,482,3,2,4,4,2),
 (81,517,3,2,4,4,3),
 (82,477,4,2,4,4,2),
 (83,517,4,2,4,4,3),
 (84,473,5,2,4,4,2),
 (85,517,5,2,4,4,3),
 (86,500,0,1,6,5,2),
 (87,500,0,1,6,5,4),
 (88,501,1,1,6,5,2),
 (89,495,1,1,6,5,4),
 (90,495,2,1,6,5,2),
 (91,500,2,1,6,5,4),
 (92,490,3,1,6,5,2),
 (93,500,3,1,6,5,4),
 (94,489,4,1,6,5,2),
 (95,500,4,1,6,5,4),
 (96,515,5,1,6,5,4),
 (97,480,5,1,6,5,2),
 (98,500,0,1,9,6,2),
 (99,500,0,1,9,6,4),
 (100,500,0,1,9,6,5),
 (101,505,1,1,9,6,5),
 (102,491,1,1,9,6,4),
 (103,502,1,1,9,6,2),
 (104,505,2,1,9,6,5),
 (105,501,2,1,9,6,2),
 (106,494,2,1,9,6,4),
 (107,498,3,1,9,6,5),
 (108,494,3,1,9,6,4),
 (109,484,3,1,9,6,2),
 (110,484,4,1,9,6,2),
 (111,503,4,1,9,6,5),
 (112,493,4,1,9,6,4),
 (113,498,5,1,9,6,4),
 (114,485,5,1,9,6,2),
 (115,505,5,1,9,6,5),
 (116,508,6,1,9,6,4),
 (117,500,6,1,9,6,5),
 (118,497,1,2,9,6,4),
 (119,505,1,2,9,6,5),
 (120,490,6,1,9,6,2),
 (121,496,2,2,9,6,5),
 (122,492,2,2,9,6,4),
 (123,504,3,2,9,6,5),
 (124,487,3,2,9,6,4),
 (125,481,2,2,9,6,2);
/*!40000 ALTER TABLE `archived_amount_left` ENABLE KEYS */;


--
-- Definition of table `bet_state`
--

DROP TABLE IF EXISTS `bet_state`;
CREATE TABLE `bet_state` (
  `ID` bigint(20) NOT NULL auto_increment,
  `IS_RUNS_WINNER` bit(1) default NULL,
  `IS_WICKETS_WINNER` bit(1) default NULL,
  `IS_WINNER` bit(1) default NULL,
  `MATCH_OVER` int(11) default NULL,
  `RESULT_AMOUNT` varchar(255) default NULL,
  `RUNS` int(11) default NULL,
  `RUNS_BET` int(11) default NULL,
  `SESSION` int(11) default NULL,
  `TIME_FACTOR` varchar(255) default NULL,
  `WICKETS` int(11) default NULL,
  `WICKETS_BET` int(11) default NULL,
  `MARKET` bigint(20) default NULL,
  `USER` bigint(20) default NULL,
  PRIMARY KEY  (`ID`),
  KEY `FK3BB6572353B77CB7` (`MARKET`),
  KEY `FK3BB6572398C22F95` (`USER`),
  CONSTRAINT `FK3BB6572398C22F95` FOREIGN KEY (`USER`) REFERENCES `user` (`ID`),
  CONSTRAINT `FK3BB6572353B77CB7` FOREIGN KEY (`MARKET`) REFERENCES `market` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=131 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bet_state`
--

/*!40000 ALTER TABLE `bet_state` DISABLE KEYS */;
INSERT INTO `bet_state` (`ID`,`IS_RUNS_WINNER`,`IS_WICKETS_WINNER`,`IS_WINNER`,`MATCH_OVER`,`RESULT_AMOUNT`,`RUNS`,`RUNS_BET`,`SESSION`,`TIME_FACTOR`,`WICKETS`,`WICKETS_BET`,`MARKET`,`USER`) VALUES 
 (1,0x00,0x00,0x01,1,'0',0,10,1,'',0,-1,1,1),
 (2,0x00,0x01,0x00,1,'0',-5,2,1,'',5,0,1,2),
 (3,0x00,0x01,0x01,2,'5',0,5,1,'',5,1,1,2),
 (4,0x00,0x00,0x00,2,'-11',-1,7,1,'',-10,0,1,1),
 (5,0x00,0x01,0x01,3,'10',0,6,1,'',10,2,1,2),
 (6,0x00,0x01,0x00,3,'7',-3,5,1,'',10,2,1,1),
 (7,0x00,0x01,0x01,4,'0',0,1,1,'',0,1,1,2),
 (8,0x00,0x00,0x00,4,'-5',-5,-999,1,'',0,-1,1,1),
 (9,0x00,0x01,0x00,5,'-6',-11,10,1,'',5,0,1,1),
 (10,0x00,0x01,0x01,5,'5',0,12,1,'',5,0,1,2),
 (11,0x00,0x00,0x00,6,'-10',0,7,1,'',-10,0,1,1),
 (12,0x00,0x01,0x01,6,'4',-1,5,1,'',5,1,1,2),
 (13,0x00,0x01,0x01,7,'10',5,11,1,'',5,1,1,2),
 (14,0x00,0x01,0x01,8,'10',5,11,1,'125000 ms',5,1,1,1),
 (15,0x00,0x01,0x00,8,'5',0,11,1,'',5,1,1,2),
 (16,0x00,0x01,0x01,9,'5',0,12,1,'',5,1,1,1),
 (17,0x00,0x01,0x01,10,'0',0,1,1,'',0,2,1,2),
 (18,0x00,0x00,0x00,10,'-6',-6,11,1,'',0,-1,1,1),
 (19,0x00,0x01,0x01,1,'5',0,12,1,'2000 ms',5,1,2,2),
 (20,0x00,0x00,0x00,1,'-11',-1,10,1,'',-10,0,2,1),
 (21,0x00,0x01,0x00,2,'-4',-4,1,1,'',0,1,2,2),
 (22,0x00,0x00,0x01,2,'0',0,6,1,'',0,-1,2,1),
 (23,0x00,0x01,0x01,3,'5',0,5,1,'',5,1,2,2),
 (24,0x00,0x00,0x00,3,'-8',-3,10,1,'',-5,0,2,1),
 (25,0x00,0x01,0x01,4,'10',5,2,1,'0 ms',5,1,2,2),
 (26,0x00,0x01,0x00,4,'5',0,2,1,'',5,1,2,1),
 (27,0x00,0x01,0x01,5,'10',5,4,1,'1000 ms',5,1,2,1),
 (28,0x00,0x01,0x00,5,'5',0,4,1,'',5,1,2,2),
 (29,0x00,0x00,0x01,6,'0',0,-999,1,'0 ms',0,-1,2,1),
 (30,0x00,0x00,0x00,6,'-5',-5,-999,1,'',0,-1,2,2),
 (31,0x00,0x01,0x00,1,'-5',-10,2,2,'',5,1,2,2),
 (32,0x00,0x00,0x01,1,'-5',0,10,2,'',-5,0,2,1),
 (33,0x00,0x00,0x00,7,'',NULL,1,2,'',NULL,1,2,1),
 (34,0x00,0x00,0x00,8,'',NULL,12,2,'',NULL,-1,2,1),
 (35,0x00,0x00,0x00,9,'',NULL,14,2,'',NULL,-1,2,1),
 (36,0x00,0x00,0x00,1,'-10',-5,2,1,'',-5,1,3,2),
 (37,0x00,0x01,0x01,1,'5',0,3,1,'',5,0,3,3),
 (38,0x00,0x00,0x00,2,'-4',-4,7,1,'',0,-1,3,2),
 (39,0x00,0x01,0x01,2,'5',0,6,1,'',5,1,3,3),
 (40,0x00,0x01,0x01,3,'10',5,8,1,'6000 ms',5,1,3,2),
 (41,0x00,0x01,0x00,3,'5',0,8,1,'',5,1,3,3),
 (42,0x00,0x00,0x00,4,'-10',0,12,1,'',-10,2,3,2),
 (43,0x00,0x01,0x01,4,'-7',-7,2,1,'',0,1,3,3),
 (44,0x00,0x01,0x01,5,'5',0,12,1,'',5,1,3,3),
 (45,0x00,0x01,0x01,5,'5',0,1,1,'',5,1,3,2),
 (46,0x00,0x01,0x01,6,'0',0,12,1,'',0,1,3,2),
 (47,0x00,0x01,0x00,6,'-12',-12,14,1,'',0,1,3,3),
 (48,0x00,0x01,0x01,7,'5',0,1,1,'',5,1,3,3),
 (49,0x00,0x00,0x00,7,'-5',-5,-999,1,'',0,-1,3,2),
 (50,0x00,0x00,0x00,8,'',NULL,12,1,'',NULL,1,3,2),
 (51,0x00,0x00,0x00,8,'',NULL,4,1,'',NULL,1,3,3),
 (52,0x00,0x00,0x00,9,'',NULL,1,1,'',NULL,1,3,2),
 (53,0x00,0x00,0x00,1,'',NULL,1,2,'',NULL,1,3,2),
 (54,0x00,0x00,0x00,10,'',NULL,1,1,'',NULL,1,3,2),
 (55,0x00,0x01,0x00,1,'-6',-11,1,1,'',5,1,4,2),
 (56,0x00,0x00,0x01,1,'0',0,7,1,'',0,-1,4,3),
 (57,0x00,0x01,0x01,2,'0',0,12,1,'',0,1,4,3),
 (58,0x00,0x00,0x00,2,'-5',-5,-999,1,'',0,-1,4,2),
 (59,0x00,0x00,0x01,3,'0',0,-999,1,'1000 ms',0,-1,4,2),
 (60,0x00,0x00,0x00,3,'-5',-5,-999,1,'',0,-1,4,3),
 (61,0x00,0x00,0x00,4,'-9',-4,14,1,'',-5,2,4,2),
 (62,0x00,0x01,0x01,4,'5',0,11,1,'',5,1,4,3),
 (63,0x00,0x01,0x00,5,'4',-1,1,1,'',5,1,4,2),
 (64,0x00,0x01,0x01,5,'10',5,2,1,'',5,1,4,3),
 (65,0x00,0x01,0x01,6,'10',0,6,1,'',10,2,4,2),
 (66,0x00,0x00,0x00,6,'-7',-2,5,1,'',-5,1,4,3),
 (67,0x00,0x01,0x01,7,'20',0,1,1,'8000 ms',20,4,4,2),
 (68,0x00,0x00,0x00,7,'-22',-7,1,1,'',-15,1,4,3),
 (69,0x00,0x01,0x01,8,'10',5,12,1,'',5,1,4,2),
 (70,0x00,0x01,0x00,8,'-5',-10,2,1,'',5,1,4,3),
 (71,0x00,0x01,0x01,9,'5',0,1,1,'91000 ms',5,1,4,2),
 (72,0x00,0x00,0x00,10,'-26',-21,22,1,'',-5,0,4,2),
 (73,0x00,0x01,0x00,9,'-6',-11,1,1,'',5,1,4,3),
 (74,0x00,0x01,0x01,10,'10',5,1,1,'',5,1,4,3),
 (75,0x00,0x01,0x00,11,'-16',-21,2,1,'',5,1,4,2),
 (76,0x00,0x01,0x01,11,'10',5,23,1,'',5,1,4,3),
 (77,0x00,0x01,0x00,12,'-5',-10,13,1,'',5,1,4,2),
 (78,0x00,0x01,0x01,12,'5',0,9,1,'',5,1,4,3),
 (79,0x00,0x00,0x00,13,'-6',-1,1,1,'',-5,1,4,2),
 (80,0x00,0x01,0x01,13,'15',5,2,1,'',10,2,4,3),
 (81,0x00,0x01,0x01,1,'15',5,1,2,'4000 ms',10,2,4,2),
 (82,0x00,0x01,0x00,1,'10',0,1,2,'',10,2,4,3),
 (83,0x00,0x01,0x00,2,'1',-4,1,2,'',5,1,4,2),
 (84,0x00,0x01,0x01,2,'10',5,5,2,'',5,1,4,3),
 (85,0x00,0x00,0x01,3,'-10',0,12,2,'',-10,3,4,2),
 (86,0x00,0x01,0x00,3,'-13',-13,14,2,'',0,2,4,3),
 (87,0x00,0x01,0x00,4,'-5',-5,23,2,'',0,1,4,2),
 (88,0x00,0x01,0x01,4,'0',0,15,2,'',0,1,4,3),
 (89,0x00,0x01,0x00,5,'-4',-4,21,2,'',0,2,4,2),
 (90,0x00,0x01,0x01,5,'0',0,16,2,'',0,2,4,3),
 (91,0x00,0x01,0x01,6,'0',0,23,2,'',0,1,4,2),
 (92,0x00,0x01,0x00,6,'-23',-23,13,2,'',0,1,4,3),
 (93,0x00,0x01,0x01,1,'1',-4,2,1,'',5,1,6,2),
 (94,0x00,0x00,0x00,1,'-5',0,4,1,'',-5,2,6,4),
 (95,0x00,0x00,0x00,2,'-6',-6,12,1,'',0,-1,6,2),
 (96,0x00,0x01,0x01,2,'5',5,6,1,'',0,1,6,4),
 (97,0x00,0x00,0x00,3,'-5',-5,-999,1,'',0,-1,6,2),
 (98,0x00,0x01,0x01,3,'0',0,7,1,'',0,0,6,4),
 (99,0x00,0x01,0x00,4,'-1',-6,12,1,'',5,0,6,2),
 (100,0x00,0x00,0x01,4,'0',5,6,1,'',-5,1,6,4),
 (101,0x00,0x00,0x00,5,'-9',-4,12,1,'',-5,1,6,2),
 (102,0x00,0x01,0x01,5,'15',5,8,1,'',10,2,6,4),
 (103,0x00,0x01,0x01,6,'10',5,4,1,'2000 ms',5,1,6,4),
 (104,0x00,0x01,0x00,6,'5',0,4,1,'',5,1,6,2),
 (105,0x00,0x01,0x00,1,'2',-3,5,1,'',5,1,9,2),
 (106,0x00,0x00,0x00,1,'-9',-4,4,1,'',-5,0,9,4),
 (107,0x00,0x01,0x01,1,'5',0,9,1,'',5,1,9,5),
 (108,0x00,0x00,0x00,2,'0',5,5,1,'',-5,2,9,5),
 (109,0x00,0x01,0x01,2,'3',-2,7,1,'',5,1,9,4),
 (110,0x00,0x00,0x00,2,'-1',-1,4,1,'',0,-1,9,2),
 (111,0x00,0x00,0x00,3,'-7',-7,7,1,'',0,-1,9,5),
 (112,0x00,0x00,0x00,3,'-17',-12,12,1,'',-5,1,9,2),
 (113,0x00,0x01,0x01,3,'0',0,6,1,'',0,2,9,4),
 (114,0x00,0x00,0x01,4,'5',5,5,1,'25000 ms',0,-1,9,5),
 (115,0x00,0x01,0x00,4,'-1',-1,4,1,'',0,2,9,4),
 (116,0x00,0x00,0x00,4,'0',0,5,1,'',0,-1,9,2),
 (117,0x00,0x01,0x00,5,'1',-4,8,1,'',5,1,9,2),
 (118,0x00,0x01,0x00,5,'2',-3,7,1,'',5,1,9,5),
 (119,0x00,0x01,0x01,5,'5',0,3,1,'',5,1,9,4),
 (120,0x00,0x01,0x01,6,'10',5,4,1,'45000 ms',5,1,9,4),
 (121,0x00,0x01,0x00,6,'5',0,4,1,'',5,1,9,2),
 (122,0x00,0x00,0x00,6,'-5',-5,-999,1,'',0,-1,9,5),
 (123,0x00,0x00,0x00,1,'-11',-6,2,2,'',-5,0,9,4),
 (124,0x00,0x01,0x01,1,'5',0,5,2,'',5,1,9,5),
 (125,0x00,0x00,0x01,2,'-5',0,6,2,'',-5,1,9,4),
 (126,0x00,0x00,0x00,2,'-9',-9,5,2,'',0,-1,9,5),
 (127,0x00,0x01,0x00,2,'-9',-9,5,2,'',0,3,9,2),
 (128,0x00,0x01,0x01,3,'8',-2,5,2,'',10,2,9,5),
 (129,0x00,0x00,0x00,3,'-5',0,6,2,'',-5,1,9,4),
 (130,0x00,0x01,0x00,3,'2',-8,15,2,'',10,2,9,2);
/*!40000 ALTER TABLE `bet_state` ENABLE KEYS */;


--
-- Definition of table `feedback`
--

DROP TABLE IF EXISTS `feedback`;
CREATE TABLE `feedback` (
  `ID` bigint(20) NOT NULL auto_increment,
  `CREATED_DATE` datetime default NULL,
  `FEEDBACK_MSG` varchar(255) default NULL,
  `FEEDBACK_TYPE` varchar(255) default NULL,
  `USER` bigint(20) default NULL,
  PRIMARY KEY  (`ID`),
  KEY `FK233BDB8598C22F95` (`USER`),
  CONSTRAINT `FK233BDB8598C22F95` FOREIGN KEY (`USER`) REFERENCES `user` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `feedback`
--

/*!40000 ALTER TABLE `feedback` DISABLE KEYS */;
INSERT INTO `feedback` (`ID`,`CREATED_DATE`,`FEEDBACK_MSG`,`FEEDBACK_TYPE`,`USER`) VALUES 
 (1,'2012-02-10 07:02:06','The game seems to be fantastic..! Thanks.','Submit a Feedback',2),
 (2,'2012-02-14 06:13:53','g','Submit a Feedback',4);
/*!40000 ALTER TABLE `feedback` ENABLE KEYS */;


--
-- Definition of table `market`
--

DROP TABLE IF EXISTS `market`;
CREATE TABLE `market` (
  `ID` bigint(20) NOT NULL auto_increment,
  `CREATION_DATE` datetime default NULL,
  `GROUP_OVER_VALUE` int(11) default NULL,
  `MARKET_CODE` varchar(255) default NULL,
  `MATCH_SESSION` int(11) default NULL,
  `PROCESSED_OVER` int(11) default NULL,
  `MATCH_MASTER_ID` bigint(20) default NULL,
  `MODERATOR_ID` bigint(20) default NULL,
  PRIMARY KEY  (`ID`),
  KEY `FK871F883C8CC5F9C6` (`MATCH_MASTER_ID`),
  KEY `FK871F883C73CE365F` (`MODERATOR_ID`),
  CONSTRAINT `FK871F883C73CE365F` FOREIGN KEY (`MODERATOR_ID`) REFERENCES `user` (`ID`),
  CONSTRAINT `FK871F883C8CC5F9C6` FOREIGN KEY (`MATCH_MASTER_ID`) REFERENCES `match_master` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `market`
--

/*!40000 ALTER TABLE `market` DISABLE KEYS */;
INSERT INTO `market` (`ID`,`CREATION_DATE`,`GROUP_OVER_VALUE`,`MARKET_CODE`,`MATCH_SESSION`,`PROCESSED_OVER`,`MATCH_MASTER_ID`,`MODERATOR_ID`) VALUES 
 (1,'2012-02-07 08:52:48',1,'26034baa-499a-432e-9eca-a5fa7471330e',1,-1,1,2),
 (2,'2012-02-08 08:53:04',1,'dc4fb1e1-7b35-43cb-97f5-87d78e8ecbf4',1,-1,2,2),
 (3,'2012-02-09 04:53:10',1,'e8ecfc50-349e-4326-a05b-2673ef7da9b8',1,-1,3,3),
 (4,'2012-02-10 05:57:34',1,'4af4fe70-16c8-4668-bf5a-ac58c57dbdc4',1,-1,4,2),
 (6,'2012-02-14 05:46:01',1,'1c164156-c653-4069-9e43-e5d4956ce4bf',1,-1,5,4),
 (7,'2012-02-15 05:17:09',1,'c1b71e31-4f82-49d9-8b2b-9a16159a24b2',1,-1,7,2),
 (9,'2012-02-15 05:20:33',1,'ba10d24f-373d-4d78-a672-2c989c7a140c',1,-1,6,2);
/*!40000 ALTER TABLE `market` ENABLE KEYS */;


--
-- Definition of table `market_user`
--

DROP TABLE IF EXISTS `market_user`;
CREATE TABLE `market_user` (
  `ID` bigint(20) NOT NULL auto_increment,
  `CREATION_DATE` datetime default NULL,
  `USER_ID` bigint(20) default NULL,
  `MARKET_ID` bigint(20) default NULL,
  PRIMARY KEY  (`ID`),
  KEY `FK931AD9EE5C828C79` (`MARKET_ID`),
  KEY `FK931AD9EE8FC631D9` (`USER_ID`),
  CONSTRAINT `FK931AD9EE8FC631D9` FOREIGN KEY (`USER_ID`) REFERENCES `user` (`ID`),
  CONSTRAINT `FK931AD9EE5C828C79` FOREIGN KEY (`MARKET_ID`) REFERENCES `market` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `market_user`
--

/*!40000 ALTER TABLE `market_user` DISABLE KEYS */;
INSERT INTO `market_user` (`ID`,`CREATION_DATE`,`USER_ID`,`MARKET_ID`) VALUES 
 (1,'2012-02-07 08:52:48',2,1),
 (2,'2012-02-07 08:53:33',1,1),
 (3,'2012-02-08 08:53:04',2,2),
 (4,'2012-02-08 08:53:20',1,2),
 (5,'2012-02-09 04:53:10',3,3),
 (6,'2012-02-09 04:53:27',2,3),
 (7,'2012-02-10 05:57:34',2,4),
 (8,'2012-02-10 05:59:55',3,4),
 (10,'2012-02-14 05:46:01',4,6),
 (11,'2012-02-14 05:47:14',2,6),
 (12,'2012-02-15 05:17:09',2,7),
 (14,'2012-02-15 05:20:33',2,9),
 (15,'2012-02-15 05:23:30',3,NULL),
 (17,'2012-02-15 07:19:57',5,9),
 (18,'2012-02-15 07:28:00',4,9),
 (19,'2012-02-15 08:33:14',5,7);
/*!40000 ALTER TABLE `market_user` ENABLE KEYS */;


--
-- Definition of table `match_master`
--

DROP TABLE IF EXISTS `match_master`;
CREATE TABLE `match_master` (
  `ID` bigint(20) NOT NULL auto_increment,
  `DESCRIPTION` varchar(200) default NULL,
  `MATCH_DATE` datetime default NULL,
  `MATCH_NAME` varchar(150) default NULL,
  `OVERS` int(11) default NULL,
  `STATUS` varchar(255) default NULL,
  `WINNER` varchar(255) default NULL,
  `TEAM_1` bigint(20) default NULL,
  `TEAM_2` bigint(20) default NULL,
  PRIMARY KEY  (`ID`),
  KEY `FK7467063C600DA6AD` (`TEAM_1`),
  KEY `FK7467063C600DA6AE` (`TEAM_2`),
  CONSTRAINT `FK7467063C600DA6AE` FOREIGN KEY (`TEAM_2`) REFERENCES `team_master` (`ID`),
  CONSTRAINT `FK7467063C600DA6AD` FOREIGN KEY (`TEAM_1`) REFERENCES `team_master` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `match_master`
--

/*!40000 ALTER TABLE `match_master` DISABLE KEYS */;
INSERT INTO `match_master` (`ID`,`DESCRIPTION`,`MATCH_DATE`,`MATCH_NAME`,`OVERS`,`STATUS`,`WINNER`,`TEAM_1`,`TEAM_2`) VALUES 
 (1,'India vs Australia','2012-02-07 09:00:00','India vs Australia',20,'ACTIVE',NULL,1,2),
 (2,'India vs Australia','2012-02-08 09:00:00','India vs Australia',20,'ACTIVE',NULL,1,2),
 (3,'India vs Australia','2012-02-09 05:00:00','India vs Australia',20,'ACTIVE',NULL,1,2),
 (4,'India vs Australia','2012-02-10 06:00:00','India vs Australia',20,'FINISHED','true',1,2),
 (5,'India vs Australia','2012-02-14 05:45:00','India vs Australia',20,'ACTIVE',NULL,1,2),
 (6,'India vs Australia','2012-02-15 06:15:00','India vs Australia',20,'FINISHED','true',1,2),
 (7,'India vs Sri Lanka','2012-02-16 08:00:00','India vs Sri Lanka',20,'ACTIVE',NULL,1,3);
/*!40000 ALTER TABLE `match_master` ENABLE KEYS */;


--
-- Definition of table `match_score`
--

DROP TABLE IF EXISTS `match_score`;
CREATE TABLE `match_score` (
  `ID` bigint(20) NOT NULL auto_increment,
  `INVALIDATE` bit(1) default NULL,
  `MATCH_OVER` int(11) default NULL,
  `RUNS` int(11) default NULL,
  `SCORE` varchar(255) default NULL,
  `SESSION_ID` int(11) default NULL,
  `TIMESTAMP` datetime default NULL,
  `WICKETS` int(11) default NULL,
  `MATCH_ID` bigint(20) default NULL,
  PRIMARY KEY  (`ID`),
  UNIQUE KEY `MATCH_ID` (`MATCH_ID`,`SESSION_ID`,`MATCH_OVER`),
  KEY `FK2D60FB58892EC97D` (`MATCH_ID`),
  CONSTRAINT `FK2D60FB58892EC97D` FOREIGN KEY (`MATCH_ID`) REFERENCES `match_master` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `match_score`
--

/*!40000 ALTER TABLE `match_score` DISABLE KEYS */;
INSERT INTO `match_score` (`ID`,`INVALIDATE`,`MATCH_OVER`,`RUNS`,`SCORE`,`SESSION_ID`,`TIMESTAMP`,`WICKETS`,`MATCH_ID`) VALUES 
 (1,0x01,0,0,'0 for 0',1,'2012-02-07 08:52:09',0,1),
 (2,0x01,1,7,'7/0',1,'2012-02-07 08:53:53',0,1),
 (3,0x01,2,6,'13/0',1,'2012-02-07 09:00:04',1,1),
 (4,0x01,3,8,'21/2',1,'2012-02-07 09:02:59',2,1),
 (5,0x01,4,2,'23/2',1,'2012-02-07 09:12:40',0,1),
 (6,0x01,5,21,'43/2',1,'2012-02-07 09:17:13',0,1),
 (7,0x01,6,6,'49/2',1,'2012-02-07 09:20:41',1,1),
 (8,0x01,7,11,'60/2',1,'2012-02-07 09:23:58',1,1),
 (9,0x01,8,11,'71/2',1,'2012-02-07 09:30:27',1,1),
 (10,0x01,9,2,'73/2',1,'2012-02-07 09:34:08',1,1),
 (11,0x01,10,5,'80/2',1,'2012-02-07 09:42:52',6,1),
 (12,0x01,0,0,'0 for 0',2,'2012-02-07 09:45:42',0,1),
 (13,0x01,0,0,'0 for 0',1,'2012-02-08 08:52:30',0,2),
 (14,0x01,1,11,'11/1',1,'2012-02-08 08:58:33',1,2),
 (15,0x01,2,5,'10/1',1,'2012-02-08 09:27:12',0,2),
 (16,0x01,3,7,'14/1',1,'2012-02-08 09:30:04',1,2),
 (17,0x01,4,2,'16/1',1,'2012-02-08 09:31:51',1,2),
 (18,0x01,5,4,'30/1',1,'2012-02-08 09:33:38',1,2),
 (19,0x01,6,3,'35/2',1,'2012-02-08 09:34:46',0,2),
 (20,0x01,0,0,'0 for 0',2,'2012-02-08 09:37:41',0,2),
 (21,0x01,1,12,'12/1',2,'2012-02-08 09:37:48',1,2),
 (22,0x01,0,0,'0 for 0',1,'2012-02-09 04:52:24',0,3),
 (23,0x01,1,7,'7/0',1,'2012-02-09 04:54:59',0,3),
 (24,0x01,2,3,'10/1',1,'2012-02-09 04:58:59',1,3),
 (25,0x01,3,8,'18/1',1,'2012-02-09 05:03:06',1,3),
 (26,0x01,4,9,'29/1',1,'2012-02-09 05:08:43',0,3),
 (27,0x01,5,3,'31/2',1,'2012-02-09 05:13:17',1,3),
 (28,0x01,6,2,'41/2',1,'2012-02-09 05:16:31',0,3),
 (29,0x01,7,12,'51/2',1,'2012-02-09 05:22:12',1,3),
 (30,0x01,8,NULL,NULL,1,'2012-02-09 05:26:36',NULL,3),
 (31,0x01,0,0,'0 for 0',1,'2012-02-10 05:57:14',0,4),
 (32,0x01,1,12,'12/1',1,'2012-02-10 06:00:07',1,4),
 (33,0x01,2,5,'17/1',1,'2012-02-10 06:04:22',0,4),
 (34,0x01,3,2,'19/1',1,'2012-02-10 06:08:06',0,4),
 (35,0x01,4,10,'29/2',1,'2012-02-10 06:11:41',1,4),
 (36,0x01,5,2,'31/2',1,'2012-02-10 06:14:26',1,4),
 (37,0x01,6,7,'38/4',1,'2012-02-10 06:18:08',2,4),
 (38,0x01,7,8,'38/8',1,'2012-02-10 06:19:11',4,4),
 (39,0x01,8,12,'43/8',1,'2012-02-10 06:21:59',1,4),
 (40,0x01,9,12,'48/8',1,'2012-02-10 06:23:17',1,4),
 (41,0x01,10,1,'25/9',1,'2012-02-10 06:24:58',1,4),
 (42,0x01,11,23,'78/2',1,'2012-02-10 06:26:29',1,4),
 (43,0x01,12,3,'78/2',1,'2012-02-10 06:27:17',1,4),
 (44,0x01,13,2,'78/2',1,'2012-02-10 06:28:03',2,4),
 (45,0x01,0,0,'0 for 0',2,'2012-02-10 06:28:35',0,4),
 (46,0x01,1,1,'1/2',2,'2012-02-10 06:29:23',2,4),
 (47,0x01,2,5,'7/2',2,'2012-02-10 06:30:27',1,4),
 (48,0x01,3,1,'9/1',2,'2012-02-10 06:31:20',1,4),
 (49,0x01,4,18,'19/2',2,'2012-02-10 06:31:31',2,4),
 (50,0x01,5,17,'12/2',2,'2012-02-10 06:31:55',3,4),
 (51,0x01,6,36,'19/2',2,'2012-02-10 06:34:21',2,4),
 (52,0x01,0,0,'0 for 0',1,'2012-02-14 05:36:45',0,5),
 (53,0x01,1,6,'6/1',1,'2012-02-14 05:48:10',1,5),
 (54,0x01,2,6,'12/1',1,'2012-02-14 05:58:19',0,5),
 (55,0x01,3,3,'17/1',1,'2012-02-14 06:00:19',1,5),
 (56,0x01,4,6,'23/1',1,'2012-02-14 06:02:37',0,5),
 (57,0x01,5,8,'38/2',1,'2012-02-14 06:09:08',2,5),
 (58,0x01,6,4,'42/2',1,'2012-02-14 06:14:06',1,5),
 (59,0x01,0,0,'0 for 0',1,'2012-02-15 05:14:37',0,6),
 (60,0x01,0,0,'0 for 0',1,'2012-02-15 05:15:30',0,7),
 (61,0x01,1,8,'8/1',1,'2012-02-15 07:28:22',1,6),
 (62,0x01,2,5,'12/1',1,'2012-02-15 07:34:33',1,6),
 (63,0x01,3,0,'18/1',1,'2012-02-15 07:39:00',0,6),
 (64,0x01,4,5,'23/1',1,'2012-02-15 07:42:52',0,6),
 (65,0x01,5,4,'24/1',1,'2012-02-15 07:46:06',1,6),
 (66,0x01,6,4,'28/`',1,'2012-02-15 07:49:43',1,6),
 (67,0x01,0,0,'0 for 0',2,'2012-02-15 07:55:03',0,6),
 (68,0x01,1,8,'8/1',2,'2012-02-15 07:56:22',1,6),
 (69,0x01,2,14,'16/1',2,'2012-02-15 08:01:09',0,6),
 (70,0x01,3,7,'18/2',2,'2012-02-15 08:06:13',2,6);
/*!40000 ALTER TABLE `match_score` ENABLE KEYS */;


--
-- Definition of table `team_master`
--

DROP TABLE IF EXISTS `team_master`;
CREATE TABLE `team_master` (
  `ID` bigint(20) NOT NULL auto_increment,
  `TEAM_ALIAS` varchar(255) default NULL,
  `TEAM_LOGO_PATH` varchar(255) default NULL,
  `TEAM_NAME` varchar(255) default NULL,
  PRIMARY KEY  (`ID`),
  UNIQUE KEY `TEAM_NAME` (`TEAM_NAME`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `team_master`
--

/*!40000 ALTER TABLE `team_master` DISABLE KEYS */;
INSERT INTO `team_master` (`ID`,`TEAM_ALIAS`,`TEAM_LOGO_PATH`,`TEAM_NAME`) VALUES 
 (1,'IND','','India'),
 (2,'AUS','','Australia'),
 (3,'SRL','','Sri Lanka');
/*!40000 ALTER TABLE `team_master` ENABLE KEYS */;


--
-- Definition of table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `ID` bigint(20) NOT NULL auto_increment,
  `EMAIL_ID` varchar(255) default NULL,
  `NAME` varchar(255) default NULL,
  `PLAYING` bit(1) default NULL,
  `PROFILE_ID` varchar(255) default NULL,
  `MARKET_ID` bigint(20) default NULL,
  PRIMARY KEY  (`ID`),
  KEY `FK27E3CB5C828C79` (`MARKET_ID`),
  CONSTRAINT `FK27E3CB5C828C79` FOREIGN KEY (`MARKET_ID`) REFERENCES `market` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`ID`,`EMAIL_ID`,`NAME`,`PLAYING`,`PROFILE_ID`,`MARKET_ID`) VALUES 
 (1,'vipul.parekh@yahoo.co.in','Vipul Parekh',0x00,'100000174626508',NULL),
 (2,'amitmeshram@hotmail.com','Amit Meshram',0x00,'659970567',NULL),
 (3,'galileomeshram@yahoo.com','Galileo Meshram',0x00,'100003090370920',NULL),
 (4,'karthickeyan.ska@wipro.com','Karthick Keyan',0x00,'100000700051228',NULL),
 (5,'batra.tushar@gmail.com','Tushar Batra',0x00,'828925720',NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;


--
-- Definition of table `user_bet`
--

DROP TABLE IF EXISTS `user_bet`;
CREATE TABLE `user_bet` (
  `ID` bigint(20) NOT NULL auto_increment,
  `BET_TIME` datetime default NULL,
  `MATCH_OVER` int(11) default NULL,
  `MATCH_SESSION` int(11) default NULL,
  `RUN_BET_AMOUNT` int(11) default NULL,
  `RUNS_BET` int(11) default NULL,
  `WICKET_BET_AMOUNT` int(11) default NULL,
  `WICKETS_BET` int(11) default NULL,
  `MARKET` bigint(20) default NULL,
  `USER` bigint(20) default NULL,
  PRIMARY KEY  (`ID`),
  UNIQUE KEY `MATCH_OVER` (`MATCH_OVER`,`MATCH_SESSION`,`USER`,`MARKET`),
  KEY `FK1ED735FD53B77CB7` (`MARKET`),
  KEY `FK1ED735FD98C22F95` (`USER`),
  CONSTRAINT `FK1ED735FD98C22F95` FOREIGN KEY (`USER`) REFERENCES `user` (`ID`),
  CONSTRAINT `FK1ED735FD53B77CB7` FOREIGN KEY (`MARKET`) REFERENCES `market` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=131 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_bet`
--

/*!40000 ALTER TABLE `user_bet` DISABLE KEYS */;
INSERT INTO `user_bet` (`ID`,`BET_TIME`,`MATCH_OVER`,`MATCH_SESSION`,`RUN_BET_AMOUNT`,`RUNS_BET`,`WICKET_BET_AMOUNT`,`WICKETS_BET`,`MARKET`,`USER`) VALUES 
 (1,'2012-02-07 08:54:17',1,1,0,10,0,-1,1,1),
 (2,'2012-02-07 08:55:10',1,1,-5,2,5,0,1,2),
 (3,'2012-02-07 09:00:57',2,1,0,5,5,1,1,2),
 (4,'2012-02-07 09:01:22',2,1,-1,7,-10,0,1,1),
 (5,'2012-02-07 09:03:27',3,1,0,6,10,2,1,2),
 (6,'2012-02-07 09:03:36',3,1,-3,5,10,2,1,1),
 (7,'2012-02-07 09:13:52',4,1,0,1,0,1,1,2),
 (8,'2012-02-07 09:15:40',4,1,-5,-999,0,-1,1,1),
 (9,'2012-02-07 09:17:49',5,1,-11,10,5,0,1,1),
 (10,'2012-02-07 09:19:00',5,1,0,12,5,0,1,2),
 (11,'2012-02-07 09:21:02',6,1,0,7,-10,0,1,1),
 (12,'2012-02-07 09:21:10',6,1,-1,5,5,1,1,2),
 (13,'2012-02-07 09:26:24',7,1,5,11,5,1,1,2),
 (14,'2012-02-07 09:28:19',8,1,5,11,5,1,1,1),
 (15,'2012-02-07 09:30:24',8,1,0,11,5,1,1,2),
 (16,'2012-02-07 09:34:47',9,1,0,12,5,1,1,1),
 (17,'2012-02-07 09:43:09',10,1,0,1,0,2,1,2),
 (18,'2012-02-07 09:43:26',10,1,-6,11,0,-1,1,1),
 (19,'2012-02-08 08:59:07',1,1,0,12,5,1,2,2),
 (20,'2012-02-08 08:59:09',1,1,-1,10,-10,0,2,1),
 (21,'2012-02-08 09:27:34',2,1,-4,1,0,1,2,2),
 (22,'2012-02-08 09:27:38',2,1,0,6,0,-1,2,1),
 (23,'2012-02-08 09:30:30',3,1,0,5,5,1,2,2),
 (24,'2012-02-08 09:30:30',3,1,-3,10,-5,0,2,1),
 (25,'2012-02-08 09:31:44',4,1,5,2,5,1,2,2),
 (26,'2012-02-08 09:31:44',4,1,0,2,5,1,2,1),
 (27,'2012-02-08 09:34:02',5,1,5,4,5,1,2,1),
 (28,'2012-02-08 09:34:03',5,1,0,4,5,1,2,2),
 (29,'2012-02-08 09:35:16',6,1,0,-999,0,-1,2,1),
 (30,'2012-02-08 09:35:16',6,1,-5,-999,0,-1,2,2),
 (31,'2012-02-08 09:38:01',1,2,-10,2,5,1,2,2),
 (32,'2012-02-08 09:38:02',1,2,0,10,-5,0,2,1),
 (33,'2012-02-08 09:51:10',7,2,NULL,1,NULL,1,2,1),
 (34,'2012-02-08 09:52:06',8,2,NULL,12,NULL,-1,2,1),
 (35,'2012-02-08 09:52:08',9,2,NULL,14,NULL,-1,2,1),
 (36,'2012-02-09 04:55:23',1,1,-5,2,-5,1,3,2),
 (37,'2012-02-09 04:55:35',1,1,0,3,5,0,3,3),
 (38,'2012-02-09 05:00:13',2,1,-4,7,0,-1,3,2),
 (39,'2012-02-09 05:00:26',2,1,0,6,5,1,3,3),
 (40,'2012-02-09 05:02:54',3,1,5,8,5,1,3,2),
 (41,'2012-02-09 05:03:00',3,1,0,8,5,1,3,3),
 (42,'2012-02-09 05:10:54',4,1,0,12,-10,2,3,2),
 (43,'2012-02-09 05:11:21',4,1,-7,2,0,1,3,3),
 (44,'2012-02-09 05:11:24',5,1,-9,12,5,1,3,3),
 (45,'2012-02-09 05:15:38',5,1,0,1,5,1,3,2),
 (46,'2012-02-09 05:17:21',6,1,0,12,0,1,3,2),
 (47,'2012-02-09 05:17:28',6,1,-12,14,0,1,3,3),
 (48,'2012-02-09 05:23:15',7,1,0,1,5,1,3,3),
 (49,'2012-02-09 05:25:13',7,1,-5,-999,0,-1,3,2),
 (50,'2012-02-09 05:26:27',8,1,NULL,12,NULL,1,3,2),
 (51,'2012-02-09 05:26:33',8,1,NULL,4,NULL,1,3,3),
 (52,'2012-02-09 05:28:03',9,1,NULL,1,NULL,1,3,2),
 (53,'2012-02-09 05:29:07',1,2,NULL,1,NULL,1,3,2),
 (54,'2012-02-09 05:29:11',10,1,NULL,1,NULL,1,3,2),
 (55,'2012-02-10 06:00:27',1,1,-11,1,5,1,4,2),
 (56,'2012-02-10 06:00:35',1,1,0,7,0,-1,4,3),
 (57,'2012-02-10 06:04:28',2,1,0,12,0,1,4,3),
 (58,'2012-02-10 06:07:23',2,1,-5,-999,0,-1,4,2),
 (59,'2012-02-10 06:11:07',3,1,0,-999,0,-1,4,2),
 (60,'2012-02-10 06:11:08',3,1,-5,-999,0,-1,4,3),
 (61,'2012-02-10 06:13:16',4,1,-4,14,-5,2,4,2),
 (62,'2012-02-10 06:13:27',4,1,0,11,5,1,4,3),
 (63,'2012-02-10 06:15:01',5,1,-1,1,5,1,4,2),
 (64,'2012-02-10 06:15:07',5,1,5,2,5,1,4,3),
 (65,'2012-02-10 06:17:58',6,1,0,6,10,2,4,2),
 (66,'2012-02-10 06:18:14',6,1,-2,5,-5,1,4,3),
 (67,'2012-02-10 06:19:09',7,1,0,1,20,4,4,2),
 (68,'2012-02-10 06:19:17',7,1,-7,1,-15,1,4,3),
 (69,'2012-02-10 06:21:49',8,1,5,12,5,1,4,2),
 (70,'2012-02-10 06:21:56',8,1,-10,2,5,1,4,3),
 (71,'2012-02-10 06:23:02',9,1,0,1,5,1,4,2),
 (72,'2012-02-10 06:24:12',10,1,-21,22,-5,0,4,2),
 (73,'2012-02-10 06:24:33',9,1,-11,1,5,1,4,3),
 (74,'2012-02-10 06:25:34',10,1,5,1,5,1,4,3),
 (75,'2012-02-10 06:26:20',11,1,-21,2,5,1,4,2),
 (76,'2012-02-10 06:26:25',11,1,5,23,5,1,4,3),
 (77,'2012-02-10 06:26:52',12,1,-10,13,5,1,4,2),
 (78,'2012-02-10 06:27:00',12,1,0,9,5,1,4,3),
 (79,'2012-02-10 06:27:55',13,1,-1,1,-5,1,4,2),
 (80,'2012-02-10 06:27:59',13,1,5,2,10,2,4,3),
 (81,'2012-02-10 06:30:07',1,2,5,1,10,2,4,2),
 (82,'2012-02-10 06:30:11',1,2,0,1,10,2,4,3),
 (83,'2012-02-10 06:30:49',2,2,-4,1,5,1,4,2),
 (84,'2012-02-10 06:30:56',2,2,5,5,5,1,4,3),
 (85,'2012-02-10 06:31:10',3,2,0,12,-10,3,4,2),
 (86,'2012-02-10 06:31:16',3,2,-13,14,0,2,4,3),
 (87,'2012-02-10 06:31:38',4,2,-5,23,0,1,4,2),
 (88,'2012-02-10 06:31:44',4,2,0,15,0,1,4,3),
 (89,'2012-02-10 06:33:38',5,2,-4,21,0,2,4,2),
 (90,'2012-02-10 06:33:45',5,2,0,16,0,2,4,3),
 (91,'2012-02-10 06:34:17',6,2,0,23,0,1,4,2),
 (92,'2012-02-10 06:34:28',6,2,-23,13,0,1,4,3),
 (93,'2012-02-14 05:48:54',1,1,-4,2,5,1,6,2),
 (94,'2012-02-14 05:51:00',1,1,0,4,-5,2,6,4),
 (95,'2012-02-14 05:59:08',2,1,-6,12,0,-1,6,2),
 (96,'2012-02-14 05:59:23',2,1,5,6,0,1,6,4),
 (97,'2012-02-14 06:01:21',3,1,-5,-999,0,-1,6,2),
 (98,'2012-02-14 06:01:44',3,1,0,7,0,0,6,4),
 (99,'2012-02-14 06:03:39',4,1,-6,12,5,0,6,2),
 (100,'2012-02-14 06:03:41',4,1,5,6,-5,1,6,4),
 (101,'2012-02-14 06:09:01',5,1,-4,12,-5,1,6,2),
 (102,'2012-02-14 06:09:30',5,1,5,8,10,2,6,4),
 (103,'2012-02-14 06:14:16',6,1,5,4,5,1,6,4),
 (104,'2012-02-14 06:14:18',6,1,0,4,5,1,6,2),
 (105,'2012-02-15 07:29:05',1,1,-3,5,5,1,9,2),
 (106,'2012-02-15 07:29:12',1,1,-4,4,-5,0,9,4),
 (107,'2012-02-15 07:29:47',1,1,0,9,5,1,9,5),
 (108,'2012-02-15 07:34:45',2,1,5,5,-5,2,9,5),
 (109,'2012-02-15 07:34:47',2,1,-2,7,5,1,9,4),
 (110,'2012-02-15 07:34:48',2,1,-1,4,0,-1,9,2),
 (111,'2012-02-15 07:39:10',3,1,-7,7,0,-1,9,5),
 (112,'2012-02-15 07:39:21',3,1,-12,12,-5,1,9,2),
 (113,'2012-02-15 07:39:28',3,1,0,6,0,2,9,4),
 (114,'2012-02-15 07:42:50',4,1,5,5,0,-1,9,5),
 (115,'2012-02-15 07:43:01',4,1,-1,4,0,2,9,4),
 (116,'2012-02-15 07:43:15',4,1,0,5,0,-1,9,2),
 (117,'2012-02-15 07:46:50',5,1,-4,8,5,1,9,2),
 (118,'2012-02-15 07:46:52',5,1,-3,7,5,1,9,5),
 (119,'2012-02-15 07:47:00',5,1,0,3,5,1,9,4),
 (120,'2012-02-15 07:50:14',6,1,5,4,5,1,9,4),
 (121,'2012-02-15 07:50:59',6,1,0,4,5,1,9,2),
 (122,'2012-02-15 07:52:45',6,1,-5,-999,0,-1,9,5),
 (123,'2012-02-15 07:57:38',1,2,-6,2,-5,0,9,4),
 (124,'2012-02-15 07:58:06',1,2,0,5,5,1,9,5),
 (125,'2012-02-15 08:01:38',2,2,0,6,-5,1,9,4),
 (126,'2012-02-15 08:02:00',2,2,-9,5,0,-1,9,5),
 (127,'2012-02-15 08:02:02',2,2,-9,5,0,3,9,2),
 (128,'2012-02-15 08:05:39',3,2,-2,5,10,2,9,5),
 (129,'2012-02-15 08:06:27',3,2,0,6,-5,1,9,4),
 (130,'2012-02-15 08:07:15',3,2,-8,15,10,2,9,2);
/*!40000 ALTER TABLE `user_bet` ENABLE KEYS */;


--
-- Definition of table `user_preference`
--

DROP TABLE IF EXISTS `user_preference`;
CREATE TABLE `user_preference` (
  `ID` bigint(20) NOT NULL auto_increment,
  `SHOW_ASSISTANT` bit(1) default NULL,
  `SHOW_VIDEO_TUTORIAL` bit(1) default NULL,
  `TERMS_ACCEPTED` bit(1) default NULL,
  `USER_ID` bigint(20) default NULL,
  PRIMARY KEY  (`ID`),
  KEY `FKD7BF5DCF8FC631D9` (`USER_ID`),
  CONSTRAINT `FKD7BF5DCF8FC631D9` FOREIGN KEY (`USER_ID`) REFERENCES `user` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_preference`
--

/*!40000 ALTER TABLE `user_preference` DISABLE KEYS */;
INSERT INTO `user_preference` (`ID`,`SHOW_ASSISTANT`,`SHOW_VIDEO_TUTORIAL`,`TERMS_ACCEPTED`,`USER_ID`) VALUES 
 (1,0x01,0x01,0x01,1),
 (2,0x01,0x01,0x01,2),
 (3,0x01,0x01,0x01,3),
 (4,0x01,0x01,0x01,4),
 (5,0x01,0x01,0x01,5);
/*!40000 ALTER TABLE `user_preference` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
