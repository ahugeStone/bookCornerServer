package com.ahuang.bookCornerServer.entity;

import lombok.Data;

import java.util.Date;

@Data
public class CommentLikeRecordEntity {
	private Integer id;// '主键',
	private Integer bookId;//'图书编号bookId',
	private String  openid;// '微信小程序openid',
	private String headImgUrl;//'微信头像Url',
	private Integer commentId;// '评论commentId',
	private String userName;//'员工姓名',
	private Date recTime;// '点赞时间',
}
