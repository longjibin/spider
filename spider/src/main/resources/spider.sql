/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50628
Source Host           : localhost:3306
Source Database       : spider

Target Server Type    : MYSQL
Target Server Version : 50628
File Encoding         : 65001

Date: 2017-11-14 17:00:49
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for goods_brand
-- ----------------------------
DROP TABLE IF EXISTS `goods_brand`;
CREATE TABLE `goods_brand` (
  `id` varchar(64) NOT NULL,
  `create_date_time` datetime NOT NULL,
  `update_date_time` datetime NOT NULL,
  `sb_id` varchar(64) NOT NULL COMMENT '电商品牌id',
  `source` varchar(20) NOT NULL COMMENT '电商平台（JD京东 TB淘宝 TM天猫）',
  `brand` varchar(50) NOT NULL COMMENT '品牌',
  `goods_list_url` varchar(255) NOT NULL,
  `category_id` int(11) NOT NULL COMMENT '所属分类',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goods_brand
-- ----------------------------

-- ----------------------------
-- Table structure for goods_category
-- ----------------------------
DROP TABLE IF EXISTS `goods_category`;
CREATE TABLE `goods_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL COMMENT '类别名',
  `parents` varchar(64) NOT NULL COMMENT '父类别id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goods_category
-- ----------------------------
INSERT INTO `goods_category` VALUES ('0', '分类', '');
INSERT INTO `goods_category` VALUES ('1', '手机', '0,');
INSERT INTO `goods_category` VALUES ('2', '手机通讯', '0,1,');
INSERT INTO `goods_category` VALUES ('3', '手机', '0,1,2');
INSERT INTO `goods_category` VALUES ('4', '对讲机', '0,1,2');
INSERT INTO `goods_category` VALUES ('5', '家用电器', '0,');
INSERT INTO `goods_category` VALUES ('6', '电视', '0,5');
INSERT INTO `goods_category` VALUES ('7', '曲面电视', '0,5,6');

-- ----------------------------
-- Table structure for goods_source
-- ----------------------------
DROP TABLE IF EXISTS `goods_source`;
CREATE TABLE `goods_source` (
  `id` varchar(64) NOT NULL,
  `create_date_time` datetime NOT NULL COMMENT '创建时间',
  `update_date_time` datetime NOT NULL COMMENT '修改时间',
  `brand_id` varchar(64) NOT NULL COMMENT '关联的品牌',
  `sku` varchar(20) NOT NULL COMMENT '商品sku',
  `url` varchar(255) NOT NULL COMMENT '商品详情url',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goods_source
-- ----------------------------

-- ----------------------------
-- Table structure for spider
-- ----------------------------
DROP TABLE IF EXISTS `spider`;
CREATE TABLE `spider` (
  `id` varchar(64) NOT NULL,
  `create_date_time` datetime NOT NULL,
  `name` varchar(20) NOT NULL,
  `url` varchar(250) NOT NULL,
  `thread_num` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of spider
-- ----------------------------
INSERT INTO `spider` VALUES ('1', '2017-10-26 15:29:19', 'techweb', 'http://www.techweb.com.cn/column/list_1.shtml', '1');
INSERT INTO `spider` VALUES ('2', '2017-10-30 15:48:19', '京东手机', 'https://list.jd.com/list.html?cat=9987,653,655', '1');
