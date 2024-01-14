
CREATE TABLE IF NOT EXISTS `plant` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `in_house` bit(1) NOT NULL,
  `name` varchar(100) NOT NULL,
  `planted_date` datetime(6) NOT NULL,
  PRIMARY KEY (`id`)
);