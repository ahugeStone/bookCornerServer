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
     * 定时执行，每工作日早上8点发送一次
     */
    //@Scheduled(fixedDelay = 1000000)
    @Scheduled(cron="0 0 8 * * MON-FRI")
    public void task () throws BaseException
    {
        System.out.println(new Date());

        String adminEmailSubject = "【开发二部图书角管理员】逾期未还图书信息汇总";
        String adminEmailContent = "<html>\n" +
                "<body>\n" +
                "    <h3>【管理员还书提醒汇总】开发二部图书角</h3>\n" +
                "<table border=\"1\">\n" +
                "  <thead>\n" +
                "    <tr>\n" +
                "      <th>借阅人</th>\n" +
                "      <th>图书名</th>\n" +
                "      <th>借阅时间</th>\n" +
                "      <th>借阅信息</th>\n" +
                "    </tr>\n" +
                "  </thead>\n" +
                "<tbody>\n";

        List<Map<String, Object>> userBorrowBookEmailList = bookService.queryBookBorrowByBookStatus();
        for (int i = 0; i < userBorrowBookEmailList.size(); i++) {
            String openid = (String) userBorrowBookEmailList.get(i).get("openid");
            List<Map<String, Object>> sendBorrowBookEmailList = bookService.queryBookBorrowByOpenidAndBookStatus(openid);
            Map<String, Object> res = new HashMap<>();
            res.put("sendBorrowBookEmailList", sendBorrowBookEmailList);
            String userEmail = (String) sendBorrowBookEmailList.get(0).get("userEmail");
            String emailSubject = "【还书提醒】开发二部图书角";
            String emailContent =
                        "<html>\n" +
                                "<body>\n" +
                                "    <h3>您好，您借阅的图书已超过还书期限，请尽快完成阅读，并及时归还图书；如已归还图书，请及时在小程序上关闭借阅。</h3>\n" +
                                "    <h3>图书角小程序二维码见下图。</h3>\n" +
                                "<table border=\"1\">\n" +
                                "  <thead>\n" +
                                "    <tr>\n" +
                                "      <th>借阅人</th>\n" +
                                "      <th>图书名</th>\n" +
                                "      <th>借阅时间</th>\n" +
                                "      <th>借阅信息</th>\n" +
                                "    </tr>\n" +
                                "  </thead>\n" +
                                "<tbody>\n";

            for (int j = 0; j < sendBorrowBookEmailList.size(); j++) {
                    Map<String, Object> map = sendBorrowBookEmailList.get(j);
                    emailContent += "   <tr>\n" +
                            "   <th>" + map.get("userName") + "\t" + "</th>\n" +
                            "   <th>" + map.get("bookName") + "\t" + "</th>\n" +
                            "   <th>" + map.get("borrowTime") + "\t" + "</th>\n" +
                            "   <th>借阅时间超过一个月需还书</th>\n" +
                            "   </tr>\n";
                    adminEmailContent += "   <tr>\n" +
                            "   <th>" + map.get("userName") + "\t" + "</th>\n" +
                            "   <th>" + map.get("bookName") + "\t" + "</th>\n" +
                            "   <th>" + map.get("borrowTime") + "\t" + "</th>\n" +
                            "   <th>借阅时间超过一个月需还书</th>\n" +
                            "   </tr>\n";
            }
            emailContent +=
                    "   </tbody>\n" +
                        "</table>\n" +
                            "<img src='data:image/jpeg;base64,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'>\n" +
                        "</body>\n" +
                        "</html>";
            System.out.println(emailContent);
            try {
                    emailService.sendHtmlEmail(userEmail, emailSubject, emailContent);
                    log.info("html邮件发送正常:" + emailSubject);
                } catch (Exception e) {
                    log.error("html邮件发送失败:" + emailSubject);
                    e.printStackTrace();
                    throw new BaseException("email.failed", "html邮件发送失败,用户邮箱为空" + emailSubject);
            }

        }

        adminEmailContent += "   </tbody>\n" +
                "</table>\n" +
                "<img src='data:image/jpeg;base64,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'>\n" +
                "</body>\n" +
                "</html>";
        System.out.println(adminEmailContent);
        //获取管理员列表
        List<Map<String, Object>> adminEmailList = bookService.queryIsAdmin();
        for (int i = 0; i < adminEmailList.size(); i++) {
            String adminEmail=(String) adminEmailList.get(i).get("userEmail");
            try {
                emailService.sendHtmlEmail(adminEmail, adminEmailSubject, adminEmailContent);
                log.info("html邮件发送正常:" + adminEmailSubject);
            } catch (Exception e) {
                log.error("html邮件发送失败:" + adminEmailSubject);
                e.printStackTrace();
                throw new BaseException("email.failed", "html邮件发送失败,用户邮箱为空" + adminEmailSubject);
            }

        }


    }

}
