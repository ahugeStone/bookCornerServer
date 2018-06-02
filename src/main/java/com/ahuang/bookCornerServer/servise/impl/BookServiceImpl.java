package com.ahuang.bookCornerServer.servise.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahuang.bookCornerServer.bo.PageList;
import com.ahuang.bookCornerServer.entity.BookBaseInfoEntity;
import com.ahuang.bookCornerServer.mapper.BookBaseInfoMapper;
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
	
	@Override
	public PageList<BookBaseInfoEntity> queryBookListPage(Map<String, Object> param) {
		Integer num = (Integer)param.get("num"); // 当前页起始id
		Integer pageSize = (Integer)param.get("pageSize"); // 页面大小
		List<BookBaseInfoEntity> booklist = bookBaseInfoMapper.queryBookListPage(param); // 图书列表
		Integer totalNum = bookBaseInfoMapper.queryBookInfoNum(param); // 图书总数
		PageList<BookBaseInfoEntity> pageList = new PageList<BookBaseInfoEntity>(num, totalNum,pageSize,booklist); //获取页面对象
		
		return pageList;
	}

}
