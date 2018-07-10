package com.ahuang.bookCornerServer;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookCornerServerApplicationTests extends BaseTest{
	
	@Value("${tx.test}")
	private boolean test;
	
	/**
	 * @throws Exception 
	* @Title: custQueryIsBinded
	* @Description: 测试custQueryIsBinded
	* @author ahuang  
	* @date 2018年6月28日 下午8:51:21
	* @version V1.0
	* @throws
	*/
	@Test
	public void custQueryIsBinded() throws Exception {
		if(test) {//测试模式不需要测试本接口
			return;
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("code", "123");
		String jsonStr = getRequest("CustQueryIsBinded",params);// 获取上送报文
		this.mockMvc.perform(post("/bookCorner/CustQueryIsBinded")
//				.session(session) // 设置模拟session
				.contentType(MediaType.APPLICATION_JSON_UTF8) // 设置报文头
				.content(jsonStr.getBytes()))		// 设置报文参数
		.andExpect(status().isOk())//判断返回200
		.andExpect(jsonPath("$._isException_").value("true"))//假的code会失败
		.andExpect(jsonPath("$.code").value("login.failed"))//假的code会失败
		.andDo(print()); // 打印测试过程
		
		this.mockMvc.perform(post("/bookCorner/CustQueryIsBinded")
				.session(session) // 设置模拟session
				.contentType(MediaType.APPLICATION_JSON_UTF8) // 设置报文头
				.content(jsonStr.getBytes()))		// 设置报文参数
		.andDo(print()) // 打印测试过程
		.andExpect(status().isOk())//判断返回200
		.andExpect(jsonPath("$._isException_").value("false"))//已登陆即使code失效也会成功
		.andExpect(jsonPath("$.result.isBinded").value("1"))//返回已经绑定
		.andExpect(jsonPath("$.result.userNo").value("3693"))//返回测试用的员工号
		.andExpect(jsonPath("$.result.userName").value("黄实"))//返回测试用的员工名
		;
	}
	
	/**
	* @Title: custBind
	* @Description: 测试CustBind接口
	* @author ahuang  
	* @date 2018年6月28日 下午9:04:27
	* @version V1.0
	* @throws
	*/
	@Test
	public void custBind() throws Exception{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userNo", "3693");
		params.put("userName", "黄实");
		params.put("nickName", "昵称");
		params.put("headImgUrl", "");
		String jsonStr = getRequest("CustBind",params);// 获取上送报文
		this.mockMvc.perform(post("/bookCorner/CustBind")
//				.session(session) // 设置模拟session
				.contentType(MediaType.APPLICATION_JSON_UTF8) // 设置报文头
				.content(jsonStr.getBytes()))		// 设置报文参数
		.andExpect(status().is(500))//判断返回200
		.andExpect(jsonPath("$._isException_").value("true"))//未登录用户无法进行绑定操作
		.andExpect(jsonPath("$.code").value("not Login!"))//未登录用户无法进行绑定操作
		.andDo(print()); // 打印测试过程
		
		this.mockMvc.perform(post("/bookCorner/CustBind")
				.session(session) // 设置模拟session
				.contentType(MediaType.APPLICATION_JSON_UTF8) // 设置报文头
				.content(jsonStr.getBytes()))		// 设置报文参数
		.andExpect(status().isOk())//判断返回200
		.andExpect(jsonPath("$._isException_").value("false"))//已绑定用户可以重复绑定
		.andDo(print()); // 打印测试过程
	}
	
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

	@Test
	public void custQueryBookDetail() throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("bookId", 16);
		String jsonStr = getRequest("CustQueryBookDetail",params);// 获取上送报文
		this.mockMvc.perform(post("/bookCorner/CustQueryBookDetail")
				.session(session) // 设置模拟session
				.contentType(MediaType.APPLICATION_JSON_UTF8) // 设置报文头
				.content(jsonStr.getBytes()))		// 设置报文参数
		.andExpect(status().isOk())//判断返回200
		.andExpect(jsonPath("$._isException_").value("false"))//判断接口返回无异常
		.andExpect(jsonPath("$.result").isNotEmpty())
		.andExpect(jsonPath("$.result.bookId").isNumber())
		.andExpect(jsonPath("$.result.bookLikeNum").isNumber())
		.andExpect(jsonPath("$.result.bookCommentNum").isNumber())
		.andExpect(jsonPath("$.result.isBorrowed").exists())
		.andExpect(jsonPath("$.result.isLiked").exists())
		.andDo(print()); // 打印测试过程
	}
	
	@Test
	public void custLikeBook() throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("bookId", 16);
		String jsonStr = getRequest("CustLikeBook",params);// 获取上送报文
		this.mockMvc.perform(post("/bookCorner/CustLikeBook")
				.session(session) // 设置模拟session
				.contentType(MediaType.APPLICATION_JSON_UTF8) // 设置报文头
				.content(jsonStr.getBytes()))		// 设置报文参数
		.andExpect(status().isOk())//判断返回200
		.andExpect(jsonPath("$._isException_").value("false"))//判断接口返回无异常
		.andDo(print()); // 打印测试过程
	}
	
	@Test
	public void custQueryBookCommentHistory() throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("bookId", 16);
		String jsonStr = getRequest("CustQueryBookCommentHistory",params);// 获取上送报文
		this.mockMvc.perform(post("/bookCorner/CustQueryBookCommentHistory")
				.session(session) // 设置模拟session
				.contentType(MediaType.APPLICATION_JSON_UTF8) // 设置报文头
				.content(jsonStr.getBytes()))		// 设置报文参数
		.andExpect(status().isOk())//判断返回200
		.andExpect(jsonPath("$._isException_").value("false"))//判断接口返回无异常
		.andExpect(jsonPath("$.result.commentHistoryList").isArray())
		.andExpect(jsonPath("$.result.commentHistoryList[0].comment").exists())
		.andExpect(jsonPath("$.result.commentHistoryList[0].userName").exists())
		.andDo(print()); // 打印测试过程
	}
	
	@Test
	public void custCommentBook() throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("bookId", 16);
		params.put("userName", "黄实");
		params.put("comment", "测试");
		String jsonStr = getRequest("CustCommentBook",params);// 获取上送报文
		this.mockMvc.perform(post("/bookCorner/CustCommentBook")
				.session(session) // 设置模拟session
				.contentType(MediaType.APPLICATION_JSON_UTF8) // 设置报文头
				.content(jsonStr.getBytes()))		// 设置报文参数
		.andExpect(status().isOk())//判断返回200
		.andExpect(jsonPath("$._isException_").value("false"))//判断接口返回无异常
		.andDo(print()); // 打印测试过程
	}
	
	@Test
	public void custQueryBookBorrowHistory() throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("bookId", 2);
		String jsonStr = getRequest("CustQueryBookBorrowHistory",params);// 获取上送报文
		this.mockMvc.perform(post("/bookCorner/CustQueryBookBorrowHistory")
				.session(session) // 设置模拟session
				.contentType(MediaType.APPLICATION_JSON_UTF8) // 设置报文头
				.content(jsonStr.getBytes()))		// 设置报文参数
		.andExpect(status().isOk())//判断返回200
		.andExpect(jsonPath("$._isException_").value("false"))//判断接口返回无异常
		.andExpect(jsonPath("$.result.borrowHistoryList").isArray())
		.andExpect(jsonPath("$.result.borrowHistoryList[0].userName").exists())
		.andExpect(jsonPath("$.result.borrowHistoryList[0].borrowTime").exists())
		.andDo(print()); // 打印测试过程
	}
	
	@Test
	public void custBorrowBook() throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("bookId", 3);
		String jsonStr = getRequest("CustBorrowBook",params);// 获取上送报文
		this.mockMvc.perform(post("/bookCorner/CustBorrowBook")
				.session(session) // 设置模拟session
				.contentType(MediaType.APPLICATION_JSON_UTF8) // 设置报文头
				.content(jsonStr.getBytes()))		// 设置报文参数
		.andExpect(status().is(500))//判断返回200
		.andExpect(jsonPath("$._isException_").value("true"))//已借阅图书不可重复借阅
		.andDo(print()); // 打印测试过程
	}
	
	@Test
	public void custReturnBook() throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("bookId", 4);
		String jsonStr = getRequest("CustReturnBook",params);// 获取上送报文
		this.mockMvc.perform(post("/bookCorner/CustReturnBook")
				.session(session) // 设置模拟session
				.contentType(MediaType.APPLICATION_JSON_UTF8) // 设置报文头
				.content(jsonStr.getBytes()))		// 设置报文参数
		.andExpect(status().is(500))//判断返回200
		.andExpect(jsonPath("$._isException_").value("true"))//已归还图书不可再次归还
		.andDo(print()); // 打印测试过程
	}
	
	@Test
	public void custQueryBookBorrowRecord() throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		String jsonStr = getRequest("CustQueryBookBorrowRecord",params);// 获取上送报文
		this.mockMvc.perform(post("/bookCorner/CustQueryBookBorrowRecord")
				.session(session) // 设置模拟session
				.contentType(MediaType.APPLICATION_JSON_UTF8) // 设置报文头
				.content(jsonStr.getBytes()))		// 设置报文参数
		.andExpect(status().isOk())//判断返回200
		.andExpect(jsonPath("$._isException_").value("false"))
		.andExpect(jsonPath("$.result.borrowRecordList").isArray())
		.andExpect(jsonPath("$.result.borrowRecordList[0].bookStatus").exists())
		.andExpect(jsonPath("$.result.borrowRecordList[0].borrowStatus").exists())
		.andExpect(jsonPath("$.result.borrowRecordList[0].bookName").exists())
		.andDo(print()); // 打印测试过程
	}
}
