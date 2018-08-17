package com.ahuang.bookCornerServer.servise;

import com.ahuang.bookCornerServer.entity.CustBindUsersEntity;
import com.ahuang.bookCornerServer.exception.BaseException;
import org.springframework.stereotype.Service;

/**
 * 
* @ClassName: CommonService
* @Description: 公共服务类
* @author ahuang
* @date 2018年6月10日 下午8:50:11
* @version V1.0
 */
@Service
public interface CommonService {
	/**
	 * 
	* @Title: getOpenidByCode
	* @Description: 使用code获取openid
	* @param code
	* @return String    返回类型
	* @author ahuang  
	* @date 2018年6月10日 下午9:04:49
	* @version V1.0
	* @throws
	 */
	String getOpenidByCode(String code);
	/**
	* @Title: getUserByOpenid
	* @Description: 根据openid获取绑定用户信息
	* @param openid
	* @return CustBindUsersEntity    返回类型
	* @author ahuang  
	* @date 2018年6月10日 下午9:54:01
	* @version V1.0
	* @throws
	*/
	CustBindUsersEntity getUserByOpenid(String openid);
	
	/**
	* @Title: custUserBind
	* @Description: 绑定用户
	* @param user
	* @throws BaseException void    返回类型
	* @author ahuang  
	* @date 2018年6月26日 下午10:02:19
	* @version V1.0
	* @throws
	*/
	void custUserBind(CustBindUsersEntity user) throws BaseException;


	/**
	* 更新用户信息
	* @params  [user]
	* @return: void
	* @Author: ahuang
	* @Date: 2018/8/17 下午9:20
	*/
    void custUserUpdate(CustBindUsersEntity user);
}
