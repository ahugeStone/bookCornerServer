package com.ahuang.bookCornerServer.controller.req;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * 
* @ClassName: CommonResponse
* @Description: 公共返回对象，JsonInclude表示返回值如果是null，则隐藏该属性
* @author ahuang
* @date 2018年6月9日 下午6:54:23
* @version V1.0
* @param <T>
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonResponse<T> {
	private Map<String, Object> header;
	private T result;
	private boolean _isException_;
	private String code;
	private String message;
	private String type;
	
	public CommonResponse(boolean isException) {
		this._isException_ = isException;
	}
	
	public CommonResponse(T result) {
		this.result = result;
		this._isException_ = false;
	}
}
