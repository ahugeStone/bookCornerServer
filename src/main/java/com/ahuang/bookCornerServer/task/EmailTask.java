package com.ahuang.bookCornerServer.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

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
    /**
     * 定时执行，每工作日中午发送一次
     */
    @Scheduled(cron="0 0 12 * * MON-FRI")
    public void task()
    {
        System.out.println(new Date());
    }
}
