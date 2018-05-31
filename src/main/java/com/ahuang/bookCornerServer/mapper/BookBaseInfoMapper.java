package com.ahuang.bookCornerServer.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import com.ahuang.bookCornerServer.entity.BookBaseInfoEntity;

public interface BookBaseInfoMapper {
	
	@Select("Select * from BOOK_BASEINFO where bookId=#{id}")
	public BookBaseInfoEntity queryById(Integer id);
	@Select("Select * from BOOK_BASEINFO")
	public List<BookBaseInfoEntity> queryByParams();
}
