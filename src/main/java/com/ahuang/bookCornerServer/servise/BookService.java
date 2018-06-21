package com.ahuang.bookCornerServer.servise;

import java.util.List;
import java.util.Map;

import com.ahuang.bookCornerServer.bo.PageList;
import com.ahuang.bookCornerServer.entity.BookBaseInfoEntity;
import com.ahuang.bookCornerServer.entity.BookCommentRecordEntity;
import com.ahuang.bookCornerServer.entity.CustBindUsersEntity;
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
	 * 
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
	public PageList<BookBaseInfoEntity> queryBookListPage(Map<String, Object> param);

	/**
	* @Title: queryBookById
	* @Description: 根据bookid查看图书详情
	* @param param
	* @return BookBaseInfoEntity    返回类型
	* @author ahuang  
	* @date 2018年6月11日 下午9:46:39
	* @version V1.0
	* @throws
	*/
	public BookBaseInfoEntity queryBookById(Map<String, Object> param);

	/**
	* @Title: queryBookDetailById
	* @Description: 根据bookid查看图书详情，包括本人是否正在借阅中
	* @param param
	* @return BookBaseInfoEntity    返回类型
	* @author ahuang  
	* @date 2018年6月12日 下午9:09:04
	* @version V1.0
	* @throws
	*/
	public BookBaseInfoEntity queryBookDetailById(Map<String, Object> param);

	/**
	* @Title: queryCommentList
	* @Description: 查询评论列表
	* @param bookId
	* @param openid
	* @return List<BookCommentRecordEntity>    返回类型
	* @author ahuang  
	* @date 2018年6月14日 下午10:32:21
	* @version V1.0
	* @throws
	*/
	public List<BookCommentRecordEntity> queryCommentList(Integer bookId); 
	
	/**
	 * @throws BaseException 
	* @Title: borrowBookById
	* @Description: 根据bookId借阅图书
	* @param bookId void    返回类型
	* @author ahuang  
	* @date 2018年6月15日 下午10:56:21
	* @version V1.0
	* @throws
	*/
	public void borrowBookById(Integer bookId, String openid) throws BaseException;

	/**
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
	public void returnBookById(Integer bookId, String openid) throws BaseException;

	/**
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
	public List<Map<String, Object>> queryBookBorrowByOpenid(String openid) throws Exception;

	/**
	* @Title: queryBookBorrowHistoryByBookId
	* @Description: 查询特定图书的被借阅记录
	* @param bookId
	* @return List<BookBorrowRecordEntity>    返回类型
	* @author ahuang  
	* @date 2018年6月19日 下午10:23:57
	* @version V1.0
	* @throws
	*/
	public List<Map<String, Object>> queryBookBorrowHistoryByBookId(Integer bookId);

	/**
	* @Title: addBookLikedRecord
	* @Description: 用户点赞图书
	* @param bookId
	* @param openid void    返回类型
	* @author ahuang  
	* @date 2018年6月21日 下午9:27:14
	* @version V1.0
	* @throws
	*/
	public void addBookLikedRecord(Integer bookId, String openid);

	/**
	 * @throws BaseException 
	* @Title: addCommentRecord
	* @Description: 添加图书评论
	* @param bookId
	* @param openid
	* @param comment void    返回类型
	* @author ahuang  
	* @date 2018年6月21日 下午10:00:13
	* @version V1.0
	* @throws
	*/
	public void addCommentRecord(Integer bookId, CustBindUsersEntity bindUser, String comment) throws BaseException;
}
