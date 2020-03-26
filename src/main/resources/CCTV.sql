/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 80017
Source Host           : localhost:3306
Source Database       : test2

Target Server Type    : MYSQL
Target Server Version : 80017
File Encoding         : 65001

Date: 2020-02-05 11:31:59
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL COMMENT '客户名称',
  `card_number` varchar(64) DEFAULT NULL COMMENT '卡号',
  `location` varchar(255) DEFAULT NULL COMMENT '位置',
  `avg_betting` decimal(20,2) DEFAULT NULL COMMENT '平均下注',
  `nationality` varchar(64) DEFAULT NULL COMMENT '国籍',
  `photo` varchar(255) DEFAULT NULL COMMENT '照片',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `create_uid` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci COMMENT='玩家清单表';

-- ----------------------------
-- Table structure for daily_log
-- ----------------------------
DROP TABLE IF EXISTS `daily_log`;
CREATE TABLE `daily_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` date DEFAULT NULL COMMENT '日期',
  `time` varchar(64) DEFAULT NULL COMMENT '时间',
  `table_code` varchar(32) DEFAULT NULL COMMENT '台号',
  `subject` varchar(255) DEFAULT NULL COMMENT '主题',
  `details` varchar(1024) DEFAULT NULL COMMENT '细节',
  `alerter_name` varchar(64) DEFAULT NULL COMMENT '通知人',
  `department_id` int(11) DEFAULT NULL COMMENT '部门ID',
  `monitor` varchar(64) DEFAULT NULL COMMENT '监控部',
  `conclusion` varchar(255) DEFAULT NULL COMMENT '结论',
  `create_uid` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `urls` varchar(1024) DEFAULT NULL COMMENT '图片路径以'',''分割',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci COMMENT='西港监控部日志表';

-- ----------------------------
-- Table structure for fill_credit_log
-- ----------------------------
DROP TABLE IF EXISTS `fill_credit_log`;
CREATE TABLE `fill_credit_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` int(11) DEFAULT '1' COMMENT '1 加彩 2 缴码',
  `date` date DEFAULT NULL COMMENT '日期',
  `time` varchar(64) DEFAULT NULL COMMENT '时间',
  `table_code` varchar(32) DEFAULT NULL COMMENT '台号',
  `total` decimal(20,2) DEFAULT NULL COMMENT '金额',
  `coin_code` varchar(32) DEFAULT NULL COMMENT '币种',
  `alerter_name` varchar(64) DEFAULT NULL COMMENT '通知人',
  `monitor` varchar(64) DEFAULT NULL COMMENT '监控部',
  `create_uid` bigint(20) DEFAULT NULL COMMENT '创建人id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci COMMENT='西港监控部日常加彩/缴码表';

-- ----------------------------
-- Table structure for high_action
-- ----------------------------
DROP TABLE IF EXISTS `high_action`;
CREATE TABLE `high_action` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` date DEFAULT NULL COMMENT '日期',
  `time` varchar(64) DEFAULT NULL COMMENT '时间',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci COMMENT='西港监控部高额活动表';

-- ----------------------------
-- Table structure for incident_log
-- ----------------------------
DROP TABLE IF EXISTS `incident_log`;
CREATE TABLE `incident_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` date DEFAULT NULL COMMENT '日期',
  `time` varchar(64) DEFAULT NULL COMMENT '时间',
  `table_code` varchar(32) DEFAULT NULL COMMENT '台号',
  `code` varchar(255) DEFAULT NULL COMMENT '事件编码',
  `coin_code` varchar(32) DEFAULT NULL COMMENT '币种',
  `total` decimal(20,2) DEFAULT NULL COMMENT '金额',
  `report` varchar(1024) DEFAULT NULL COMMENT '报告',
  `involve_uid` bigint(20) DEFAULT NULL COMMENT '涉及员工',
  `department_id` bigint(20) DEFAULT NULL COMMENT '部门id',
  `monitor` varchar(255) DEFAULT NULL COMMENT '监控部',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_uid` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `urls` varchar(1024) DEFAULT NULL COMMENT '图片路径以'',''分割',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci COMMENT='西港监控部事件报告表';

-- ----------------------------
-- Table structure for title_list_item
-- ----------------------------
DROP TABLE IF EXISTS `title_list_item`;
CREATE TABLE `title_list_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `subject_id` bigint(20) DEFAULT NULL COMMENT '事件主题名称Id',
  `text` varchar(1024) DEFAULT NULL COMMENT '错误内容',
  `code` varchar(32) DEFAULT NULL COMMENT '代码',
  `create_uid` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci COMMENT='事件名称及编号';

-- ----------------------------
-- Table structure for title_main
-- ----------------------------
DROP TABLE IF EXISTS `title_main`;
CREATE TABLE `title_main` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `subject` varchar(255) DEFAULT NULL COMMENT '事件名称',
  `create_uid` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci COMMENT='事件记录主题名称表';
