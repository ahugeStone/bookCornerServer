package com.ahuang.bookCornerServer.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/bookTest")
public class bookTest {
	
	@RequestMapping("/bTest")
	public Map<String, Object> bTest(@RequestBody Map<String, Object> req) {
		log.info("into boot");
		return req;
	}
}
