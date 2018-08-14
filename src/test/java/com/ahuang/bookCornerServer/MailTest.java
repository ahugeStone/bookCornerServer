package com.ahuang.bookCornerServer;

import com.ahuang.bookCornerServer.exception.BaseException;
import com.ahuang.bookCornerServer.servise.EmailService;
import com.ahuang.bookCornerServer.task.EmailTask;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 发送邮件测试
 *
 * @author ahuang
 * @version V1.0
 * @Title: MailTest
 * @Program: bookCornerServer
 * @Package com.ahuang.bookCornerServer
 * @create 2018-07-24 21:13
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class MailTest {
    @Autowired
    private EmailService emailService;
    @Autowired
    private EmailTask etask;

    @Test
    public void sendMail() {
        String content="<html>\n" +
                "<body>\n" +
                "    <h3>hello world ! 这是一封Html邮件!</h3>\n" +
                    "<img src=''>\n" +
                "</html>";

        emailService.sendHtmlEmail("puxueweiwei@163.com","this is html mail",content);

    }
    @Test
    public void etaskTest() {
        etask.task();
    }
}
