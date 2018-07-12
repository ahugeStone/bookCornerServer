package com.ahuang.bookCornerServer.controller.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
    @NotBlank(message = "员工工号不能为空")
    private String userNo;
    /**
     * 员工姓名
     */
    @NotBlank(message = "员工姓名不能为空")
    private String userName;
    /**
     * 微信昵称
     */
    @NotBlank(message = "用户昵称不能为空")
    private String nickName;
    /**
     * 用户头像
     */
    @NotNull(message = "头像不能为空")
    private String headImgUrl;

}
