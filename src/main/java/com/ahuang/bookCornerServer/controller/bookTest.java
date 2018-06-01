package com.ahuang.bookCornerServer.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ahuang.bookCornerServer.bo.PageList;
import com.ahuang.bookCornerServer.entity.BookBaseInfoEntity;
import com.ahuang.bookCornerServer.mapper.BookBaseInfoMapper;
import com.ahuang.bookCornerServer.servise.BookService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/bookTest")
public class bookTest {
	@Autowired
	private BookBaseInfoMapper bookBaseInfoMapper;
	
	@Autowired
	private BookService bookService;
	
	@RequestMapping("/bTest")
	public Map<String, Object> bTest(@RequestBody Map<String, Object> req) {
		log.info("into boot");
		return req;
	}
	@RequestMapping("/queryBookById")
	public BookBaseInfoEntity queryBookById(@RequestBody Map<String, Object> req) {
		return bookBaseInfoMapper.queryById((Integer)req.get("id"));
	}
	@RequestMapping("/queryBooks")
	public List<BookBaseInfoEntity> queryBooks(@RequestBody Map<String, Object> req) {
		return bookBaseInfoMapper.queryBookList();
	}
	@RequestMapping("/queryBooksPages")
	public PageList<BookBaseInfoEntity> queryBooksPages(@RequestBody Map<String, Object> req) {
		Integer num = (Integer)req.get("num");
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("num", num);
		param.put("pageSize", 10);
		return bookService.queryBookListPage(param);
	}
}
