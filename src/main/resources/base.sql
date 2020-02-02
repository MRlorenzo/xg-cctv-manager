/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 80017
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 80017
File Encoding         : 65001

Date: 2020-01-26 03:17:36
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for js_exception
-- ----------------------------
DROP TABLE IF EXISTS `js_exception`;
CREATE TABLE `js_exception` (
  `exception_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '异常主键',
  `user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户名',
  `ip` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'IP地址',
  `file_name` varchar(255) DEFAULT NULL COMMENT '文件名',
  `line_no` int(11) DEFAULT NULL COMMENT '行号',
  `col_no` int(11) DEFAULT NULL COMMENT '列号',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `exception_val` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '异常信息',
  PRIMARY KEY (`exception_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4288 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- ----------------------------
-- Records of js_exception
-- ----------------------------

-- ----------------------------
-- Table structure for sys_department
-- ----------------------------
DROP TABLE IF EXISTS `sys_department`;
CREATE TABLE `sys_department` (
  `department_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `name` varchar(255) DEFAULT NULL COMMENT '部门中文名称',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '描述',
  `department_code` varchar(255) DEFAULT NULL COMMENT '部门编号',
  `sort` int(3) DEFAULT NULL COMMENT '排序字段',
  PRIMARY KEY (`department_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci COMMENT='部门表';

-- ----------------------------
-- Records of sys_department
-- ----------------------------
INSERT INTO `sys_department` VALUES ('1', '账房', 'Cage', 'Cage', '2');
INSERT INTO `sys_department` VALUES ('2', '财务', 'Finance', 'Finance', '4');
INSERT INTO `sys_department` VALUES ('3', '场面', 'Table Games', 'Table Games', '3');
INSERT INTO `sys_department` VALUES ('4', '董事长', 'President', 'President', '1');
INSERT INTO `sys_department` VALUES ('8', '客服', 'Customer service', 'CS', '30');
INSERT INTO `sys_department` VALUES ('9', '市场', 'Market', 'MK', '6');
INSERT INTO `sys_department` VALUES ('10', '人事', 'HR', 'HR', '5');
INSERT INTO `sys_department` VALUES ('11', '测试', '测试', 'TEST', '0');

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `path` varchar(255) NOT NULL COMMENT '请求路径',
  `pid` bigint(20) DEFAULT NULL COMMENT '父id',
  `is_del` int(1) DEFAULT '0' COMMENT '是否删除了',
  `sort` int(11) DEFAULT '0',
  `perms` varchar(255) DEFAULT NULL COMMENT '权限字符串',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES ('53', '/permission', '0', '0', '0', null, '权限相关');
INSERT INTO `sys_permission` VALUES ('54', 'role', '53', '0', '0', null, '编辑角色');
INSERT INTO `sys_permission` VALUES ('55', 'department', '53', '0', '0', null, '编辑部门');
INSERT INTO `sys_permission` VALUES ('56', 'users', '53', '0', '0', null, '编辑账号');
INSERT INTO `sys_permission` VALUES ('57', 'menus', '53', '0', '0', null, '编辑菜单，同时编辑权限');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '角色名称',
  `description` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `is_del` int(1) DEFAULT '0' COMMENT '是否已删除 0否 1是',
  `sort` int(3) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8 COMMENT='角色';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', 'admin', '管理员', '1', '2017-04-03 14:50:06', '0', '1');
INSERT INTO `sys_role` VALUES ('41', 'hr', '测试角色', '1', '2019-08-30 21:02:11', '0', '0');
INSERT INTO `sys_role` VALUES ('42', 'TEST', '测试角色', '1', '2019-09-04 18:19:50', '0', null);

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) NOT NULL,
  `permission_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=543 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES ('538', '1', '53');
INSERT INTO `sys_role_permission` VALUES ('539', '1', '54');
INSERT INTO `sys_role_permission` VALUES ('540', '1', '55');
INSERT INTO `sys_role_permission` VALUES ('541', '1', '56');
INSERT INTO `sys_role_permission` VALUES ('542', '1', '57');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态  0：禁用   1：正常',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `department_id` bigint(20) DEFAULT NULL COMMENT '部门id',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `no` varchar(255) DEFAULT NULL COMMENT '序号',
  `nick_name` varchar(64) DEFAULT NULL COMMENT '姓名',
  `work_no` varchar(64) DEFAULT NULL COMMENT '工号',
  `nationality` varchar(64) DEFAULT NULL COMMENT '国籍',
  `avatar` varchar(255) DEFAULT NULL COMMENT '照片',
  `position` varchar(64) DEFAULT NULL COMMENT '职位',
  `hire_date` datetime DEFAULT NULL COMMENT '聘用日期',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=677 DEFAULT CHARSET=utf8 COMMENT='系统用户';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', '1', null, '2016-11-11 11:11:11', '4', '管理员', null, null, null, null, null, null, null);
INSERT INTO `sys_user` VALUES ('675', 'test', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', '1', null, '2020-01-25 19:29:31', '10', 'test', null, null, null, null, null, null, null);
INSERT INTO `sys_user` VALUES ('676', 'test2', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', '1', null, '2020-01-25 19:31:15', '10', 'test', null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `role_id` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=922 DEFAULT CHARSET=utf8 COMMENT='用户与角色对应关系';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('921', '1', '1');
