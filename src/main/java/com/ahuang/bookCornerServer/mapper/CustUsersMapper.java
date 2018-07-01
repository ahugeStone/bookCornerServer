package com.ahuang.bookCornerServer.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.ahuang.bookCornerServer.entity.CustUsersEntity;
import org.springframework.stereotype.Service;

@Service
public interface CustUsersMapper {
	String TABLE_NAEM = " CUST_USERS ";
	String SELECT_FIELDS = " userNo, userName ";
	
	/**
	* @Title: queryByUserNo
	* @Description: 查询用户是否可以绑定
	* @param userNo
	* @return CustUsersEntity    返回类型
	* @author ahuang  
	* @date 2018年6月26日 下午9:56:17
	* @version V1.0
	* @throws
	*/
	@Select("Select " + SELECT_FIELDS + " from " + TABLE_NAEM 
			+ " where userNo=#{userNo}")
	public CustUsersEntity queryByUserNo(@Param("userNo") String userNo);
}
