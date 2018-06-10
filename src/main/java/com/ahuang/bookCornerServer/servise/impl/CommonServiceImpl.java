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
import com.ahuang.bookCornerServer.mapper.CustBindUsersMapper;
import com.ahuang.bookCornerServer.servise.CommonService;
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
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private CustBindUsersMapper custBindUsersMapper;
	
	@SuppressWarnings("unchecked")
	@Override
	public String getOpenidByCode(String code) {
    	RestTemplate restTemplate = new RestTemplate();
    	String url = String.format(code2sessionUrl, appid, secret, code);
    	log.info("RequestToTx:" + url);
    	ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
    	Map<String, Object> res = new HashMap<String, Object>();
    	try {
			res = objectMapper.readValue(responseEntity.getBody(), Map.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	log.info("ResponseFromTX:" + responseEntity.toString());
    	String openid = null;
    	if(!ObjectUtils.isEmpty(res) && !ObjectUtils.isEmpty(res.get("openid"))) {
    		// 如果返回报文中有openid说明登陆成功
    		openid = (String)res.get("openid");
    	} else {
    		//否则说明登陆失败
    	}
		return openid;
	}
	
	@Override
	public CustBindUsersEntity getUserByOpenid(String openid) {
		return custBindUsersMapper.queryByOpenid(openid);
	}

}
