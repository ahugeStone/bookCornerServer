package com.ahuang.bookCornerServer.task;

import com.ahuang.bookCornerServer.servise.BookService;
import com.ahuang.bookCornerServer.servise.EmailService;
import com.ahuang.bookCornerServer.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

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
    private final EmailService emailService;
    private final BookService bookService;

    private String normalEmailFormat =  "<html>\n" +
            "<body>\n" +
            "<h3>%s</h3>"+
            "    <h3>您好，您借阅的图书已超过还书期限，请尽快完成阅读，并及时归还图书；如已归还图书，请及时在小程序上关闭借阅。</h3>\n" +
            "    <h3>图书角小程序二维码见下图。</h3>\n" +
            "<table border=\"1\">\n" +
            "    <tr>\n" +
            "      <th>借阅人</th>\n" +
            "      <th>图书名</th>\n" +
            "      <th>借阅时间</th>\n" +
            "      <th>借阅信息</th>\n" +
            "    </tr>\n" +
            "%s" +
            "</table>\n" +
            "<img src='" + Constant.IMG_BASE64 + "'>\n" +
            "</body>\n" +
            "</html>";

    private String adminEmailFormat = "<html>\n" +
            "<body>\n" +
            "<h3>%s</h3>"+
            "    <h3>管理员您好，今天邮件通知情况如下，请查收。</h3>\n" +
            "<table border=\"1\">\n" +
            "    <tr>\n" +
            "      <th>借阅人</th>\n" +
            "      <th>图书名</th>\n" +
            "      <th>借阅时间</th>\n" +
            "      <th>相关信息</th>\n" +
            "    </tr>\n" +
            "%s" +
            "   </tbody>\n" +
            "</table>\n" +
            "<img src='" + Constant.IMG_BASE64 + "'>\n" +
            "</body>\n" +
            "</html>";

    @Autowired
    public EmailTask(EmailService emailService, BookService bookService) {
        this.emailService = emailService;
        this.bookService = bookService;
    }

    /**
     * 定时执行，每工作日早上8点发送一次
     */
    //@Scheduled(fixedDelay = 1000000)
    @Scheduled(cron="0 0 8 * * MON-FRI")
    public void task () {
        log.info("[EMAIL TASK BEGIN-----------------------]");
        String adminEmailSubject = "【开发二部图书角管理员】逾期未还图书信息汇总";
        StringBuilder adminEmailContent = new StringBuilder();
//        查询所有逾期未还的全部用户
        List<Map<String, Object>> userBorrowTooLongList = bookService.queryBookBorrowByBookStatus();
        for (Map<String, Object> anUserBorrowTooLong : userBorrowTooLongList) {
            String openid = (String) anUserBorrowTooLong.get("openid");
//            查询特定用户的逾期未还图书情况
            List<Map<String, Object>> bookBorrowTooLongList = bookService.queryBookBorrowByOpenidAndBookStatus(openid);
            String userName = (String) bookBorrowTooLongList.get(0).get("userName");
            String userEmail = null;
            // 邮箱为空，不进行邮件拼装
            if(StringUtil.isNullOrEmpty(bookBorrowTooLongList.get(0).get("userEmail"))){
                log.info("用户邮箱为空，用户名:" + userName);
            } else {
                userEmail = (String) bookBorrowTooLongList.get(0).get("userEmail");
            }

            String emailSubject = "【还书提醒】开发二部图书角";
            StringBuilder emailContent = new StringBuilder();

            for (Map<String, Object> map : bookBorrowTooLongList) {
                String status = null==userEmail ? "用户邮箱为空，无法发送":"借阅时间超过一个月需还书";
                String eachLine = "<tr><th>%s</th><th>%s</th><th>%s</th><th>%s</th></tr>";
                String tmp = String.format(eachLine, map.get("userName"),map.get("bookName"), map.get("borrowTime"),status);

                emailContent.append(tmp);
                adminEmailContent.append(tmp);
            }
            if(null == userEmail) {
                // 邮箱为空不发送，但是生成管理员邮件
                continue;
            }
            emailContent = new StringBuilder(String.format(normalEmailFormat, userName, emailContent.toString()));
            try {
                emailService.sendHtmlEmail(userEmail, emailSubject, emailContent.toString());
                log.info("html邮件发送正常--收件人：" + userName + "，邮箱：" + userEmail + "，邮件标题：" + emailSubject);
            } catch (Exception e) {
                log.error("html邮件发送失败--收件人：" + userName + "，邮箱：" + userEmail + "，邮件标题：" + emailSubject);
                e.printStackTrace();
            }

        }


        //获取管理员列表
        List<Map<String, Object>> adminEmailList = bookService.queryIsAdmin();
        if(StringUtil.isNullOrEmpty(adminEmailList)) {
            log.info("ADMIN 管理员列表为空，不发送管理员邮件");
        }
        for (Map<String, Object> anAdminEmail : adminEmailList) {
            String adminName = (String) anAdminEmail.get("userName");
            if(StringUtil.isNullOrEmpty(anAdminEmail.get("userEmail"))) {
                log.info("管理员邮箱为空，姓名:" + adminName);
                continue;
            }
            String adminEmailHtml = String.format(adminEmailFormat,adminName, adminEmailContent.toString());
            String adminEmail = (String) anAdminEmail.get("userEmail");
            try {
                emailService.sendHtmlEmail(adminEmail, adminEmailSubject, adminEmailHtml);
                log.info("admin邮件发送正常--收件人" + adminName + "，邮箱：" + adminEmail + "，邮件标题：" + adminEmailSubject);
            } catch (Exception e) {
                log.error("admin邮件发送失败--收件人" + adminName + "，邮箱：" + adminEmail + "，邮件标题：" + adminEmailSubject);
                e.printStackTrace();
            }

        }
        log.info("[EMAIL TASK END++++++++++++++++++++++++]");

    }

}
