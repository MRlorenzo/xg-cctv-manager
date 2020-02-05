/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 80017
Source Host           : localhost:3306
Source Database       : test2

Target Server Type    : MYSQL
Target Server Version : 80017
File Encoding         : 65001

Date: 2020-02-05 11:28:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for high_action
-- ----------------------------
DROP TABLE IF EXISTS `high_action`;
CREATE TABLE `high_action` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `no` varchar(64) DEFAULT NULL COMMENT '序号',
  `date` datetime DEFAULT NULL COMMENT '日期',
  `table_code` varchar(32) DEFAULT NULL COMMENT '台号',
  `code` varchar(64) DEFAULT NULL COMMENT '编码',
  `coin_code` varchar(32) DEFAULT NULL COMMENT '币种',
  `total` decimal(20,2) DEFAULT NULL COMMENT '金额',
  `report` varchar(1024) DEFAULT NULL COMMENT '上下水报告',
  `involve_uid` bigint(20) DEFAULT NULL COMMENT '涉及员工',
  `monitor` varchar(64) DEFAULT NULL COMMENT '监控部',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_uid` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `urls` varchar(1024) DEFAULT NULL COMMENT '图片路径以'',''分割',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='西港监控部高额活动表';
