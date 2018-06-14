package com.ahuang.bookCornerServer.servise.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.ahuang.bookCornerServer.bo.BookList;
import com.ahuang.bookCornerServer.entity.BookBaseInfoEntity;
import com.ahuang.bookCornerServer.entity.BookBorrowRecordEntity;
import com.ahuang.bookCornerServer.entity.BookCommentRecordEntity;
import com.ahuang.bookCornerServer.mapper.BookBaseInfoMapper;
import com.ahuang.bookCornerServer.mapper.BookBorrowRecordMapper;
import com.ahuang.bookCornerServer.mapper.BookCommentRecordMapper;
import com.ahuang.bookCornerServer.servise.BookService;
/**
 * 
* @ClassName: BookServiceImpl
* @Description: 图书服务类
* @author ahuang
* @date 2018年6月2日 下午9:58:15
* @version V1.0
 */
@Service
public class BookServiceImpl implements BookService {
	@Autowired
	private BookBaseInfoMapper bookBaseInfoMapper;
	
	@Autowired
	private BookBorrowRecordMapper bookBorrowRecordMapper;
	
	@Autowired
	private BookCommentRecordMapper bookCommentRecordMapper;
	
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
		if(!ObjectUtils.isEmpty(bo)) {
			if(null == isBorrowed || null == isBorrowed.getBorrowStatus()) {
				bo.setIsBorrowed("0");
			} else {
				bo.setIsBorrowed(isBorrowed.getBorrowStatus());
			}
		}
		return bo;
	}
	
	@Override
	public List<BookCommentRecordEntity> queryCommentList(Integer bookId) {
		return bookCommentRecordMapper.queryCommentList(bookId);
	}
}
