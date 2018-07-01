package com.ahuang.bookCornerServer.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.ahuang.bookCornerServer.entity.BookCommentRecordEntity;
import org.springframework.stereotype.Service;

@Service
public interface BookCommentRecordMapper {
	String COMMENT_RECORD_NAME = " BOOK_COMMENTRECORD c";
	String COMMENT_RECORD_FIELDS=" id, bookId, openid, headImgUrl, userName, comment, recTime ";
	
	@Select("Select " + COMMENT_RECORD_FIELDS + " from " + COMMENT_RECORD_NAME
			+ " where bookId=#{bookId} ")
	public List<BookCommentRecordEntity> queryCommentList(@Param("bookId") Integer bookId);
	
	@Insert("Insert into BOOK_COMMENTRECORD(bookId, openid, headImgUrl, userName, comment, recTime) "
			+ "values(#{bookId}, #{openid}, #{headImgUrl}, #{userName}, #{comment}, SYSDATE())")
	public Integer insertCommentRecord(BookCommentRecordEntity entity);
	
	/**
	* @Title: queryCommentById
	* @Description: 查询用户是否评论过该图书
	* @param bookId
	* @param openid
	* @return BookCommentRecordEntity    返回类型
	* @author ahuang  
	* @date 2018年6月21日 下午10:14:41
	* @version V1.0
	* @throws
	*/
	@Select("Select "  + COMMENT_RECORD_FIELDS + " from " + COMMENT_RECORD_NAME
			+ " where bookId=#{bookId} and openid=#{openid} limit 1")
	public BookCommentRecordEntity queryCommentById(@Param("bookId") Integer bookId, @Param("openid") String openid);
}
