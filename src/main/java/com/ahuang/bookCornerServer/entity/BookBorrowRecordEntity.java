package com.ahuang.bookCornerServer.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class BookBorrowRecordEntity implements Serializable{

	private static final long serialVersionUID = -6601593729028300400L;
	private Integer id;
	private Integer bookId;
	private String bookName;
	private String borrowStatus;
	private String openid;
	private String headImgUrl;
	private String userName;
	private String borrowTime;
	private Date returnTime;
}
