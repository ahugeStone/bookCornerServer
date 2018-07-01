package com.ahuang.bookCornerServer.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.ahuang.bookCornerServer.bo.WXUser;
import com.ahuang.bookCornerServer.controller.req.CommonResponse;
import com.ahuang.bookCornerServer.controller.req.Request;
import com.ahuang.bookCornerServer.controller.req.Response;
import com.ahuang.bookCornerServer.entity.CustBindUsersEntity;
import com.ahuang.bookCornerServer.exception.BaseException;
import com.ahuang.bookCornerServer.servise.CommonService;
import com.ahuang.bookCornerServer.util.StringUtil;

import lombok.extern.slf4j.Slf4j;

/**
* @ClassName: CommonController
* @Description: 公共接口，登陆等
* @author ahuang
* @date 2018年6月8日 下午9:02:00
* @version V1.0
 */
@Slf4j
@RestController
@RequestMapping("/bookCorner")
public class CommonController extends BaseController{
	private final CommonService commonService;
	/**
	* @fieldName: test
	* @fieldType: boolean
	* @Description: 是否为测试模式（测试模式根据任意code都可以获取测试用户openid）
	*/
	@Value("${tx.test}")
	private boolean test;

	@Autowired
	public CommonController(CommonService commonService) {
		this.commonService = commonService;
	}

	@RequestMapping(path="/CustQueryIsBinded",method = { RequestMethod.POST })
	public CommonResponse<?> CustQueryIsBinded(@RequestBody @Valid Request req, HttpSession session) throws BaseException {
		WXUser user = null;
		String openid = null;
		if (!checkLogin(session)) {
        	log.info("未登陆，获取openid");
        	String code = (String) req.getParam("code");
        	if(!test) {
        		openid = commonService.getOpenidByCode(code);
        	} else {
        		openid = "oe0Ej0besxqth6muj72ZzfYGmMp0";
        	}
        	if(!StringUtil.isNullOrEmpty(openid)) {
        		// 如果返回报文中有openid说明登陆成功
        		user = new WXUser();
        		user.setOpenid(openid);
        		// 保存查询到的openid，加快后续查询速度
        		session.setAttribute("user", user);
        	} else {
        		//否则说明登陆失败
        		throw new BaseException("login.failed", "小程序登陆校验失败");
        	}
        } else {
        	user = (WXUser)session.getAttribute("user");
        	openid = user.getOpenid();
        	log.info("已登陆，openid=" + openid);
        }
		
		// 根据openid查询用户绑定信息
		CustBindUsersEntity bindUser = (CustBindUsersEntity)session.getAttribute("bindUser");
				
		if(StringUtil.isNullOrEmpty(bindUser)) {
			log.debug("session中没有openid:" + openid + "绑定信息，从库中查询……");
			bindUser = commonService.getUserByOpenid(openid);
			if(StringUtil.isNullOrEmpty(bindUser)) {
				log.info("OPENID:" + openid + "未绑定！");
			} else {
				log.debug("库中查询到openid:" + openid + "绑定信息，写入session……");
				session.setAttribute("bindUser", bindUser);
			}
		} 
		Map<String, Object> res = new HashMap<String, Object> ();
		res.put("isBinded", "0");//默认未绑定
		if(!StringUtil.isNullOrEmpty(bindUser)) {
			res.put("isBinded", "1");//已绑定
//			res.put("openid", openid);
			res.put("userNo", bindUser.getUserNo());
			res.put("userName", bindUser.getUserName());
		}
		
		return getRes(res);
	}
	@RequestMapping("/CustBind")
	public Response custBind(@RequestBody @Valid Request req, HttpSession session) throws BaseException {
		this.checkLoginExp(session);//如果用户没有登陆则报错
		String userNo = (String) req.getParam("userNo");
		String userName = (String)req.getParam("userName");
		String nickName = (String)req.getParam("nickName");
		String headImgUrl = (String)req.getParam("headImgUrl");
		WXUser user = (WXUser)session.getAttribute("user");
		String openid = user.getOpenid();
		CustBindUsersEntity bindUser = commonService.getUserByOpenid(openid);
		if(StringUtil.isNullOrEmpty(bindUser)) {
			// 如果库中没有绑定记录，说明用户需要绑定
			bindUser = new CustBindUsersEntity();
			bindUser.setOpenid(openid);
			bindUser.setUserName(userName);
			bindUser.setHeadImgUrl(headImgUrl);
			bindUser.setUserNo(userNo);
			bindUser.setNickName(nickName);
			commonService.custUserBind(bindUser);
			log.debug("绑定成功openid:" + openid + "，写入session……");
			session.setAttribute("bindUser", bindUser);
		} else {
			log.info("该用户已经绑定，openid:" + openid);
		}
		
		return getRes(null);
	}
}
