package com.ahuang.bookCornerServer.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.ahuang.bookCornerServer.bo.WXUser;
import com.ahuang.bookCornerServer.controller.req.CommonRequest;
import com.ahuang.bookCornerServer.controller.req.CommonResponse;
import com.ahuang.bookCornerServer.controller.req.CustQueryIsBindedReq;
import com.ahuang.bookCornerServer.entity.CustBindUsersEntity;
import com.ahuang.bookCornerServer.exception.BaseException;
import com.ahuang.bookCornerServer.servise.CommonService;
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
	@Autowired
	private CommonService commonService;
	
	@RequestMapping(path="/CustQueryIsBinded",method = { RequestMethod.POST })
	public CommonResponse<?> CustQueryIsBinded(@RequestBody @Valid CommonRequest<CustQueryIsBindedReq> req, HttpSession session) throws BaseException {
		WXUser user = null;
		String openid = null;
		if (!checkLogin(session)) {
        	log.info("未登陆，获取openid");
        	String code = req.getParams().getCode();
        	openid = commonService.getOpenidByCode(code);
        	if(!ObjectUtils.isEmpty(openid)) {
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
        	log.info("已登陆，user=" + user.toString());
        	openid = user.getOpenid();
        }
		// 根据openid查询用户绑定信息
		CustBindUsersEntity bindUser = commonService.getUserByOpenid(openid);
		Map<String, Object> res = new HashMap<String, Object> ();
		res.put("isBinded", "0");//默认未绑定
		if(!ObjectUtils.isEmpty(bindUser)) {
			res.put("isBinded", "1");//已绑定
			res.put("openid", openid);
			res.put("userNo", bindUser.getUserNo());
			res.put("userName", bindUser.getUserName());
		}
		
		return getRes(res);
	}
}
