package com.ahuang.bookCornerServer.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.ahuang.bookCornerServer.entity.BookCommentRecordEntity;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Service;

@Service
public interface BookCommentRecordMapper {
	String COMMENT_RECORD_NAME = " BOOK_COMMENTRECORD c";
	String COMMENT_RECORD_FIELDS=" id, bookId, openid, headImgUrl, userName, comment, recTime,commentLikeNum,isThumbup ";

	/**
	* 查询特定图书的所有评论
	* @params  [bookId]
	* @return: java.util.List<com.ahuang.bookCornerServer.entity.BookCommentRecordEntity>
	* @Author: ahuang
	* @Date: 2018/7/19 下午9:17
	*/
	@Select("Select " + COMMENT_RECORD_FIELDS + " from " + COMMENT_RECORD_NAME
			+ " where bookId=#{bookId} ")
	List<BookCommentRecordEntity> queryCommentList(@Param("bookId") Integer bookId);

	/**
	* 插入用户的图书评论
	* @params  [entity]
	* @return: java.lang.Integer
	* @Author: ahuang
	* @Date: 2018/7/19 下午9:17
	*/
	@Insert("Insert into BOOK_COMMENTRECORD(bookId, openid, headImgUrl, userName, comment, recTime) "
			+ "values(#{bookId}, #{openid}, #{headImgUrl}, #{userName}, #{comment}, SYSDATE())")
	Integer insertCommentRecord(BookCommentRecordEntity entity);
	
	/**
	 * 查询用户是否评论过该图书
	* @Title: queryCommentById
	* @Description: 查询用户是否评论过该图书
	* @param bookId
	* @param openid
	* @return BookCommentRecordEntity    返回类型
	* @author ahuang  
	* @date 2018年6月21日 下午10:14:41
	* @version V1.0
	* @throws
	*/
	@Select("Select "  + COMMENT_RECORD_FIELDS + " from " + COMMENT_RECORD_NAME
			+ " where bookId=#{bookId} and openid=#{openid} limit 1")
	BookCommentRecordEntity queryCommentById(@Param("bookId") Integer bookId, @Param("openid") String openid);

	/**
	 * 为特定评论点赞
	 * @params  [commentId]
	 * @return: java.lang.Integer
	 * @Author: puxuewei
	 * @Date: 2018/7/25 下午8:48
	 */

	@Update("Update " + COMMENT_RECORD_NAME + "  set commentLikeNum=commentLikeNum+1 "
			+ " where id=#{commentId}")
	Integer updateCommentLikeNumByOne(Integer commentId);

	/**
	 * 判断当前用户是否为该条评论点赞
	 * @params  [commentId]
	 * @return: java.lang.Integer
	 * @Author: puxuewei
	 * @Date: 2018/7/26 下午5:22
	 */

	@Update("Update " + COMMENT_RECORD_NAME + "  set isThumbup = '1' "
			+ " where id=#{commentId}")
	Integer updateisThumbup(Integer commentId);

}
