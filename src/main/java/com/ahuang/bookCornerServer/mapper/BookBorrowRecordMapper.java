package com.ahuang.bookCornerServer.mapper;

import java.util.List;
import java.util.Map;

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
	
	@Select("Select  r.id, r.bookId, r.bookName, r.borrowStatus, r.headImgUrl, r.userName, r.borrowTime, r.returnTime,"
			+ "b.bookStatus from BOOK_BORROWRECORD r ,BOOK_BASEINFO b"
			+ " where r.bookId=#{bookId} "
			+ " and r.bookId=b.bookId  "
			+ " order by r.borrowTime desc")
	public List<Map<String, Object>> queryBookBorrowHistoryByBookId(@Param("bookId") Integer id);
	
	@Select("Select  r.id, r.bookId, r.bookName, r.borrowStatus, r.openid, r.headImgUrl, r.userName, r.borrowTime, r.returnTime," 
			+  "b.bookStatus from BOOK_BORROWRECORD r ,BOOK_BASEINFO b"
			+ " where r.bookId=b.bookId "
			+ " and r.openid=#{openid} order by r.borrowTime desc")
	public List<Map<String, Object>> queryBookBorrowByOpenid(@Param("openid") String openid);
	
	@Insert("Insert into BOOK_BORROWRECORD ( bookId, bookName, borrowStatus, openid, headImgUrl, userName, borrowTime, returnTime  )" 
	+ " values( #{bookId}, #{bookName}, #{borrowStatus}, #{openid}, #{headImgUrl}, #{userName}, #{borrowTime}, #{returnTime})" )
	public Integer insertBorrowRecord(BookBorrowRecordEntity entity);
	
	@Update("Update BOOK_BORROWRECORD set "
			+ "borrowStatus=#{borrowStatus}, returnTime=#{returnTime} "
			+ "where bookId=#{bookId} "
			+ "and openid=#{openid} order by borrowTime desc limit 1")
	public Integer updateBorrowRecord(BookBorrowRecordEntity entity);
}
