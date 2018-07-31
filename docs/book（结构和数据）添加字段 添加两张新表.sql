/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50721
 Source Host           : localhost:3306
 Source Schema         : booknew

 Target Server Type    : MySQL
 Target Server Version : 50721
 File Encoding         : 65001

 Date: 31/07/2018 16:43:59
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for book_baseinfo
-- ----------------------------

alter table `BOOK_BASEINFO` add `bookScore` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '0' COMMENT '得分:默认为0';

-- ----------------------------
-- Table structure for cust_users
-- ----------------------------

alter table `CUST_USERS` add `userEmail` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '员工邮箱';


-- ----------------------------
-- Table structure for book_commentrecord
-- ----------------------------

alter table `BOOK_COMMENTRECORD` add COLUMN `commentLikeNum` int(11) NOT NULL DEFAULT 0 COMMENT '评论点赞数';
-- alter table `BOOK_COMMENTRECORD` add COLUMN `isThumbup` char(1) NOT NULL DEFAULT '0' COMMENT '是否点赞了: 0：否；1：是。默认为0';

-- ----------------------------
-- Table structure for comment_likerecord
-- ----------------------------
DROP TABLE IF EXISTS `COMMENT_LIKERECORD`;
CREATE TABLE `COMMENT_LIKERECORD`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `commentId` int(11) NOT NULL,
  `openid` varchar(100) NOT NULL,
  `recTime` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `bookId` int(11) DEFAULT NULL,
  `headImgUrl` varchar(255) DEFAULT NULL,
  `userName` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
);

-- ----------------------------
-- Records of comment_likerecord
-- ----------------------------
INSERT INTO `COMMENT_LIKERECORD` VALUES (1, 1, 'oe0Ej0d3pqW_Bi5Bmg2lvpSdOtRE', '2018-07-25 16:42:56', 1, '12121212', '黄实');


-- ----------------------------
-- Table structure for message_baseinfo
-- ----------------------------
DROP TABLE IF EXISTS `MESSAGE_BASEINFO`;
CREATE TABLE `MESSAGE_BASEINFO`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `operationType` char(1) NOT NULL,
  `operationContent` varchar(500) DEFAULT NULL,
  `operationTime` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `userName` varchar(20) NOT NULL,
  `bookId` int(11) NOT NULL,
  `bookName` varchar(100) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
);

-- ----------------------------
-- Records of message_baseinfo
-- ----------------------------
INSERT INTO `MESSAGE_BASEINFO` VALUES (1, '1', '1', '2018-07-25 11:38:23', '黄实', 1, '中国震撼');

SET FOREIGN_KEY_CHECKS = 1;
