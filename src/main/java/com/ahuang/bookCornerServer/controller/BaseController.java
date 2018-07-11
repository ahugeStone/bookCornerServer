package com.ahuang.bookCornerServer.controller;

import com.ahuang.bookCornerServer.bo.WXUser;
import com.ahuang.bookCornerServer.controller.req.Response;
import com.ahuang.bookCornerServer.entity.CustBindUsersEntity;
import com.ahuang.bookCornerServer.exception.AuthException;
import com.ahuang.bookCornerServer.exception.BaseException;
import com.ahuang.bookCornerServer.servise.CommonService;
import com.ahuang.bookCornerServer.util.JWTUtil;
import com.ahuang.bookCornerServer.util.StringUtil;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 
* @ClassName: BaseController
* @Description: 基础Controller，其他Controller需要继承他，提供公共方法，比如判断是否登陆
* @author ahuang
* @date 2018年6月9日 下午8:19:35
* @version V1.0
 */
@Slf4j
@RestController
public class BaseController {

	@Autowired
	CommonService commonService;

	/**
     * 是否为调试模式（调试模式默认注入测试用户）
	* @fieldName: debug
	* @fieldType: boolean
	* @Description: 是否为调试模式（调试模式默认注入测试用户）
	*/
	@Value("${tx.debug}")
	boolean debug;
    /**
     * 是否为测试模式（测试模式任意code都可以获取测试用户openid）
     * @fieldName: debug
     * @fieldType: boolean
     * @Description: 是否为测试模式（测试模式任意code都可以获取测试用户openid）
     */
    @Value("${tx.test}")
    boolean test;

    /**
     * 测试openid
     */
    @Value("${test.openid}")
	String testOpenid;

    /**
     * JWT加密密钥
     */
    @Value("${jwt.secret}")
    String SECRET;

	/**
	 * 
	* @Title: getRes
	* @Description: 获取返回对象
	* @return CommonResponse<?>    返回类型
	* @author ahuang  
	* @date 2018年6月9日 下午6:10:35
	* @version V1.0
	 */
	Response getRes(Object res) {
		log.info("res:" + res);
		return new Response(res);
	}
	/**
	 * 
	* @Title: checkLogin
	* @Description: 检查用户是否登陆
	* @return boolean    返回类型
	* @author ahuang  
	* @date 2018年6月9日 下午6:11:00
	* @version V1.0
	 */
	boolean checkLogin(HttpSession session) {
		Object sessionUser = session.getAttribute("user");
		if (StringUtil.isNullOrEmpty(sessionUser)) {
			if(!debug) {
				return false;
			} else {
				log.debug("调试模式，植入默认用户……");
				WXUser user = new WXUser();
				String openid=testOpenid;
        		user.setOpenid(openid);
        		session.setAttribute("user", user);
        		CustBindUsersEntity bindUser = commonService.getUserByOpenid(openid);
        		session.setAttribute("bindUser", bindUser);
			}
		}
		return true;
	}
    /**
    * 校验JWT
    * @params  [request]
    * @return: java.lang.String
    * @Author: ahuang
    * @Date: 2018/7/7 下午9:37
    */
    CustBindUsersEntity checkLoginForJWTSilence(HttpServletRequest request) {
        String tokenJWT = request.getHeader("Authorization");
        CustBindUsersEntity user = null;
        try{
            user = JWTUtil.getInfo(tokenJWT, SECRET);
        } catch( ExpiredJwtException exp ) {
            // 超时异常
            log.error("token.JWT.timeout");
        } catch (Exception e) {
            // 其他异常
            log.error("token.JWT.error");
        }
        if(StringUtil.isNullOrEmpty(user)) {
            if(debug) {
                // 如果jwt检测失败，但是为调试模式，则返回调试用户
                log.info("调试模式，校验成功");
				user = commonService.getUserByOpenid(testOpenid);
            } else {
                log.info("非调试模式，token解析失败");
            }

        }
        return user;
    }

    /**
     * 查询用户是否登陆，未登陆抛出异常
     * @param request
     * @return
     * @throws BaseException
     */
    CustBindUsersEntity checkLoginForJWT(HttpServletRequest request) throws BaseException {
        CustBindUsersEntity res = checkLoginForJWTSilence(request);
        if(StringUtil.isNullOrEmpty(res)) {
            throw new AuthException("not Login!", "没有登陆或登陆超时");
        }
        if(StringUtil.isNullOrEmpty(res.getUserNo())) {
            throw new AuthException("not Binded!", "用户没有绑定");
        }
        return res;
    }

	/**
	 * 
	* @Title: checkLoginExp
	* @Description: 未登陆抛出异常
	* @return void    返回类型
	* @author ahuang  
	* @date 2018年6月9日 下午6:12:58
	* @version V1.0
	 */
    void checkLoginExp(HttpSession session) throws BaseException {
		Object sessionUser = session.getAttribute("user");
		if (StringUtil.isNullOrEmpty(sessionUser)) {
			if(!debug) {
				throw new BaseException("not Login!", "没有登陆");
			} else {
				log.debug("调试模式，植入默认用户……");
				WXUser user = new WXUser();
				String openid=testOpenid;
        		user.setOpenid(openid);
        		session.setAttribute("user", user);
        		CustBindUsersEntity bindUser = commonService.getUserByOpenid(openid);
        		session.setAttribute("bindUser", bindUser);
			}
		}
	}
}
