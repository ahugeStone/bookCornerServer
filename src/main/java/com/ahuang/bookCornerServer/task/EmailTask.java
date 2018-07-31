package com.ahuang.bookCornerServer.task;

import com.ahuang.bookCornerServer.servise.BookService;
import com.ahuang.bookCornerServer.servise.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 定时发送提醒邮件
 *
 * @author ahuang
 * @version V1.0
 * @Title: EmailTask
 * @Program: bookCornerServer
 * @Package com.ahuang.bookCornerServer.task
 * @create 2018-07-24 20:41
 */
@Component
public class EmailTask {
    @Autowired
    private EmailService emailService;
    @Autowired
    private BookService bookService;

    /**
     * 定时执行，每工作日中午发送一次
     */
    //@Scheduled(fixedDelay = 100000)
    @Scheduled(cron="0 0 12 * * MON-FRI")
    public void task()
    {
        System.out.println(new Date());

        List<Map<String, Object>> userBorrowBookEmailList = bookService.queryBookBorrowByBookStatus();
        for (int i = 0; i < userBorrowBookEmailList.size(); i++) {
            String openid = ""+userBorrowBookEmailList.get(i).get("openid");
            List<Map<String, Object>> sendBorrowBookEmailList = bookService.queryBookBorrowByOpenidAndBookStatus(openid);
            Map<String, Object> res = new HashMap<>();
            res.put("sendBorrowBookEmailList", sendBorrowBookEmailList);
            String userEmail = ""+sendBorrowBookEmailList.get(0).get("userEmail");
            String emailSubject = "开发二部图书角温馨提示"+"\t"+sendBorrowBookEmailList.get(0).get("userName")+"\t"+"欠书超过一个月";
            String emailContent = "";
            for (int j = 0; j < sendBorrowBookEmailList.size(); j++) {
                Map<String, Object> map = sendBorrowBookEmailList.get(j);
                emailContent += "图书："+map.get("bookName")+"\t"+"借阅时间超过一个月需还书"+"\n";
            }
            emailService.sendSimpleEmail(userEmail,emailSubject,emailContent);
        }
    }

}
