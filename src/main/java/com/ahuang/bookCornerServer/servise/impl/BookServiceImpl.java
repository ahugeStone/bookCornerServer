package com.ahuang.bookCornerServer.servise.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahuang.bookCornerServer.bo.PageList;
import com.ahuang.bookCornerServer.entity.BookBaseInfoEntity;
import com.ahuang.bookCornerServer.mapper.BookBaseInfoMapper;
import com.ahuang.bookCornerServer.servise.BookService;

@Service
public class BookServiceImpl implements BookService {
	@Autowired
	private BookBaseInfoMapper bookBaseInfoMapper;
	
	@Override
	public PageList<BookBaseInfoEntity> queryBookListPage(Map<String, Object> param) {
		Integer num = (Integer)param.get("num");
		num = num <= 0 ? 1:num;
		Integer pageSize = (Integer)param.get("pageSize");
//		PageList pageList = new PageList(pageSize, num, null, null, null);
		PageList<BookBaseInfoEntity> pageList = new PageList<BookBaseInfoEntity>();
		List<BookBaseInfoEntity> booklist = bookBaseInfoMapper.queryBookListPage(param);
		Integer totalNum = bookBaseInfoMapper.queryBookInfoNum(param);
		Integer totalPageNum = totalNum%pageSize == 0? totalNum/pageSize:totalNum/pageSize+1;
		Integer endNum = num + pageSize;
		endNum  = endNum > totalNum? totalNum:endNum;
		num = num > endNum? endNum:num;
		Integer currentPageNum = endNum%pageSize == 0? num/pageSize:num/pageSize+1;
		currentPageNum = currentPageNum > totalPageNum? totalPageNum:currentPageNum;
		boolean isLastPage = endNum == totalNum;
		
		pageList.setObjectList(booklist);
		pageList.setPageSize(pageSize);
		pageList.setStartNum(num);
		pageList.setTotalNum(totalNum);
		pageList.setLastPage(isLastPage);
		pageList.setEndNum(endNum);
		pageList.setTotalPageNum(totalPageNum);
		pageList.setCurrentPageNum(currentPageNum);
		
		return pageList;
	}

}
