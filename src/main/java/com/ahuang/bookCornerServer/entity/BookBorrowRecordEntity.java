package com.ahuang.bookCornerServer.entity;

import java.io.Serializable;
import java.util.Date;

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
	private String openid;
	private String headImgUrl;
	private String userName;
	private Date borrowTime;
	private Date returnTime;
}
