package com.ahuang.bookCornerServer.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.ahuang.bookCornerServer.entity.CustBindUsersEntity;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

/**
* @ClassName: CustBindUsersMapper
* @Description: CUST_BINDUSERS表的Mapper
* @author ahuang
* @date 2018年6月10日 下午9:44:56
* @version V1.0
*/
@Service
public interface CustBindUsersMapper {
	String TABLE_NAEM = " CUST_BINDUSERS ";
	String SELECT_FIELDS = " id, openid, nickName, headImgUrl, userNo, userName, isAdmin, recTime ";

	/**
	* 查询特定用户详细信息
	* @params  [openid]
	* @return: com.ahuang.bookCornerServer.entity.CustBindUsersEntity
	* @Author: ahuang
	* @Date: 2018/7/19 下午9:20
	*/
	@Select("Select " + SELECT_FIELDS + "from " + TABLE_NAEM
			+ " where openid=#{openid}")
	CustBindUsersEntity queryByOpenid(String openid);

	/**
	* 新增绑定用户
	* @params  [entity]
	* @return: java.lang.Integer
	* @Author: ahuang
	* @Date: 2018/7/19 下午9:21
	*/
	@Insert("Insert into " + TABLE_NAEM +" (openid, nickName, headImgUrl, userNo, userName, isAdmin, recTime) "
			+ " values(#{openid}, #{nickName}, #{headImgUrl}, #{userNo}, #{userName}, 0, SYSDATE())")
	Integer insertUserInfo(CustBindUsersEntity entity);

	/**
	* 更新用户昵称，头像等信息
	* @params  [entity]
	* @return: java.lang.Integer
	* @Author: ahuang
	* @Date: 2018/8/17 下午9:17
	*/
	@Update("Update " + TABLE_NAEM + " set nickName=#{nickName}, headImgUrl=#{headImgUrl}, recTime=SYSDATE() " +
			"where openid=#{openid}")
	Integer updateUserInfo(CustBindUsersEntity entity);

	/**
	 * 查询管理员信息
	 * @params
	 * @return: List<Map<String, Object>>
	 * @Author: puxuewei
	 * @Date: 2018/8/11 下午7:31
	 */
	@Select("Select cb.openid, cb.isAdmin,cb.userName,cb.userNo,cu.userEmail from CUST_BINDUSERS cb,CUST_USERS cu"
			+ " where cb.isAdmin='1' and cb.userNo=cu.userNo ")
	List<Map<String, Object>> queryIsAdmin();
}
