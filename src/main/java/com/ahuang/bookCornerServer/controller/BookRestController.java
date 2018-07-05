package com.ahuang.bookCornerServer.controller;

import com.ahuang.bookCornerServer.controller.req.CustQueryBookListReq;
import com.ahuang.bookCornerServer.entity.BookBaseInfoEntity;
import com.ahuang.bookCornerServer.servise.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * Rest风格的接口
 *
 * @author ahuang
 * @version V1.0
 * @Title: BookRestController
 * @Program: bookCornerServer
 * @Package com.ahuang.bookCornerServer.controller
 * @create 2018-07-05 22:34
 */
@RestController
@RequestMapping(path="/bookCorner/v1")
public class BookRestController {
    @Autowired
    private BookService bookService;

    @RequestMapping(path="/books",method = { RequestMethod.GET })
    public Object custQueryBookList(@Valid CustQueryBookListReq req) {
        Integer num = req.getNum();
        String bookName = req.getBookName();
        String bookType = req.getBookType();
        String bookStatus = req.getBookStatus();
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("num", num);
        param.put("pageSize", 20);
        param.put("bookName", bookName);
        param.put("bookType", bookType);
        param.put("bookStatus", bookStatus);
        Object res = bookService.queryBookListPage(param);

        return res;
    }

    @RequestMapping(path="/books/{bookId}",method = { RequestMethod.GET })
    public BookBaseInfoEntity custQueryBookDetail(@PathVariable Integer bookId) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("id", bookId);
        param.put("openid", "oe0Ej0besxqth6muj72ZzfYGmMp0");
        BookBaseInfoEntity res = bookService.queryBookDetailById(param);
        return res;
    }
}
