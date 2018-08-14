-- ----------------------------
-- Table structure for BOOK_BASEINFO
-- ----------------------------

ALTER TABLE `BOOK_BASEINFO` ADD COLUMN `bookScore` varchar(11) NOT NULL DEFAULT '0' COMMENT '得分:默认为0';

ALTER TABLE `BOOK_BASEINFO` ADD COLUMN `isbn13` varchar(20) NULL DEFAULT NULL COMMENT '图书的isbn码';

-- ----------------------------
-- Table structure for CUST_USERS
-- ----------------------------

ALTER TABLE `CUST_USERS` ADD COLUMN `userEmail` varchar(200) NULL DEFAULT NULL COMMENT '员工邮箱';


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
  UNIQUE INDEX `U_OPENID_COMMENTID` (`openid`, `commentId`) USING BTREE COMMENT '同一用户只能点赞一个评论一次'
);


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
  INDEX `idx_operationTime` (`operationTime`) USING BTREE
);
