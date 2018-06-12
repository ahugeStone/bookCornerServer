package com.ahuang.bookCornerServer.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ahuang.bookCornerServer.bo.WXUser;
import com.ahuang.bookCornerServer.controller.req.CommonRequest;
import com.ahuang.bookCornerServer.controller.req.CommonResponse;
import com.ahuang.bookCornerServer.controller.req.CustQueryBookDetailReq;
import com.ahuang.bookCornerServer.controller.req.CustQueryBookListReq;
import com.ahuang.bookCornerServer.exception.BaseException;
import com.ahuang.bookCornerServer.servise.BookService;


@RestController
@RequestMapping("/bookCorner")
public class BookController  extends BaseController{
	@Autowired
	private BookService bookService;

	@RequestMapping("/CustQueryBookList")
	public CommonResponse<?> custQueryBookList(@RequestBody @Valid CommonRequest<CustQueryBookListReq> req, HttpSession session) throws BaseException {
		this.checkLoginExp(session);
		Integer num = req.getParams().getNum();
		String bookName = req.getParams().getBookName();
		String bookType = req.getParams().getBookType();
		String bookStatus = req.getParams().getBookStatus();
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("num", num);
		param.put("pageSize", 10);
		param.put("bookName", bookName);
		param.put("bookType", bookType);
		param.put("bookStatus", bookStatus);
		Object res = bookService.queryBookListPage(param);
		
		return getRes(res);
	}
	@RequestMapping("/CustQueryBookDetail")
	public CommonResponse<?> custQueryBookDetail(@RequestBody @Valid CommonRequest<CustQueryBookDetailReq> req, HttpSession session) throws BaseException {
		this.checkLoginExp(session);
		WXUser user = (WXUser)session.getAttribute("user");
		Integer bookId = req.getParams().getBookId();
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("id", bookId);
		param.put("openid", user.getOpenid());
		Object res = bookService.queryBookDetailById(param);
		
		return getRes(res);
	}
}
