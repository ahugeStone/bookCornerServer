package com.ahuang.bookCornerServer.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
* @ClassName: MessageBaseInfoEntity
* @Description: 公告栏信息实体类
* @author lct
* @date 2018年7月27日 上午10:50:58
* @version V1.0
 */
@Data
public class MessageBaseInfoEntity implements Serializable{
	/**
	* @Fields serialVersionUID : 序列化
	*/
	private static final long serialVersionUID = 235344467676212938L;

	private Integer id;
	private String operationType;
	private String operationContent;
	private Date operationTime;
	private String userName;
	private Integer bookId;
	private String bookName;

}
