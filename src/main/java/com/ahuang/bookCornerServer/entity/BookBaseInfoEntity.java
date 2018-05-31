package com.ahuang.bookCornerServer.entity;

import java.util.Date;

public class BookBaseInfoEntity {
	/**图书编号**/
	private Integer bookId;
	/**图书名**/
	private String bookName;
	/**图书作者**/
	private String bookWriter;
	/**图书简介,默认为‘-’**/
	private String bookBrief;
	/**图书分类：0：党建；1：技术**/
	private String bookType;
	/**图书状态：0：借出；1：在库**/
	private String bookStatus;
	/**图书来源：0：采购；1：捐赠**/
	private String bookSource;
	/**图书购买者**/
	private String bookBuyer;
	/**图书购买/捐赠时间**/
	private Date bookTime;
	/**图书备注,默认为‘-’**/
	private String bookRemark;
	/**图书点赞数：默认为0**/
	private Integer bookLikeNum;
	/**图书评论数:默认为0**/
	private Integer bookCommentNum;
	/**入库时间**/
	private Date recTime;
	public Integer getBookId() {
		return bookId;
	}
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getBookWriter() {
		return bookWriter;
	}
	public void setBookWriter(String bookWriter) {
		this.bookWriter = bookWriter;
	}
	public String getBookBrief() {
		return bookBrief;
	}
	public void setBookBrief(String bookBrief) {
		this.bookBrief = bookBrief;
	}
	public String getBookType() {
		return bookType;
	}
	public void setBookType(String bookType) {
		this.bookType = bookType;
	}
	public String getBookStatus() {
		return bookStatus;
	}
	public void setBookStatus(String bookStatus) {
		this.bookStatus = bookStatus;
	}
	public String getBookSource() {
		return bookSource;
	}
	public void setBookSource(String bookSource) {
		this.bookSource = bookSource;
	}
	public String getBookBuyer() {
		return bookBuyer;
	}
	public void setBookBuyer(String bookBuyer) {
		this.bookBuyer = bookBuyer;
	}
	public Date getBookTime() {
		return bookTime;
	}
	public void setBookTime(Date bookTime) {
		this.bookTime = bookTime;
	}
	public String getBookRemark() {
		return bookRemark;
	}
	public void setBookRemark(String bookRemark) {
		this.bookRemark = bookRemark;
	}
	public Integer getBookLikeNum() {
		return bookLikeNum;
	}
	public void setBookLikeNum(Integer bookLikeNum) {
		this.bookLikeNum = bookLikeNum;
	}
	public Integer getBookCommentNum() {
		return bookCommentNum;
	}
	public void setBookCommentNum(Integer bookCommentNum) {
		this.bookCommentNum = bookCommentNum;
	}
	public Date getRecTime() {
		return recTime;
	}
	public void setRecTime(Date recTime) {
		this.recTime = recTime;
	}
}
