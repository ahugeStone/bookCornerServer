package com.ahuang.bookCornerServer.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 
* @ClassName: BookBaseInfoEntity
* @Description: 图书实体类
* @author ahuang
* @date 2018年6月2日 下午10:00:06
* @version V1.0
 */
@Data
public class BookBaseInfoEntity implements Serializable{
	/**
	* @Fields serialVersionUID : 序列化
	*/
	private static final long serialVersionUID = 235344467676212938L;
	/**
	* @fieldName: bookId
	* @fieldType: Integer
	* @Description: 图书编号
	*/
	private Integer bookId;
	/**
	* @fieldName: bookName
	* @fieldType: String
	* @Description: 图书名
	*/
	private String bookName;
	/**
	* @fieldName: bookWriter
	* @fieldType: String
	* @Description: 图书作者
	*/
	private String bookWriter;
	/**
	* @fieldName: bookBrief
	* @fieldType: String
	* @Description: 图书简介,默认为‘-’
	*/
	private String bookBrief;
	/**
	* @fieldName: bookType
	* @fieldType: String
	* @Description: 图书分类：0：党建；1：技术
	*/
	private String bookType;
	/**
	* @fieldName: bookStatus
	* @fieldType: String
	* @Description: 图书状态：0：借出；1：在库
	*/
	private String bookStatus;
	/**
	* @fieldName: bookSource
	* @fieldType: String
	* @Description: 图书来源：0：采购；1：捐赠
	*/
	private String bookSource;
	/**
	* @fieldName: bookBuyer
	* @fieldType: String
	* @Description: 图书购买者
	*/
	private String bookBuyer;
	/**
	* @fieldName: bookTime
	* @fieldType: Date
	* @Description: 图书购买/捐赠时间
	*/
	private Date bookTime;
	/**
	* @fieldName: bookRemark
	* @fieldType: String
	* @Description: 图书备注,默认为‘-’
	*/
	private String bookRemark;
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
	/**
	* @fieldName: recTime
	* @fieldType: Date
	* @Description: 入库时间
	*/
	private Date recTime;
	
	/**
	* @fieldName: isBorrowed
	* @fieldType: String
	* @Description: 是否正被当前用户借阅
	*/
	private String isBorrowed;
	
	/**
	* @fieldName: isLiked
	* @fieldType: String
	* @Description: 当前用户是否点赞该图书
	*/
	private String isLiked;
	
	/**
	* @fieldName: isCommented
	* @fieldType: String
	* @Description: 当前用户是否评论该书
	*/
	private String isCommented;

	/**
	 * @fieldName: bookScore
	 * @fieldType: String
	 * @Description: 图书得分
	 */
	private String bookScore;

}
