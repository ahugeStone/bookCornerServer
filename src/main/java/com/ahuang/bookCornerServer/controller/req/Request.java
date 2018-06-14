package com.ahuang.bookCornerServer.controller.req;

import java.util.Map;

/**
* @ClassName: Request
* @Description: 通用的map类型request
* @author ahuang
* @date 2018年6月14日 下午8:28:51
* @version V1.0
*/
public class Request extends CommonRequest<Map<String, Object>>{
	/**
	* @Title: getParam
	* @Description: 获取param中参数
	* @param key
	* @return Object    返回类型
	* @author ahuang  
	* @date 2018年6月14日 下午8:29:09
	* @version V1.0
	* @throws
	*/
	public Object getParam(String key) {
		return super.getParams().get(key);
	}
	
	/**
	* @Title: getHeader
	* @Description: 获取header中参数
	* @param key
	* @return Object    返回类型
	* @author ahuang  
	* @date 2018年6月14日 下午8:29:52
	* @version V1.0
	* @throws
	*/
	public Object getHeader(String key) {
		return super.getHeader().get(key);
	}
}
