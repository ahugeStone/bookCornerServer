package com.ahuang.bookCornerServer;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookCornerServerApplicationTests extends BaseTest{
	/**
	* @Title: custQueryBookList
	* @Description: 测试接口CustQueryBookList
	* @throws Exception void    返回类型
	* @author ahuang  
	* @date 2018年6月25日 下午10:58:10
	* @version V1.0
	* @throws
	*/
	@Test
	public void custQueryBookList() throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("bookName", "");
		params.put("bookType", "");
		params.put("bookStatus", "");
		String jsonStr = getRequest("CustQueryBookList",params);// 获取上送报文
		this.mockMvc.perform(post("/bookCorner/CustQueryBookList")
				.session(session) // 设置模拟session
				.contentType(MediaType.APPLICATION_JSON_UTF8) // 设置报文头
				.content(jsonStr.getBytes()))		// 设置报文参数
		.andExpect(status().isOk())//判断返回200
		.andExpect(jsonPath("$._isException_").value("false"))//判断接口返回无异常
		.andDo(print()); // 打印测试过程
	}

}
