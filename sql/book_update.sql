-- ----------------------------
-- Table structure for BOOK_BASEINFO
-- ----------------------------

alter table `BOOK_BASEINFO` add `bookScore` varchar(11)  NOT NULL DEFAULT '0' COMMENT '得分:默认为0';

-- ----------------------------
-- Table structure for CUST_USERS
-- ----------------------------

alter table `CUST_USERS` add `userEmail` varchar(40) DEFAULT NULL COMMENT '员工邮箱';


-- ----------------------------
-- Table structure for BOOK_COMMENTRECORD
-- ----------------------------

alter table `BOOK_COMMENTRECORD` add COLUMN `commentLikeNum` int(11) NOT NULL DEFAULT 0 COMMENT '评论点赞数';


-- ----------------------------
-- Table structure for COMMENT_LIKERECORD
-- ----------------------------
DROP TABLE IF EXISTS `COMMENT_LIKERECORD`;
CREATE TABLE `COMMENT_LIKERECORD`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `commentId` int(11) NOT NULL,
  `openid` varchar(100)  NOT NULL,
  `recTime` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `bookId` int(11) DEFAULT NULL,
  `headImgUrl` varchar(200)  DEFAULT NULL,
  `userName` varchar(20)  DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `U_OPENID_COMMENTID` (openid,commentId) USING BTREE COMMENT '同一用户只能点赞一个评论一次'
);

-- ----------------------------
-- Records of COMMENT_LIKERECORD
-- ----------------------------
INSERT INTO `COMMENT_LIKERECORD` VALUES (1, 1, 'oe0Ej0d3pqW_Bi5Bmg2lvpSdOtRE', '2018-07-25 16:42:56', 1, '12121212', '黄实');


-- ----------------------------
-- Table structure for MESSAGE_BASEINFO
-- ----------------------------
DROP TABLE IF EXISTS `MESSAGE_BASEINFO`;
CREATE TABLE `MESSAGE_BASEINFO`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `operationType` char(1)  NOT NULL,
  `operationContent` varchar(500)  NOT NULL,
  `operationTime` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `userName` varchar(20)  NOT NULL,
  `bookId` int(11) NOT NULL,
  `bookName` varchar(100)  NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_operationTime` (`operationTime`) USING BTREE
);

-- ----------------------------
-- Records of MESSAGE_BASEINFO
-- ----------------------------
INSERT INTO `MESSAGE_BASEINFO` VALUES (1, '1', '1', '2018-07-25 11:38:23', '黄实', 1, '中国震撼');
