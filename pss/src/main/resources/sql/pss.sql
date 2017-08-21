/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50628
Source Host           : localhost:3306
Source Database       : pss

Target Server Type    : MYSQL
Target Server Version : 50628
File Encoding         : 65001

Date: 2017-08-21 21:44:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `demo`
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
-- Table structure for `employee`
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

-- ----------------------------
-- Table structure for `employee_role`
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

-- ----------------------------
-- Table structure for `menu`
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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('0', '2017-08-21 17:36:10', '2017-08-21 17:36:10', '1', '1', null, '1', '顶级菜单', '0', '', '', '', '');
INSERT INTO `menu` VALUES ('d5713f5c064e4ac79a0387cfe5579e83', '2017-08-21 21:09:29', '2017-08-21 21:09:29', '1', '1', null, '1', '资料管理', '10', '', '', '0', '');

-- ----------------------------
-- Table structure for `role`
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

-- ----------------------------
-- Table structure for `role_menu`
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
INSERT INTO `role_menu` VALUES ('ef51fe6c3f244ccb905af5cba13203eb', '2017-08-21 21:10:43', '2017-08-21 21:10:43', '1', '1', null, '1', '1', 'd5713f5c064e4ac79a0387cfe5579e83');
