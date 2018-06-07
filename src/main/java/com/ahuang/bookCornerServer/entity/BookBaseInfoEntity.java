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
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/
	private static final long serialVersionUID = 235344467676212938L;
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
}
