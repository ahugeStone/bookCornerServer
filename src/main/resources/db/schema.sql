drop table if exists "BOOK_BASEINFO";
create table "BOOK_BASEINFO" (
  "bookId" int not null primary key auto_increment,
  "bookName" varchar(100) not null,
  "bookWriter" varchar(100) not null,
  "bookBrief" varchar(2000) null default null,
  "bookType" char(1) not null,
  "bookStatus" char(1) not null,
  "bookSource" char(1) not null default '0',
  "bookBuyer" varchar(20) null default null,
  "bookTime" timestamp not null,
  "bookRemark" varchar(100) null default null,
  "bookLikeNum" int not null default 0,
  "bookCommentNum" int not null default 0,
  "recTime" timestamp not null,
  "bookScore" varchar(11) not null default '0',
  "isbn13" varchar(20) null default null
);
create index idx_baseinfo_bookName on BOOK_BASEINFO("bookName");
create index idx_baseinfo_bookType on BOOK_BASEINFO("bookType");
create index idx_baseinfo_bookStatus on BOOK_BASEINFO("bookStatus");

drop table if exists "BOOK_BORROWRECORD";
create table "BOOK_BORROWRECORD" (
  "id" int not null primary key auto_increment,
  "bookId" int not null,
  "bookName" varchar(100) not null,
  "borrowStatus" char(1) not null,
  "openid" varchar(100) not null,
  "headImgUrl" varchar(200) not null,
  "userName" varchar(20) not null,
  "borrowTime" timestamp null default null,
  "returnTime" timestamp null default null
);
create index idx_borrowrecord_bookId on BOOK_BORROWRECORD("bookId");
create index idx_borrowrecord_openid on BOOK_BORROWRECORD("openid");

drop table if exists "BOOK_COMMENTRECORD";
create table "BOOK_COMMENTRECORD" (
  "id" int not null primary key auto_increment,
  "bookId" int not null,
  "openid" varchar(100) not null,
  "headImgUrl" varchar(200) not null,
  "userName" varchar(20) not null,
  "comment" varchar(500) not null,
  "recTime" timestamp not null,
  "commentLikeNum" int not null default 0
);
create index idx_commentrecord_bookId on BOOK_COMMENTRECORD("bookId");

drop table if exists "BOOK_LIKERECORD";
create table "BOOK_LIKERECORD" (
  "id" int not null primary key auto_increment,
  "bookId" int not null,
  "openid" varchar(100) not null,
  "recTime" timestamp not null
);

drop table if exists "COMMENT_LIKERECORD";
create table "COMMENT_LIKERECORD" (
  "id" int not null primary key auto_increment,
  "commentId" int not null,
  "openid" varchar(100) not null ,
  "recTime" datetime not null default '0000-00-00 00:00:00' on update current_timestamp(),
  "bookId" int default null ,
  "headImgUrl" varchar(200) default null ,
  "userName" varchar(20) default null
);
create unique index idx_commentlikerecord_openid_commentId on COMMENT_LIKERECORD("openid","commentId");

DROP TABLE IF EXISTS "CUST_BINDUSERS";
CREATE TABLE "CUST_BINDUSERS" (
  "id" int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  "openid" varchar(100) NOT NULL,
  "nickName" varchar(50) NOT NULL,
  "headImgUrl" varchar(200) DEFAULT NULL ,
  "userNo" varchar(10) NOT NULL ,
  "userName" varchar(20) NOT NULL ,
  "isAdmin" char(1) NOT NULL DEFAULT '0' ,
  "recTime" datetime NOT NULL
);
create unique index idx_cust_bindusers_userNo on CUST_BINDUSERS("userNo");
create index idx_cust_bindusers_openid on CUST_BINDUSERS("openid");

DROP TABLE IF EXISTS "CUST_USERS";
CREATE TABLE "CUST_USERS" (
  "userNo" char(4) NOT NULL PRIMARY KEY,
  "userName" varchar(20) NOT NULL ,
  "userEmail" varchar(200) DEFAULT NULL
);

DROP TABLE IF EXISTS "MESSAGE_BASEINFO";
CREATE TABLE "MESSAGE_BASEINFO" (
  "id" int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT ,
  "operationType" char(1) NOT NULL,
  "operationContent" varchar(500) NOT NULL ,
  "operationTime" datetime NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE current_timestamp()  ,
  "userName" varchar(20) NOT NULL  ,
  "bookId" int(11) NOT NULL ,
  "bookName" varchar(100) NOT NULL
);
create index idx_message_baseinfo_operationTime on MESSAGE_BASEINFO("operationTime");
