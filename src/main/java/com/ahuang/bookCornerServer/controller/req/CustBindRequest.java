package com.ahuang.bookCornerServer.controller.req;

import lombok.Data;

/**
 * custBind用户绑定接口请求报文
 *
 * @author ahuang
 * @version V1.0
 * @Title: CustBindRequest
 * @Program: bookCornerServer
 * @Package com.ahuang.bookCornerServer.controller.req
 * @create 2018-07-09 22:08
 */
@Data
public class CustBindRequest {
    /**
     * 员工工号
     */
    private String userNo;
    /**
     * 员工姓名
     */
    private String userName;
    /**
     * 微信昵称
     */
    private String nickName;
    /**
     * 用户头像
     */
    private String headImgUrl;

}
