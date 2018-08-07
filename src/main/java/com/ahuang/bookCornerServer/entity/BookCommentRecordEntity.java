package com.ahuang.bookCornerServer.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class BookCommentRecordEntity implements Serializable{

	private static final long serialVersionUID = -646748675460108178L;
	private Integer id;
	private Integer bookId;
	@JsonIgnore
	private String openid;
	private String headImgUrl;
	private String userName;
	private String comment;
	private Date recTime;
	private Integer commentLikeNum;
	/**
	 * @fieldName: isLiked
	 * @fieldType: String
	 * @Description: 当前用户是否点赞该评论
	 */
	private String isLiked;

}
