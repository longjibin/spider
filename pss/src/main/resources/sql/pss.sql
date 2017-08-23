/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50628
Source Host           : localhost:3306
Source Database       : pss

Target Server Type    : MYSQL
Target Server Version : 50628
File Encoding         : 65001

Date: 2017-08-23 17:47:17
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for demo
-- ----------------------------
DROP TABLE IF EXISTS `demo`;
CREATE TABLE `demo` (
  `id` varchar(64) NOT NULL COMMENT '主键',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `create_user` varchar(64) NOT NULL COMMENT '创建人',
  `update_user` varchar(64) NOT NULL COMMENT '修改人',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `del_sign` int(11) NOT NULL COMMENT '删除标记(1正常 2删除)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of demo
-- ----------------------------

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `id` varchar(64) NOT NULL COMMENT '主键',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `create_user` varchar(64) NOT NULL COMMENT '创建人',
  `update_user` varchar(64) NOT NULL COMMENT '修改人',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `del_sign` int(11) NOT NULL COMMENT '删除标记(1正常 2删除)',
  `job_no` varchar(20) NOT NULL COMMENT '工号',
  `login_name` varchar(30) NOT NULL COMMENT '登录名',
  `login_pass` varchar(32) NOT NULL COMMENT '登录密码',
  `nick_name` varchar(30) NOT NULL COMMENT '昵称',
  `head_pic` varchar(255) DEFAULT NULL COMMENT '头像',
  `email` varchar(30) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(11) NOT NULL COMMENT '手机',
  `tel` varchar(11) DEFAULT NULL COMMENT '电话',
  `status` int(11) NOT NULL COMMENT '状态(1正常 2禁用)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES ('1', '2017-08-18 14:23:44', '2017-08-18 14:26:08', '1', '1', '系统管理员', '1', '20170818', 'admin', '21232f297a57a5a743894a0e4a801fc3', 'admin', '', '996835067@qq.com', '13251407498', '02368629315', '1');
INSERT INTO `employee` VALUES ('2', '2017-08-22 13:53:22', '2017-08-22 13:53:26', '1', '1', '测试用户', '1', '20170822', 'test', '21232f297a57a5a743894a0e4a801fc3', 'test', null, '996835067@qq.com', '18584568356', '02368629315', '1');

-- ----------------------------
-- Table structure for employee_role
-- ----------------------------
DROP TABLE IF EXISTS `employee_role`;
CREATE TABLE `employee_role` (
  `id` varchar(64) NOT NULL COMMENT '主键',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `create_user` varchar(64) NOT NULL COMMENT '创建人',
  `update_user` varchar(64) NOT NULL COMMENT '修改人',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `del_sign` int(11) NOT NULL COMMENT '删除标记(1正常 2删除)',
  `employee_id` varchar(64) NOT NULL COMMENT '员工id',
  `role_id` varchar(64) NOT NULL COMMENT '员工id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of employee_role
-- ----------------------------
INSERT INTO `employee_role` VALUES ('054a379886c1428e88efe2995b59e31b', '2017-08-21 20:51:31', '2017-08-21 20:51:31', '1', '1', null, '1', '1', '1');
INSERT INTO `employee_role` VALUES ('1874910c89204cb59282102d65f496d1', '2017-08-22 13:59:12', '2017-08-22 13:59:12', '1', '1', null, '1', '2', '2');

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` varchar(64) NOT NULL COMMENT '主键',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `create_user` varchar(64) NOT NULL COMMENT '创建人',
  `update_user` varchar(64) NOT NULL COMMENT '修改人',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `del_sign` int(11) NOT NULL COMMENT '删除标记(1正常 2删除)',
  `name` varchar(10) NOT NULL COMMENT '菜单名',
  `sort` int(11) DEFAULT NULL COMMENT '序号',
  `url` varchar(255) DEFAULT NULL COMMENT '菜单链接地址',
  `icon` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  `parent_id` varchar(64) NOT NULL COMMENT '父菜单id',
  `permissions` varchar(300) NOT NULL DEFAULT '权限标志',
  `type` int(11) NOT NULL COMMENT '菜单类型(1表示普通菜单 2表示系统菜单)',
  `level` int(11) NOT NULL COMMENT '菜单级数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('0caeae1b80c74879a94352e8842b8c21', '2017-08-22 09:59:10', '2017-08-22 09:59:10', '1', '1', null, '1', '系统设置', '20', '', '', '1', 'system:view', '2', '1');
INSERT INTO `menu` VALUES ('1', '2017-08-21 17:36:10', '2017-08-21 17:36:10', '1', '1', null, '1', '顶级菜单', '0', '', '', '', '', '2', '0');
INSERT INTO `menu` VALUES ('47efd6c234ac4fd4b16bab4a16d1a0e1', '2017-08-22 16:04:34', '2017-08-22 16:04:34', '1', '1', null, '1', '菜单管理', '10', 'admin/menu/list', '', '0caeae1b80c74879a94352e8842b8c21', '', '2', '2');
INSERT INTO `menu` VALUES ('c86f413d2017417b8a837e497d285628', '2017-08-22 16:05:32', '2017-08-22 16:05:32', '1', '1', null, '1', '权限管理', '10', 'admin/menu/form', '', '0caeae1b80c74879a94352e8842b8c21', '', '2', '2');
INSERT INTO `menu` VALUES ('d5713f5c064e4ac79a0387cfe5579e83', '2017-08-21 21:09:29', '2017-08-21 21:09:29', '1', '1', null, '1', '资料管理', '10', 'admin/data', '', '1', '', '1', '1');
INSERT INTO `menu` VALUES ('df2a49f1d16f4b91bfd5af339e37a08c', '2017-08-22 16:05:23', '2017-08-22 16:05:23', '1', '1', null, '1', '角色管理', '10', '', '', '0caeae1b80c74879a94352e8842b8c21', '', '2', '2');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` varchar(64) NOT NULL COMMENT '主键',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `create_user` varchar(64) NOT NULL COMMENT '创建人',
  `update_user` varchar(64) NOT NULL COMMENT '修改人',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `del_sign` int(11) NOT NULL COMMENT '删除标记(1正常 2删除)',
  `name` varchar(20) NOT NULL COMMENT '角色名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '2017-08-21 17:15:58', '2017-08-21 17:15:58', '1', '1', null, '1', '系统管理员');
INSERT INTO `role` VALUES ('2', '2017-08-22 13:54:43', '2017-08-22 13:54:47', '1', '1', null, '1', '系统测试人员');

-- ----------------------------
-- Table structure for role_menu
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu` (
  `id` varchar(64) NOT NULL COMMENT '主键',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `create_user` varchar(64) NOT NULL COMMENT '创建人',
  `update_user` varchar(64) NOT NULL COMMENT '修改人',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `del_sign` int(11) NOT NULL COMMENT '删除标记(1正常 2删除)',
  `role_id` varchar(64) NOT NULL COMMENT '角色id',
  `menu_id` varchar(64) NOT NULL COMMENT '菜单id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_menu
-- ----------------------------
INSERT INTO `role_menu` VALUES ('2ee790cc602d47e39b085ef2591d864a', '2017-08-22 16:07:18', '2017-08-22 16:07:18', '1', '1', null, '1', '1', 'df2a49f1d16f4b91bfd5af339e37a08c');
INSERT INTO `role_menu` VALUES ('5d7c9dbd5b5944a895309adb1241bfd6', '2017-08-22 16:06:54', '2017-08-22 16:06:54', '1', '1', null, '1', '1', '47efd6c234ac4fd4b16bab4a16d1a0e1');
INSERT INTO `role_menu` VALUES ('5fb2d032410f4965a92616f7614bc87f', '2017-08-22 16:07:06', '2017-08-22 16:07:06', '1', '1', null, '1', '1', 'c86f413d2017417b8a837e497d285628');
INSERT INTO `role_menu` VALUES ('62df7aa718004b10aa4a189604facc42', '2017-08-22 13:57:54', '2017-08-22 13:57:54', '1', '1', null, '1', '2', 'd5713f5c064e4ac79a0387cfe5579e83');
INSERT INTO `role_menu` VALUES ('a367bbd4783947128cc00089928e800c', '2017-08-22 13:57:23', '2017-08-22 13:57:23', '1', '1', null, '1', '1', '0caeae1b80c74879a94352e8842b8c21');
INSERT INTO `role_menu` VALUES ('ef51fe6c3f244ccb905af5cba13203eb', '2017-08-21 21:10:43', '2017-08-21 21:10:43', '1', '1', null, '1', '1', 'd5713f5c064e4ac79a0387cfe5579e83');
