package com.ahuang.bookCornerServer.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.ahuang.bookCornerServer.entity.BookBorrowRecordEntity;

public interface BookBorrowRecordMapper {
	String BORROW_RECORD_NAME = " BOOK_BORROWRECORD r ";
	String BORROW_RECORD_FIELDS = " id, bookId, bookName, borrowStatus, openid, headImgUrl, userName, borrowTime, returnTime ";
	
	@Select("Select " + BORROW_RECORD_FIELDS + " from " + BORROW_RECORD_NAME 
			+ " where bookId=#{id} "
			+ "and openid=#{openid} order by borrowTime desc limit 1")
	public BookBorrowRecordEntity queryBookBorrowStatus(@Param("id") Integer id, @Param("openid") String openid);
	
	@Insert("Insert into BOOK_BORROWRECORD ( bookId, bookName, borrowStatus, openid, headImgUrl, userName, borrowTime, returnTime  )" 
	+ " values( #{bookId}, #{bookName}, #{borrowStatus}, #{openid}, #{headImgUrl}, #{userName}, #{borrowTime}, #{returnTime})" )
	public Integer insertBorrowRecord(BookBorrowRecordEntity entity);
	
	@Update("Update BOOK_BORROWRECORD set "
			+ "borrowStatus=#{borrowStatus}, returnTime=#{returnTime} "
			+ "where bookId=#{bookId} "
			+ "and openid=#{openid} order by borrowTime desc limit 1")
	public Integer updateBorrowRecord(BookBorrowRecordEntity entity);
}
