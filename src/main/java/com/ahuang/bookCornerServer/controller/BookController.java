package com.ahuang.bookCornerServer.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.ahuang.bookCornerServer.servise.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ahuang.bookCornerServer.bo.WXUser;
import com.ahuang.bookCornerServer.controller.req.CommonRequest;
import com.ahuang.bookCornerServer.controller.req.CommonResponse;
import com.ahuang.bookCornerServer.controller.req.CustQueryBookDetailReq;
import com.ahuang.bookCornerServer.controller.req.CustQueryBookListReq;
import com.ahuang.bookCornerServer.controller.req.Request;
import com.ahuang.bookCornerServer.controller.req.Response;
import com.ahuang.bookCornerServer.entity.BookBaseInfoEntity;
import com.ahuang.bookCornerServer.entity.CustBindUsersEntity;
import com.ahuang.bookCornerServer.exception.BaseException;
import com.ahuang.bookCornerServer.servise.BookService;

/**@RestController ： 组合注解，spring 4之后新加的注解，相当于@Controller和@ResponseBody配合使用
 @Controller ： 处理http请求
 @RequestMapping ： 配置URL映射
 **/
@RestController
//@RequestMapping(path="/bookCorner", produces="application/json;charset=UTF-8")
@RequestMapping(path="/bookCorner")
public class BookController  extends BaseController{
	private final BookService bookService;

	/**
     * 自动配置
	 **/
    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    /**
	* @Title: custQueryBookList
	* @Description: 查询图书列表（分页）
	* @param req
	* @param session
	* @return
	* @throws BaseException CommonResponse<?>    返回类型
	* @author ahuang  
	* @date 2018年6月15日 下午9:43:33
	* @version V1.0
	* @throws
	*/
	@RequestMapping("/CustQueryBookList")
	public CommonResponse<?> custQueryBookList(@RequestBody @Valid CommonRequest<CustQueryBookListReq> req, HttpSession session) throws BaseException {
		this.checkLoginExp(session);
		Integer num = req.getParams().getNum();
		String bookName = req.getParams().getBookName();
		String bookType = req.getParams().getBookType();
		String bookStatus = req.getParams().getBookStatus();
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("num", num);
		param.put("pageSize", 20);
		param.put("bookName", bookName);
		param.put("bookType", bookType);
		param.put("bookStatus", bookStatus);
		Object res = bookService.queryBookListPage(param);

		return getRes(res);
	}
	/**
	* @Title: custQueryBookDetail
	* @Description: 查询图书详情
	* @param req
	* @param session
	* @return
	* @throws BaseException CommonResponse<?>    返回类型
	* @author ahuang  
	* @date 2018年6月15日 下午9:43:56
	* @version V1.0
	* @throws
	*/
	@RequestMapping("/CustQueryBookDetail")
	public CommonResponse<?> custQueryBookDetail(@RequestBody @Valid CommonRequest<CustQueryBookDetailReq> req, HttpSession session) throws BaseException {
		this.checkLoginExp(session);
		WXUser user = (WXUser)session.getAttribute("user");
		Integer bookId = req.getParams().getBookId();
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("id", bookId);
		param.put("openid", user.getOpenid());
		BookBaseInfoEntity res = bookService.queryBookDetailById(param);

		return getRes(res);
	}
	/**
	 * @throws BaseException 
	* @Title: custQueryBookCommentHistory
	* @Description: 查询图书评论
	* @param req
	* @param session
	* @return Response    返回类型
	* @author ahuang  
	* @date 2018年6月15日 下午9:44:16
	* @version V1.0
	* @throws
	*/
	@RequestMapping("/CustQueryBookCommentHistory")
	public Response custQueryBookCommentHistory(@RequestBody @Valid Request req , HttpSession session) throws BaseException {
		this.checkLoginExp(session);
		Integer bookId = (Integer)req.getParam("bookId");
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("commentHistoryList", bookService.queryCommentList(bookId));
		return getRes(result);
	}
	/**
	* @Title: custBorrowBook
	* @Description: 借阅图书
	* @param req
	* @param session
	* @return
	* @throws BaseException Response    返回类型
	* @author ahuang  
	* @date 2018年6月15日 下午10:16:59
	* @version V1.0
	* @throws
	*/
	@RequestMapping("/CustBorrowBook")
	public Response custBorrowBook(@RequestBody @Valid Request req , HttpSession session) throws BaseException {
		this.checkLoginExp(session);
		Integer bookId = (Integer)req.getParam("bookId");
		CustBindUsersEntity bindUser = (CustBindUsersEntity)session.getAttribute("bindUser");
		bookService.borrowBookById(bookId, bindUser.getOpenid());
		return getRes(null);
	}
	
	/**
	* @Title: custReturnBook
	* @Description: 图书归还
	* @param req
	* @param session
	* @return
	* @throws BaseException Response    返回类型
	* @author ahuang  
	* @date 2018年6月16日 上午9:21:46
	* @version V1.0
	* @throws
	*/
	@RequestMapping("/CustReturnBook")
	public Response custReturnBook(@RequestBody @Valid Request req , HttpSession session) throws BaseException {
		this.checkLoginExp(session);
		Integer bookId = (Integer)req.getParam("bookId");
		CustBindUsersEntity bindUser = (CustBindUsersEntity)session.getAttribute("bindUser");
		bookService.returnBookById(bookId, bindUser.getOpenid());
		return getRes(null);
	}
	@RequestMapping("/CustQueryBookBorrowRecord")
	public Response custQueryBookBorrowRecord(@RequestBody @Valid Request req , HttpSession session) throws Exception {
		this.checkLoginExp(session);
		CustBindUsersEntity bindUser = (CustBindUsersEntity)session.getAttribute("bindUser");
		List<Map<String, Object>> borrowRecordList = bookService.queryBookBorrowByOpenid(bindUser.getOpenid());
		Map<String, Object> res = new HashMap<String, Object>();
		res.put("borrowRecordList", borrowRecordList);
		return getRes(res);
	} 
	
	@RequestMapping("/CustQueryBookBorrowHistory")
	public Response custQueryBookBorrowHistory(@RequestBody @Valid Request req , HttpSession session) throws Exception {
		this.checkLoginExp(session);
		Integer bookId = Integer.valueOf(req.getParam("bookId").toString());
		List<Map<String, Object>> borrowHistoryList = bookService.queryBookBorrowHistoryByBookId(bookId);
		Map<String, Object> res = new HashMap<String, Object>();
		res.put("borrowHistoryList", borrowHistoryList);
		return getRes(res);
	}
	//用户点赞图书
	@RequestMapping("/CustLikeBook")
	public Response custLikeBook(@RequestBody @Valid Request req , HttpSession session) throws BaseException {
		this.checkLoginExp(session);
		CustBindUsersEntity bindUser = (CustBindUsersEntity)session.getAttribute("bindUser");
		Integer bookId = (Integer)req.getParam("bookId");
		String openid = bindUser.getOpenid();
		bookService.addBookLikedRecord(bookId, openid);
		return getRes(null);
	}

	//用户点赞评论
   /* @RequestMapping("/custLikeComment")
    public Response custLikeComment(@RequestBody @Valid Request req , HttpSession session) throws BaseException {
        this.checkLoginExp(session);
        CustBindUsersEntity bindUser = (CustBindUsersEntity)session.getAttribute("bindUser");
		Integer bookId = (Integer)req.getParam("bookId");
        Integer commentId = (Integer)req.getParam("commentId");
		bookService.addCommentLikedRecord(bookId, bindUser, commentId);
        return getRes(null);
    }*/

	
//	@RequestMapping(value="/CustCommentBook", produces="text/plain;charset=UTF-8;")
	@RequestMapping(path="/CustCommentBook")
	public Response custCommentBook(@RequestBody @Valid Request req , HttpSession session) throws BaseException {
		this.checkLoginExp(session);
		CustBindUsersEntity bindUser = (CustBindUsersEntity)session.getAttribute("bindUser");
		Integer bookId = (Integer)req.getParam("bookId");
		String comment = (String)req.getParam("comment");
		bookService.addCommentRecord(bookId, bindUser, comment);
		return getRes(null);
	} 
}
