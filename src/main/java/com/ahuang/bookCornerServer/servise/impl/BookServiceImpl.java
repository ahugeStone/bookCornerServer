package com.ahuang.bookCornerServer.servise.impl;

import java.util.AbstractList;
import java.util.Date;
import java.util.List;
import java.util.Map;

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
import ch.qos.logback.core.net.SyslogOutputStream;
import com.ahuang.bookCornerServer.entity.*;
import com.ahuang.bookCornerServer.mapper.*;
=======
import com.ahuang.bookCornerServer.entity.*;
import com.ahuang.bookCornerServer.mapper.*;
import com.ahuang.bookCornerServer.servise.MessageService;
>>>>>>> e831cb2d6c25bb43888d3b08000647813c6d3efa
=======
import com.ahuang.bookCornerServer.entity.*;
import com.ahuang.bookCornerServer.mapper.*;
import com.ahuang.bookCornerServer.servise.MessageService;
>>>>>>> e831cb2d6c25bb43888d3b08000647813c6d3efa
=======
import com.ahuang.bookCornerServer.entity.*;
import com.ahuang.bookCornerServer.mapper.*;
import com.ahuang.bookCornerServer.servise.MessageService;
>>>>>>> e831cb2d6c25bb43888d3b08000647813c6d3efa
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ahuang.bookCornerServer.bo.BookList;
import com.ahuang.bookCornerServer.servise.BookService;
import com.ahuang.bookCornerServer.util.StringUtil;

import lombok.extern.slf4j.Slf4j;

import com.ahuang.bookCornerServer.exception.*;

/**
 * The type Book service.
 *
 * @author ahuang
 * @version V1.0
 * @ClassName: BookServiceImpl
 * @Description: 图书服务类
 * @date 2018年6月2日 下午9:58:15
 */
@Slf4j
@Service
public class BookServiceImpl implements BookService {
	private final BookBaseInfoMapper bookBaseInfoMapper;
	
	private final BookBorrowRecordMapper bookBorrowRecordMapper;
	
	private final BookCommentRecordMapper bookCommentRecordMapper;
	
	private final CustBindUsersMapper custBindUsersMapper;
	
	private final BookLikeRecordMapper bookLikeRecordMapper;
    private final MessageBaseInfoMapper messageBaseInfoMapper;
<<<<<<< HEAD
<<<<<<< HEAD

<<<<<<< HEAD
	private final CommentLikeRecordMapper commentLikeRecordMapper;

    @Autowired
    public BookServiceImpl(BookBaseInfoMapper bookBaseInfoMapper, BookBorrowRecordMapper bookBorrowRecordMapper, BookCommentRecordMapper bookCommentRecordMapper, CustBindUsersMapper custBindUsersMapper, BookLikeRecordMapper bookLikeRecordMapper,CommentLikeRecordMapper commentLikeRecordMapper) {
=======
	@Autowired
    public BookServiceImpl(BookBaseInfoMapper bookBaseInfoMapper, BookBorrowRecordMapper bookBorrowRecordMapper, BookCommentRecordMapper bookCommentRecordMapper, CustBindUsersMapper custBindUsersMapper, BookLikeRecordMapper bookLikeRecordMapper, MessageBaseInfoMapper messageBaseInfoMapper) {
>>>>>>> e831cb2d6c25bb43888d3b08000647813c6d3efa
=======

	@Autowired
    public BookServiceImpl(BookBaseInfoMapper bookBaseInfoMapper, BookBorrowRecordMapper bookBorrowRecordMapper, BookCommentRecordMapper bookCommentRecordMapper, CustBindUsersMapper custBindUsersMapper, BookLikeRecordMapper bookLikeRecordMapper, MessageBaseInfoMapper messageBaseInfoMapper) {
>>>>>>> e831cb2d6c25bb43888d3b08000647813c6d3efa
=======

	@Autowired
    public BookServiceImpl(BookBaseInfoMapper bookBaseInfoMapper, BookBorrowRecordMapper bookBorrowRecordMapper, BookCommentRecordMapper bookCommentRecordMapper, CustBindUsersMapper custBindUsersMapper, BookLikeRecordMapper bookLikeRecordMapper, MessageBaseInfoMapper messageBaseInfoMapper) {
>>>>>>> e831cb2d6c25bb43888d3b08000647813c6d3efa
        this.bookBaseInfoMapper = bookBaseInfoMapper;
        this.bookBorrowRecordMapper = bookBorrowRecordMapper;
        this.bookCommentRecordMapper = bookCommentRecordMapper;
        this.custBindUsersMapper = custBindUsersMapper;
        this.bookLikeRecordMapper = bookLikeRecordMapper;
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
        this.commentLikeRecordMapper = commentLikeRecordMapper;
=======
		this.messageBaseInfoMapper = messageBaseInfoMapper;
>>>>>>> e831cb2d6c25bb43888d3b08000647813c6d3efa
=======
		this.messageBaseInfoMapper = messageBaseInfoMapper;
>>>>>>> e831cb2d6c25bb43888d3b08000647813c6d3efa
=======
		this.messageBaseInfoMapper = messageBaseInfoMapper;
>>>>>>> e831cb2d6c25bb43888d3b08000647813c6d3efa
    }


    @Override
	public BookList<BookBaseInfoEntity> queryBookListPage(Map<String, Object> param) {
		Integer num = (Integer)param.get("num"); // 当前页起始id
		Integer pageSize = (Integer)param.get("pageSize"); // 页面大小
		param.put("isCount", false);
		List<BookBaseInfoEntity> booklist = bookBaseInfoMapper.queryBookListPage(param); // 图书列表
		param.put("isCount", true);
		Integer totalNum = bookBaseInfoMapper.queryBookInfoNum(param); // 图书总数

        return new BookList<>(num, totalNum, pageSize, booklist);
	}

	@Override
	public BookBaseInfoEntity queryBookById(Map<String, Object> param) {
		Integer bookId = (Integer) param.get("bookId");
		return bookBaseInfoMapper.queryById(bookId);
	}
	
	@Override
	public BookBaseInfoEntity queryBookDetailById(Map<String, Object> param) {
		Integer id = (Integer)param.get("id");
		String openid = (String)param.get("openid");
		BookBaseInfoEntity bo = bookBaseInfoMapper.queryById(id);
        BookBorrowRecordEntity isBorrowed = bookBorrowRecordMapper.queryBookBorrowStatus(id, openid);
		if(!StringUtil.isNullOrEmpty(bo)) {
			// 判断当前图书是否正被当前用户借阅中
			if(null == isBorrowed || null == isBorrowed.getBorrowStatus()) {
				bo.setIsBorrowed("0");
			} else {
				if("0".equals(isBorrowed.getBorrowStatus())) {
					bo.setIsBorrowed("1");
				} else {
					bo.setIsBorrowed("0");
				}
			}
		}
		BookLikeRecordEntity isLiked = bookLikeRecordMapper.queryBookLikeRecordById(id, openid);
		if(!StringUtil.isNullOrEmpty(isLiked)) {
			bo.setIsLiked("1");
		}
		BookCommentRecordEntity isComment = bookCommentRecordMapper.queryCommentById(id, openid);
		if(!StringUtil.isNullOrEmpty(isComment)) {
			bo.setIsCommented("1");
		}
		return bo;
	}
	
	@Override
	public List<BookCommentRecordEntity> queryCommentList(Integer bookId) {
		return bookCommentRecordMapper.queryCommentList(bookId);
	}

	//查询特定用户的借阅图书历史，包括当前图书的被借阅状态
	@Override
	public List<Map<String, Object>> queryBookBorrowByOpenid(String openid) {
		return bookBorrowRecordMapper.queryBookBorrowByOpenid(openid);
	}

	//查询特定用户的借阅图书情况，且借书状态bookStatus 0，且借出时间大于30天
	public List<Map<String, Object>> queryBookBorrowByOpenidAndBookStatus(String openid) {
		return bookBorrowRecordMapper.queryBookBorrowByOpenidAndBookStatus(openid);
	}

	//查询全部用户的借阅图书情况，且借书状态bookStatus 0，且借出时间大于30天
	public List<Map<String, Object>> queryBookBorrowByBookStatus() {
		return bookBorrowRecordMapper.queryBookBorrowByBookStatus();
	}

	@Override
	public List<Map<String, Object>> queryBookBorrowHistoryByBookId(Integer bookId) {
		return bookBorrowRecordMapper.queryBookBorrowHistoryByBookId(bookId);
	}
	
	@Override
	public void addCommentRecord(Integer bookId, CustBindUsersEntity bindUser, String comment) throws BaseException {
		BookCommentRecordEntity entity = new BookCommentRecordEntity();
        BookBaseInfoEntity bookInfo = bookBaseInfoMapper.queryById(bookId);

		entity.setBookId(bookId);
		entity.setComment(comment);
		entity.setOpenid(bindUser.getOpenid());
		entity.setHeadImgUrl(bindUser.getHeadImgUrl());
		entity.setUserName(bindUser.getUserName());
		Integer r1 = bookCommentRecordMapper.insertCommentRecord(entity);
		log.debug("bookCommentRecordMapper插入数据条数："+r1);
		if(1 != r1) {
			throw new BaseException("comment.failed", "评论图书失败");
		}
		Integer r2 = bookBaseInfoMapper.updateBookCommentNumByOne(bookId);
		log.debug("bookBaseInfoMapper插入数据条数："+r2);
		if(1 != r2) {
			throw new BaseException("comment.failed", "评论图书失败");
		}
        MessageBaseInfoEntity m = new MessageBaseInfoEntity();
        m.setBookId(bookId);
        m.setBookName(bookInfo.getBookName());
        m.setOperationTime(new Date());
        m.setOperationContent(comment);
        m.setOperationType("2");
        m.setUserName(bindUser.getUserName());
        messageBaseInfoMapper.insertMessage(m);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
	public void addBookLikedRecord(Integer bookId, String openid) {
		BookLikeRecordEntity entity = new BookLikeRecordEntity();
		entity.setBookId(bookId);
		entity.setOpenid(openid);
		BookLikeRecordEntity bo = bookLikeRecordMapper.queryBookLikeRecordById(bookId, openid);
		if(!StringUtil.isNullOrEmpty(bo)) {
			log.info("该用户已经点过赞了，openid:" + openid);
			return;
		}
		Integer bl = bookLikeRecordMapper.insertBookLikeRecord(entity);
		Integer bb = bookBaseInfoMapper.updateBookLikeNumByOne(bookId);
		log.debug("bookLikeRecord插入数据条数:" + bl);
		log.debug("bookBaseInfo插入数据条数:" + bb);
	}

	//评论点赞功能
	@Override
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
	public void addCommentLikedRecord(Integer bookId,CustBindUsersEntity bindUser,Integer commentId) {
		CommentLikeRecordEntity entity = new CommentLikeRecordEntity();
		entity.setBookId(bookId);
		entity.setCommentId(commentId);
		String openid = bindUser.getOpenid();
		entity.setOpenid(openid);
		entity.setHeadImgUrl(bindUser.getHeadImgUrl());
		entity.setUserName(bindUser.getUserName());
		entity.setRecTime(new Date());
		CommentLikeRecordEntity co = commentLikeRecordMapper.queryCommentLikeRecordById(commentId, openid);
		bookCommentRecordMapper.updateisThumbup(commentId);
		if(!StringUtil.isNullOrEmpty(co)) {
			log.info("该用户已经点过赞了，openid:" + openid);
			return;
		}

		Integer cl = commentLikeRecordMapper.insertCommentLikeRecord(entity);
		//BookCommentRecordMapper 评论点赞记录表中点赞数添加一个字段，评论点赞数自增1
		Integer cc = bookCommentRecordMapper.updateCommentLikeNumByOne(commentId);
		log.debug("commentLikeRecord插入数据条数:" + cl);
		log.debug("commentBaseInfo插入数据条数:" + cc);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
	public void borrowBookById(Integer bookId, String openid) throws BaseException {
		BookBorrowRecordEntity isBorrowed = bookBorrowRecordMapper.queryBookBorrowStatus(bookId, openid);
		BookBaseInfoEntity bookInfo = bookBaseInfoMapper.queryById(bookId);
		CustBindUsersEntity user = custBindUsersMapper.queryByOpenid(openid);
		// 0 借出 1归还
		if(!StringUtil.isNullOrEmpty(bookInfo) && "1".equals(bookInfo.getBookStatus()) 
//				&& (StringUtil.isNullOrEmpty(isBorrowed) || "1".equals(isBorrowed.getBorrowStatus()))
				) {
			log.debug("图书可借阅bookId：" + bookId);
			BookBorrowRecordEntity borrowRecord = new BookBorrowRecordEntity();
			borrowRecord.setBookId(bookId);
			borrowRecord.setBookName(bookInfo.getBookName());
			borrowRecord.setBorrowStatus("0");//0借出 1归还
			borrowRecord.setBorrowTime(new Date());
			borrowRecord.setHeadImgUrl(user.getHeadImgUrl());
			borrowRecord.setOpenid(openid);
			borrowRecord.setReturnTime(null);
			borrowRecord.setUserName(user.getUserName());
			// 插入借阅记录
			bookBorrowRecordMapper.insertBorrowRecord(borrowRecord);
			// 修改图书借阅信息-0借阅
			if(1 != bookBaseInfoMapper.updateBookBorrowStatus(bookId, "0")) {
				log.debug("updateBookBorrowStatus更新条数不是一条bookId：" + bookId);
				throw new BaseException("can.not.borrow", "本书当前不可借阅bookId：" + bookId);
			}

			//插入借阅记录到图书操作记录表中 借阅操作为0 归还操作为1 评论操作为2
			//BookOperationRecordEntity operationRecord = new BookOperationRecordEntity();
			//operationRecord.setBookId(bookId);
			//operationRecord.setBookName(bookInfo.getBookName());
			//operationRecord.setUserName(user.getUserName());
			//operationRecord.setOperationType("0");
			//operationRecord.setOperationTime(new Date());
			//operationRecord.setOperationContent("借阅");


		} else {
			// 不可借阅
			log.debug("bookInfo.getBookStatus：" + bookInfo.getBookStatus() + ", isBorrowed:" + isBorrowed);
			throw new BaseException("can.not.borrow", "本书当前不可借阅bookId：" + bookId);
		}

		MessageBaseInfoEntity m = new MessageBaseInfoEntity();
		m.setBookId(bookId);
		m.setBookName(bookInfo.getBookName());
		m.setOperationTime(new Date());
		m.setOperationContent("");
		m.setOperationType("0");
		m.setUserName(user.getUserName());
		messageBaseInfoMapper.insertMessage(m);




	}
	@Override
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
	public void returnBookById(Integer bookId, String openid) throws BaseException {
		BookBorrowRecordEntity isBorrowed = bookBorrowRecordMapper.queryBookBorrowStatus(bookId, openid);
		BookBaseInfoEntity bookInfo = bookBaseInfoMapper.queryById(bookId);
        CustBindUsersEntity user = custBindUsersMapper.queryByOpenid(openid);
//		CustBindUsersEntity user = custBindUsersMapper.queryByOpenid(openid);
		if((!StringUtil.isNullOrEmpty(bookInfo) && "0".equals(bookInfo.getBookStatus()) )
				&& (!StringUtil.isNullOrEmpty(isBorrowed) && "0".equals(isBorrowed.getBorrowStatus()))
				) {
			log.debug("图书可以归还bookid：" + bookId);
			// 修改图书借阅信息-1归还
			Integer result = bookBaseInfoMapper.updateBookBorrowStatus(bookId, "1");
			if(1 != result) {
				log.debug("updateBookBorrowStatus更新条数不是1条：" + bookId);
				throw new BaseException("can.not.return", "本书当前无法归还bookId：" + bookId);
			}
			// 更新借阅详情, 通过更新条数判断是否更新成功
			BookBorrowRecordEntity borrowRecord = new BookBorrowRecordEntity();
			borrowRecord.setBookId(bookId);
			borrowRecord.setBorrowStatus("1");//0借出 1归还
			borrowRecord.setReturnTime(new Date());
			borrowRecord.setOpenid(openid);
			if(1!=bookBorrowRecordMapper.updateBorrowRecord(borrowRecord)) {
				log.debug("updateBorrowRecord更新条数不是1条：" + bookId);
				throw new BaseException("can.not.return", "本书当前无法归还bookId：" + bookId);
			}
		} else {
			log.debug("图书无法归还bookid：" + bookId);
			throw new BaseException("can.not.return", "本书当前无法归还bookId：" + bookId);
		}
        MessageBaseInfoEntity m = new MessageBaseInfoEntity();
        m.setBookId(bookId);
        m.setBookName(bookInfo.getBookName());
        m.setOperationTime(new Date());
        m.setOperationContent("");
        m.setOperationType("1");
        m.setUserName(user.getUserName());
        messageBaseInfoMapper.insertMessage(m);
	}
}
