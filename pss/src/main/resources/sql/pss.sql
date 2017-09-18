/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50628
Source Host           : localhost:3306
Source Database       : pss

Target Server Type    : MYSQL
Target Server Version : 50628
File Encoding         : 65001

Date: 2017-09-18 23:00:01
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
INSERT INTO `employee_role` VALUES ('1874910c89204cb59282102d65f496d1', '2017-08-22 13:59:12', '2017-08-22 13:59:12', '1', '1', null, '1', '2', '2');
INSERT INTO `employee_role` VALUES ('57cb90c20e5c4a7bb6b14b6eb9f62b01', '2017-09-15 19:40:50', '2017-09-15 19:40:50', '1', '1', null, '1', '7f1f152ef53d4963b4076c46b890cccf', '541a26de2d21483588a6d87f9cd855c6');

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
  `type` int(11) NOT NULL COMMENT '菜单类型(1表示普通菜单 2表示系统菜单)',
  `level` int(11) NOT NULL COMMENT '菜单级数',
  `visible` int(11) NOT NULL COMMENT '可见（1表示可见 2表示隐藏）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('0caeae1b80c74879a94352e8842b8c21', '2017-08-22 09:59:10', '2017-09-15 20:21:36', '1', '1', '', '1', '系统设置', '1000', '', 'fa fa-cogs', '1', 'system:view', '2', '1', '1');
INSERT INTO `menu` VALUES ('0e49673b549a4e0593758f4dcfb14bab', '2017-09-13 15:11:20', '2017-09-14 10:30:54', '1', '1', '', '1', '员工删除', '40', '', '', 'c86f413d2017417b8a837e497d285628', 'system:employee:delete', '1', '3', '2');
INSERT INTO `menu` VALUES ('0eba9cf6523b4883a0b1671f08f97964', '2017-09-15 20:24:21', '2017-09-15 20:24:21', '1', '1', '', '1', '历史任务', '20', '', 'fa fa-history', '7aa5bb07b08e42ffba35002e63012fd8', '', '1', '2', '1');
INSERT INTO `menu` VALUES ('1', '2017-08-21 17:36:10', '2017-08-21 17:36:10', '1', '1', null, '1', '顶级菜单', '0', '', '', '0', '', '2', '0', '2');
INSERT INTO `menu` VALUES ('124b42de84914f82b2bc064ca138e0cc', '2017-09-14 16:43:02', '2017-09-14 16:43:17', '1', '1', '', '1', '重置密码', '10', '', '', '38f11805b8b44def8cd461d5bad5860e', 'system:employee:resetpass', '1', '4', '2');
INSERT INTO `menu` VALUES ('12c218294ad74b1f858a7ed4d7150978', '2017-09-15 20:21:17', '2017-09-15 20:22:54', '1', '1', '', '1', '我的通知', '20', '', '', '1', '', '1', '1', '1');
INSERT INTO `menu` VALUES ('38f11805b8b44def8cd461d5bad5860e', '2017-09-05 10:57:08', '2017-09-14 10:30:40', '1', '1', '', '1', '员工编辑', '20', '', '', 'c86f413d2017417b8a837e497d285628', 'system:employee:edit', '2', '3', '2');
INSERT INTO `menu` VALUES ('3bdb78904d3041f99c94b80e93bc00c6', '2017-09-14 10:17:04', '2017-09-14 10:17:04', '1', '1', '', '1', '角色分配', '30', '', '', 'c86f413d2017417b8a837e497d285628', 'system:employee:roleset', '1', '3', '2');
INSERT INTO `menu` VALUES ('47efd6c234ac4fd4b16bab4a16d1a0e1', '2017-08-22 16:04:34', '2017-08-22 16:04:34', '1', '1', null, '1', '菜单管理', '10', 'menu/list', 'fa fa-bars', '0caeae1b80c74879a94352e8842b8c21', 'system:menu:view', '2', '2', '1');
INSERT INTO `menu` VALUES ('6e1d26e3f1cb4c659b3c149e1bdb1b44', '2017-09-14 10:30:28', '2017-09-14 10:30:28', '1', '1', '', '1', '查看员工', '10', '', '', 'c86f413d2017417b8a837e497d285628', 'system:employee:view', '1', '3', '2');
INSERT INTO `menu` VALUES ('7aa5bb07b08e42ffba35002e63012fd8', '2017-09-15 20:22:44', '2017-09-15 20:28:26', '1', '1', '', '1', '我的任务', '30', '', 'fa fa-tasks', '1', '', '1', '1', '1');
INSERT INTO `menu` VALUES ('86cceb967db04688a12cef367953dee1', '2017-08-26 16:22:55', '2017-08-26 16:22:55', '1', '1', '', '1', '菜单编辑', '10', '', '', '47efd6c234ac4fd4b16bab4a16d1a0e1', 'system:menu:edit', '1', '3', '2');
INSERT INTO `menu` VALUES ('991b61473e1543e696e03e38904c2549', '2017-09-05 16:56:04', '2017-09-06 15:55:31', '1', '1', '', '1', '员工禁用', '20', '', '', '38f11805b8b44def8cd461d5bad5860e', 'system:employee:disable', '2', '4', '2');
INSERT INTO `menu` VALUES ('c86f413d2017417b8a837e497d285628', '2017-08-22 16:05:32', '2017-09-14 10:30:01', '1', '1', '', '1', '员工管理', '30', 'employee/list', 'fa fa-users', '0caeae1b80c74879a94352e8842b8c21', 'system:employee:manage', '2', '2', '1');
INSERT INTO `menu` VALUES ('d5713f5c064e4ac79a0387cfe5579e83', '2017-08-21 21:09:29', '2017-08-31 20:29:39', '1', '1', '', '1', '资料管理', '10', 'data', '', '1', '', '1', '1', '1');
INSERT INTO `menu` VALUES ('dde6a694648e4ffb8814f4ebe7381735', '2017-09-15 20:23:56', '2017-09-15 20:23:56', '1', '1', '', '1', '待办任务', '10', '', 'fa fa-pause', '7aa5bb07b08e42ffba35002e63012fd8', '', '1', '2', '1');
INSERT INTO `menu` VALUES ('df2a49f1d16f4b91bfd5af339e37a08c', '2017-08-22 16:05:23', '2017-09-01 15:43:52', '1', '1', '', '1', '角色管理', '20', 'role/list', '', '0caeae1b80c74879a94352e8842b8c21', '', '2', '2', '1');

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
INSERT INTO `role` VALUES ('1', '2017-08-21 17:15:58', '2017-09-15 20:27:16', '1', '1', '', '1', '系统管理员');
INSERT INTO `role` VALUES ('2', '2017-08-22 13:54:43', '2017-09-14 10:02:10', '1', '1', '', '1', '测试人员');
INSERT INTO `role` VALUES ('4eff49fda8d94a58ae6dacddcbd4a93a', '2017-09-12 21:19:24', '2017-09-15 20:19:31', '7f1f152ef53d4963b4076c46b890cccf', '7f1f152ef53d4963b4076c46b890cccf', '', '1', 'boss秘书');
INSERT INTO `role` VALUES ('541a26de2d21483588a6d87f9cd855c6', '2017-09-06 17:19:44', '2017-09-15 20:16:33', '1', '1', '', '1', 'boss');

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
INSERT INTO `role_menu` VALUES ('0cd55f65ab1a448fa2baf0c8c2d7a7ab', '2017-09-15 20:19:31', '2017-09-15 20:19:31', '7f1f152ef53d4963b4076c46b890cccf', '7f1f152ef53d4963b4076c46b890cccf', null, '1', '4eff49fda8d94a58ae6dacddcbd4a93a', '38f11805b8b44def8cd461d5bad5860e');
INSERT INTO `role_menu` VALUES ('14174381607540d783647cd90d665be7', '2017-09-15 20:27:16', '2017-09-15 20:27:16', '1', '1', null, '1', '1', '1');
INSERT INTO `role_menu` VALUES ('21b081e860d745419d1b3faac0749db8', '2017-09-15 20:27:16', '2017-09-15 20:27:16', '1', '1', null, '1', '1', '6e1d26e3f1cb4c659b3c149e1bdb1b44');
INSERT INTO `role_menu` VALUES ('2be9cc2970bf49f0925037c9d857edc9', '2017-09-15 20:27:16', '2017-09-15 20:27:16', '1', '1', null, '1', '1', '0eba9cf6523b4883a0b1671f08f97964');
INSERT INTO `role_menu` VALUES ('39c6db6462a044b883e8fdfcc1d105b9', '2017-09-15 20:27:16', '2017-09-15 20:27:16', '1', '1', null, '1', '1', 'c86f413d2017417b8a837e497d285628');
INSERT INTO `role_menu` VALUES ('3e292bc45e73486db8d7d8d70454c8db', '2017-09-15 20:27:16', '2017-09-15 20:27:16', '1', '1', null, '1', '1', 'df2a49f1d16f4b91bfd5af339e37a08c');
INSERT INTO `role_menu` VALUES ('4a07346ab474483d9b9d3845aa1c57d3', '2017-09-15 20:27:16', '2017-09-15 20:27:16', '1', '1', null, '1', '1', '38f11805b8b44def8cd461d5bad5860e');
INSERT INTO `role_menu` VALUES ('4c3c71b6c0a8455f9e81f553e12b2464', '2017-09-15 20:16:33', '2017-09-15 20:16:33', '1', '1', null, '1', '541a26de2d21483588a6d87f9cd855c6', '38f11805b8b44def8cd461d5bad5860e');
INSERT INTO `role_menu` VALUES ('4e05549b6f1146e48e58bf7aed60536c', '2017-09-15 20:27:16', '2017-09-15 20:27:16', '1', '1', null, '1', '1', '124b42de84914f82b2bc064ca138e0cc');
INSERT INTO `role_menu` VALUES ('57f6648a8e4948adb78d43a71bd79a4e', '2017-09-15 20:19:31', '2017-09-15 20:19:31', '7f1f152ef53d4963b4076c46b890cccf', '7f1f152ef53d4963b4076c46b890cccf', null, '1', '4eff49fda8d94a58ae6dacddcbd4a93a', '3bdb78904d3041f99c94b80e93bc00c6');
INSERT INTO `role_menu` VALUES ('58750a4b6d054b90b004251d7300f5b7', '2017-09-15 20:27:16', '2017-09-15 20:27:16', '1', '1', null, '1', '1', 'dde6a694648e4ffb8814f4ebe7381735');
INSERT INTO `role_menu` VALUES ('5ad35a5b0f5c47b4bdb5611b4f234367', '2017-09-15 20:16:33', '2017-09-15 20:16:33', '1', '1', null, '1', '541a26de2d21483588a6d87f9cd855c6', '124b42de84914f82b2bc064ca138e0cc');
INSERT INTO `role_menu` VALUES ('5b1165b2996b4eb99380495432909e7f', '2017-09-15 20:19:31', '2017-09-15 20:19:31', '7f1f152ef53d4963b4076c46b890cccf', '7f1f152ef53d4963b4076c46b890cccf', null, '1', '4eff49fda8d94a58ae6dacddcbd4a93a', '0caeae1b80c74879a94352e8842b8c21');
INSERT INTO `role_menu` VALUES ('5bb17fe8b1614d10baca120260d394d9', '2017-09-15 20:27:16', '2017-09-15 20:27:16', '1', '1', null, '1', '1', '991b61473e1543e696e03e38904c2549');
INSERT INTO `role_menu` VALUES ('5f3c75151ff5440fade6238387fb7296', '2017-09-15 20:27:16', '2017-09-15 20:27:16', '1', '1', null, '1', '1', '12c218294ad74b1f858a7ed4d7150978');
INSERT INTO `role_menu` VALUES ('61ea488566ff4e28b29198047c102162', '2017-09-15 20:19:31', '2017-09-15 20:19:31', '7f1f152ef53d4963b4076c46b890cccf', '7f1f152ef53d4963b4076c46b890cccf', null, '1', '4eff49fda8d94a58ae6dacddcbd4a93a', 'd5713f5c064e4ac79a0387cfe5579e83');
INSERT INTO `role_menu` VALUES ('63a235f05bdf449e8693f31fac8af01f', '2017-09-14 10:02:10', '2017-09-14 10:02:10', '1', '1', null, '1', '2', '38f11805b8b44def8cd461d5bad5860e');
INSERT INTO `role_menu` VALUES ('6973e9ca0dd8480686b6ffa319711be1', '2017-09-15 20:27:16', '2017-09-15 20:27:16', '1', '1', null, '1', '1', '0caeae1b80c74879a94352e8842b8c21');
INSERT INTO `role_menu` VALUES ('707153527bdc4939825383f8de046888', '2017-09-15 20:27:16', '2017-09-15 20:27:16', '1', '1', null, '1', '1', '47efd6c234ac4fd4b16bab4a16d1a0e1');
INSERT INTO `role_menu` VALUES ('84231f8d2e2a4a378f9a1fdbfb3171b5', '2017-09-15 20:16:33', '2017-09-15 20:16:33', '1', '1', null, '1', '541a26de2d21483588a6d87f9cd855c6', '1');
INSERT INTO `role_menu` VALUES ('90be3f0ed0904c53a454bd03b5b9435e', '2017-09-15 20:19:31', '2017-09-15 20:19:31', '7f1f152ef53d4963b4076c46b890cccf', '7f1f152ef53d4963b4076c46b890cccf', null, '1', '4eff49fda8d94a58ae6dacddcbd4a93a', 'c86f413d2017417b8a837e497d285628');
INSERT INTO `role_menu` VALUES ('92d0d2d9f95146409cef27e68be7d3d7', '2017-09-15 20:19:31', '2017-09-15 20:19:31', '7f1f152ef53d4963b4076c46b890cccf', '7f1f152ef53d4963b4076c46b890cccf', null, '1', '4eff49fda8d94a58ae6dacddcbd4a93a', '6e1d26e3f1cb4c659b3c149e1bdb1b44');
INSERT INTO `role_menu` VALUES ('9cc31d4a25ae4f97b9d66ffd6cf8a9aa', '2017-09-14 10:02:10', '2017-09-14 10:02:10', '1', '1', null, '1', '2', '0caeae1b80c74879a94352e8842b8c21');
INSERT INTO `role_menu` VALUES ('a1e0269d2fd14ca9bc680cedbc428fbf', '2017-09-15 20:16:33', '2017-09-15 20:16:33', '1', '1', null, '1', '541a26de2d21483588a6d87f9cd855c6', '0e49673b549a4e0593758f4dcfb14bab');
INSERT INTO `role_menu` VALUES ('a305d1a1ef07401ca603daeaa1a95163', '2017-09-15 20:27:16', '2017-09-15 20:27:16', '1', '1', null, '1', '1', '0e49673b549a4e0593758f4dcfb14bab');
INSERT INTO `role_menu` VALUES ('aa3453e6f5a240b7b4f2220fdb103905', '2017-09-15 20:16:33', '2017-09-15 20:16:33', '1', '1', null, '1', '541a26de2d21483588a6d87f9cd855c6', 'c86f413d2017417b8a837e497d285628');
INSERT INTO `role_menu` VALUES ('ad61a078a6c34f329b3b5f60d4f713b0', '2017-09-15 20:27:16', '2017-09-15 20:27:16', '1', '1', null, '1', '1', 'd5713f5c064e4ac79a0387cfe5579e83');
INSERT INTO `role_menu` VALUES ('b24abed17c3c46bda02624fa76cda5b9', '2017-09-14 10:02:10', '2017-09-14 10:02:10', '1', '1', null, '1', '2', 'c86f413d2017417b8a837e497d285628');
INSERT INTO `role_menu` VALUES ('b3a2086b138643daa3cc909a802603a0', '2017-09-15 20:16:33', '2017-09-15 20:16:33', '1', '1', null, '1', '541a26de2d21483588a6d87f9cd855c6', 'd5713f5c064e4ac79a0387cfe5579e83');
INSERT INTO `role_menu` VALUES ('bc4c0c6f2a694924b937b0f841b3d5c3', '2017-09-15 20:19:31', '2017-09-15 20:19:31', '7f1f152ef53d4963b4076c46b890cccf', '7f1f152ef53d4963b4076c46b890cccf', null, '1', '4eff49fda8d94a58ae6dacddcbd4a93a', '991b61473e1543e696e03e38904c2549');
INSERT INTO `role_menu` VALUES ('bef36a15603647d4969326bf1bbe787c', '2017-09-14 10:02:10', '2017-09-14 10:02:10', '1', '1', null, '1', '2', '991b61473e1543e696e03e38904c2549');
INSERT INTO `role_menu` VALUES ('c54c3076ed30426eae377e1e3d6bb8c3', '2017-09-15 20:16:33', '2017-09-15 20:16:33', '1', '1', null, '1', '541a26de2d21483588a6d87f9cd855c6', '3bdb78904d3041f99c94b80e93bc00c6');
INSERT INTO `role_menu` VALUES ('c9570d5d751a49018bb0e746d32ee57f', '2017-09-15 20:27:16', '2017-09-15 20:27:16', '1', '1', null, '1', '1', '86cceb967db04688a12cef367953dee1');
INSERT INTO `role_menu` VALUES ('c9c6a671ef654952b314720237717012', '2017-09-14 10:02:10', '2017-09-14 10:02:10', '1', '1', null, '1', '2', '1');
INSERT INTO `role_menu` VALUES ('ce21a9c8aa90407ea16377c5ace52515', '2017-09-15 20:16:33', '2017-09-15 20:16:33', '1', '1', null, '1', '541a26de2d21483588a6d87f9cd855c6', '6e1d26e3f1cb4c659b3c149e1bdb1b44');
INSERT INTO `role_menu` VALUES ('d4ed9f8638384256a073c1bb448256e2', '2017-09-15 20:27:16', '2017-09-15 20:27:16', '1', '1', null, '1', '1', '3bdb78904d3041f99c94b80e93bc00c6');
INSERT INTO `role_menu` VALUES ('e5e7553e38994247a29c3c7121eb6234', '2017-09-15 20:27:16', '2017-09-15 20:27:16', '1', '1', null, '1', '1', '7aa5bb07b08e42ffba35002e63012fd8');
INSERT INTO `role_menu` VALUES ('eae25bbd9d104f72965b98e6c55a4279', '2017-09-15 20:16:33', '2017-09-15 20:16:33', '1', '1', null, '1', '541a26de2d21483588a6d87f9cd855c6', 'df2a49f1d16f4b91bfd5af339e37a08c');
INSERT INTO `role_menu` VALUES ('ed80a422a7f940028b81ce042a0fd462', '2017-09-15 20:16:33', '2017-09-15 20:16:33', '1', '1', null, '1', '541a26de2d21483588a6d87f9cd855c6', '991b61473e1543e696e03e38904c2549');
INSERT INTO `role_menu` VALUES ('f94faa35c33946fa8deeb2fcc1ef014f', '2017-09-15 20:16:33', '2017-09-15 20:16:33', '1', '1', null, '1', '541a26de2d21483588a6d87f9cd855c6', '0caeae1b80c74879a94352e8842b8c21');
INSERT INTO `role_menu` VALUES ('ff24677bda614794968433627c76fc6c', '2017-09-15 20:19:31', '2017-09-15 20:19:31', '7f1f152ef53d4963b4076c46b890cccf', '7f1f152ef53d4963b4076c46b890cccf', null, '1', '4eff49fda8d94a58ae6dacddcbd4a93a', '124b42de84914f82b2bc064ca138e0cc');
