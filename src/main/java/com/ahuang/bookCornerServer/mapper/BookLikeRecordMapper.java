package com.ahuang.bookCornerServer.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.ahuang.bookCornerServer.entity.BookLikeRecordEntity;

public interface BookLikeRecordMapper {
	@Insert("Insert into BOOK_LIKERECORD (bookId, openid, recTime) "
			+ "values(#{bookId}, #{openid}, SYSDATE())")
	public Integer insertBookLikeRecord(BookLikeRecordEntity entity);
	
	@Select("Select id, bookId, openid, recTime from BOOK_LIKERECORD "
			+ " where bookId=#{bookId} and openid=#{openid}")
	public BookLikeRecordEntity queryBookLikeRecordById(@Param("bookId") Integer bookId, @Param("openid") String openid);
}
