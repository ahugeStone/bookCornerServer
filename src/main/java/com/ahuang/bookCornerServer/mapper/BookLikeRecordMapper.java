package com.ahuang.bookCornerServer.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.ahuang.bookCornerServer.entity.BookLikeRecordEntity;
import org.springframework.stereotype.Service;

@Service
public interface BookLikeRecordMapper {
	/**
	*  插入用户点赞记录
	* @params  [entity]
	* @return: java.lang.Integer
	* @Author: ahuang
	* @Date: 2018/7/19 下午9:16
	*/
	@Insert("Insert into BOOK_LIKERECORD (bookId, openid, recTime) "
			+ "values(#{bookId}, #{openid}, SYSDATE())")
	Integer insertBookLikeRecord(BookLikeRecordEntity entity);

	/**
	* 查询特定用户是否点赞过该图书
	* @params  [bookId, openid]
	* @return: com.ahuang.bookCornerServer.entity.BookLikeRecordEntity
	* @Author: ahuang
	* @Date: 2018/7/19 下午9:18
	*/
	@Select("Select id, bookId, openid, recTime from BOOK_LIKERECORD "
			+ " where bookId=#{bookId} and openid=#{openid}")
	BookLikeRecordEntity queryBookLikeRecordById(@Param("bookId") Integer bookId, @Param("openid") String openid);
}
