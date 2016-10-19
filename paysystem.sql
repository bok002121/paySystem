/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50715
Source Host           : localhost:3306
Source Database       : paysystem

Target Server Type    : MYSQL
Target Server Version : 50715
File Encoding         : 65001

Date: 2016-10-19 17:26:29
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_group`
-- ----------------------------
DROP TABLE IF EXISTS `t_group`;
CREATE TABLE `t_group` (
  `group_id` int(11) NOT NULL AUTO_INCREMENT,
  `group_name` char(20) NOT NULL,
  PRIMARY KEY (`group_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_group
-- ----------------------------
INSERT INTO `t_group` VALUES ('1', 'admin');
INSERT INTO `t_group` VALUES ('2', 'test');

-- ----------------------------
-- Table structure for `t_group_menu`
-- ----------------------------
DROP TABLE IF EXISTS `t_group_menu`;
CREATE TABLE `t_group_menu` (
  `g_m_id` int(11) NOT NULL AUTO_INCREMENT,
  `group_id` int(11) NOT NULL,
  `menu_id` int(11) NOT NULL,
  PRIMARY KEY (`g_m_id`),
  KEY `gm_group` (`group_id`),
  KEY `gm_menu` (`menu_id`),
  CONSTRAINT `gm_group` FOREIGN KEY (`group_id`) REFERENCES `t_group` (`group_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `gm_menu` FOREIGN KEY (`menu_id`) REFERENCES `t_menu` (`menut_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_group_menu
-- ----------------------------
INSERT INTO `t_group_menu` VALUES ('1', '1', '2');
INSERT INTO `t_group_menu` VALUES ('2', '1', '3');
INSERT INTO `t_group_menu` VALUES ('3', '1', '4');
INSERT INTO `t_group_menu` VALUES ('5', '1', '5');
INSERT INTO `t_group_menu` VALUES ('6', '1', '6');
INSERT INTO `t_group_menu` VALUES ('7', '1', '7');
INSERT INTO `t_group_menu` VALUES ('8', '1', '8');
INSERT INTO `t_group_menu` VALUES ('9', '2', '3');
INSERT INTO `t_group_menu` VALUES ('10', '2', '4');
INSERT INTO `t_group_menu` VALUES ('11', '2', '5');
INSERT INTO `t_group_menu` VALUES ('12', '1', '9');

-- ----------------------------
-- Table structure for `t_menu`
-- ----------------------------
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu` (
  `menut_id` int(11) NOT NULL AUTO_INCREMENT,
  `menu_name` varchar(32) NOT NULL,
  `menu_url` varchar(64) NOT NULL,
  `menu_parent` int(11) NOT NULL,
  `menu_grade` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`menut_id`),
  KEY `menu_child` (`menu_parent`),
  CONSTRAINT `menu_child` FOREIGN KEY (`menu_parent`) REFERENCES `t_menu` (`menut_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_menu
-- ----------------------------
INSERT INTO `t_menu` VALUES ('1', '空置', '#', '1', '1');
INSERT INTO `t_menu` VALUES ('2', '首页', 'index.jsp', '1', '1');
INSERT INTO `t_menu` VALUES ('3', '用户信息', '#', '1', '1');
INSERT INTO `t_menu` VALUES ('4', '基本信息', 'self/info', '3', '2');
INSERT INTO `t_menu` VALUES ('5', '工资查询', 'self/paySearch', '3', '2');
INSERT INTO `t_menu` VALUES ('6', '人员管理', '#', '1', '1');
INSERT INTO `t_menu` VALUES ('7', '用户管理', 'person/manage', '6', '2');
INSERT INTO `t_menu` VALUES ('8', '导入导出', 'person/inout', '6', '2');
INSERT INTO `t_menu` VALUES ('9', 'test', '#', '4', '3');

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(20) NOT NULL,
  `user_pwd` char(64) NOT NULL,
  `user_status` int(11) NOT NULL COMMENT '0 停用 1 启用',
  `createtime` datetime NOT NULL COMMENT '开户时间',
  `updatetime` datetime NOT NULL COMMENT '更新登录时间',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'admin', 'a+123456', '1', '2016-10-17 16:46:16', '2016-10-17 16:46:20');

-- ----------------------------
-- Table structure for `t_user_group`
-- ----------------------------
DROP TABLE IF EXISTS `t_user_group`;
CREATE TABLE `t_user_group` (
  `u_g_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `group_id` int(11) NOT NULL,
  PRIMARY KEY (`u_g_id`),
  KEY `ug_user` (`user_id`),
  KEY `ug_group` (`group_id`),
  CONSTRAINT `ug_group` FOREIGN KEY (`group_id`) REFERENCES `t_group` (`group_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `ug_user` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user_group
-- ----------------------------
INSERT INTO `t_user_group` VALUES ('1', '1', '1');
INSERT INTO `t_user_group` VALUES ('2', '1', '2');
