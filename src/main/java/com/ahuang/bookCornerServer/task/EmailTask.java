package com.ahuang.bookCornerServer.task;

import com.ahuang.bookCornerServer.exception.BaseException;
import com.ahuang.bookCornerServer.servise.BookService;
import com.ahuang.bookCornerServer.servise.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

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
@Slf4j
@Component
public class EmailTask {
    @Autowired
    private EmailService emailService;
    @Autowired
    private BookService bookService;

    /**
     * 定时执行，每工作日中午发送一次
     */
    //@Scheduled(fixedDelay = 1000000)
    @Scheduled(cron="0 0 12 * * MON-FRI")
    public void task () throws BaseException
    {
        System.out.println(new Date());

        List<Map<String, Object>> userBorrowBookEmailList = bookService.queryBookBorrowByBookStatus();
        for (int i = 0; i < userBorrowBookEmailList.size(); i++) {
            String openid = (String) userBorrowBookEmailList.get(i).get("openid");
            List<Map<String, Object>> sendBorrowBookEmailList = bookService.queryBookBorrowByOpenidAndBookStatus(openid);
            Map<String, Object> res = new HashMap<>();
            res.put("sendBorrowBookEmailList", sendBorrowBookEmailList);
            String userEmail = (String)sendBorrowBookEmailList.get(0).get("userEmail");
            String emailSubject = "开发二部图书角还书温馨提醒"+"\t"+sendBorrowBookEmailList.get(0).get("userName")+"\t";
            String emailContent =
                    "<html>\n" +
                    "<body>\n" +
                    "    <h3>您好，您借阅的图书已超过还书期限，请尽快完成阅读，并及时归还图书；如已归还图书，请及时在小程序上关闭借阅。</h3>\n"+
                    "<table border=\"1\">\n" +
                    "  <thead>\n" +
                    "    <tr>\n" +
                    "      <th>图书名</th>\n" +
                            "<th>借阅时间</th>\n" +
                    "      <th>借阅信息</th>\n" +
                    "    </tr>\n" +
                    "  </thead>\n"+
                            "<tbody>\n";

            for (int j = 0; j < sendBorrowBookEmailList.size(); j++) {
                Map<String, Object> map = sendBorrowBookEmailList.get(j);
                emailContent += "   <tr>\n" +
                                "   <th>" + map.get("bookName")+"\t" + "</th>\n"+
                                "   <th>" + map.get("borrowTime")+"\t" + "</th>\n"+
                                "   <th>借阅时间超过一个月需还书</th>\n"+
                                "   </tr>\n";
            }
            emailContent += "   </tbody>\n"+
                            "</table>\n"+
                            "</body>\n" +
                            "</html>";
            System.out.println(emailContent);
            try {
                emailService.sendHtmlEmail(userEmail, emailSubject, emailContent);
                log.info("html邮件发送正常:" + emailSubject);
            } catch (Exception e){
                log.error("html邮件发送失败:" + emailSubject);
                e.printStackTrace();
                throw new BaseException("email.failed", "html邮件发送失败,用户邮箱为空"+emailSubject);
            }

          //发送简单邮件
          /*  try{
                emailService.sendSimpleEmail(userEmail,emailSubject,emailContent);
                log.info("简单邮件发送正常:" + emailSubject);
            } catch(Exception e) {
                log.error("简单邮件发送失败:" + emailSubject);
                e.printStackTrace();
                throw new BaseException("email.failed", "简单邮件发送失败,用户邮箱为空"+emailSubject);
            }*/
        }
    }

}
