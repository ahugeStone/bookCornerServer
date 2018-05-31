package com.ahuang.bookCornerServer.mapper;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ahuang.bookCornerServer.entity.BookBaseInfoEntity;

public interface BookBaseInfoMapper extends PagingAndSortingRepository<BookBaseInfoEntity,String>{
	
	@Select("Select * from BOOK_BASEINFO where bookId=#{id}")
	public BookBaseInfoEntity queryById(Integer id);
	
	@Select("Select * from BOOK_BASEINFO")
	public List<BookBaseInfoEntity> queryBookList();
	
	@Select("select * from BOOK_BASEINFO")
    public Page<BookBaseInfoEntity> queryBookListPages(Pageable pageable);
	
	@Select("select * from BOOK_BASEINFO limit #{num, jdbcType=INTEGER}, #{pageSize, jdbcType=INTEGER}")
    public List<BookBaseInfoEntity> queryBookListPage(Map<String, Object> param);
}
