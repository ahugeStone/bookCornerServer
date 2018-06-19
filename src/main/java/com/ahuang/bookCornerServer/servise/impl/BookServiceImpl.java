package com.ahuang.bookCornerServer.servise.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ahuang.bookCornerServer.bo.BookList;
import com.ahuang.bookCornerServer.entity.BookBaseInfoEntity;
import com.ahuang.bookCornerServer.entity.BookBorrowRecordEntity;
import com.ahuang.bookCornerServer.entity.BookCommentRecordEntity;
import com.ahuang.bookCornerServer.entity.CustBindUsersEntity;
import com.ahuang.bookCornerServer.mapper.BookBaseInfoMapper;
import com.ahuang.bookCornerServer.mapper.BookBorrowRecordMapper;
import com.ahuang.bookCornerServer.mapper.BookCommentRecordMapper;
import com.ahuang.bookCornerServer.mapper.CustBindUsersMapper;
import com.ahuang.bookCornerServer.servise.BookService;
import com.ahuang.bookCornerServer.util.StringUtil;

import lombok.extern.slf4j.Slf4j;

import com.ahuang.bookCornerServer.exception.*;
/**
 * 
* @ClassName: BookServiceImpl
* @Description: 图书服务类
* @author ahuang
* @date 2018年6月2日 下午9:58:15
* @version V1.0
 */
@Slf4j
@Service
public class BookServiceImpl implements BookService {
	//TODO 不要暴露openid给前端
	@Autowired
	private BookBaseInfoMapper bookBaseInfoMapper;
	
	@Autowired
	private BookBorrowRecordMapper bookBorrowRecordMapper;
	
	@Autowired
	private BookCommentRecordMapper bookCommentRecordMapper;
	
	@Autowired
	private CustBindUsersMapper custBindUsersMapper;
	
	@Override
	public BookList<BookBaseInfoEntity> queryBookListPage(Map<String, Object> param) {
		Integer num = (Integer)param.get("num"); // 当前页起始id
		Integer pageSize = (Integer)param.get("pageSize"); // 页面大小
		param.put("isCount", false);
		List<BookBaseInfoEntity> booklist = bookBaseInfoMapper.queryBookListPage(param); // 图书列表
		param.put("isCount", true);
		Integer totalNum = bookBaseInfoMapper.queryBookInfoNum(param); // 图书总数
		BookList<BookBaseInfoEntity> pageList = new BookList<BookBaseInfoEntity>(num, totalNum,pageSize,booklist); //获取页面对象
		
		return pageList;
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
		return bo;
	}
	
	@Override
	public List<BookCommentRecordEntity> queryCommentList(Integer bookId) {
		return bookCommentRecordMapper.queryCommentList(bookId);
	}
	
	@Override
	public List<Map<String, Object>> queryBookBorrowByOpenid(String openid) throws Exception {
		List<Map<String, Object>> result = bookBorrowRecordMapper.queryBookBorrowByOpenid(openid);
		return result;
	}
	
	@Override
	public List<Map<String, Object>> queryBookBorrowHistoryByBookId(Integer bookId) {
		List<Map<String, Object>> result = bookBorrowRecordMapper.queryBookBorrowHistoryByBookId(bookId);
		return result;
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
}
