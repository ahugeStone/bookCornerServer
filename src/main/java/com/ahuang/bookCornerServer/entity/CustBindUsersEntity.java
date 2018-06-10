package com.ahuang.bookCornerServer.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
* @ClassName: CustBindUsersEntity
* @Description: 用户绑定实体类
* @author ahuang
* @date 2018年6月10日 下午9:33:26
* @version V1.0
 */
@Data
public class CustBindUsersEntity implements Serializable{
	/**
	* @fieldName: serialVersionUID
	* @fieldType: long
	* @Description: 序列化
	*/
	private static final long serialVersionUID = 1263848774526191527L;
	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * 微信小程序openid
	 */
	private String openid;
	/**
	 * 微信昵称
	 */
	private String nickName;
	/**
	 * 微信头像Url
	 */
	private String headImgUrl;
	/**
	 * 员工号
	 */
	private String userNo;
	/**
	 * 员工姓名
	 */
	private String userName;
	/**
	 * 是否是管理员: 0：否；1：是。默认为0。
	 */
	private String isAdmin;
	/**
	 * 绑定时间
	 */
	private Date recTime;
}
