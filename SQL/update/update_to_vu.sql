ALTER TABLE `user` ADD COLUMN `USER_TYPE` varchar(20) NOT NULL DEFAULT 'normal_user';

INSERT INTO `user` (`ID`, `CREATED_DATE`, `UPDATED_DATE`, `EMAIL_ID`, `NAME`, `PLAYING`, `PROFILE_ID`, `MARKET_ID`,`user_type`)
VALUES (-1,now(),now(),'creckett@creckett.com', 'creckett',true,'-1',NULL,'virtual_user');

alter table creckett.user add LOGIN_COUNT bigint(20);

CREATE TABLE IF NOT EXISTS `user_match_vote` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREATED_DATE` datetime DEFAULT NULL,
  `UPDATED_DATE` datetime DEFAULT NULL,
  `LEFT_AMOUNT` int(11) DEFAULT NULL,
  `MATCH_OVER` int(11) DEFAULT NULL,
  `SESSION_ID` int(11) DEFAULT NULL,
  `MARKET_ID` bigint(20) DEFAULT NULL,
  `MATCH_ID` bigint(20) DEFAULT NULL,
  `USER_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK39E6038E5C828C79` (`MARKET_ID`),
  KEY `FK39E6038E892EC97D` (`MATCH_ID`),
  KEY `FK39E6038E8FC631D9` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;