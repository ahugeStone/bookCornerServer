package com.ahuang.bookCornerServer.controller;

import com.ahuang.bookCornerServer.controller.req.CustQueryBookListReq;
import com.ahuang.bookCornerServer.entity.BookBaseInfoEntity;
import com.ahuang.bookCornerServer.entity.CustBindUsersEntity;
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

    @Value("${jwt.secret}")
    private String SECRET;

    @RequestMapping(path="/token",method = { RequestMethod.GET })
    public Map<String, Object> CustQueryIsBinded(@RequestParam("code") String code) throws BaseException {
        String openid = commonService.getOpenidByCode(code);
        if(StringUtil.isNullOrEmpty(openid)) {
            if(test) {
                // 测试模式如果openid查询失败，直接赋予测试用户的openid
                openid = testOpenid;
            } else {
                //否则说明登陆失败
                throw new BaseException("login.failed", "小程序登陆校验失败");
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

            long EXPIRATIONTIME = 60_000;     // 1分钟

            String JWT = Jwts.builder()
                    // 保存权限（角色）
                    .claim("authorities", "ROLE_ADMIN,AUTH_WRITE")
                    // 用户名写入标题
                    .setSubject(openid)
                    // 有效期设置
                    .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                    // 签名设置
                    .signWith(SignatureAlgorithm.HS512, SECRET)
                    .compact();
            res.put("jwt", JWT);
        } else {
            log.info("OPENID:" + openid + "未绑定！");
        }

        return res;
    }

    @Autowired
    public BookRestController(BookService bookService, CommonService commonService) {
        this.bookService = bookService;
        this.commonService = commonService;
    }


    @RequestMapping(path="/books",method = { RequestMethod.GET })
    public Object custQueryBookList(@Valid CustQueryBookListReq req) {
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

    @RequestMapping(path="/books/{bookId}",method = { RequestMethod.GET })
    public BookBaseInfoEntity custQueryBookDetail(@PathVariable Integer bookId, HttpServletRequest request) throws BaseException {
        String openid = checkLoginForJWT(request);
        Map<String, Object> param = new HashMap<>();
        param.put("id", bookId);
        param.put("openid", openid);
        return bookService.queryBookDetailById(param);
    }
}
