/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50628
Source Host           : localhost:3306
Source Database       : pss

Target Server Type    : MYSQL
Target Server Version : 50628
File Encoding         : 65001

Date: 2017-09-15 17:32:37
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
  `job_no` varchar(25) NOT NULL COMMENT '工号',
  `login_name` varchar(30) NOT NULL COMMENT '登录名',
  `login_pass` varchar(32) NOT NULL COMMENT '登录密码',
  `nick_name` varchar(30) DEFAULT NULL COMMENT '昵称',
  `head_pic` varchar(255) DEFAULT NULL COMMENT '头像',
  `email` varchar(30) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(11) DEFAULT NULL COMMENT '手机',
  `tel` varchar(11) DEFAULT NULL COMMENT '电话',
  `status` int(11) NOT NULL COMMENT '状态(1正常 2禁用 3离职)',
  `entry_time` varchar(20) DEFAULT NULL COMMENT '入职时间',
  `quit_time` varchar(20) DEFAULT NULL COMMENT '离职时间',
  `birthday` varchar(20) DEFAULT NULL COMMENT '生日',
  `idcard_no` varchar(20) NOT NULL COMMENT '身份证号码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES ('1', '2017-08-18 14:23:44', '2017-08-18 14:26:08', '1', '1', '系统管理员', '1', '20170818', 'admin', '21232f297a57a5a743894a0e4a801fc3', 'admin', '', '996835067@qq.com', '13251407498', '02368629315', '1', null, null, '1991-08-12', '51162219920812521X');
INSERT INTO `employee` VALUES ('2', '2017-08-22 13:53:22', '2017-09-14 13:03:47', '1', '2', '测试用户', '1', '20170822', 'test', '', '逆风飞翔的猪', null, null, null, null, '1', '2017-09-12', '', null, '51162219920812521X');
INSERT INTO `employee` VALUES ('7f1f152ef53d4963b4076c46b890cccf', '2017-09-06 16:20:48', '2017-09-14 17:27:34', '1', '1', '', '1', '201709061504686048009', 'liuxiang', 'ba3d2b3b70bc70003acbca8baf1b63b5', null, null, null, null, null, '1', '2017-01-20', '2017-08-20', null, '51162219920812521X');

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
INSERT INTO `employee_role` VALUES ('440773a3814c423da6de09913170ab13', '2017-09-15 17:30:07', '2017-09-15 17:30:07', '1', '1', null, '1', '7f1f152ef53d4963b4076c46b890cccf', '2');
INSERT INTO `employee_role` VALUES ('e96e54772f264b1b8948b44ba4a46c18', '2017-09-12 20:04:16', '2017-09-12 20:04:16', '1', '1', null, '1', '7f1f152ef53d4963b4076c46b890cccf', '541a26de2d21483588a6d87f9cd855c6');

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
  `visible` int(11) NOT NULL COMMENT '可见（1表示可见 2表示隐藏）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('0caeae1b80c74879a94352e8842b8c21', '2017-08-22 09:59:10', '2017-08-22 09:59:10', '1', '1', null, '1', '系统设置', '30', '', 'fa fa-cogs', '1', 'system:view', '2', '1', '1');
INSERT INTO `menu` VALUES ('0e49673b549a4e0593758f4dcfb14bab', '2017-09-13 15:11:20', '2017-09-14 10:30:54', '1', '1', '', '1', '员工删除', '40', '', '', 'c86f413d2017417b8a837e497d285628', 'system:employee:delete', '1', '3', '2');
INSERT INTO `menu` VALUES ('1', '2017-08-21 17:36:10', '2017-08-21 17:36:10', '1', '1', null, '1', '顶级菜单', '0', '', '', '0', '', '2', '0', '2');
INSERT INTO `menu` VALUES ('124b42de84914f82b2bc064ca138e0cc', '2017-09-14 16:43:02', '2017-09-14 16:43:17', '1', '1', '', '1', '重置密码', '10', '', '', '38f11805b8b44def8cd461d5bad5860e', 'system:employee:resetpass', '1', '4', '2');
INSERT INTO `menu` VALUES ('38f11805b8b44def8cd461d5bad5860e', '2017-09-05 10:57:08', '2017-09-14 10:30:40', '1', '1', '', '1', '员工编辑', '20', '', '', 'c86f413d2017417b8a837e497d285628', 'system:employee:edit', '2', '3', '2');
INSERT INTO `menu` VALUES ('3bdb78904d3041f99c94b80e93bc00c6', '2017-09-14 10:17:04', '2017-09-14 10:17:04', '1', '1', '', '1', '角色分配', '30', '', '', 'c86f413d2017417b8a837e497d285628', 'system:employee:roleset', '1', '3', '2');
INSERT INTO `menu` VALUES ('47efd6c234ac4fd4b16bab4a16d1a0e1', '2017-08-22 16:04:34', '2017-08-22 16:04:34', '1', '1', null, '1', '菜单管理', '10', 'menu/list', '', '0caeae1b80c74879a94352e8842b8c21', 'system:menu:view', '2', '2', '1');
INSERT INTO `menu` VALUES ('6e1d26e3f1cb4c659b3c149e1bdb1b44', '2017-09-14 10:30:28', '2017-09-14 10:30:28', '1', '1', '', '1', '查看员工', '10', '', '', 'c86f413d2017417b8a837e497d285628', 'system:employee:view', '1', '3', '2');
INSERT INTO `menu` VALUES ('86cceb967db04688a12cef367953dee1', '2017-08-26 16:22:55', '2017-08-26 16:22:55', '1', '1', '', '1', '菜单编辑', '10', '', '', '47efd6c234ac4fd4b16bab4a16d1a0e1', 'system:menu:edit', '1', '3', '2');
INSERT INTO `menu` VALUES ('991b61473e1543e696e03e38904c2549', '2017-09-05 16:56:04', '2017-09-06 15:55:31', '1', '1', '', '1', '员工禁用', '20', '', '', '38f11805b8b44def8cd461d5bad5860e', 'system:employee:disable', '2', '4', '2');
INSERT INTO `menu` VALUES ('c86f413d2017417b8a837e497d285628', '2017-08-22 16:05:32', '2017-09-14 10:30:01', '1', '1', '', '1', '员工管理', '30', 'employee/list', '', '0caeae1b80c74879a94352e8842b8c21', 'system:employee:manage', '2', '2', '1');
INSERT INTO `menu` VALUES ('d5713f5c064e4ac79a0387cfe5579e83', '2017-08-21 21:09:29', '2017-08-31 20:29:39', '1', '1', '', '1', '资料管理', '10', 'data', '', '1', '', '1', '1', '1');
INSERT INTO `menu` VALUES ('df2a49f1d16f4b91bfd5af339e37a08c', '2017-08-22 16:05:23', '2017-09-01 15:43:52', '1', '1', '', '1', '角色管理', '20', 'role/list', '', '0caeae1b80c74879a94352e8842b8c21', '', '2', '2', '1');

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
INSERT INTO `role` VALUES ('1', '2017-08-21 17:15:58', '2017-09-14 16:43:58', '1', '1', '', '1', '系统管理员');
INSERT INTO `role` VALUES ('2', '2017-08-22 13:54:43', '2017-09-14 10:02:10', '1', '1', '', '1', '测试人员');
INSERT INTO `role` VALUES ('4eff49fda8d94a58ae6dacddcbd4a93a', '2017-09-12 21:19:24', '2017-09-12 21:34:22', '7f1f152ef53d4963b4076c46b890cccf', '7f1f152ef53d4963b4076c46b890cccf', '', '1', 'boss秘书');
INSERT INTO `role` VALUES ('541a26de2d21483588a6d87f9cd855c6', '2017-09-06 17:19:44', '2017-09-14 10:48:58', '1', '1', '', '1', 'boss');

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
INSERT INTO `role_menu` VALUES ('0cdb4cca46be44b5a88c1dfcf8e62301', '2017-09-14 16:43:58', '2017-09-14 16:43:58', '1', '1', null, '1', '1', '86cceb967db04688a12cef367953dee1');
INSERT INTO `role_menu` VALUES ('1272c9989dd94f3ca43593e70c8063bb', '2017-09-14 10:48:58', '2017-09-14 10:48:58', '1', '1', null, '1', '541a26de2d21483588a6d87f9cd855c6', 'd5713f5c064e4ac79a0387cfe5579e83');
INSERT INTO `role_menu` VALUES ('13576d2d1f8b47ab962e35b22427cb6c', '2017-09-14 10:48:58', '2017-09-14 10:48:58', '1', '1', null, '1', '541a26de2d21483588a6d87f9cd855c6', '6e1d26e3f1cb4c659b3c149e1bdb1b44');
INSERT INTO `role_menu` VALUES ('13939c53be35479dadbca445ab192488', '2017-09-14 16:43:58', '2017-09-14 16:43:58', '1', '1', null, '1', '1', '1');
INSERT INTO `role_menu` VALUES ('1671abd0346c44c095d8f2901a5b3079', '2017-09-12 21:34:22', '2017-09-12 21:34:22', '7f1f152ef53d4963b4076c46b890cccf', '7f1f152ef53d4963b4076c46b890cccf', null, '1', '4eff49fda8d94a58ae6dacddcbd4a93a', '0caeae1b80c74879a94352e8842b8c21');
INSERT INTO `role_menu` VALUES ('2f75089074f8455e82fef536cbab6e97', '2017-09-14 16:43:58', '2017-09-14 16:43:58', '1', '1', null, '1', '1', '0caeae1b80c74879a94352e8842b8c21');
INSERT INTO `role_menu` VALUES ('393b39148a7f4ca3a0dbd69b70bb3a27', '2017-09-14 10:48:58', '2017-09-14 10:48:58', '1', '1', null, '1', '541a26de2d21483588a6d87f9cd855c6', 'df2a49f1d16f4b91bfd5af339e37a08c');
INSERT INTO `role_menu` VALUES ('3ab8f77ff1fb47258866e87ca49258bb', '2017-09-14 10:48:58', '2017-09-14 10:48:58', '1', '1', null, '1', '541a26de2d21483588a6d87f9cd855c6', '0caeae1b80c74879a94352e8842b8c21');
INSERT INTO `role_menu` VALUES ('404000c10a8f41fe94ab3cf1383432b9', '2017-09-12 21:34:22', '2017-09-12 21:34:22', '7f1f152ef53d4963b4076c46b890cccf', '7f1f152ef53d4963b4076c46b890cccf', null, '1', '4eff49fda8d94a58ae6dacddcbd4a93a', 'c86f413d2017417b8a837e497d285628');
INSERT INTO `role_menu` VALUES ('4c8ed3e6132d47ecaa656bb46e96d278', '2017-09-14 16:43:58', '2017-09-14 16:43:58', '1', '1', null, '1', '1', '38f11805b8b44def8cd461d5bad5860e');
INSERT INTO `role_menu` VALUES ('54a455d0ee834220aa7c60a34f7706d8', '2017-09-14 16:43:58', '2017-09-14 16:43:58', '1', '1', null, '1', '1', '0e49673b549a4e0593758f4dcfb14bab');
INSERT INTO `role_menu` VALUES ('58542cc198024124afd9b187b8a9e005', '2017-09-14 16:43:58', '2017-09-14 16:43:58', '1', '1', null, '1', '1', 'd5713f5c064e4ac79a0387cfe5579e83');
INSERT INTO `role_menu` VALUES ('5cc86a53189240d68b43013529f7f808', '2017-09-14 16:43:58', '2017-09-14 16:43:58', '1', '1', null, '1', '1', '991b61473e1543e696e03e38904c2549');
INSERT INTO `role_menu` VALUES ('63a235f05bdf449e8693f31fac8af01f', '2017-09-14 10:02:10', '2017-09-14 10:02:10', '1', '1', null, '1', '2', '38f11805b8b44def8cd461d5bad5860e');
INSERT INTO `role_menu` VALUES ('654a70b9084d4053b0a71d2fd1f52b71', '2017-09-14 16:43:58', '2017-09-14 16:43:58', '1', '1', null, '1', '1', '3bdb78904d3041f99c94b80e93bc00c6');
INSERT INTO `role_menu` VALUES ('711185e1004b4f2c8872269d5926d316', '2017-09-14 16:43:58', '2017-09-14 16:43:58', '1', '1', null, '1', '1', 'c86f413d2017417b8a837e497d285628');
INSERT INTO `role_menu` VALUES ('88d27f2f3f5a4e82bf9d8e69022869c5', '2017-09-14 16:43:58', '2017-09-14 16:43:58', '1', '1', null, '1', '1', '47efd6c234ac4fd4b16bab4a16d1a0e1');
INSERT INTO `role_menu` VALUES ('8ca0b854fd574c17b339fd31048fc56d', '2017-09-14 16:43:58', '2017-09-14 16:43:58', '1', '1', null, '1', '1', '6e1d26e3f1cb4c659b3c149e1bdb1b44');
INSERT INTO `role_menu` VALUES ('94faaee7a3ad4c6b9741f7ebde7bf9fc', '2017-09-14 10:48:58', '2017-09-14 10:48:58', '1', '1', null, '1', '541a26de2d21483588a6d87f9cd855c6', 'c86f413d2017417b8a837e497d285628');
INSERT INTO `role_menu` VALUES ('9cc31d4a25ae4f97b9d66ffd6cf8a9aa', '2017-09-14 10:02:10', '2017-09-14 10:02:10', '1', '1', null, '1', '2', '0caeae1b80c74879a94352e8842b8c21');
INSERT INTO `role_menu` VALUES ('a0d0bed286914b09a5812e495b3e908b', '2017-09-14 16:43:58', '2017-09-14 16:43:58', '1', '1', null, '1', '1', 'df2a49f1d16f4b91bfd5af339e37a08c');
INSERT INTO `role_menu` VALUES ('b24abed17c3c46bda02624fa76cda5b9', '2017-09-14 10:02:10', '2017-09-14 10:02:10', '1', '1', null, '1', '2', 'c86f413d2017417b8a837e497d285628');
INSERT INTO `role_menu` VALUES ('bef36a15603647d4969326bf1bbe787c', '2017-09-14 10:02:10', '2017-09-14 10:02:10', '1', '1', null, '1', '2', '991b61473e1543e696e03e38904c2549');
INSERT INTO `role_menu` VALUES ('c4c34258a717441ebc4897069ae07729', '2017-09-12 21:34:22', '2017-09-12 21:34:22', '7f1f152ef53d4963b4076c46b890cccf', '7f1f152ef53d4963b4076c46b890cccf', null, '1', '4eff49fda8d94a58ae6dacddcbd4a93a', 'd5713f5c064e4ac79a0387cfe5579e83');
INSERT INTO `role_menu` VALUES ('c9c6a671ef654952b314720237717012', '2017-09-14 10:02:10', '2017-09-14 10:02:10', '1', '1', null, '1', '2', '1');
INSERT INTO `role_menu` VALUES ('cde59f25e0bb4590b5fee99bd0a45ab7', '2017-09-14 16:43:58', '2017-09-14 16:43:58', '1', '1', null, '1', '1', '124b42de84914f82b2bc064ca138e0cc');
INSERT INTO `role_menu` VALUES ('e4bc33bd5b934c3fa003edc47bd0c52d', '2017-09-14 10:48:58', '2017-09-14 10:48:58', '1', '1', null, '1', '541a26de2d21483588a6d87f9cd855c6', '1');
INSERT INTO `role_menu` VALUES ('e9b65401aed64bb1a3aa8e45673b6fcc', '2017-09-12 21:34:22', '2017-09-12 21:34:22', '7f1f152ef53d4963b4076c46b890cccf', '7f1f152ef53d4963b4076c46b890cccf', null, '1', '4eff49fda8d94a58ae6dacddcbd4a93a', '991b61473e1543e696e03e38904c2549');
INSERT INTO `role_menu` VALUES ('f18078682a444201a1d62e4c98b133b4', '2017-09-12 21:34:22', '2017-09-12 21:34:22', '7f1f152ef53d4963b4076c46b890cccf', '7f1f152ef53d4963b4076c46b890cccf', null, '1', '4eff49fda8d94a58ae6dacddcbd4a93a', '38f11805b8b44def8cd461d5bad5860e');
