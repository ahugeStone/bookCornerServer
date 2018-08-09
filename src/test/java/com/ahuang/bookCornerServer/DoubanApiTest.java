package com.ahuang.bookCornerServer;

import com.ahuang.bookCornerServer.entity.BookBaseInfoEntity;
import com.ahuang.bookCornerServer.mapper.BookBaseInfoMapper;
import com.ahuang.bookCornerServer.util.StringUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 豆瓣api测试
 *
 * @author ahuang
 * @version V1.0
 * @Title: DoubanApiTest
 * @Program: bookCornerServer
 * @Package com.ahuang.bookCornerServer
 * @create 2018-08-04 13:29
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class DoubanApiTest {
    @Autowired
    private BookBaseInfoMapper bookBaseInfoMapper;

    @Autowired
    private ObjectMapper objectMapper;

    String queryByKeyWord = "https://api.douban.com/v2/book/search?q=%s&count=%s";


    @Test
    public void loadBookInfo() {
        RestTemplate restTemplate = new RestTemplate();
//        List<BookBaseInfoEntity> bookList = bookBaseInfoMapper.queryBookList();
//        for (BookBaseInfoEntity book : bookList ) {
//            if(StringUtil.isNullOrEmpty(book.getBookBrief()) || "图书简介".equals(book.getBookBrief())) {
//                getDouban(restTemplate, book);
//            }
//        }
//        BookBaseInfoEntity bookInfo = bookBaseInfoMapper.queryById(75);
//        getDouban(restTemplate, bookInfo);
    }

    private void getDouban(RestTemplate restTemplate, BookBaseInfoEntity bookInfo) {
        String url = String.format(queryByKeyWord, URLEncoder.encode(bookInfo.getBookName()), 3);
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        Map<String, Object> res = new HashMap<>();
        try {
            res = objectMapper.readValue(responseEntity.getBody(), Map.class);
//            log.info("书名：" + res.toString());
            for(Object item: ((ArrayList)res.get("books"))) {
                Map book = (HashMap)item;
                try{
                    String bookName = String.valueOf(book.get("title"));
                    String author = String.valueOf(book.get("author"));
                    String isbn13 = String.valueOf(book.get("isbn13"));
                    String rating = String.valueOf(((HashMap)book.get("rating")).get("average"));
                    String bookBrief = String.valueOf(book.get("summary"));
                    log.info(bookName + "," + author + "," + isbn13);
                    if(StringUtil.isNullOrEmpty(bookInfo.getBookBrief()) || "图书简介".equals(bookInfo.getBookBrief())) {
                        bookBaseInfoMapper.updateBookInfoFromDouban(bookInfo.getBookId(), rating, isbn13, author, bookBrief);
                    } else {
                        bookBaseInfoMapper.updateBookInfoFromDouban(bookInfo.getBookId(), rating, isbn13, author, null);
                    }

                } catch(Exception e) {
                    log.error("error");
                }
            }
            log.info("---------------------------");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
