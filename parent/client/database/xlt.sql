/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : xlt

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2019-01-03 21:15:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_barrage`
-- ----------------------------
DROP TABLE IF EXISTS `t_barrage`;
CREATE TABLE `t_barrage` (
  `contentId` int(50) NOT NULL COMMENT '唯一标识',
  `content` varchar(255) NOT NULL COMMENT '内容',
  `time` datetime NOT NULL COMMENT '时间',
  `imageId` int(11) NOT NULL,
  PRIMARY KEY (`contentId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_barrage
-- ----------------------------
INSERT INTO `t_barrage` VALUES ('1', '啦啦啦啦啦啦啦啦啦啦啦啦', '2018-06-28 20:02:00', '1');
INSERT INTO `t_barrage` VALUES ('2', '628', '2018-07-05 14:23:34', '2');
INSERT INTO `t_barrage` VALUES ('3', '我想你', '2018-07-03 14:24:16', '1');
INSERT INTO `t_barrage` VALUES ('4', '今天下雨', '2018-07-03 14:24:34', '1');
INSERT INTO `t_barrage` VALUES ('5', '趁加班来看看6000寸的大屏', '2018-07-01 14:25:00', '1');
INSERT INTO `t_barrage` VALUES ('6', '今天下雨，被淋成了落汤鸡', '2018-07-04 20:21:42', '1');
INSERT INTO `t_barrage` VALUES ('7', '今天心情超级好', '2018-07-05 14:26:24', '1');
INSERT INTO `t_barrage` VALUES ('8', 'test', '2018-12-20 20:07:21', '1');

-- ----------------------------
-- Table structure for `t_images`
-- ----------------------------
DROP TABLE IF EXISTS `t_images`;
CREATE TABLE `t_images` (
  `imageId` int(11) NOT NULL COMMENT '图片id',
  `imageAddress` varchar(100) NOT NULL COMMENT '图片的地址',
  `imageAlias` char(255) NOT NULL COMMENT '图片别名',
  PRIMARY KEY (`imageId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_images
-- ----------------------------
INSERT INTO `t_images` VALUES ('1', '/img/home/1.jpg', '自定义1');
INSERT INTO `t_images` VALUES ('2', '/img/home/2.jpg', '自定义2');
INSERT INTO `t_images` VALUES ('3', '/img/home/3.jpg', '自定义3');
INSERT INTO `t_images` VALUES ('4', '/img/home/4.jpg', '自定义4');
INSERT INTO `t_images` VALUES ('5', '/img/home/5.jpg', '自定义5');
INSERT INTO `t_images` VALUES ('6', '/img/home/6.jpg', '自定义6');
INSERT INTO `t_images` VALUES ('7', '/img/home/7.jpg', '自定义7');
INSERT INTO `t_images` VALUES ('8', '/img/home/8.jpg', '自定义8');
INSERT INTO `t_images` VALUES ('9', '/img/home/9.jpg', '自定义9');
INSERT INTO `t_images` VALUES ('10', '/img/home/10.jpg', '自定义10');
INSERT INTO `t_images` VALUES ('11', '/img/home/11.jpg', '自定义11');

-- ----------------------------
-- Table structure for `t_log`
-- ----------------------------
DROP TABLE IF EXISTS `t_log`;
CREATE TABLE `t_log` (
  `logId` int(20) NOT NULL COMMENT '主键',
  `userId` int(11) NOT NULL COMMENT '用户id，外键',
  `logType` varchar(10) DEFAULT NULL COMMENT '日子类型',
  `logMessage` varchar(30) DEFAULT NULL COMMENT '日志信息',
  `logIsError` char(1) NOT NULL COMMENT '是否是错误信息',
  `logTime` datetime NOT NULL COMMENT '日志产生时间',
  PRIMARY KEY (`logId`),
  KEY `fk_userId` (`userId`),
  CONSTRAINT `fk_userId` FOREIGN KEY (`userId`) REFERENCES `t_user` (`userId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_log
-- ----------------------------
INSERT INTO `t_log` VALUES ('0', '1', '1', 'login', '0', '2018-12-15 16:31:46');
INSERT INTO `t_log` VALUES ('1', '1', '1', 'login', '0', '2018-12-15 17:23:12');
INSERT INTO `t_log` VALUES ('2', '1', '1', 'login', '0', '2018-12-15 17:26:40');
INSERT INTO `t_log` VALUES ('3', '1', '1', 'login', '0', '2018-12-16 18:09:21');
INSERT INTO `t_log` VALUES ('4', '1', '1', 'login', '0', '2018-12-16 18:24:30');
INSERT INTO `t_log` VALUES ('5', '1', '1', 'login', '0', '2018-12-16 18:51:15');
INSERT INTO `t_log` VALUES ('6', '1', '1', 'login', '0', '2018-12-16 19:56:08');
INSERT INTO `t_log` VALUES ('7', '1', '1', 'login', '0', '2018-12-16 20:27:12');
INSERT INTO `t_log` VALUES ('8', '1', '1', 'login', '0', '2018-12-16 21:25:13');
INSERT INTO `t_log` VALUES ('9', '1', '1', 'login', '0', '2018-12-17 20:33:26');
INSERT INTO `t_log` VALUES ('10', '1', '1', 'login', '0', '2018-12-17 21:10:04');
INSERT INTO `t_log` VALUES ('11', '1', '1', 'login', '0', '2018-12-17 21:13:00');
INSERT INTO `t_log` VALUES ('12', '1', '1', 'login', '0', '2018-12-18 19:32:53');
INSERT INTO `t_log` VALUES ('13', '1', '1', 'login', '0', '2018-12-18 20:07:47');
INSERT INTO `t_log` VALUES ('14', '1', '1', 'login', '0', '2018-12-19 20:23:24');
INSERT INTO `t_log` VALUES ('15', '1', '1', 'login', '0', '2018-12-19 21:01:20');
INSERT INTO `t_log` VALUES ('16', '1', '1', 'login', '0', '2018-12-19 21:31:32');
INSERT INTO `t_log` VALUES ('17', '1', '1', 'login', '0', '2018-12-20 19:39:51');
INSERT INTO `t_log` VALUES ('18', '1', '1', 'login', '0', '2018-12-20 21:43:32');
INSERT INTO `t_log` VALUES ('19', '1', '1', 'login', '0', '2018-12-20 22:01:23');
INSERT INTO `t_log` VALUES ('20', '1', '1', 'login', '0', '2018-12-21 19:56:54');
INSERT INTO `t_log` VALUES ('21', '1', '1', 'login', '0', '2018-12-22 12:07:00');
INSERT INTO `t_log` VALUES ('22', '1', '1', 'login', '0', '2018-12-22 14:39:48');
INSERT INTO `t_log` VALUES ('23', '1', '1', 'login', '0', '2018-12-22 15:28:34');
INSERT INTO `t_log` VALUES ('24', '1', '1', 'login', '0', '2018-12-22 17:12:41');
INSERT INTO `t_log` VALUES ('25', '1', '1', 'login', '0', '2018-12-22 18:04:04');
INSERT INTO `t_log` VALUES ('26', '1', '1', 'login', '0', '2018-12-22 20:16:27');
INSERT INTO `t_log` VALUES ('27', '1', '1', 'login', '0', '2018-12-22 20:26:15');
INSERT INTO `t_log` VALUES ('28', '1', '1', 'login', '0', '2018-12-23 12:03:29');
INSERT INTO `t_log` VALUES ('29', '1', '1', 'login', '0', '2018-12-23 13:26:24');
INSERT INTO `t_log` VALUES ('30', '1', '1', 'login', '0', '2018-12-23 20:42:01');
INSERT INTO `t_log` VALUES ('31', '1', '1', 'login', '0', '2018-12-25 22:26:45');
INSERT INTO `t_log` VALUES ('32', '1', '1', 'login', '0', '2018-12-25 22:26:45');
INSERT INTO `t_log` VALUES ('33', '1', '1', 'login', '0', '2018-12-28 19:54:55');
INSERT INTO `t_log` VALUES ('34', '1', '1', 'login', '0', '2019-01-02 19:32:39');
INSERT INTO `t_log` VALUES ('35', '2', '1', 'login', '0', '2019-01-02 19:33:47');

-- ----------------------------
-- Table structure for `t_permission`
-- ----------------------------
DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission` (
  `pId` int(11) NOT NULL DEFAULT '0',
  `permission` varchar(20) NOT NULL COMMENT '权限',
  `roleId` int(11) NOT NULL,
  PRIMARY KEY (`pId`),
  KEY `fk_p_roleId` (`roleId`),
  CONSTRAINT `fk_p_roleId` FOREIGN KEY (`roleId`) REFERENCES `t_role` (`roleId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_permission
-- ----------------------------
INSERT INTO `t_permission` VALUES ('1', 'DataBase', '1');
INSERT INTO `t_permission` VALUES ('2', 'See', '1');

-- ----------------------------
-- Table structure for `t_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `userId` int(11) NOT NULL COMMENT '用户id',
  `roleId` int(11) NOT NULL COMMENT '角色id',
  `role` varchar(20) NOT NULL COMMENT '角色',
  PRIMARY KEY (`roleId`),
  KEY `fk__role` (`userId`),
  CONSTRAINT `fk__role` FOREIGN KEY (`userId`) REFERENCES `t_user` (`userId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('1', '1', 'SuperAdmin');
INSERT INTO `t_role` VALUES ('2', '2', 'General');
INSERT INTO `t_role` VALUES ('3', '3', 'General');

-- ----------------------------
-- Table structure for `t_session`
-- ----------------------------
DROP TABLE IF EXISTS `t_session`;
CREATE TABLE `t_session` (
  `userId` int(11) NOT NULL,
  `sessionId` varchar(255) NOT NULL,
  `updateTime` datetime NOT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_session
-- ----------------------------
INSERT INTO `t_session` VALUES ('1', '658789905AB9F8131B2447178433B799', '2018-12-14 21:36:51');

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `userId` int(20) NOT NULL COMMENT '用户id',
  `userName` varchar(20) NOT NULL COMMENT '账号名称',
  `userPwd` varchar(255) NOT NULL,
  `uIsLock` char(1) NOT NULL COMMENT '标志位,0表示锁定',
  `uSalt` blob NOT NULL COMMENT '盐',
  `uIsManage` char(1) NOT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', '123456789', '134dcd479fab4cab519a04b30ebd73', '0', 0x265C3D5894BD6D0148EBFE7CC8606045, '1');
INSERT INTO `t_user` VALUES ('2', 'test', 'c51459c5f56aaeef2f5548622a9c4e2', '0', 0x5A5260FB8E74D742C3D976BDBF59436A, '0');
INSERT INTO `t_user` VALUES ('3', 'test2', 'd3953bb225e446583e461235fe47f34c', '0', 0xB8E8F9C0B022339C91D23B34C728C93F, '0');

-- ----------------------------
-- Table structure for `t_wechat`
-- ----------------------------
DROP TABLE IF EXISTS `t_wechat`;
CREATE TABLE `t_wechat` (
  `w_id` int(11) NOT NULL,
  `w_content` varchar(255) NOT NULL,
  `w_temp1` varchar(255) DEFAULT NULL,
  `w_time` datetime NOT NULL,
  `w_nickName` varchar(30) NOT NULL,
  PRIMARY KEY (`w_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_wechat
-- ----------------------------
INSERT INTO `t_wechat` VALUES ('1', '123', null, '2019-01-02 11:33:58', '游客');
INSERT INTO `t_wechat` VALUES ('2', 'test', null, '2019-01-02 15:13:00', '昊天');
