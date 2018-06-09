package com.ahuang.bookCornerServer.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ahuang.bookCornerServer.bo.WXUser;
import com.ahuang.bookCornerServer.controller.req.CommonRequest;
import com.ahuang.bookCornerServer.controller.req.CommonResponse;
import com.ahuang.bookCornerServer.controller.req.CustQueryIsBindedReq;
import com.ahuang.bookCornerServer.exception.BaseException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * 
* @ClassName: CommonController
* @Description: 公共接口，登陆等
* @author ahuang
* @date 2018年6月8日 下午9:02:00
* @version V1.0
 */
@Slf4j
@RestController
@RequestMapping("/common")
public class CommonController extends BaseController{
	
	@Value("${url.code2session}")
    private String code2sessionUrl;
	@Value("${tx.appid}")
    private String appid;
	@Value("${tx.secret}")
    private String secret;
	@Autowired
	private ObjectMapper objectMapper;
	
	@RequestMapping(path="/CustQueryIsBinded",method = { RequestMethod.POST })
	public CommonResponse<?> CustQueryIsBinded(@RequestBody @Valid CommonRequest<CustQueryIsBindedReq> req, HttpSession session) throws BaseException {
		WXUser user = null;
		Map<String, Object> res = new HashMap<String, Object>();
		if (!checkLogin(session)) {
        	log.info("未登陆，获取openid");
        	String code = req.getParams().getCode();
        	RestTemplate restTemplate = new RestTemplate();
        	ResponseEntity<String> responseEntity = restTemplate.getForEntity(String.format(code2sessionUrl, appid, secret, code), String.class);
        	try {
				res = objectMapper.readValue(responseEntity.getBody(), Map.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
        	log.info("ResponseFromTX:" + responseEntity.toString());
        	if(!ObjectUtils.isEmpty(res) && !ObjectUtils.isEmpty(res.get("openid"))) {
        		// 如果返回报文中有openid说明登陆成功
        		String openid = (String)res.get("openid");
        		user = new WXUser();
        		user.setOpenid(openid);
        		session.setAttribute("user", user);
        	} else {
        		//否则说明登陆失败
        		throw new BaseException("login.failed", "小程序登陆校验失败");
        	}
        } else {
        	user = (WXUser)session.getAttribute("user");
        	log.info("已登陆，user=" + user.toString());
        }
		return getRes(user);
	}
}
