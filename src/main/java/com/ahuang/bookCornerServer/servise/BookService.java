package com.ahuang.bookCornerServer.servise;

import java.util.List;
import java.util.Map;

import com.ahuang.bookCornerServer.bo.PageList;
import com.ahuang.bookCornerServer.entity.*;
import com.ahuang.bookCornerServer.exception.BaseException;

/**
 * 
* @ClassName: BookService
* @Description: 图书服务接口
* @author ahuang
* @date 2018年6月2日 下午9:58:50
* @version V1.0
 */
public interface BookService {
	/**
	 * 查询图书，返回分页的结果
	* @Title: queryBookListPage
	* @Description: 查询图书，返回分页的结果
	* @param @param param
	* @param @return    图书列表分页结果
	* @return PageList<BookBaseInfoEntity>    返回类型
	* @author ahuang  
	* @date 2018年6月2日 下午9:59:05
	* @version V1.0
	* @throws
	 */
	PageList<BookBaseInfoEntity> queryBookListPage(Map<String, Object> param);

	/**
	 * 根据bookid查看图书详情
	* @Title: queryBookById
	* @Description: 根据bookid查看图书详情
	* @param param
	* @return BookBaseInfoEntity    返回类型
	* @author ahuang  
	* @date 2018年6月11日 下午9:46:39
	* @version V1.0
	* @throws
	*/
	BookBaseInfoEntity queryBookById(Map<String, Object> param);

	/**
     * 根据bookid查看图书详情，包括本人是否正在借阅中
	* @Title: queryBookDetailById
	* @Description: 根据bookid查看图书详情，包括本人是否正在借阅中
	* @param param
	* @return BookBaseInfoEntity    返回类型
	* @author ahuang  
	* @date 2018年6月12日 下午9:09:04
	* @version V1.0
	* @throws
	*/
	BookBaseInfoEntity queryBookDetailById(Map<String, Object> param);

	/**
     * 查询评论列表
	* @Title: queryCommentList
	* @Description: 查询评论列表
	* @param bookId
	* @return List<BookCommentRecordEntity>    返回类型
	* @author ahuang  
	* @date 2018年6月14日 下午10:32:21
	* @version V1.0
	* @throws
	*/
	//List<BookCommentRecordEntity> queryCommentList(Integer bookId);
	List<BookCommentRecordEntity> queryCommentList(Map<String, Object> param);
	
	/**
     * 根据bookId借阅图书
	 * @throws BaseException 
	* @Title: borrowBookById
	* @Description: 根据bookId借阅图书
	* @param bookId void    返回类型
	* @author ahuang  
	* @date 2018年6月15日 下午10:56:21
	* @version V1.0
	* @throws
	*/
	void borrowBookById(Integer bookId, String openid) throws BaseException;

	/**
     * 根据bookid归还图书
	 * @throws BaseException 
	* @Title: returnBookById
	* @Description: 根据bookid归还图书
	* @param bookId
	* @param openid void    返回类型
	* @author ahuang  
	* @date 2018年6月16日 上午9:24:35
	* @version V1.0
	* @throws
	*/
	void returnBookById(Integer bookId, String openid) throws BaseException;

	/**
     * 查询特定用户的借阅历史
	 * @throws Exception 
	* @Title: queryBookBorrowByOpenid
	* @Description: 查询特定用户的借阅历史
	* @param openid
	* @return List<BookBorrowRecordEntity>    返回类型
	* @author ahuang  
	* @date 2018年6月19日 下午9:03:01
	* @version V1.0
	* @throws
	*/
	List<BookBorrowRecordEntity> queryBookBorrowByOpenid(String openid) throws Exception;

	/**
	 * 查询特定用户的借阅图书情况，且借书状态bookStatus 0，且借出时间大于30天
	 * @throws Exception
	 * @Title: queryBookBorrowByOpenidAndBookStatus
	 * @Description: 查询特定用户的借阅图书情况，且借书状态bookStatus 0，且借出时间大于30天
	 * @param openid
	 * @return List<BookBorrowRecordEntity> ？   返回类型
	 * @author puxuewei
	 * @date 2018年7月27日 下午3:07
	 * @version V1.0
	 * @throws
	 */
	List<Map<String, Object>> queryBookBorrowByOpenidAndBookStatus(String openid);

	/**
	 * 查询全部用户的借阅图书情况，且借书状态bookStatus 0，且借出时间大于30天
	 * @throws Exception
	 * @Title: queryBookBorrowByBookStatus
	 * @Description: 查询全部用户的借阅图书情况，且借书状态bookStatus 0，且借出时间大于30天
	 * @param
	 * @return List<BookBorrowRecordEntity> ？   返回类型
	 * @author puxuewei
	 * @date 2018年7月30日 下午7:07
	 * @version V1.0
	 * @throws
	 */
	List<Map<String, Object>> queryBookBorrowByBookStatus();


	/**
    * 查询特定图书的被借阅记录
	* @Title: queryBookBorrowHistoryByBookId
	* @Description: 查询特定图书的被借阅记录
	* @param bookId
	* @return List<BookBorrowRecordEntity>    返回类型
	* @author ahuang  
	* @date 2018年6月19日 下午10:23:57
	* @version V1.0
	* @throws
	*/
	List<Map<String, Object>> queryBookBorrowHistoryByBookId(Integer bookId);



	/**
     * 用户点赞图书
	* @Title: addBookLikedRecord
	* @param bookId
	* @param openid void    返回类型
	* @author ahuang  
	* @date 2018年6月21日 下午9:27:14
	* @version V1.0
	* @throws
	*/
	void addBookLikedRecord(Integer bookId, String openid);

	/**
	 * 用户点赞评论
	 * @Title: addCommentLikedRecord
	 * @param commentId
	 * @param openid void    返回类型
	 * @author puxuewei
	 * @date 2018年7月25日 上午11:04:13
	 * @version V1.0
	 * @throws
	 */
	void addCommentLikedRecord(Integer bookId,CustBindUsersEntity bindUser, Integer commentId) throws BaseException;

	/**
	 * 添加图书评论
     *
	 * @throws BaseException 
	* @Title: addCommentRecord
	* @param bookId
	* @param comment void    返回类型
	* @author ahuang  
	* @date 2018年6月21日 下午10:00:13
	* @version V1.0
	* @throws
	*/
	void addCommentRecord(Integer bookId, CustBindUsersEntity bindUser, String comment) throws BaseException;
	/**
	 * 公告栏信息表插入
	 *
	 * @Title: insertMessage
	 * @param bookId，bookName，operationContent，operationType，userName
	 * @author lct
	 * @date 2018年8月9日 上午10:00:13
	 * @version V1.0
	 * @throws
	 */
	MessageBaseInfoEntity insertMessage(Integer bookId, String bookName, String operationContent, String operationType, String userName);

	/**
	 * 查询管理员信息
	 *
	 * @throws BaseException
	 * @Title: addCommentRecord
	 * @param
	 * @return: com.ahuang.bookCornerServer.entity.CustBindUsersEntity
	 * @author puxuewei
	 * @date 2018年8月11日 下午7:40
	 * @version V1.0
	 * @throws
	 */
	List<Map<String, Object>>  queryIsAdmin();
}
