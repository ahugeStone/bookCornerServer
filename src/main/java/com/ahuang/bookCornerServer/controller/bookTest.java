package com.ahuang.bookCornerServer.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.ahuang.bookCornerServer.bo.PageList;
import com.ahuang.bookCornerServer.entity.BookBaseInfoEntity;
import com.ahuang.bookCornerServer.mapper.BookBaseInfoMapper;
import com.ahuang.bookCornerServer.servise.BookService;

import lombok.extern.slf4j.Slf4j;

/**
 * 
* @ClassName: bookTest
* @Description: 测试controller
* @author ahuang
* @date 2018年6月2日 下午10:01:31
* @version V1.0
 */
@Slf4j
@RestController
@RequestMapping("/bookTest")
public class bookTest {
	@Autowired
	private BookBaseInfoMapper bookBaseInfoMapper;
	
	@Autowired
	private BookService bookService;
	@RequestMapping(path="/bTest",method = { RequestMethod.POST })
	public Map<String, Object> bTest(@RequestBody Map<String, Object> req, HttpSession session) {
		log.info("into boot");
		String browser = "boot";
		Object sessionBrowser = session.getAttribute("browser");
        if (sessionBrowser == null) {
        	log.info("不存在session，设置browser=" + browser);
            session.setAttribute("browser", browser);
        } else {
        	log.info("存在session，browser=" + sessionBrowser.toString());
        }
		return req;
	}
	@RequestMapping(path="/getOpenIdTest",method = { RequestMethod.POST })
	public Map<String, Object> getOpenIdTest(@RequestBody Map<String, Object> req, HttpSession session) {
		String code = (String)req.get("code");
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> requestEntity = new HttpEntity<String>("", headers);
        ResponseEntity<String> response = null;
        try {
            response = restTemplate.exchange(
                    "https://api.weixin.qq.com/sns/jscode2session?appid=wx55ffea6285c395c7&secret=6a6b6f5c3c399dbc447de427d4cc403c&js_code="
            + code 
            + "&grant_type=authorization_code",
                    HttpMethod.GET,
                    null,
                    String.class);
        } catch (RestClientException e) {
            log.error(e.toString());
//            throw e;
        }
        log.info(response.getBody());
        
		return req;
	}
//	@RequestMapping("/queryBookById")
//	public BookBaseInfoEntity queryBookById(@RequestBody Map<String, Object> req) {
//		return bookBaseInfoMapper.queryById((Integer)req.get("id"));
//	}
	@RequestMapping("/queryBooks")
	public List<BookBaseInfoEntity> queryBooks(@RequestBody Map<String, Object> req) {
		return bookBaseInfoMapper.queryBookList();
	}
	@RequestMapping("/queryBooksPages")
	public PageList<BookBaseInfoEntity> queryBooksPages(@RequestBody Map<String, Object> req) {
		Integer num = (Integer)req.get("num");
		String bookName = (String) req.get("bookName");
		String bookType = (String) req.get("bookType");
		String bookStatus = (String) req.get("bookStatus");
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("num", num);
		param.put("pageSize", 10);
		param.put("bookName", bookName);
		param.put("bookType", bookType);
		param.put("bookStatus", bookStatus);
		return bookService.queryBookListPage(param);
	}
}
