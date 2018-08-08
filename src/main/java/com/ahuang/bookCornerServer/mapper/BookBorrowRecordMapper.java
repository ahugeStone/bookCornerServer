package com.ahuang.bookCornerServer.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.ahuang.bookCornerServer.entity.BookBorrowRecordEntity;
import org.springframework.stereotype.Service;

@Service
public interface BookBorrowRecordMapper {
	String BORROW_RECORD_NAME = " BOOK_BORROWRECORD r ";
	String BORROW_RECORD_FIELDS = " id, bookId, bookName, borrowStatus, openid, headImgUrl, userName, borrowTime, returnTime ";
	/**
	* 查询特定用户借阅特定图书的最后一条记录
	* @params  [id, openid]
	* @return: com.ahuang.bookCornerServer.entity.BookBorrowRecordEntity
	* @Author: ahuang
	* @Date: 2018/7/19 下午8:52
	*/
	@Select("Select " + BORROW_RECORD_FIELDS + " from " + BORROW_RECORD_NAME 
			+ " where bookId=#{id} "
			+ "and openid=#{openid} order by borrowTime desc limit 1")
	BookBorrowRecordEntity queryBookBorrowStatus(@Param("id") Integer id, @Param("openid") String openid);

	/**
	* 查询特定图书的被借阅历史，以及当前借阅状态
	* @params  [id]
	* @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
	* @Author: ahuang
	* @Date: 2018/7/19 下午8:54
	*/
	@Select("Select  r.id, r.bookId, r.bookName, r.borrowStatus, r.headImgUrl, r.userName, r.borrowTime, r.returnTime,"
			+ "b.bookStatus from BOOK_BORROWRECORD r ,BOOK_BASEINFO b"
			+ " where r.bookId=#{bookId} "
			+ " and r.bookId=b.bookId  "
			+ " order by r.borrowTime desc")
	List<Map<String, Object>> queryBookBorrowHistoryByBookId(@Param("bookId") Integer id);

	/**
	 * 查询特定用户的借阅图书历史，包括当前图书的被借阅状态
	 * @params  [openid]
	 * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
	 * @Author: ahuang
	 * @Date: 2018/7/19 下午8:55
	 */
	@Select("Select  r.id, r.bookId, r.bookName, r.borrowStatus, r.openid, r.headImgUrl, r.userName, r.borrowTime, r.returnTime,"
			+  "b.bookStatus from BOOK_BORROWRECORD r ,BOOK_BASEINFO b"
			+ " where r.bookId=b.bookId "
			+ " and r.openid=#{openid} order by r.borrowTime desc")
	List<Map<String, Object>> queryBookBorrowByOpenid(@Param("openid") String openid);

	/**
	 * 查询特定用户的借阅图书历史，包括当前图书的被借阅状态
	 * @params  [openid]
	 * @return: List<com.ahuang.bookCornerServer.entity.BookBorrowRecordEntity>
	 * @Author: puxuewei
	 * @Date: 2018/8/8 下午7:00
	 */
	@Select("Select  r.id, r.bookId, r.bookName, r.borrowStatus, r.openid, r.headImgUrl, r.userName, r.borrowTime, r.returnTime,"
			+  "b.bookStatus from BOOK_BORROWRECORD r ,BOOK_BASEINFO b"
			+ " where r.bookId=b.bookId "
			+ " and r.openid=#{openid} order by r.borrowTime desc")
	List<BookBorrowRecordEntity> queryBookBorrowByOpenidNew(@Param("openid") String openid);

	/**
	 * 查询特定用户的逾期未还图书情况（借书状态bookStatus 0，且借出时间大于30天）
	 * @params  [openid]
	 * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
	 * @Author: puxuewei
	 * @Date: 2018/7/27 下午2:29
	 */
	@Select("Select  r.id, r.bookId, r.bookName, r.borrowStatus, r.openid, r.headImgUrl, r.userName, r.borrowTime,"
			+  "b.bookStatus, cu.userEmail from BOOK_BORROWRECORD r ,BOOK_BASEINFO b ,CUST_USERS cu, CUST_BINDUSERS cb"
			+ " where r.bookId=b.bookId and r.openid=#{openid} and r.openid= cb.openid and cb.userNo=cu.userNo " +
			"and r.borrowStatus='0' and DATEDIFF(now(),r.borrowTime)>30  order by r.borrowTime desc ")
	List<Map<String, Object>> queryBookBorrowByOpenidAndBookStatus(@Param("openid") String openid);

	/**
	 * 查询所有逾期未还的全部用户（借书状态bookStatus 0，且借出时间大于30天）
	 * @params
	 * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
	 * @Author: puxuewei
	 * @Date: 2018/7/30 下午7:00
	 */
	@Select("Select r.openid from BOOK_BORROWRECORD r ,BOOK_BASEINFO b ,CUST_USERS cu,CUST_BINDUSERS cb"
			+" where r.bookId=b.bookId  and r.openid= cb.openid and cb.userNo=cu.userNo " +
			"and r.borrowStatus='0' and DATEDIFF(now(),r.borrowTime)>30  group BY r.userName ")
	List<Map<String, Object>> queryBookBorrowByBookStatus();

	/**
	* 插入一条图书被借阅的记录
	* @params  [entity]
	* @return: java.lang.Integer
	* @Author: ahuang
	* @Date: 2018/7/19 下午8:56
	*/
	@Insert("Insert into BOOK_BORROWRECORD ( bookId, bookName, borrowStatus, openid, headImgUrl, userName, borrowTime, returnTime  )" 
	+ " values( #{bookId}, #{bookName}, #{borrowStatus}, #{openid}, #{headImgUrl}, #{userName}, #{borrowTime}, #{returnTime})" )
	Integer insertBorrowRecord(BookBorrowRecordEntity entity);

	/**
	* 更新一条图书归还记录
	* @params  [entity]
	* @return: java.lang.Integer
	* @Author: ahuang
	* @Date: 2018/7/19 下午8:56
	*/
	@Update("Update BOOK_BORROWRECORD set "
			+ "borrowStatus=#{borrowStatus}, returnTime=#{returnTime} "
			+ "where bookId=#{bookId} "
			+ "and openid=#{openid} order by borrowTime desc limit 1")
	Integer updateBorrowRecord(BookBorrowRecordEntity entity);
}
