/*
 Navicat Premium Data Transfer

 Source Server         : myTencent-prod
 Source Server Type    : MariaDB
 Source Server Version : 100308
 Source Host           : cvm.ahuangtongxue.cn:13306
 Source Schema         : book

 Target Server Type    : MariaDB
 Target Server Version : 100308
 File Encoding         : 65001

 Date: 19/07/2018 21:56:23
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for BOOK_BASEINFO
-- ----------------------------
DROP TABLE IF EXISTS `BOOK_BASEINFO`;
CREATE TABLE `BOOK_BASEINFO` (
  `bookId` int(11) NOT NULL COMMENT '图书编号',
  `bookName` varchar(100) COLLATE utf8mb4_bin NOT NULL COMMENT '图书名',
  `bookWriter` varchar(100) COLLATE utf8mb4_bin NOT NULL COMMENT '图书作者',
  `bookBrief` varchar(2000) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '图书简介,默认为‘-’',
  `bookType` char(1) COLLATE utf8mb4_bin NOT NULL COMMENT '图书分类：0：党建；1：技术',
  `bookStatus` char(1) COLLATE utf8mb4_bin NOT NULL COMMENT '图书状态：0：借出；1：在库',
  `bookSource` char(1) COLLATE utf8mb4_bin NOT NULL DEFAULT '0' COMMENT '图书来源：0：采购；1：捐赠',
  `bookBuyer` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '图书购买者',
  `bookTime` datetime NOT NULL COMMENT '图书购买/捐赠时间',
  `bookRemark` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '图书备注,默认为‘-’',
  `bookLikeNum` int(11) NOT NULL DEFAULT 0 COMMENT '图书点赞数：默认为0',
  `bookCommentNum` int(11) NOT NULL DEFAULT 0 COMMENT '图书评论数:默认为0',
  `recTime` datetime NOT NULL COMMENT '入库时间',
  PRIMARY KEY (`bookId`) USING BTREE,
  KEY `idx_bookName` (`bookName`) USING BTREE,
  KEY `idx_bookType` (`bookType`) USING BTREE,
  KEY `idx_bookStatus` (`bookStatus`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='图书基本信息表';

-- ----------------------------
-- Table structure for BOOK_BORROWRECORD
-- ----------------------------
DROP TABLE IF EXISTS `BOOK_BORROWRECORD`;
CREATE TABLE `BOOK_BORROWRECORD` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `bookId` int(11) NOT NULL COMMENT '图书编号',
  `bookName` varchar(100) COLLATE utf8mb4_bin NOT NULL COMMENT '图书名',
  `borrowStatus` char(1) COLLATE utf8mb4_bin NOT NULL COMMENT '图书借还状态:0：借出；1：归还',
  `openid` varchar(100) COLLATE utf8mb4_bin NOT NULL COMMENT '微信小程序openid',
  `headImgUrl` varchar(200) COLLATE utf8mb4_bin NOT NULL COMMENT '微信头像Url',
  `userName` varchar(20) COLLATE utf8mb4_bin NOT NULL COMMENT '员工姓名',
  `borrowTime` datetime DEFAULT NULL COMMENT '借出时间',
  `returnTime` datetime DEFAULT NULL COMMENT '归还时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_bookId` (`bookId`) USING BTREE,
  KEY `idx_openid` (`openid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='图书借阅记录表';

-- ----------------------------
-- Table structure for BOOK_COMMENTRECORD
-- ----------------------------
DROP TABLE IF EXISTS `BOOK_COMMENTRECORD`;
CREATE TABLE `BOOK_COMMENTRECORD` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `bookId` int(11) NOT NULL COMMENT '图书编号',
  `openid` varchar(100) COLLATE utf8mb4_bin NOT NULL COMMENT '微信小程序openid',
  `headImgUrl` varchar(200) COLLATE utf8mb4_bin NOT NULL COMMENT '微信头像Url',
  `userName` varchar(20) COLLATE utf8mb4_bin NOT NULL COMMENT '员工姓名',
  `comment` varchar(500) COLLATE utf8mb4_bin NOT NULL COMMENT '评论内容',
  `recTime` datetime NOT NULL COMMENT '评论时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_bookId` (`bookId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='图书评论记录表';

-- ----------------------------
-- Table structure for BOOK_LIKERECORD
-- ----------------------------
DROP TABLE IF EXISTS `BOOK_LIKERECORD`;
CREATE TABLE `BOOK_LIKERECORD` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `bookId` int(11) NOT NULL COMMENT '图书编号',
  `openid` varchar(100) COLLATE utf8mb4_bin NOT NULL COMMENT '微信小程序openid',
  `recTime` datetime NOT NULL COMMENT '点赞时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='图书点赞记录表';

-- ----------------------------
-- Table structure for CUST_BINDUSERS
-- ----------------------------
DROP TABLE IF EXISTS `CUST_BINDUSERS`;
CREATE TABLE `CUST_BINDUSERS` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `openid` varchar(100) COLLATE utf8mb4_bin NOT NULL COMMENT '微信小程序openid',
  `nickName` varchar(50) COLLATE utf8mb4_bin NOT NULL COMMENT '微信昵称',
  `headImgUrl` varchar(200) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '微信头像Url',
  `userNo` varchar(10) COLLATE utf8mb4_bin NOT NULL COMMENT '员工号',
  `userName` varchar(20) COLLATE utf8mb4_bin NOT NULL COMMENT '员工姓名',
  `isAdmin` char(1) COLLATE utf8mb4_bin NOT NULL DEFAULT '0' COMMENT '是否是管理员: 0：否；1：是。默认为0。',
  `recTime` datetime NOT NULL COMMENT '绑定时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uidx_userno` (`userNo`) USING BTREE,
  KEY `idx_openid` (`openid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='员工绑定信息表';

-- ----------------------------
-- Table structure for CUST_USERS
-- ----------------------------
DROP TABLE IF EXISTS `CUST_USERS`;
CREATE TABLE `CUST_USERS` (
  `userNo` char(4) COLLATE utf8mb4_bin NOT NULL COMMENT '员工号',
  `userName` varchar(20) COLLATE utf8mb4_bin NOT NULL COMMENT '员工姓名',
  PRIMARY KEY (`userNo`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='员工基本信息表';

SET FOREIGN_KEY_CHECKS = 1;
