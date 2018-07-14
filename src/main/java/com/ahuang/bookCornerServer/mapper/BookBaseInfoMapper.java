package com.ahuang.bookCornerServer.mapper;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.data.domain.Page;

import com.ahuang.bookCornerServer.entity.BookBaseInfoEntity;
import org.springframework.stereotype.Service;

/**
 * 
* @ClassName: BookBaseInfoMapper
* @Description: BOOK_BASEINFO表的Mapper
* @author ahuang
* @date 2018年6月2日 下午10:00:58
* @version V1.00
 */
@Service
@CacheNamespace(size=100, implementation=org.mybatis.caches.ehcache.EhcacheCache.class) 
public interface BookBaseInfoMapper{
	String TABLE_NAEM = " BOOK_BASEINFO b ";
	String BOOK_BASEINFO_NAME = " BOOK_BASEINFO b ";
	String SELECT_FIELDS = " bookId, bookName, bookWriter, bookBrief, bookType, bookStatus, bookSource, bookBuyer, "
			+ "bookTime, bookRemark, bookLikeNum, bookCommentNum, recTime ";
	String BORROW_RECORD_NAME = " BOOK_BORROWRECORD r ";
	String BORROW_RECORD_FIELDS = "borrowStatus";
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
	
//	@Select("Select b.bookId bookId, b.bookName bookName, b.bookWriter bookWriter, b.bookBrief bookBrief, " 
//			+  "b.bookType bookType, b.bookStatus bookStatus, b.bookSource bookSource, b.bookBuyer bookBuyer, " 
//			+  "b.bookTime bookTime, b.bookRemark bookRemark, b.bookLikeNum bookLikeNum, b.bookCommentNum bookCommentNum, " 
//			+  "b.recTime recTime , r.borrowStatus borrowStatus"
//			+ " from " + BOOK_BASEINFO_NAME + " LEFT JOIN " + BORROW_RECORD_NAME 
//			+ " on b.bookId=r.bookId"
//			+ " where "
//			+ "b.bookId=#{id} "
//			+ "and r.openid=#{openid} ")
//	@Results({
//        @Result(property = "isBorrowed",  column = "borrowStatus"),
//    })
//	public BookBaseInfoEntity queryBookDetailById(Map<String, Object> param);
	
	@Select("Select " + BORROW_RECORD_FIELDS + " from " + BORROW_RECORD_NAME 
			+ " where bookId=#{id} "
			+ "and openid=#{openid} order by borrowTime desc limit 1")
	public String queryBookBorrowStatus(Map<String, Object> param);
	
	@Update("Update "+TABLE_NAEM+" set bookStatus=#{bookStatus} where bookId=#{bookId}")
	public Integer updateBookBorrowStatus(@Param("bookId") Integer bookId,  @Param("bookStatus") String bookStatus);
	
	@Select("Select " + SELECT_FIELDS + " from BOOK_BASEINFO")
	public List<BookBaseInfoEntity> queryBookList();
	
	@Select("select " + SELECT_FIELDS + " from  " + TABLE_NAEM )
    public Page<BookBaseInfoEntity> queryBookListPages(Pageable pageable);
	
	@Update("Update " + TABLE_NAEM + "  set bookLikeNum=bookLikeNum+1 "
			+ " where bookId=#{bookId}")
	public Integer updateBookLikeNumByOne(Integer bookId);
	
	@Update("Update " + TABLE_NAEM + "  set bookCommentNum=bookCommentNum+1 "
			+ " where bookId=#{bookId}")
	public Integer updateBookCommentNumByOne(Integer bookId);
	
	@Select( "<script>"
	+  "select " + SELECT_FIELDS + " from  " + TABLE_NAEM 
	+ BOOK_LIST_WHERE
    + "order by bookId desc "
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
