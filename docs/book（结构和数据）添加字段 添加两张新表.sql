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

alter table `book_baseinfo` add `bookScore` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '0' COMMENT '得分:默认为0';

-- ----------------------------
-- Table structure for cust_users
-- ----------------------------

alter table `cust_users` add `userEmail` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '0' COMMENT '员工邮箱:默认为0';


-- ----------------------------
-- Table structure for book_commentrecord
-- ----------------------------

alter table `book_commentrecord` add `commentLikeNum` int(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT 0 COMMENT '评论点赞数';
alter table `book_commentrecord` add `isThumbup` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '0' COMMENT '是否点赞了: 0：否；1：是。默认为0';

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
-- Records of comment_likerecord
-- ----------------------------
INSERT INTO `comment_likerecord` VALUES (1, 1, 'oe0Ej0d3pqW_Bi5Bmg2lvpSdOtRE', '2018-07-25 16:42:56', 1, '12121212', '黄实');


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

-- ----------------------------
-- Records of message_baseinfo
-- ----------------------------
INSERT INTO `message_baseinfo` VALUES (1, '1', '1', '2018-07-25 11:38:23', '黄实', 1, '中国震撼');

SET FOREIGN_KEY_CHECKS = 1;
