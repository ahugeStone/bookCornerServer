package com.ahuang.bookCornerServer.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ahuang.bookCornerServer.entity.BookBaseInfoEntity;
import com.ahuang.bookCornerServer.mapper.BookBaseInfoMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/bookTest")
public class bookTest {
	@Autowired
	private BookBaseInfoMapper bookBaseInfoMapper;
	
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
		return bookBaseInfoMapper.queryByParams();
	}
}
