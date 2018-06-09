package com.ahuang.bookCornerServer.bo;

import lombok.Data;

/**
 * 
* @ClassName: WXUser
* @Description: session中保存的用户对象
* @author ahuang
* @date 2018年6月9日 下午8:18:23
* @version V1.0
 */
@Data
public class WXUser {
	/**
	 * 小程序用户openid
	 */
	private String openid;
}
