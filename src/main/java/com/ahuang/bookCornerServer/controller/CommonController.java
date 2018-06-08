package com.ahuang.bookCornerServer.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.ahuang.bookCornerServer.controller.req.CustQueryIsBindedReq;
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
public class CommonController {
	
	@Value("${url.code2session}")
    private String code2sessionUrl;
	@Value("${tx.appid}")
    private String appid;
	@Value("${tx.secret}")
    private String secret;
	@Autowired
	private ObjectMapper objectMapper;
	
	@RequestMapping(path="/CustQueryIsBinded",method = { RequestMethod.POST })
	public Map<String, Object> CustQueryIsBinded(@RequestBody @Validated CustQueryIsBindedReq req, HttpSession session) {
		Object sessionUser = session.getAttribute("user");
		Map<String, Object> res = new HashMap<String, Object>();
		if (sessionUser == null) {
        	log.info("未登陆，获取openid");
        	String code = req.getCode();
        	RestTemplate restTemplate = new RestTemplate();
    		HttpHeaders headers = new HttpHeaders();
    		ResponseEntity<String> response = null;
            headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
            try {
            	String requestUrl = String.format(code2sessionUrl, appid, secret, code);
            	log.info("requestUrl:"+requestUrl);
                response = restTemplate.exchange(
                		requestUrl,
                        HttpMethod.GET,
                        null,
                        String.class);
            } catch (RestClientException e) {
                log.error(e.toString());
//                throw e;
            }
            log.info("response"+ response.getBody());
            try {
            	res = objectMapper.readValue(response.getBody(), Map.class);
            }catch(Exception e) {
            	log.error("error"+ e);
            }
            session.setAttribute("browser", null);
        } else {
        	log.info("已登陆，openid=" + sessionUser.toString());
        }
		return res;
	}
}
