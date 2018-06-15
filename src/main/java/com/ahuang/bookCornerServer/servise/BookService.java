package com.ahuang.bookCornerServer.servise;

import java.util.List;
import java.util.Map;

import com.ahuang.bookCornerServer.bo.PageList;
import com.ahuang.bookCornerServer.entity.BookBaseInfoEntity;
import com.ahuang.bookCornerServer.entity.BookCommentRecordEntity;
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
}
