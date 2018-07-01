package com.ahuang.bookCornerServer.servise.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

import com.ahuang.bookCornerServer.entity.CustBindUsersEntity;
import com.ahuang.bookCornerServer.entity.CustUsersEntity;
import com.ahuang.bookCornerServer.exception.BaseException;
import com.ahuang.bookCornerServer.mapper.CustBindUsersMapper;
import com.ahuang.bookCornerServer.mapper.CustUsersMapper;
import com.ahuang.bookCornerServer.servise.CommonService;
import com.ahuang.bookCornerServer.util.StringUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CommonServiceImpl implements CommonService {
	@Value("${url.code2session}")
    private String code2sessionUrl;
	@Value("${tx.appid}")
    private String appid;
	@Value("${tx.secret}")
    private String secret;
	private final ObjectMapper objectMapper;
	private final CustBindUsersMapper custBindUsersMapper;
	private final CustUsersMapper custUsersMapper;

	@Autowired
	public CommonServiceImpl(ObjectMapper objectMapper, CustBindUsersMapper custBindUsersMapper, CustUsersMapper custUsersMapper) {
		this.objectMapper = objectMapper;
		this.custBindUsersMapper = custBindUsersMapper;
		this.custUsersMapper = custUsersMapper;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getOpenidByCode(String code) {
    	RestTemplate restTemplate = new RestTemplate();
    	String url = String.format(code2sessionUrl, appid, secret, code);
    	log.info("RequestToTx:" + url);
    	ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
    	Map<String, Object> res = new HashMap<>();
    	try {
			res = objectMapper.readValue(responseEntity.getBody(), Map.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	log.info("ResponseFromTX:" + responseEntity.toString());
    	String openid = null;
        //否则说明登陆失败
        if(!ObjectUtils.isEmpty(res) && !ObjectUtils.isEmpty(res.get("openid"))) {
    		// 如果返回报文中有openid说明登陆成功
    		openid = (String)res.get("openid");
    		log.debug("腾讯校验通过，openid" + openid);
    	} else log.debug("腾讯校验失败");
		return openid;
	}
	
	@Override
	public CustBindUsersEntity getUserByOpenid(String openid) {
		return custBindUsersMapper.queryByOpenid(openid);
	}
	
	@Override
	public void custUserBind(CustBindUsersEntity user) throws BaseException {
		
		CustUsersEntity cuser = custUsersMapper.queryByUserNo(user.getUserNo());
		if(StringUtil.isNullOrEmpty(cuser)) {
			throw new BaseException("user.not.allowed", "该用户无法绑定");
		} else if(!user.getUserName().equals(cuser.getUserName())) {
			throw new BaseException("user.name.not.match", "该用户绑定信息有误");
		}
		Integer count = custBindUsersMapper.insertUserInfo(user);
		if(count != 1) {
			throw new BaseException("user.cant.add", "该用户绑定失败");
		}
	}
}
