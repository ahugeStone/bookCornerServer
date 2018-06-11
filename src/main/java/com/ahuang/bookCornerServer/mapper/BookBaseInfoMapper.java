package com.ahuang.bookCornerServer.mapper;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ahuang.bookCornerServer.entity.BookBaseInfoEntity;

/**
 * 
* @ClassName: BookBaseInfoMapper
* @Description: BOOK_BASEINFO表的Mapper
* @author ahuang
* @date 2018年6月2日 下午10:00:58
* @version V1.0
 */
@CacheNamespace(size=100, implementation=org.mybatis.caches.ehcache.EhcacheCache.class) 
public interface BookBaseInfoMapper extends PagingAndSortingRepository<BookBaseInfoEntity,String>{
	String TABLE_NAEM = " BOOK_BASEINFO ";
	String SELECT_FIELDS = " bookId, bookName, bookWriter, bookBrief, bookType, bookStatus, bookSource, bookBuyer, bookTime, bookRemark, bookLikeNum, bookCommentNum, recTime ";
	String BOOK_LIST_WHERE = "<where>"
			+ "<if test='bookName!=null and bookName!=\"\"'>"
			+ "<bind name=\"pattern\" value=\"'%' + bookName + '%'\" />" 
			+ "and bookName like #{pattern} "
			+ "</if>"
			+ "<if test='bookType!=null and bookType!=\"\"'>"
			+ "and bookType = #{bookType}"
			+ "</if>"
			+ "<if test='bookStatus!=null and bookStatus!=\"\"'>"
			+ "and bookStatus = #{bookStatus}"
			+ "</if>"
		+"</where>";
	
	@Select("Select " + SELECT_FIELDS + " from " + TABLE_NAEM + " where bookId=#{id}")
	public BookBaseInfoEntity queryById(Integer id);
	
	@Select("Select " + SELECT_FIELDS + " from BOOK_BASEINFO")
	public List<BookBaseInfoEntity> queryBookList();
	
	@Select("select " + SELECT_FIELDS + " from  " + TABLE_NAEM )
    public Page<BookBaseInfoEntity> queryBookListPages(Pageable pageable);
	
	@Select( "<script>"
	+  "select " + SELECT_FIELDS + " from  " + TABLE_NAEM 
	+ BOOK_LIST_WHERE
	+ "<if test='num!=null and pageSize != null '>"
	+ "  limit #{num}, #{pageSize}"
	+ "</if>"  
	+ "<if test='num==null and pageSize != null '>"
	+ "  limit 0, #{pageSize}"
	+ "</if>"  
	+ "</script>")
	@Options(useCache=true)
    public List<BookBaseInfoEntity> queryBookListPage(Map<String, Object> param);
	
	@Select("<script>"
			+ "select count(1) from " + TABLE_NAEM
			+ BOOK_LIST_WHERE
		+ "</script>")
	@Options(useCache=true)
	public Integer queryBookInfoNum(Map<String, Object> param);
	
}
