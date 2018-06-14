package com.ahuang.bookCornerServer.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.ahuang.bookCornerServer.entity.BookCommentRecordEntity;

public interface BookCommentRecordMapper {
	String COMMENT_RECORD_NAME = " BOOK_COMMENTRECORD c";
	String COMMENT_RECORD_FIELDS=" id, bookId, openid, headImgUrl, userName, comment, recTime ";
	
	@Select("Select " + COMMENT_RECORD_FIELDS + " from " + COMMENT_RECORD_NAME
			+ " where bookId=#{bookId} ")
	public List<BookCommentRecordEntity> queryCommentList(@Param("bookId") Integer bookId);
}
