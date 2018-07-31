package com.ahuang.bookCornerServer.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.ahuang.bookCornerServer.entity.CommentLikeRecordEntity;
import org.springframework.stereotype.Service;

@Service
public interface CommentLikeRecordMapper {
	/**
	*  插入用户评论点赞记录
	* @params  [entity]
	* @return: java.lang.Integer
	* @Author: puxuewei
	* @Date: 2018/7/25 上午11:12
	*/
	@Insert("Insert into COMMENT_LIKERECORD (commentId, openid, recTime, bookId, headImgUrl, userName) "
			+ "values(#{commentId}, #{openid}, SYSDATE(), #{bookId}, #{headImgUrl}, #{userName})")
	Integer insertCommentLikeRecord(CommentLikeRecordEntity entity);

	/**
	* 查询特定用户是否点赞过该评论
	* @params  [commentId, openid]
	* @return: com.ahuang.bookCornerServer.entity.CommentLikeRecordEntity
	* @Author: puxuewei
	* @Date: 2018/7/19 上午11:12
	*/
	@Select("Select id,bookId, openid, commentId,recTime from COMMENT_LIKERECORD "
			+ " where commentId=#{commentId} and openid=#{openid}")
	CommentLikeRecordEntity queryCommentLikeRecordById(@Param("commentId") Integer commentId, @Param("openid") String openid);
}
