package com.ahuang.bookCornerServer.util;

import com.ahuang.bookCornerServer.entity.CustBindUsersEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * JWT相关工具类
 *
 * @author ahuang
 * @version V1.0
 * @Title: JWTUtil
 * @Program: bookCornerServer
 * @Package com.ahuang.bookCornerServer.util
 * @create 2018-07-11 20:55
 */
public class JWTUtil {

    public static String getToken(String openid, CustBindUsersEntity bindUser, String SECRET, long EXPIRATIONTIME) {
        // 如果传入bindUser，说明该用户已经绑定，否则说明没有绑定
        String tokenJWT;
        if(StringUtil.isNullOrEmpty(bindUser)) {
            tokenJWT = Jwts.builder() //生成token
                    // 保存用户信息
                    // 用户openid写入标题
                    .setSubject(openid)
                    // 有效期设置
                    .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                    // 签名设置
                    .signWith(SignatureAlgorithm.HS512, SECRET)
                    .compact();
        } else {
            tokenJWT = Jwts.builder() //生成token
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
        }
        return tokenJWT;
    }
    /**
    * 根据token获取用户信息，如果返回的user为空，说明没有登陆，如果user只有openid不为空，说明登陆但未绑定，如果user信息完整，说明已登陆且已绑定
    * @params  [token]
    * @return: com.ahuang.bookCornerServer.entity.CustBindUsersEntity
    * @Author: ahuang
    * @Date: 2018/7/11 下午9:24
    */
    public static CustBindUsersEntity getInfo(String token, String SECRET) {
        if(StringUtil.isNullOrEmpty(token)) {// 如果token为空，则返回null
            return null;
        }
        CustBindUsersEntity user = new CustBindUsersEntity();
        String TOKEN_PREFIX = "Bearer";        // Token前缀
        Claims claims = Jwts.parser()
                // 验签
                .setSigningKey(SECRET)
                // 去掉 Bearer
                .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                .getBody();
        // 获取token中信息
        user.setOpenid(claims.getSubject());
        user.setUserName((String) claims.get("userName"));
        user.setUserNo((String) claims.get("userNo"));
        user.setHeadImgUrl((String) claims.get("headImgUrl"));
        user.setNickName((String) claims.get("nickName"));
        user.setId((Integer) claims.get("id"));
        user.setIsAdmin((String) claims.get("isAdmin"));
//            String auth = (String) claims.get("authorities");
//            Date date = claims.getExpiration();
//            log.info(openid);
//            log.info(auth);
//            log.info(date.toString());
        return user;
    }

    public static LoginStatus getLoginStatus(CustBindUsersEntity user) {
        if(StringUtil.isNullOrEmpty(user)) {
            return LoginStatus.NotLogin;
        } else {
            if(StringUtil.isNullOrEmpty(user.getOpenid())) {
                return LoginStatus.NotLogin;
            } else if(StringUtil.isNullOrEmpty(user.getUserNo())) {
                return LoginStatus.LoginWithoutBinded;
            } else {
                return LoginStatus.LoginAndBinded;
            }
        }
    }
}
