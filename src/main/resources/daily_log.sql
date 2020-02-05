/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 80017
Source Host           : localhost:3306
Source Database       : test2

Target Server Type    : MYSQL
Target Server Version : 80017
File Encoding         : 65001

Date: 2020-02-05 11:15:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for daily_log
-- ----------------------------
DROP TABLE IF EXISTS `daily_log`;
CREATE TABLE `daily_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `no` varchar(64) DEFAULT NULL COMMENT '序号',
  `date` datetime DEFAULT NULL COMMENT '日期',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='西港监控部日志表';
