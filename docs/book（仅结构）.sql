/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50721
 Source Host           : localhost:3306
 Source Schema         : book

 Target Server Type    : MySQL
 Target Server Version : 50721
 File Encoding         : 65001

 Date: 25/07/2018 16:43:43
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for book_baseinfo
-- ----------------------------
DROP TABLE IF EXISTS `book_baseinfo`;
CREATE TABLE `book_baseinfo`  (
  `bookId` int(11) NOT NULL COMMENT '图书编号',
  `bookName` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '图书名',
  `bookWriter` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '图书作者',
  `bookBrief` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '图书简介,默认为‘-’',
  `bookType` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '图书分类：0：党建；1：技术',
  `bookStatus` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '图书状态：0：借出；1：在库',
  `bookSource` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '0' COMMENT '图书来源：0：采购；1：捐赠',
  `bookBuyer` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '图书购买者',
  `bookTime` datetime(0) NOT NULL COMMENT '图书购买/捐赠时间',
  `bookRemark` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '图书备注,默认为‘-’',
  `bookLikeNum` int(11) NOT NULL DEFAULT 0 COMMENT '图书点赞数：默认为0',
  `bookCommentNum` int(11) NOT NULL DEFAULT 0 COMMENT '图书评论数:默认为0',
  `recTime` datetime(0) NOT NULL COMMENT '入库时间',
  `bookScore` int(11) NOT NULL COMMENT '得分',
  PRIMARY KEY (`bookId`) USING BTREE,
  INDEX `idx_bookName`(`bookName`) USING BTREE,
  INDEX `idx_bookType`(`bookType`) USING BTREE,
  INDEX `idx_bookStatus`(`bookStatus`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '图书基本信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for book_borrowrecord
-- ----------------------------
DROP TABLE IF EXISTS `book_borrowrecord`;
CREATE TABLE `book_borrowrecord`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `bookId` int(11) NOT NULL COMMENT '图书编号',
  `bookName` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '图书名',
  `borrowStatus` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '图书借还状态:0：借出；1：归还',
  `openid` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '微信小程序openid',
  `headImgUrl` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '微信头像Url',
  `userName` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '员工姓名',
  `borrowTime` datetime(0) DEFAULT NULL COMMENT '借出时间',
  `returnTime` datetime(0) DEFAULT NULL COMMENT '归还时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_bookId`(`bookId`) USING BTREE,
  INDEX `idx_openid`(`openid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 40 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '图书借阅记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for book_commentrecord
-- ----------------------------
DROP TABLE IF EXISTS `book_commentrecord`;
CREATE TABLE `book_commentrecord`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `bookId` int(11) NOT NULL COMMENT '图书编号',
  `openid` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '微信小程序openid',
  `headImgUrl` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '微信头像Url',
  `userName` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '员工姓名',
  `comment` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '评论内容',
  `recTime` datetime(0) NOT NULL COMMENT '评论时间',
  `commentLikeNum` int(11) NOT NULL DEFAULT 0 COMMENT '评论点赞数',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_bookId`(`bookId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '图书评论记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for book_likerecord
-- ----------------------------
DROP TABLE IF EXISTS `book_likerecord`;
CREATE TABLE `book_likerecord`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `bookId` int(11) NOT NULL COMMENT '图书编号',
  `openid` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '微信小程序openid',
  `recTime` datetime(0) NOT NULL COMMENT '点赞时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '图书点赞记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for comment_likerecord
-- ----------------------------
DROP TABLE IF EXISTS `comment_likerecord`;
CREATE TABLE `comment_likerecord`  (
  `id` int(11) NOT NULL,
  `commentId` int(11) NOT NULL,
  `openid` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `recTime` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `bookId` int(11) DEFAULT NULL,
  `headImgUrl` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `userName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for cust_bindusers
-- ----------------------------
DROP TABLE IF EXISTS `cust_bindusers`;
CREATE TABLE `cust_bindusers`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `openid` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '微信小程序openid',
  `nickName` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '微信昵称',
  `headImgUrl` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '微信头像Url',
  `userNo` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '员工号',
  `userName` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '员工姓名',
  `isAdmin` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '0' COMMENT '是否是管理员: 0：否；1：是。默认为0。',
  `recTime` datetime(0) NOT NULL COMMENT '绑定时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uidx_userno`(`userNo`) USING BTREE,
  INDEX `idx_openid`(`openid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 66 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '员工绑定信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for cust_users
-- ----------------------------
DROP TABLE IF EXISTS `cust_users`;
CREATE TABLE `cust_users`  (
  `userNo` char(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '员工号',
  `userName` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '员工姓名',
  `userEmail` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '员工邮箱',
  PRIMARY KEY (`userNo`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '员工基本信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for message_baseinfo
-- ----------------------------
DROP TABLE IF EXISTS `message_baseinfo`;
CREATE TABLE `message_baseinfo`  (
  `id` int(11) NOT NULL,
  `operationType` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `operationContent` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `operationTime` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `userName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `bookId` int(11) NOT NULL,
  `bookName` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
