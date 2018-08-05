package com.ahuang.bookCornerServer.mapper;

import com.ahuang.bookCornerServer.entity.BookBaseInfoEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 
* @ClassName: BookBaseInfoMapper
* @Description: BOOK_BASEINFO表的Mapper
* @author ahuang
* @date 2018年6月2日 下午10:00:58
* @version V1.00
 */
@Service
@CacheNamespace(size=100, implementation=org.mybatis.caches.ehcache.EhcacheCache.class) // 使用应用缓存
public interface BookBaseInfoMapper{
	String TABLE_NAEM = " BOOK_BASEINFO b ";
	String SELECT_FIELDS = " bookId, bookName, bookWriter, bookBrief, bookType, bookStatus, bookSource, bookBuyer, "
			+ "bookTime, bookRemark, bookLikeNum, bookCommentNum, recTime, bookScore, isbn13 ";
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

	/**
	* 查询图书基本信息
	* @params  [id]
	* @return: com.ahuang.bookCornerServer.entity.BookBaseInfoEntity
	* @Author: ahuang
	* @Date: 2018/7/19 下午8:59
	*/
	@Select("Select " + SELECT_FIELDS + " from " + TABLE_NAEM + " where bookId=#{id}")
	BookBaseInfoEntity queryById(Integer id);
	
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

//	/**
//	*
//	* @params  [param]
//	* @return: java.lang.String
//	* @Author: ahuang
//	* @Date: 2018/7/19 下午8:59
//	*/
//	@Select("Select " + BORROW_RECORD_FIELDS + " from " + BORROW_RECORD_NAME
//			+ " where bookId=#{id} "
//			+ "and openid=#{openid} order by borrowTime desc limit 1")
//	String queryBookBorrowStatus(Map<String, Object> param);

    /**
    * 更新图书借阅状态
    * @params  [bookId, bookStatus]
    * @return: java.lang.Integer
    * @Author: ahuang
    * @Date: 2018/7/19 下午9:02
    */
	@Update("Update "+TABLE_NAEM+" set bookStatus=#{bookStatus} where bookId=#{bookId}")
	Integer updateBookBorrowStatus(@Param("bookId") Integer bookId,  @Param("bookStatus") String bookStatus);

	/**
	* 根据豆瓣信息更新表
	* @params  [bookId, bookScore, isbn13, doubanAuthor]
	* @return: java.lang.Integer
	* @Author: ahuang
	* @Date: 2018/8/4 下午7:02
	*/
	@Update("<script>" +
			"Update" + TABLE_NAEM +
			"<set>"+
			"<if test='bookScore!=null'>" +
			"bookScore=#{bookScore}," +
			"</if>"+
			"<if test='isbn13!=null'>" +
			"isbn13=#{isbn13}," +
			"</if>"+
			"<if test='doubanAuthor!=null'>" +
			"doubanAuthor=#{doubanAuthor}," +
			"</if>"+
			"<if test='bookBrief!=null'>" +
			"bookBrief=#{bookBrief}," +
			"</if>"+
			"</set>"+
			" where bookId=#{bookId}" +
			"</script>")
	Integer updateBookInfoFromDouban(@Param("bookId") Integer bookId, @Param("bookScore") String bookScore,
									 @Param("isbn13") String isbn13, @Param("doubanAuthor") String doubanAuthor,
									 @Param("bookBrief") String bookBrief);

	/**
	* 查询图书列表
	* @params  []
	* @return: java.util.List<com.ahuang.bookCornerServer.entity.BookBaseInfoEntity>
	* @Author: ahuang
	* @Date: 2018/7/19 下午9:12
	*/
	@Select("Select " + SELECT_FIELDS + " from BOOK_BASEINFO")
	List<BookBaseInfoEntity> queryBookList();

	/**
	* 为特定图书点赞
	* @params  [bookId]
	* @return: java.lang.Integer
	* @Author: ahuang
	* @Date: 2018/7/19 下午9:14
	*/
	@Update("Update " + TABLE_NAEM + "  set bookLikeNum=bookLikeNum+1 "
			+ " where bookId=#{bookId}")
	Integer updateBookLikeNumByOne(Integer bookId);

	/**
	* 为特定图书增加评论数
	* @params  [bookId]
	* @return: java.lang.Integer
	* @Author: ahuang
	* @Date: 2018/7/19 下午9:14
	*/
	@Update("Update " + TABLE_NAEM + "  set bookCommentNum=bookCommentNum+1 "
			+ " where bookId=#{bookId}")
	Integer updateBookCommentNumByOne(Integer bookId);

	/**
	* 查询图书列表（分页）
	* @params  [param]
	* @return: java.util.List<com.ahuang.bookCornerServer.entity.BookBaseInfoEntity>
	* @Author: ahuang
	* @Date: 2018/7/19 下午9:14
	*/
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
//	@Options(useCache=true)
    List<BookBaseInfoEntity> queryBookListPage(Map<String, Object> param);

	/**
	* 查询图书总数
	* @params  [param]
	* @return: java.lang.Integer
	* @Author: ahuang
	* @Date: 2018/7/19 下午9:15
	*/
	@Select("<script>"
			+ "select count(1) from " + TABLE_NAEM
			+ BOOK_LIST_WHERE
		+ "</script>")
//	@Options(useCache=true)
	Integer queryBookInfoNum(Map<String, Object> param);
	
}
