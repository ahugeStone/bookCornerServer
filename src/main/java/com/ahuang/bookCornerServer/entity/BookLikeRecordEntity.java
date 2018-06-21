package com.ahuang.bookCornerServer.entity;

import java.util.Date;

import lombok.Data;
@Data
public class BookLikeRecordEntity {
	private Integer id;// '主键',
	private Integer bookId;// '图书编号',
	private String  openid;// '微信小程序openid',
	private Date recTime;// '点赞时间',
	  
}
