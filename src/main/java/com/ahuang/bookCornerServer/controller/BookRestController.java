package com.ahuang.bookCornerServer.controller;

import com.ahuang.bookCornerServer.controller.req.CustBindRequest;
import com.ahuang.bookCornerServer.controller.req.CustQueryBookListReq;
import com.ahuang.bookCornerServer.entity.BookBaseInfoEntity;
import com.ahuang.bookCornerServer.entity.CustBindUsersEntity;
import com.ahuang.bookCornerServer.exception.AuthException;
import com.ahuang.bookCornerServer.exception.BaseException;
import com.ahuang.bookCornerServer.servise.BookService;
import com.ahuang.bookCornerServer.servise.CommonService;
import com.ahuang.bookCornerServer.util.StringUtil;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;
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

    private final BookService bookService;

    private final CommonService commonService;

    @Autowired
    public BookRestController(BookService bookService, CommonService commonService) {
        this.bookService = bookService;
        this.commonService = commonService;
    }

    /**
     * JWT加密密钥
     */
    @Value("${jwt.secret}")
    private String SECRET;

    /**
     * JWT超时时间
     */
    @Value("${jwt.expiration.time}")
    private long EXPIRATIONTIME;

    /**
    * 查询用户是否绑定，如果绑定返回jwt的token
    * @params  [code] 腾讯oauth的code
    * @return: java.util.Map<java.lang.String,java.lang.Object>
    * @Author: ahuang
    * @Date: 2018/7/8 下午10:27
    */
    @RequestMapping(path="/token",method = { RequestMethod.GET })
    public Map<String, Object> CustQueryIsBinded(@RequestParam("code") String code) throws BaseException {
        String openid = commonService.getOpenidByCode(code);
        if(StringUtil.isNullOrEmpty(openid)) {
            if(test) {
                // 测试模式如果openid查询失败，直接赋予测试用户的openid
                openid = testOpenid;
            } else {
                //否则说明登陆失败
                throw new AuthException("login.failed", "小程序登陆校验失败");
            }
        }
        // 根据openid查询用户绑定信息
        CustBindUsersEntity bindUser = commonService.getUserByOpenid(openid);
        // 拼接返回报文
        Map<String, Object> res = new HashMap<>();
        res.put("isBinded", "0");//默认未绑定
        if(!StringUtil.isNullOrEmpty(bindUser)) {
            res.put("isBinded", "1");//已绑定
//			res.put("openid", openid);
            res.put("userNo", bindUser.getUserNo());
            res.put("userName", bindUser.getUserName());

            String tokenJWT = Jwts.builder() //生成token
                    // 保存用户信息
//                    .claim("authorities", "ROLE_ADMIN,AUTH_WRITE")
                    .claim("id", bindUser.getId())
                    .claim("userNo", bindUser.getUserNo())
                    .claim("userName", bindUser.getUserName())
                    .claim("nickName", bindUser.getNickName())
                    .claim("headImgUrl", bindUser.getHeadImgUrl())
                    .claim("isAdmin", bindUser.getIsAdmin())
                    // 用户openid写入标题
                    .setSubject(openid)
                    // 有效期设置
                    .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                    // 签名设置
                    .signWith(SignatureAlgorithm.HS512, SECRET)
                    .compact();
            res.put("token", tokenJWT);
        } else {
            log.info("OPENID:" + openid + "未绑定！");
            String tokenJWT = Jwts.builder() //生成token
                    // 保存用户信息
                    // 用户openid写入标题
                    .setSubject(openid)
                    // 有效期设置
                    .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                    // 签名设置
                    .signWith(SignatureAlgorithm.HS512, SECRET)
                    .compact();
            res.put("token", tokenJWT);
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
        String bookName = req.getBookName();
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
        checkLoginForJWT(request);
        Map<String, Object> result = new HashMap<>();
        result.put("commentHistoryList", bookService.queryCommentList(bookId));

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
        if("borrow".equals(action)) {//借阅图书
            log.debug("borrow");
            bookService.borrowBookById(bookId, user.getOpenid());
        } else if("return".equals(action)) {//归还图书
            log.debug("return");
            bookService.returnBookById(bookId, user.getOpenid());
        } else if("thumbup".equals(action)) {//图书点赞
            bookService.addBookLikedRecord(bookId, user.getOpenid());
        }

    }

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
        List<Map<String, Object>> borrowRecordList = bookService.queryBookBorrowByOpenid(user.getOpenid());
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
    public Map custBind(@PathVariable("userNo") String userNo, CustBindRequest req, HttpServletRequest request) throws BaseException {
        CustBindUsersEntity user = checkLoginForJWTSilence(request);
        CustBindUsersEntity bindUser = commonService.getUserByOpenid(user.getOpenid());
        // 拼接返回报文
        Map<String, Object> res = new HashMap<>();
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
        String tokenJWT = Jwts.builder() //生成token
                // 保存用户信息
//                    .claim("authorities", "ROLE_ADMIN,AUTH_WRITE")
                .claim("id", bindUser.getId())
                .claim("userNo", bindUser.getUserNo())
                .claim("userName", bindUser.getUserName())
                .claim("nickName", bindUser.getNickName())
                .claim("headImgUrl", bindUser.getHeadImgUrl())
                .claim("isAdmin", bindUser.getIsAdmin())
                // 用户openid写入标题
                .setSubject(user.getOpenid())
                // 有效期设置
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                // 签名设置
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        res.put("token", tokenJWT);
        res.put("isBinded", "1");//已绑定
//			res.put("openid", openid);
        res.put("userNo", bindUser.getUserNo());
        res.put("userName", bindUser.getUserName());
        return res;
    }
}
