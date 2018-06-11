package com.ahuang.bookCornerServer.controller.req;

import javax.validation.constraints.NotNull;

import lombok.Data;

/**
* @ClassName: CustQueryBookDetailReq
* @Description: CustQueryBookDetailReq接口请求报文
* @author ahuang
* @date 2018年6月11日 下午9:44:00
* @version V1.0
*/
@Data
public class CustQueryBookDetailReq {
	@NotNull(message = "bookId不能为空")
	private Integer bookId;
}
