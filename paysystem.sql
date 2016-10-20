/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50715
Source Host           : localhost:3306
Source Database       : paysystem

Target Server Type    : MYSQL
Target Server Version : 50715
File Encoding         : 65001

Date: 2016-10-20 17:18:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_auth`
-- ----------------------------
DROP TABLE IF EXISTS `t_auth`;
CREATE TABLE `t_auth` (
  `auth_id` int(11) NOT NULL AUTO_INCREMENT,
  `auth_name` varchar(64) NOT NULL,
  `auth_action` varchar(256) NOT NULL,
  `column_name` varchar(64) NOT NULL,
  PRIMARY KEY (`auth_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_auth
-- ----------------------------

-- ----------------------------
-- Table structure for `t_auth_column`
-- ----------------------------
DROP TABLE IF EXISTS `t_auth_column`;
CREATE TABLE `t_auth_column` (
  `column_id` int(11) NOT NULL AUTO_INCREMENT,
  `column_name` varchar(64) NOT NULL,
  PRIMARY KEY (`column_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_auth_column
-- ----------------------------

-- ----------------------------
-- Table structure for `t_category`
-- ----------------------------
DROP TABLE IF EXISTS `t_category`;
CREATE TABLE `t_category` (
  `cate_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '类别 指代 在职/退休/遗嘱/聘用制',
  `cate_name` char(32) NOT NULL,
  PRIMARY KEY (`cate_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_category
-- ----------------------------

-- ----------------------------
-- Table structure for `t_department`
-- ----------------------------
DROP TABLE IF EXISTS `t_department`;
CREATE TABLE `t_department` (
  `dep_id` int(11) NOT NULL AUTO_INCREMENT,
  `dep_name` varchar(64) NOT NULL,
  PRIMARY KEY (`dep_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_department
-- ----------------------------

-- ----------------------------
-- Table structure for `t_grade`
-- ----------------------------
DROP TABLE IF EXISTS `t_grade`;
CREATE TABLE `t_grade` (
  `grade_id` int(11) NOT NULL AUTO_INCREMENT,
  `grade_name` char(32) NOT NULL,
  PRIMARY KEY (`grade_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_grade
-- ----------------------------

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
-- Table structure for `t_group_auth`
-- ----------------------------
DROP TABLE IF EXISTS `t_group_auth`;
CREATE TABLE `t_group_auth` (
  `g_a_id` int(11) NOT NULL AUTO_INCREMENT,
  `group_id` int(11) NOT NULL,
  `auth_id` int(11) NOT NULL,
  PRIMARY KEY (`g_a_id`),
  KEY `ga_group` (`group_id`),
  KEY `ga_auth` (`auth_id`),
  CONSTRAINT `ga_auth` FOREIGN KEY (`auth_id`) REFERENCES `t_auth` (`auth_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `ga_group` FOREIGN KEY (`group_id`) REFERENCES `t_group` (`group_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_group_auth
-- ----------------------------

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
  CONSTRAINT `gm_menu` FOREIGN KEY (`menu_id`) REFERENCES `t_menu` (`menu_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8;

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
INSERT INTO `t_group_menu` VALUES ('12', '2', '2');
INSERT INTO `t_group_menu` VALUES ('14', '1', '2');
INSERT INTO `t_group_menu` VALUES ('15', '1', '3');
INSERT INTO `t_group_menu` VALUES ('16', '1', '6');
INSERT INTO `t_group_menu` VALUES ('17', '1', '10');
INSERT INTO `t_group_menu` VALUES ('18', '1', '15');
INSERT INTO `t_group_menu` VALUES ('19', '1', '4');
INSERT INTO `t_group_menu` VALUES ('20', '1', '5');
INSERT INTO `t_group_menu` VALUES ('21', '1', '7');
INSERT INTO `t_group_menu` VALUES ('22', '1', '8');
INSERT INTO `t_group_menu` VALUES ('23', '1', '9');
INSERT INTO `t_group_menu` VALUES ('24', '1', '11');
INSERT INTO `t_group_menu` VALUES ('25', '1', '12');
INSERT INTO `t_group_menu` VALUES ('26', '1', '13');
INSERT INTO `t_group_menu` VALUES ('27', '1', '14');
INSERT INTO `t_group_menu` VALUES ('28', '1', '16');
INSERT INTO `t_group_menu` VALUES ('29', '1', '17');
INSERT INTO `t_group_menu` VALUES ('30', '1', '18');
INSERT INTO `t_group_menu` VALUES ('31', '1', '19');
INSERT INTO `t_group_menu` VALUES ('32', '1', '20');
INSERT INTO `t_group_menu` VALUES ('33', '1', '21');
INSERT INTO `t_group_menu` VALUES ('34', '1', '22');

-- ----------------------------
-- Table structure for `t_menu`
-- ----------------------------
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu` (
  `menu_id` int(11) NOT NULL AUTO_INCREMENT,
  `menu_name` varchar(32) NOT NULL,
  `menu_url` varchar(64) NOT NULL,
  `menu_parent` int(11) NOT NULL,
  `menu_grade` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`menu_id`),
  KEY `menu_child` (`menu_parent`),
  CONSTRAINT `menu_child` FOREIGN KEY (`menu_parent`) REFERENCES `t_menu` (`menu_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_menu
-- ----------------------------
INSERT INTO `t_menu` VALUES ('1', '空置', '#', '1', '1');
INSERT INTO `t_menu` VALUES ('2', '首页', 'index.jsp', '1', '1');
INSERT INTO `t_menu` VALUES ('3', '用户信息', '#', '1', '1');
INSERT INTO `t_menu` VALUES ('4', '基本信息', 'self/info', '3', '2');
INSERT INTO `t_menu` VALUES ('5', '工资查询', 'self/paySearch', '3', '2');
INSERT INTO `t_menu` VALUES ('6', '人员管理', '#', '1', '1');
INSERT INTO `t_menu` VALUES ('7', '添加用户', 'person/add', '6', '2');
INSERT INTO `t_menu` VALUES ('8', '用户维护', 'person/manage', '6', '2');
INSERT INTO `t_menu` VALUES ('9', '导入导出', 'person/inout', '6', '2');
INSERT INTO `t_menu` VALUES ('10', '工资管理', '#', '1', '1');
INSERT INTO `t_menu` VALUES ('11', '工资发放', 'pay/pay', '10', '2');
INSERT INTO `t_menu` VALUES ('12', '工资维护', 'pay/manage', '10', '2');
INSERT INTO `t_menu` VALUES ('13', '奖金发放', 'pay/payPrize', '10', '2');
INSERT INTO `t_menu` VALUES ('14', '导入导出', 'pay/inout', '10', '2');
INSERT INTO `t_menu` VALUES ('15', '参数设置', '#', '1', '1');
INSERT INTO `t_menu` VALUES ('16', '工资项目', 'setting/payStruct', '15', '2');
INSERT INTO `t_menu` VALUES ('17', '个税', 'setting/revenue', '15', '2');
INSERT INTO `t_menu` VALUES ('18', '公积金', 'setting/paf', '15', '2');
INSERT INTO `t_menu` VALUES ('19', '部门管理', 'setting/dep', '15', '2');
INSERT INTO `t_menu` VALUES ('20', '职别管理', 'setting/grade', '15', '2');
INSERT INTO `t_menu` VALUES ('21', '职类管理', 'setting/cate', '15', '2');
INSERT INTO `t_menu` VALUES ('22', '角色分配', 'setting/group', '15', '1');

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'admin', 'a+123456', '1', '2016-10-17 16:46:16', '2016-10-17 16:46:20');
INSERT INTO `t_user` VALUES ('2', 'test', 'a+123456', '1', '2016-10-20 14:59:51', '2016-10-20 14:59:54');

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user_group
-- ----------------------------
INSERT INTO `t_user_group` VALUES ('1', '1', '1');
INSERT INTO `t_user_group` VALUES ('2', '1', '2');
INSERT INTO `t_user_group` VALUES ('3', '2', '2');

-- ----------------------------
-- Table structure for `t_user_info`
-- ----------------------------
DROP TABLE IF EXISTS `t_user_info`;
CREATE TABLE `t_user_info` (
  `user_id` int(11) NOT NULL,
  `name` char(20) NOT NULL,
  `sex` char(8) NOT NULL,
  `birthday` date NOT NULL,
  `id_card` char(20) NOT NULL,
  `join_time` date NOT NULL,
  `grade_name` char(32) NOT NULL,
  `cate_name` char(64) NOT NULL,
  `ic_card` char(32) NOT NULL,
  `dep_name` varchar(64) NOT NULL,
  `job_no` char(20) NOT NULL,
  KEY `info_userid` (`user_id`),
  CONSTRAINT `info_userid` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user_info
-- ----------------------------
INSERT INTO `t_user_info` VALUES ('2', 'test', '男', '1992-05-21', '441231199205213418', '2016-10-20', '科员', '在职', '1234567899876543210', '科技科', '0000');
