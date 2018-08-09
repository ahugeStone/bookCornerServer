package com.ahuang.bookCornerServer.controller;

import com.ahuang.bookCornerServer.controller.req.CustBindRequest;
import com.ahuang.bookCornerServer.controller.req.CustQueryBookListReq;
import com.ahuang.bookCornerServer.controller.req.Response;
import com.ahuang.bookCornerServer.entity.BookBaseInfoEntity;
import com.ahuang.bookCornerServer.entity.BookBorrowRecordEntity;
import com.ahuang.bookCornerServer.entity.CustBindUsersEntity;
import com.ahuang.bookCornerServer.exception.AuthException;
import com.ahuang.bookCornerServer.exception.BaseException;
import com.ahuang.bookCornerServer.servise.BookService;
import com.ahuang.bookCornerServer.servise.CommonService;
import com.ahuang.bookCornerServer.servise.EmailService;
import com.ahuang.bookCornerServer.servise.MessageService;
import com.ahuang.bookCornerServer.util.BookActions;
import com.ahuang.bookCornerServer.util.JWTUtil;
import com.ahuang.bookCornerServer.util.LoginStatus;
import com.ahuang.bookCornerServer.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Rest风格的接口
 *
 * @author ahuang
 * @version V1.0
 * @Title: BookRestController
 * @Program: bookCornerServer
 * @Package com.ahuang.bookCornerServer.controller
 * @create 2018-07-05 22:34
 */
@Slf4j
@RestController
@RequestMapping(path="/bookCorner/v1")
public class BookRestController extends BaseController{
    /**
     * JWT加密密钥
     */
    @Value("${jwt.secret}")
    protected String SECRET;

    /**
     * JWT超时时间
     */
    @Value("${jwt.expiration.time}")
    protected long EXPIRATIONTIME;

    private final BookService bookService;

    private final CommonService commonService;
    private final MessageService messageService;

    private final EmailService emailService;
    @Autowired

    public BookRestController(BookService bookService, CommonService commonService,EmailService emailService,MessageService messageService) {
        this.bookService = bookService;
        this.commonService = commonService;
        this.emailService = emailService;
        this.messageService = messageService;
    }

    /**
    * 查询用户是否绑定，如果绑定返回jwt的token
    * @params  [code] 腾讯oauth的code
    * @return: java.util.Map<java.lang.String,java.lang.Object>
    * @Author: ahuang
    * @Date: 2018/7/8 下午10:27
    */
    @RequestMapping(path="/token",method = { RequestMethod.GET })
    public Map<String, Object> CustQueryIsBinded(@RequestParam("code") String code, HttpServletRequest request) throws BaseException {
        CustBindUsersEntity bindUser = checkLoginForJWTSilence(request);// 获取JWT中用户信息
        String openid = null;
        // 拼接返回报文
        Map<String, Object> res = new HashMap<>();

        switch (JWTUtil.getLoginStatus(bindUser)) {// 判断登陆状态
            case NotLogin:
                openid = commonService.getOpenidByCode(code);
                if (StringUtil.isNullOrEmpty(openid)) {
                    if (test) {
                        // 测试模式如果openid查询失败，直接赋予测试用户的openid
                        openid = testOpenid;
                    } else {
                        //否则说明登陆失败
                        throw new AuthException("login.failed", "小程序登陆校验失败");
                    }
                }
                // 根据openid查询用户绑定信息
                bindUser = commonService.getUserByOpenid(openid);
            case LoginWithoutBinded:
                if(StringUtil.isNullOrEmpty(bindUser) || StringUtil.isNullOrEmpty(bindUser.getUserNo())) {
                    log.info("OPENID:" + openid + "未绑定！");
                    String tokenJWT = JWTUtil.getToken(openid, null, SECRET, EXPIRATIONTIME);
                    res.put("isBinded", "0");//默认未绑定
                    res.put("token", tokenJWT);
                    return res;
                }
            case LoginAndBinded:
                res.put("isBinded", "1");//已绑定
                res.put("userNo", bindUser.getUserNo());
                res.put("userName", bindUser.getUserName());

                String tokenJWT = JWTUtil.getToken(openid, bindUser, SECRET, EXPIRATIONTIME);
                res.put("token", tokenJWT);
                break;
            default:
                break;
        }

        return res;
    }

    /**
    * 查询图书列表
    * @params  [req]
    * @return: java.lang.Object
    * @Author: ahuang
    * @Date: 2018/7/8 下午10:37
    */
    @RequestMapping(path="/books",method = { RequestMethod.GET })
    public Object custQueryBookList(@Valid CustQueryBookListReq req, HttpServletRequest request) throws BaseException {
        checkLoginForJWT(request);
        Integer num = req.getNum();
        //搜索的关键词都改成小写
        String bookName = req.getBookName().toLowerCase();
        String bookType = req.getBookType();
        String bookStatus = req.getBookStatus();
        Map<String, Object> param = new HashMap<>();
        param.put("num", num);
        param.put("pageSize", 20);
        param.put("bookName", bookName);
        param.put("bookType", bookType);
        param.put("bookStatus", bookStatus);

        return bookService.queryBookListPage(param);
    }

    /**
    * 查询图书详情
    * @params  [bookId, request]
    * @return: com.ahuang.bookCornerServer.entity.BookBaseInfoEntity
    * @Author: ahuang
    * @Date: 2018/7/8 下午10:38
    */
    @RequestMapping(path="/books/{bookId}",method = { RequestMethod.GET })
    public BookBaseInfoEntity custQueryBookDetail(@PathVariable Integer bookId, HttpServletRequest request) throws BaseException {
        CustBindUsersEntity user = checkLoginForJWT(request);
        Map<String, Object> param = new HashMap<>();
        param.put("id", bookId);
        param.put("openid", user.getOpenid());
        return bookService.queryBookDetailById(param);
    }

    /**
    * 查询特定图书的所有评论
    * @params  [bookId, request]
    * @return: java.util.Map
    * @Author: ahuang
    * @Date: 2018/7/8 下午10:55
    */
    @RequestMapping(path="/books/{bookId}/comments",method = { RequestMethod.GET })
    public Map custQueryBookCommentHistory(@PathVariable("bookId") Integer bookId, HttpServletRequest request) throws BaseException {
        //checkLoginForJWT(request);
        CustBindUsersEntity user = checkLoginForJWT(request);
        Map<String, Object> param = new HashMap<>();
        param.put("id", bookId);
        param.put("openid", user.getOpenid());

        Map<String, Object> result = new HashMap<>();
        result.put("commentHistoryList", bookService.queryCommentList(param));
        return result;
    }

    /**
    * 评论图书
    * @params  [bookId, comment, request]
    * @return: void
    * @Author: ahuang
    * @Date: 2018/7/9 下午9:42
    */
    @RequestMapping(path="/books/{bookId}/comments",method = { RequestMethod.POST })
    public void custCommentBook(@PathVariable("bookId") Integer bookId, @RequestParam("comment") String comment, HttpServletRequest request) throws BaseException {
        CustBindUsersEntity user = checkLoginForJWT(request);
        bookService.addCommentRecord(bookId, user, comment);
    }

    /**
     * 用户点赞评论
     * @params  [bookId, commentId, request]
     * @return: void
     * @Author: puxuewei
     * @Date: 2018/7/25 下午3:42
     */
    @RequestMapping(path="/books/{bookId}/comments/{commentId}",method = { RequestMethod.POST })
    public Response custLikeComment(@PathVariable("bookId") Integer bookId,@PathVariable("commentId") Integer commentId, HttpServletRequest request) throws BaseException {
        CustBindUsersEntity user = checkLoginForJWT(request);
        bookService.addCommentLikedRecord(bookId, user, commentId);
        return getRes(null);
    }

    /**
    * 查询特定图书借阅历史
    * @params  [bookId, request]
    * @return: java.util.Map
    * @Author: ahuang
    * @Date: 2018/7/9 下午9:43
    */
    @RequestMapping(path="books/{bookId}/history")
    public Map custQueryBookBorrowHistory(@PathVariable("bookId") Integer bookId, HttpServletRequest request) throws BaseException {
        checkLoginForJWT(request);
        List<Map<String, Object>> borrowHistoryList = bookService.queryBookBorrowHistoryByBookId(bookId);
        Map<String, Object> res = new HashMap<>();
        res.put("borrowHistoryList", borrowHistoryList);
        return res;
    }
    
    /**
    * 操作图书
    * @params  [bookId, request]
    * @return: void
    * @Author: ahuang
    * @Date: 2018/7/9 下午8:31
    */
    @RequestMapping(path="/books/{bookId}",method = { RequestMethod.POST })
    public void custHandleBook(@PathVariable Integer bookId, @RequestParam("action") String action,
                               HttpServletRequest request) throws BaseException {
        CustBindUsersEntity user = checkLoginForJWT(request);
        if(BookActions.BORROW.toString().equals(action)) {//借阅图书
            log.debug(BookActions.BORROW.toString());
            bookService.borrowBookById(bookId, user.getOpenid());
        } else if(BookActions.RETURN.toString().equals(action)) {//归还图书
            log.debug(BookActions.RETURN.toString());
            bookService.returnBookById(bookId, user.getOpenid());
        } else if(BookActions.THUMBUP.toString().equals(action)) {//图书点赞
            log.debug(BookActions.THUMBUP.toString());
            bookService.addBookLikedRecord(bookId, user.getOpenid());
        } else {
            throw new BaseException("not.defined.action:" + action, "未知的图书操作");
        }

    }

   /* /**
    * 获取用户借阅图书的列表
    * @params  [request]
    * @return: java.util.Map
    * @Author: ahuang
    * @Date: 2018/7/9 下午9:05
    *//*
    @RequestMapping(path="users/{userNo}/history", method = { RequestMethod.GET })
    public Map custQueryBookBorrowRecord(@PathVariable("userNo") String userNo, HttpServletRequest request) throws Exception {
        CustBindUsersEntity user = checkLoginForJWT(request);
        if (!user.getUserNo().equals(userNo)) {
            throw new AuthException("userNo.not.match", "用户信息不符");
        }
        List<Map<String, Object>> borrowRecordList = bookService.queryBookBorrowByOpenid(user.getOpenid());
        Map<String, Object> res = new HashMap<>();
        res.put("borrowRecordList", borrowRecordList);
        return res;
    }*/

    /**
     * 获取用户借阅图书的列表
     * @params  [request]
     * @return: java.util.Map
     * @Author: ahuang
     * @Date: 2018/7/9 下午9:05
     */
    @RequestMapping(path="users/{userNo}/history", method = { RequestMethod.GET })
    public Map custQueryBookBorrowRecord(@PathVariable("userNo") String userNo, HttpServletRequest request) throws Exception {
        CustBindUsersEntity user = checkLoginForJWT(request);
        if (!user.getUserNo().equals(userNo)) {
            throw new AuthException("userNo.not.match", "用户信息不符");
        }
        List<BookBorrowRecordEntity> borrowRecordList = bookService.queryBookBorrowByOpenid(user.getOpenid());
        Map<String, Object> res = new HashMap<>();
        res.put("borrowRecordList", borrowRecordList);
        return res;
    }


    /**
    * 新用户绑定
    * @params  [userNo, req, request]
    * @return: java.util.Map
    * @Author: ahuang
    * @Date: 2018/7/9 下午10:58
    */
    @RequestMapping(path="users/{userNo}", method = { RequestMethod.POST })
    public Map custBind(@PathVariable("userNo") String userNo, @Valid CustBindRequest req, HttpServletRequest request) throws BaseException {
        CustBindUsersEntity user = checkLoginForJWTSilence(request);
        LoginStatus status = JWTUtil.getLoginStatus(user);
        CustBindUsersEntity bindUser;
        // 拼接返回报文
        Map<String, Object> res = new HashMap<>();
        if(LoginStatus.LoginAndBinded.equals(status)) {
            bindUser = user;
            log.info("该用户已经成功登陆，openid:" + user.getOpenid());
        } else if(LoginStatus.LoginWithoutBinded.equals(status)) {
            bindUser = commonService.getUserByOpenid(user.getOpenid());
            if(StringUtil.isNullOrEmpty(bindUser)) {
                // 如果库中没有绑定记录，说明用户需要绑定
                bindUser = new CustBindUsersEntity();
                bindUser.setOpenid(user.getOpenid());
                bindUser.setUserName(req.getUserName());
                bindUser.setHeadImgUrl(req.getHeadImgUrl());
                bindUser.setUserNo(userNo);
                bindUser.setNickName(req.getNickName());
                commonService.custUserBind(bindUser);
                log.debug("绑定成功openid:" + user.getOpenid() + ",生成token");
            } else {
                log.info("该用户已经绑定，openid:" + user.getOpenid());
            }
        } else {
            log.error("用户未登陆，无法获取openid");
            throw new AuthException("not.login", "用户未登陆");
        }
        // 生成新的token
        String tokenJWT = JWTUtil.getToken(user.getOpenid(), bindUser, SECRET, EXPIRATIONTIME);
        res.put("token", tokenJWT);
        res.put("isBinded", "1");//已绑定
//			res.put("openid", openid);
        res.put("userNo", bindUser.getUserNo());
        res.put("userName", bindUser.getUserName());
        return res;
    }
    /**
     * 查询首页公告栏信息
     * @params  []
     * @return: java.util.Map
     * @Author: lct
     * @Date: 2018/7/26 上午11:55
     */
    @RequestMapping(path="/messages",method = { RequestMethod.GET })
    public Map messageInfoQuery(@RequestParam("num") Integer num, HttpServletRequest request) throws BaseException {
        checkLoginForJWT(request);
        Map<String, Object> result = new HashMap<>();
        result.put("messageList", messageService.queryMessageList(num));

        return result;
    }
}
