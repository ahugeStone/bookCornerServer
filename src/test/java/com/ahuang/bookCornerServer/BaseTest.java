package com.ahuang.bookCornerServer;

import com.ahuang.bookCornerServer.bo.WXUser;
import com.ahuang.bookCornerServer.entity.CustBindUsersEntity;
import com.ahuang.bookCornerServer.util.JWTUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.JsonPathResultMatchers;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.result.StatusResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;

public class BaseTest {
	MockMvc mockMvc;
	MockHttpSession session;
	private ObjectMapper mapper;
	String tokenBinded;
	String tokenNotBinded;

    /**
     * JWT加密密钥
     */
    @Value("${jwt.secret}")
    String SECRET;

    /**
     * JWT超时时间
     */
    @Value("${jwt.expiration.time}")
    long EXPIRATIONTIME;

	/**
	 * 测试openid
	 */
	@Value("${test.openid}")
	String testOpenid;

	@Autowired
	protected WebApplicationContext wac;
	/**
	* @Title: setupMockMvc
	* @Description: 测试类公共初始化方法，初始化参数，session初始化放在具体测试类中
	* @author ahuang  
	* @date 2018年6月25日 下午10:52:41
	* @version V1.0
	*/
	@Before
	public void setupMockMvc(){
	    mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	    mapper = new ObjectMapper();
	    session = new MockHttpSession();
	    WXUser user = new WXUser();
		String openid=testOpenid;
		user.setOpenid(openid);
		session.setAttribute("user", user);
		CustBindUsersEntity bindUser = new CustBindUsersEntity();
		bindUser.setOpenid(openid);
		bindUser.setUserName("黄实");
		bindUser.setUserNo("3693");
		bindUser.setHeadImgUrl("");
		session.setAttribute("bindUser", bindUser);
		tokenBinded = JWTUtil.getToken(testOpenid, bindUser, SECRET, EXPIRATIONTIME);
		tokenNotBinded = JWTUtil.getToken(testOpenid, null, SECRET, EXPIRATIONTIME);
	}
	/**
	* @Title: getRequest
	* @Description: 获取请求保文string
	* @param method 方法名
	* @param params 请求参数
	* @throws JsonProcessingException String    返回类型
	* @author ahuang  
	* @date 2018年6月25日 下午10:53:38
	* @version V1.0
	*/
	String getRequest(String method, Map<String, Object> params) throws JsonProcessingException {
		Map<String, Object> request = new HashMap<>();
		Map<String, Object> header = new HashMap<>();
		header.put("local", "zh_CN");
		header.put("agent", "WEB15");
		header.put("bfw-ctrl", "json");
		request.put("method", method);
		request.put("header", header);
		request.put("params", params);
		return mapper.writeValueAsString(request);
	}
	/**
	* 获取rest接口报文
	* @params  [params]
	* @return: java.lang.String
	* @Author: ahuang
	* @Date: 2018/7/11 下午8:29
	*/
	String getRestRequest(Map<String, Object> params) throws JsonProcessingException {
        return mapper.writeValueAsString(params);
    }
	/**
	* @Title: post
	* @Description: 简化post
	* @return MockHttpServletRequestBuilder    返回类型
	* @author ahuang  
	* @date 2018年6月25日 下午11:07:21
	* @version V1.0
	*/
	MockHttpServletRequestBuilder post(String str) {
		return MockMvcRequestBuilders.post(str);
	}

	/**
	* 简化get
	* @params  [str]
	* @return: org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder
	* @Author: ahuang
	* @Date: 2018/7/11 下午8:35
	*/
    MockHttpServletRequestBuilder get(String str) {
        return MockMvcRequestBuilders.get(str);
    }
	/**
	* @Title: status
	* @Description: 简化status
	* @return StatusResultMatchers    返回类型
	* @author ahuang  
	* @date 2018年6月25日 下午11:07:39
	* @version V1.0
	*/
	StatusResultMatchers status() {
		return MockMvcResultMatchers.status();
	}
	/**
	* @Title: jsonPath
	* @Description: 简化jsonPath
	* @return JsonPathResultMatchers    返回类型
	* @author ahuang  
	* @date 2018年6月25日 下午11:08:53
	* @version V1.0
	*/
	JsonPathResultMatchers jsonPath(String str) {
		return MockMvcResultMatchers.jsonPath(str);
	}
	/**
	* @Title: print
	* @Description: 简化print
	* @return ResultHandler    返回类型
	* @author ahuang  
	* @date 2018年6月25日 下午11:09:02
	* @version V1.0
	*/
	ResultHandler print() {
		return MockMvcResultHandlers.print();
	}
}
