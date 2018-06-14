package com.ahuang.bookCornerServer.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class BookCommentRecordEntity implements Serializable{

	private static final long serialVersionUID = -646748675460108178L;
	private Integer id;
	private Integer bookId;
	private String openid;
	private String headImgUrl;
	private String userName;
	private String comment;
	private Date recTime;
}
