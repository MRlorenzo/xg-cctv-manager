/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 80017
Source Host           : localhost:3306
Source Database       : test2

Target Server Type    : MYSQL
Target Server Version : 80017
File Encoding         : 65001

Date: 2020-02-05 11:32:42
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for js_exception
-- ----------------------------
DROP TABLE IF EXISTS `js_exception`;
CREATE TABLE `js_exception` (
  `exception_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '异常主键',
  `user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户名',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `stack` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '异常信息',
  `url` varchar(255) DEFAULT NULL,
  `message` varchar(255) DEFAULT NULL,
  `info` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`exception_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
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
-- Table structure for sys_oss
-- ----------------------------
DROP TABLE IF EXISTS `sys_oss`;
CREATE TABLE `sys_oss` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `url` varchar(200) DEFAULT NULL COMMENT 'URL地址',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='文件上传';

-- ----------------------------
-- Records of sys_oss
-- ----------------------------

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
) ENGINE=InnoDB AUTO_INCREMENT=136 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES ('53', '/permission', '0', '0', '0', null, '权限相关');
INSERT INTO `sys_permission` VALUES ('54', 'role', '53', '0', '0', 'sysPermission:list,sysRole:list', '编辑角色');
INSERT INTO `sys_permission` VALUES ('55', 'department', '53', '0', '0', 'sysDepartment:list', '编辑部门');
INSERT INTO `sys_permission` VALUES ('57', 'menus', '53', '0', '0', 'sysPermission:list', '编辑菜单，同时编辑权限');
INSERT INTO `sys_permission` VALUES ('58', '/components', '0', '0', '0', null, '组件');
INSERT INTO `sys_permission` VALUES ('59', 'tinymce', '58', '0', '0', null, '富文本编辑器');
INSERT INTO `sys_permission` VALUES ('60', 'markdown', '58', '0', '0', null, 'Markdown');
INSERT INTO `sys_permission` VALUES ('61', 'json-editor', '58', '0', '0', null, 'JSON 编辑器');
INSERT INTO `sys_permission` VALUES ('62', 'split-pane', '58', '0', '0', null, 'Splitpane');
INSERT INTO `sys_permission` VALUES ('63', 'avatar-upload', '58', '0', '0', null, '头像上传');
INSERT INTO `sys_permission` VALUES ('64', 'dropzone', '58', '0', '0', null, 'Dropzone');
INSERT INTO `sys_permission` VALUES ('65', 'sticky', '58', '0', '0', null, 'Sticky');
INSERT INTO `sys_permission` VALUES ('66', 'count-to', '58', '0', '0', null, 'Count To');
INSERT INTO `sys_permission` VALUES ('67', 'mixin', '58', '0', '0', null, '小组件');
INSERT INTO `sys_permission` VALUES ('68', 'back-to-top', '58', '0', '0', null, '返回顶部');
INSERT INTO `sys_permission` VALUES ('69', 'drag-dialog', '58', '0', '0', null, '拖拽 Dialog');
INSERT INTO `sys_permission` VALUES ('70', 'drag-select', '58', '0', '0', null, '拖拽 Select');
INSERT INTO `sys_permission` VALUES ('71', 'drag-kanban', '58', '0', '0', null, '可拖拽看板');
INSERT INTO `sys_permission` VALUES ('72', '/error', '0', '0', '0', null, '错误页面');
INSERT INTO `sys_permission` VALUES ('73', '401', '72', '0', '0', null, '401');
INSERT INTO `sys_permission` VALUES ('74', '404', '72', '0', '0', null, '404');
INSERT INTO `sys_permission` VALUES ('75', '/error-log', '0', '0', '0', null, '错误日志');
INSERT INTO `sys_permission` VALUES ('76', 'error-log-index', '75', '0', '0', null, '错误日志');
INSERT INTO `sys_permission` VALUES ('77', '/excel', '0', '0', '0', null, 'Excel');
INSERT INTO `sys_permission` VALUES ('78', 'export-excel', '77', '0', '0', null, '导出 Excel');
INSERT INTO `sys_permission` VALUES ('79', 'export-selected-excel', '77', '0', '0', null, '导出 已选择项');
INSERT INTO `sys_permission` VALUES ('80', 'export-merge-header', '77', '0', '0', null, '导出 多级表头');
INSERT INTO `sys_permission` VALUES ('81', 'upload-excel', '77', '0', '0', null, '上传 Excel');
INSERT INTO `sys_permission` VALUES ('82', '/zip', '0', '0', '0', null, 'Zip');
INSERT INTO `sys_permission` VALUES ('83', 'zip-download', '82', '0', '0', null, 'Export Zip');
INSERT INTO `sys_permission` VALUES ('84', '/pdf', '0', '0', '0', null, 'PDF');
INSERT INTO `sys_permission` VALUES ('85', 'pdf-index', '84', '0', '0', null, 'PDF');
INSERT INTO `sys_permission` VALUES ('86', '/theme', '0', '0', '0', null, '换肤');
INSERT INTO `sys_permission` VALUES ('87', 'theme-index', '86', '0', '0', null, '换肤');
INSERT INTO `sys_permission` VALUES ('88', '/i18n', '0', '0', '0', null, '国际化');
INSERT INTO `sys_permission` VALUES ('89', 'i18n-index', '88', '0', '0', null, '国际化');
INSERT INTO `sys_permission` VALUES ('90', '/icon', '0', '0', '0', null, '图标');
INSERT INTO `sys_permission` VALUES ('91', 'icon-index', '90', '0', '0', null, '图标');
INSERT INTO `sys_permission` VALUES ('92', '/cctv', '0', '0', '0', null, 'CCTV');
INSERT INTO `sys_permission` VALUES ('93', 'daily-log', '92', '0', '0', 'dailyLog:list', '监控部日志');
INSERT INTO `sys_permission` VALUES ('94', 'incident-log', '92', '0', '0', 'incidentLog:list', '事件报告');
INSERT INTO `sys_permission` VALUES ('95', 'high-action', '92', '0', '0', 'highAction:list', '高额活动');
INSERT INTO `sys_permission` VALUES ('96', 'title-list', '92', '0', '0', 'titleListItem:list', '事件名称及编号');
INSERT INTO `sys_permission` VALUES ('97', 'fill', '92', '0', '0', 'fillCreditLog:list', '加彩日志');
INSERT INTO `sys_permission` VALUES ('98', 'credit', '92', '0', '0', 'fillCreditLog:list', '缴码日志');
INSERT INTO `sys_permission` VALUES ('99', 'customer', '92', '0', '0', 'customer:list', '玩家清单');
INSERT INTO `sys_permission` VALUES ('100', 'title-subject', '92', '0', '0', 'titleMain:list', '事件主题');
INSERT INTO `sys_permission` VALUES ('101', 'dailyLog:excel', '93', '0', '0', 'dailyLog:excel', '导出excel的权限');
INSERT INTO `sys_permission` VALUES ('102', 'menus:save', '57', '0', '0', 'sysPermission:save', '新增菜单的权利');
INSERT INTO `sys_permission` VALUES ('103', 'users', '92', '0', '0', 'sysuser:list', '编辑账号的界面');
INSERT INTO `sys_permission` VALUES ('104', 'users:info', '103', '0', '0', 'sysuser:info', '查看账号信息(必需的权限)');
INSERT INTO `sys_permission` VALUES ('105', 'customer:excel', '99', '0', '0', 'customer:excel', '导出excel的权限');
INSERT INTO `sys_permission` VALUES ('106', 'customer:save', '99', '0', '0', 'customer:save', '保存客户信息的权限');
INSERT INTO `sys_permission` VALUES ('107', 'customer:delete', '99', '0', '0', 'customer:delete', '删除客户信息的权限');
INSERT INTO `sys_permission` VALUES ('108', 'dailyLog:save', '93', '0', '0', 'dailyLog:save', '保存');
INSERT INTO `sys_permission` VALUES ('109', 'dailyLog:delete', '93', '0', '0', 'dailyLog:delete', '删除');
INSERT INTO `sys_permission` VALUES ('110', 'fill:excel', '97', '0', '0', 'fillCreditLog:excel', '导出excel的权限');
INSERT INTO `sys_permission` VALUES ('111', 'credit:excel', '98', '0', '0', 'fillCreditLog:excel', '导出excel的权限');
INSERT INTO `sys_permission` VALUES ('112', 'fill:save', '97', '0', '0', 'fillCreditLog:save', '保存');
INSERT INTO `sys_permission` VALUES ('113', 'credit:save', '98', '0', '0', 'fillCreditLog:save', '保存');
INSERT INTO `sys_permission` VALUES ('114', 'fill:delete', '92', '0', '0', 'fillCreditLog:delete', '删除');
INSERT INTO `sys_permission` VALUES ('115', 'credit:delete', '98', '0', '0', 'fillCreditLog:delete', '删除');
INSERT INTO `sys_permission` VALUES ('116', 'fill:delete', '97', '0', '0', 'fillCreditLog:delete', '删除');
INSERT INTO `sys_permission` VALUES ('117', 'highAction:excel', '95', '0', '0', 'highAction:excel', '导出excel的权限');
INSERT INTO `sys_permission` VALUES ('118', 'highAction:save', '95', '0', '0', 'highAction:save', '保存信息');
INSERT INTO `sys_permission` VALUES ('119', 'highAction:delete', '95', '0', '0', 'highAction:delete', '删除');
INSERT INTO `sys_permission` VALUES ('120', 'incidentLog:excel', '94', '0', '0', 'incidentLog:excel', '导出excel的权限');
INSERT INTO `sys_permission` VALUES ('121', 'incidentLog:save', '94', '0', '0', 'incidentLog:save', '保存信息');
INSERT INTO `sys_permission` VALUES ('122', 'incidentLog:delete', '94', '0', '0', 'incidentLog:delete', '删除');
INSERT INTO `sys_permission` VALUES ('123', 'sysDepartment:save', '55', '0', '0', 'sysDepartment:save', '新增');
INSERT INTO `sys_permission` VALUES ('124', 'sysDepartment:delete', '55', '0', '0', 'sysDepartment:delete', '删除');
INSERT INTO `sys_permission` VALUES ('125', 'daily-log:upload', '93', '0', '0', 'sysOss:upload', '上传文件');
INSERT INTO `sys_permission` VALUES ('126', 'incident-log:upload', '94', '0', '0', 'sysOss:upload', '上传文件');
INSERT INTO `sys_permission` VALUES ('127', 'menus:delete', '57', '0', '0', 'sysPermission:delete', '删除');
INSERT INTO `sys_permission` VALUES ('128', 'role:save', '54', '0', '0', 'sysRole:save', '新增角色');
INSERT INTO `sys_permission` VALUES ('129', 'users:search', '103', '0', '0', 'sysuser:search', '搜索与模糊匹配');
INSERT INTO `sys_permission` VALUES ('130', 'user:save', '103', '0', '0', 'sysuser:save', '保存');
INSERT INTO `sys_permission` VALUES ('131', 'users:delete', '103', '0', '0', 'sysuser:delete', '删除');
INSERT INTO `sys_permission` VALUES ('132', 'title-list:save', '96', '0', '0', 'titleListItem:save', '保存');
INSERT INTO `sys_permission` VALUES ('133', 'title-list:delete', '96', '0', '0', 'titleListItem:delete', '删除');
INSERT INTO `sys_permission` VALUES ('134', 'title-subject:save', '100', '0', '0', 'titleMain:save', '保存');
INSERT INTO `sys_permission` VALUES ('135', 'title-subject:delete', '100', '0', '0', 'titleMain:delete', '删除');
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='角色';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', 'admin', '管理员', '1', '2017-04-03 14:50:06', '0', '1');
INSERT INTO `sys_role` VALUES ('2', 'hr', '测试角色', '1', '2019-08-30 21:02:11', '0', '0');

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) NOT NULL,
  `permission_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=895 DEFAULT CHARSET=utf8;

-- ---- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES ('812', '41', '104');
INSERT INTO `sys_role_permission` VALUES ('813', '41', '103');
INSERT INTO `sys_role_permission` VALUES ('814', '41', '92');
INSERT INTO `sys_role_permission` VALUES ('815', '1', '128');
INSERT INTO `sys_role_permission` VALUES ('816', '1', '54');
INSERT INTO `sys_role_permission` VALUES ('817', '1', '123');
INSERT INTO `sys_role_permission` VALUES ('818', '1', '124');
INSERT INTO `sys_role_permission` VALUES ('819', '1', '55');
INSERT INTO `sys_role_permission` VALUES ('820', '1', '102');
INSERT INTO `sys_role_permission` VALUES ('821', '1', '127');
INSERT INTO `sys_role_permission` VALUES ('822', '1', '57');
INSERT INTO `sys_role_permission` VALUES ('823', '1', '53');
INSERT INTO `sys_role_permission` VALUES ('824', '1', '101');
INSERT INTO `sys_role_permission` VALUES ('825', '1', '108');
INSERT INTO `sys_role_permission` VALUES ('826', '1', '109');
INSERT INTO `sys_role_permission` VALUES ('827', '1', '125');
INSERT INTO `sys_role_permission` VALUES ('828', '1', '93');
INSERT INTO `sys_role_permission` VALUES ('829', '1', '120');
INSERT INTO `sys_role_permission` VALUES ('830', '1', '121');
INSERT INTO `sys_role_permission` VALUES ('831', '1', '122');
INSERT INTO `sys_role_permission` VALUES ('832', '1', '126');
INSERT INTO `sys_role_permission` VALUES ('833', '1', '94');
INSERT INTO `sys_role_permission` VALUES ('834', '1', '117');
INSERT INTO `sys_role_permission` VALUES ('835', '1', '118');
INSERT INTO `sys_role_permission` VALUES ('836', '1', '119');
INSERT INTO `sys_role_permission` VALUES ('837', '1', '95');
INSERT INTO `sys_role_permission` VALUES ('838', '1', '110');
INSERT INTO `sys_role_permission` VALUES ('839', '1', '112');
INSERT INTO `sys_role_permission` VALUES ('840', '1', '116');
INSERT INTO `sys_role_permission` VALUES ('841', '1', '97');
INSERT INTO `sys_role_permission` VALUES ('842', '1', '111');
INSERT INTO `sys_role_permission` VALUES ('843', '1', '113');
INSERT INTO `sys_role_permission` VALUES ('844', '1', '115');
INSERT INTO `sys_role_permission` VALUES ('845', '1', '98');
INSERT INTO `sys_role_permission` VALUES ('846', '1', '132');
INSERT INTO `sys_role_permission` VALUES ('847', '1', '133');
INSERT INTO `sys_role_permission` VALUES ('848', '1', '96');
INSERT INTO `sys_role_permission` VALUES ('849', '1', '134');
INSERT INTO `sys_role_permission` VALUES ('850', '1', '135');
INSERT INTO `sys_role_permission` VALUES ('851', '1', '100');
INSERT INTO `sys_role_permission` VALUES ('852', '1', '104');
INSERT INTO `sys_role_permission` VALUES ('853', '1', '129');
INSERT INTO `sys_role_permission` VALUES ('854', '1', '130');
INSERT INTO `sys_role_permission` VALUES ('855', '1', '131');
INSERT INTO `sys_role_permission` VALUES ('856', '1', '103');
INSERT INTO `sys_role_permission` VALUES ('857', '1', '105');
INSERT INTO `sys_role_permission` VALUES ('858', '1', '106');
INSERT INTO `sys_role_permission` VALUES ('859', '1', '107');
INSERT INTO `sys_role_permission` VALUES ('860', '1', '99');
INSERT INTO `sys_role_permission` VALUES ('861', '1', '92');
INSERT INTO `sys_role_permission` VALUES ('862', '1', '91');
INSERT INTO `sys_role_permission` VALUES ('863', '1', '90');
INSERT INTO `sys_role_permission` VALUES ('864', '1', '59');
INSERT INTO `sys_role_permission` VALUES ('865', '1', '60');
INSERT INTO `sys_role_permission` VALUES ('866', '1', '61');
INSERT INTO `sys_role_permission` VALUES ('867', '1', '62');
INSERT INTO `sys_role_permission` VALUES ('868', '1', '63');
INSERT INTO `sys_role_permission` VALUES ('869', '1', '64');
INSERT INTO `sys_role_permission` VALUES ('870', '1', '65');
INSERT INTO `sys_role_permission` VALUES ('871', '1', '66');
INSERT INTO `sys_role_permission` VALUES ('872', '1', '67');
INSERT INTO `sys_role_permission` VALUES ('873', '1', '68');
INSERT INTO `sys_role_permission` VALUES ('874', '1', '69');
INSERT INTO `sys_role_permission` VALUES ('875', '1', '70');
INSERT INTO `sys_role_permission` VALUES ('876', '1', '71');
INSERT INTO `sys_role_permission` VALUES ('877', '1', '58');
INSERT INTO `sys_role_permission` VALUES ('878', '1', '73');
INSERT INTO `sys_role_permission` VALUES ('879', '1', '74');
INSERT INTO `sys_role_permission` VALUES ('880', '1', '72');
INSERT INTO `sys_role_permission` VALUES ('881', '1', '76');
INSERT INTO `sys_role_permission` VALUES ('882', '1', '75');
INSERT INTO `sys_role_permission` VALUES ('883', '1', '78');
INSERT INTO `sys_role_permission` VALUES ('884', '1', '79');
INSERT INTO `sys_role_permission` VALUES ('885', '1', '80');
INSERT INTO `sys_role_permission` VALUES ('886', '1', '81');
INSERT INTO `sys_role_permission` VALUES ('887', '1', '77');
INSERT INTO `sys_role_permission` VALUES ('888', '1', '83');
INSERT INTO `sys_role_permission` VALUES ('889', '1', '82');
INSERT INTO `sys_role_permission` VALUES ('890', '1', '85');
INSERT INTO `sys_role_permission` VALUES ('891', '1', '84');
INSERT INTO `sys_role_permission` VALUES ('892', '1', '87');
INSERT INTO `sys_role_permission` VALUES ('893', '1', '86');
INSERT INTO `sys_role_permission` VALUES ('894', '1', '89');
INSERT INTO `sys_role_permission` VALUES ('895', '1', '88');

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
  `nationality` varchar(64) DEFAULT NULL COMMENT '国籍',
  `avatar` varchar(255) DEFAULT NULL COMMENT '照片',
  `hire_date` datetime DEFAULT NULL COMMENT '聘用日期',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='系统用户';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', '1', null, '2016-11-11 11:11:11', '4', '管理员', null, null, null);
INSERT INTO `sys_user` VALUES ('2', 'test', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', '1', null, '2020-01-25 19:29:31', '10', 'test', null, null, null);
INSERT INTO `sys_user` VALUES ('3', 'test2', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', '1', null, '2020-01-25 19:31:15', '10', 'test', null, null, null);

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
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='用户与角色对应关系';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1', '1');
