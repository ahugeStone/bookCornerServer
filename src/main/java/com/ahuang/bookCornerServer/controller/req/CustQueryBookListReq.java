package com.ahuang.bookCornerServer.controller.req;

import lombok.Data;

/**
* @ClassName: CustQueryBookListReq
* @Description: CustQueryBookListReq请求报文
* @author ahuang
* @date 2018年6月11日 下午8:54:43
* @version V1.0
 */
@Data
public class CustQueryBookListReq {
	private Integer num;
	private String bookName;
	private String bookType;
	private String bookStatus;
}
