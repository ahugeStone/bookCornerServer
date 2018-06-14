package com.ahuang.bookCornerServer.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

import com.ahuang.bookCornerServer.bo.WXUser;
import com.ahuang.bookCornerServer.controller.req.Response;
import com.ahuang.bookCornerServer.exception.BaseException;

import lombok.extern.slf4j.Slf4j;

/**
 * 
* @ClassName: BaseController
* @Description: 基础Controller，其他Controller需要继承他，提供公共方法，比如判断是否登陆
* @author ahuang
* @date 2018年6月9日 下午8:19:35
* @version V1.0
 */
@Slf4j
@RestController
public class BaseController {
	/**
	* @fieldName: debug
	* @fieldType: boolean
	* @Description: 是否为调试模式（调试模式不校验腾讯是否登陆）
	*/
	@Value("${tx.debug}")
	private boolean debug;
	/**
	 * 
	* @Title: getRes
	* @Description: 获取返回对象
	* @param @param res
	* @return CommonResponse<?>    返回类型
	* @author ahuang  
	* @date 2018年6月9日 下午6:10:35
	* @version V1.0
	* @throws
	 */
	public Response getRes(Object res) {
		log.info("res:" + res);
		return new Response(res);
	}
	/**
	 * 
	* @Title: checkLogin
	* @Description: 检查用户是否登陆
	* @param @param session
	* @param @return    设定文件
	* @return boolean    返回类型
	* @author ahuang  
	* @date 2018年6月9日 下午6:11:00
	* @version V1.0
	* @throws
	 */
	public boolean checkLogin(HttpSession session) {
		Object sessionUser = session.getAttribute("user");
		if (sessionUser == null) {
			if(!debug) {
				return false;
			} else {
				WXUser user = new WXUser();
        		user.setOpenid("oe0Ej0besxqth6muj72ZzfYGmMp0");
        		session.setAttribute("user", user);
			}
		}
		return true;
	}
	/**
	 * 
	* @Title: checkLoginExp
	* @Description: 未登陆抛出异常
	* @param @param session
	* @param @throws Exception    设定文件
	* @return void    返回类型
	* @author ahuang  
	* @date 2018年6月9日 下午6:12:58
	* @version V1.0
	* @throws
	 */
	public void checkLoginExp(HttpSession session) throws BaseException {
		Object sessionUser = session.getAttribute("user");
		if (sessionUser == null) {
			if(!debug) {
				throw new BaseException("not Login!", "没有登陆");
			} else {
				WXUser user = new WXUser();
        		user.setOpenid("oe0Ej0besxqth6muj72ZzfYGmMp0");
        		session.setAttribute("user", user);
			}
		}
	}
}
