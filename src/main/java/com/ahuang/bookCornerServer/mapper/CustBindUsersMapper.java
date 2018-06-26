package com.ahuang.bookCornerServer.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.ahuang.bookCornerServer.entity.CustBindUsersEntity;

/**
* @ClassName: CustBindUsersMapper
* @Description: CUST_BINDUSERS表的Mapper
* @author ahuang
* @date 2018年6月10日 下午9:44:56
* @version V1.0
*/
public interface CustBindUsersMapper {
	String TABLE_NAEM = " CUST_BINDUSERS ";
	String SELECT_FIELDS = " id, openid, nickName, headImgUrl, userNo, userName, isAdmin, recTime ";
	
	@Select("Select " + SELECT_FIELDS + "from " + TABLE_NAEM
			+ " where openid=#{openid}")
	public CustBindUsersEntity queryByOpenid(String openid);
	
	@Insert("Insert into " + TABLE_NAEM +" (openid, nickName, headImgUrl, userNo, userName, isAdmin, recTime) "
			+ " values(#{openid}, #{nickName}, #{headImgUrl}, #{userNo}, #{userName}, 0, SYSDATE())")
	public Integer insertUserInfo(CustBindUsersEntity entity);
	
}
