package com.ahuang.bookCornerServer;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * rest接口自动化测试
 *
 * @author ahuang
 * @version V1.0
 * @Title: BookCornerServerRestTest
 * @Program: bookCornerServer
 * @Package com.ahuang.bookCornerServer
 * @create 2018-07-10 22:31
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class BookCornerServerRestTest  extends BaseTest{

    @Value("${tx.test}")
    private boolean test;

    @Test
    public void custQueryIsBinded() {
        log.info("into test");
    }
}
