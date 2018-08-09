package com.ahuang.bookCornerServer.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookBorrowRecordEntity implements Serializable{

	private static final long serialVersionUID = -6601593729028300400L;
	private Integer id;
	private Integer bookId;
	private String bookName;
	private String borrowStatus;
	@JsonIgnore
	private String openid;
	private String headImgUrl;
	private String userName;
	private Date borrowTime;
	private Date returnTime;

	/**
	 * @fieldName: bookLikeNum
	 * @fieldType: Integer
	 * @Description: 图书点赞数：默认为0
	 */
	private Integer bookLikeNum;
	/**
	 * @fieldName: bookCommentNum
	 * @fieldType: Integer
	 * @Description: 图书评论数:默认为0
	 */
	private Integer bookCommentNum;
}
