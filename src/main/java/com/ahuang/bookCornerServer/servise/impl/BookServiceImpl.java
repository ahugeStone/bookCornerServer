package com.ahuang.bookCornerServer.servise.impl;

import com.ahuang.bookCornerServer.bo.BookList;
import com.ahuang.bookCornerServer.entity.*;
import com.ahuang.bookCornerServer.exception.BaseException;
import com.ahuang.bookCornerServer.mapper.*;
import com.ahuang.bookCornerServer.servise.BookService;
import com.ahuang.bookCornerServer.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

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

	private final CommentLikeRecordMapper commentLikeRecordMapper;


	@Autowired
    public BookServiceImpl(BookBaseInfoMapper bookBaseInfoMapper, BookBorrowRecordMapper bookBorrowRecordMapper, BookCommentRecordMapper bookCommentRecordMapper, CustBindUsersMapper custBindUsersMapper, BookLikeRecordMapper bookLikeRecordMapper,CommentLikeRecordMapper commentLikeRecordMapper, MessageBaseInfoMapper messageBaseInfoMapper) {
        this.bookBaseInfoMapper = bookBaseInfoMapper;
        this.bookBorrowRecordMapper = bookBorrowRecordMapper;
        this.bookCommentRecordMapper = bookCommentRecordMapper;
        this.custBindUsersMapper = custBindUsersMapper;
        this.bookLikeRecordMapper = bookLikeRecordMapper;
        this.commentLikeRecordMapper = commentLikeRecordMapper;
        this.messageBaseInfoMapper = messageBaseInfoMapper;

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
		//查询当前用户是否点赞，是在图书详情页面调用的
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

	//查询评论记录
	@Override
	public List<BookCommentRecordEntity> queryCommentList(Map<String, Object> param) {
		Integer bookId = (Integer)param.get("id");
		String openid = (String)param.get("openid");
		List<BookCommentRecordEntity> bookCommentRecordEntities=bookCommentRecordMapper.queryCommentList(bookId);
        for (BookCommentRecordEntity bookCommentRecordEntity : bookCommentRecordEntities) {
            int commentId = bookCommentRecordEntity.getId();
            CommentLikeRecordEntity isLiked = commentLikeRecordMapper.queryCommentLikeRecordById(commentId, openid);
            if (!StringUtil.isNullOrEmpty(isLiked)) {
                bookCommentRecordEntity.setIsLiked("1");
            }
        }
		return bookCommentRecordEntities;
	}

	//查询特定用户的借阅图书历史，包括当前图书的被借阅状态
	@Override
	public List<BookBorrowRecordEntity> queryBookBorrowByOpenid(String openid) {
		//return bookBorrowRecordMapper.queryBookBorrowByOpenid(openid);
        return bookBorrowRecordMapper.queryBookBorrowByOpenidNew(openid);

	}

	//查询特定用户的逾期未还图书情况（借书状态bookStatus 0，且借出时间大于30天）
	public List<Map<String, Object>> queryBookBorrowByOpenidAndBookStatus(String openid) {
		return bookBorrowRecordMapper.queryBookBorrowByOpenidAndBookStatus(openid);
	}

	//查询所有逾期未还的全部用户（借书状态bookStatus 0，且借出时间大于30天）
	public List<Map<String, Object>> queryBookBorrowByBookStatus() {
		return bookBorrowRecordMapper.queryBookBorrowByBookStatus();
	}
	//查询管理员信息
	public List<Map<String, Object>>  queryIsAdmin(){
		return custBindUsersMapper.queryIsAdmin();
	}

	@Override
	public List<Map<String, Object>> queryBookBorrowHistoryByBookId(Integer bookId) {
		return bookBorrowRecordMapper.queryBookBorrowHistoryByBookId(bookId);
	}
	
	@Override
	public void addCommentRecord(Integer bookId, CustBindUsersEntity bindUser, String comment) throws BaseException {
		BookCommentRecordEntity entity = new BookCommentRecordEntity();
        BookBaseInfoEntity bookInfo = bookBaseInfoMapper.queryById(bookId);
		//entity.setRecTime(new Date());
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
	public void addCommentLikedRecord(Integer bookId,CustBindUsersEntity bindUser,Integer commentId) throws BaseException {
		CommentLikeRecordEntity entity = new CommentLikeRecordEntity();
		entity.setBookId(bookId);
		entity.setCommentId(commentId);
		String openid = bindUser.getOpenid();
		entity.setOpenid(openid);
		entity.setHeadImgUrl(bindUser.getHeadImgUrl());
		entity.setUserName(bindUser.getUserName());
		entity.setRecTime(new Date());
		CommentLikeRecordEntity isLiked = commentLikeRecordMapper.queryCommentLikeRecordById(commentId, openid);
		//BookCommentRecordEntity bc = bookCommentRecordMapper.queryComment(bookId,commentId);
		if(!StringUtil.isNullOrEmpty(isLiked)) {
			//return; 返回状态给前端 该用户是否点过赞
			//bc.setIsLiked("1");
			log.info("该用户已经点过赞了，openid:" + openid);
			throw new BaseException("comment.failed", "该用户已经点过赞了");
		}

		Integer cl;
		try{
			cl = commentLikeRecordMapper.insertCommentLikeRecord(entity);
		} catch(Exception e) {
			e.printStackTrace();
			throw new BaseException("comment.failed", "该用户已经点过赞了");
		}
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

		} else {
			// 不可借阅
			log.debug("bookInfo.getBookStatus：" + bookInfo.getBookStatus() + ", isBorrowed:" + isBorrowed);
			throw new BaseException("can.not.borrow", "本书当前不可借阅bookId：" + bookId);
		}

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

	}
	@Override
	public MessageBaseInfoEntity insertMessage(Integer bookId, String bookName, String operationContent, String operationType, String userName)

	{
		MessageBaseInfoEntity m = new MessageBaseInfoEntity();
		m.setBookId(bookId);
		m.setBookName(bookName);
		m.setOperationTime(new Date());
		m.setOperationContent(operationContent);
		m.setOperationType(operationType);
		m.setUserName(userName);
		messageBaseInfoMapper.insertMessage(m);
		return m;
	}
}
