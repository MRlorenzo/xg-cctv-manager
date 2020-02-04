/*
Navicat MySQL Data Transfer

Source Server         : dev
Source Server Version : 50639
Source Host           : server:3306
Source Database       : casinos

Target Server Type    : MYSQL
Target Server Version : 50639
File Encoding         : 65001

Date: 2020-02-04 12:49:39
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_oss
-- ----------------------------
DROP TABLE IF EXISTS `sys_oss`;
CREATE TABLE `sys_oss` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `url` varchar(200) DEFAULT NULL COMMENT 'URL地址',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4079 DEFAULT CHARSET=utf8 COMMENT='文件上传';
