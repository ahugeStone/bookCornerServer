package com.ahuang.bookCornerServer.servise;

/**
 * 邮件服务
 *
 * @author ahuang
 * @version V1.0
 * @Title: EmailService
 * @Program: bookCornerServer
 * @Package com.ahuang.bookCornerServer.servise
 * @create 2018-07-24 20:58
 */
public interface EmailService {
    /**
     * 发送简单邮件
     * @param to
     * @param subject
     * @param content
     */
    void sendSimpleEmail(String to, String subject, String content);
    /**
     * 发送html格式邮件
     * @param to
     * @param subject
     * @param content
     */
    void sendHtmlEmail(String to, String subject, String content,String filePath);
    /**
     * 发送带附件的邮件
     * @param to
     * @param subject
     * @param content
     * @param filePath
     */
    void sendAttachmentsEmail(String to, String subject, String content, String filePath);
    /**
     * 发送带静态资源的邮件
     * @param to
     * @param subject
     * @param content
     * @param rscPath
     * @param rscId
     */
    void sendInlineResourceEmail(String to, String subject, String content, String rscPath, String rscId);
}
