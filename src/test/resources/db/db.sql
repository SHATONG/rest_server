SET foreign_key_checks = 0;

-- ----------------------------
-- Table structure for `division`
-- ----------------------------
DROP TABLE IF EXISTS `division`;
CREATE TABLE `division` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `nickname` varchar(50) DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of division
-- ----------------------------
INSERT INTO `division` VALUES ('1', 'zz', 'xxcc', '2017-06-13 13:23:02', '2017-06-09 13:23:12', '0');

-- ----------------------------
-- Table structure for `smartglass`
-- ----------------------------
DROP TABLE IF EXISTS `smartglass`;
CREATE TABLE `smartglass` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `certification_key` varchar(50) NOT NULL,
  `div_id` int(11) DEFAULT NULL,
  `nickname` varchar(50) DEFAULT NULL,
  `width` smallint(6) NOT NULL DEFAULT '0',
  `height` smallint(6) NOT NULL DEFAULT '0',
  `frame_rate` smallint(6) NOT NULL DEFAULT '0',
  `create_time` datetime NOT NULL,
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` tinyint(1) unsigned zerofill NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_smartglass_certification_key` (`certification_key`),
  KEY `fk_div_smartglass_id` (`div_id`),
  CONSTRAINT `fk_div_smartglass_id` FOREIGN KEY (`div_id`) REFERENCES `division` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of smartglass
-- ----------------------------
INSERT INTO `smartglass` VALUES ('1', '123', '1', 'liu', '30', '50', '30', '2017-06-28 13:22:33', '2017-06-09 13:23:24', '0');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(1000) NOT NULL,
  `div_id` int(11) DEFAULT NULL,
  `nickname` varchar(50) DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `is_deleted` tinyint(3) unsigned zerofill NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_user_username` (`username`),
  KEY `fk_div_user_id` (`div_id`),
  CONSTRAINT `fk_div_user_id` FOREIGN KEY (`div_id`) REFERENCES `division` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'test', '8344DBC2E9CCEEE09196F73BA5D373BB81857238FB69C0BB16E5D29F07EC0CEDC6F37E2F39AC482B187F4EEA4D9AC351BB362D3FD13E86A05F6A92B15ED39045C5C3206264A6DADE44A785079F9B7033C36D4B4A7E22AD912952C58878785AE995B6D931EA3974A46AE5C16776A5F00ABE75B72CADAF1FA9164BFA21E9587A22', '1', '2', '2017-06-05 11:40:52', '0');