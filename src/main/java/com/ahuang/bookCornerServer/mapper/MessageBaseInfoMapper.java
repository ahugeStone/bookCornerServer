package com.ahuang.bookCornerServer.mapper;

import com.ahuang.bookCornerServer.entity.BookBaseInfoEntity;
import com.ahuang.bookCornerServer.entity.BookBorrowRecordEntity;
import com.ahuang.bookCornerServer.entity.MessageBaseInfoEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 *
* @ClassName: MessageBaseInfoMapper
* @Description: MESSAGE_BASEINFO表的Mapper
* @author lct
* @date 2018年7月27日 上午10:50:58
* @version V1.00
 */
@Service

public interface MessageBaseInfoMapper {
	//String TABLE_NAME = " MESSAGE_BASEINFO m ";
	String SELECT_FIELDS = " id, operationType, operationContent, operationTime, userName, bookId, bookName ";
	Integer num = 0;




	/**
	* 查询公告栏信息列表
	* @params  []
	* @return: java.util.List<com.ahuang.bookCornerServer.entity.messageBaseInfoEntity>
	* @Author: lct
	* @Date: 2018年7月27日 上午10:50:58
	*/
	@Select("Select " + SELECT_FIELDS + " from MESSAGE_BASEINFO order by operationTime desc limit 0 , #{num}"  )
	List<MessageBaseInfoEntity> queryMessageList(Integer num);




    @Insert("Insert into MESSAGE_BASEINFO ( operationType, operationContent, operationTime, userName, bookId, bookName)"
            + " values( #{operationType}, #{operationContent}, SYSDATE(), #{userName}, #{bookId}, #{bookName})" )
    Integer insertMessage(MessageBaseInfoEntity entity);


}
