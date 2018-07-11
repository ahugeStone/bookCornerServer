package com.ahuang.bookCornerServer;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
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

    /**
     * 测试虚假code用户登陆
     * @throws Exception
     */
    @Test
    public void custQueryIsBindedErrorCode() throws Exception {
        if(test) {//测试模式不需要测试本接口
            return;
        }
//        Map<String, Object> params = new HashMap<String, Object>();
//        params.put("code", "123");
//        String jsonStr = getRestRequest(params);// 获取上送报文
        //测试code失效
        this.mockMvc.perform(get("/bookCorner/v1/token")
                .contentType(MediaType.APPLICATION_JSON_UTF8) // 设置报文头
//                .header("Authorization", "Bearer " + tokenBinded)
                .param("code", "123") // 设置报文参数
//                .content(jsonStr.getBytes()) // 设置报文参数
        )
        .andDo(print())// 打印测试过程
        .andExpect(status().is4xxClientError())//判断返回403
        .andExpect(jsonPath("$.code").value("login.failed"))//假的code会失败
        ;


    }

    /**
     * 测试未绑定用户登陆
     * @throws Exception
     */
    @Test
    public void custQueryIsBindedNotBind() throws Exception {
        //测试未绑定用户查询
        this.mockMvc.perform(get("/bookCorner/v1/token")
                        .contentType(MediaType.APPLICATION_JSON_UTF8) // 设置报文头
                        .header("Authorization", "Bearer " + tokenNotBinded)
                        .param("code", "123") // 设置报文参数
//                .content(jsonStr.getBytes()) // 设置报文参数
        )
                .andDo(print())// 打印测试过程
                .andExpect(status().isOk())//判断返回200
                .andExpect(jsonPath("$.token").isString())//会成功获取token
                .andExpect(jsonPath("$.isBinded").value("0"))//返回没有绑定
                .andExpect(jsonPath("$.userNo").doesNotExist())//不会返回测试用的员工号
                .andExpect(jsonPath("$.userName").doesNotExist())//不会返回测试用的员工名
        ;

    }

    /**
     * 测试已绑定用户登陆
     * @throws Exception
     */
    @Test
    public void custQueryIsBindedisBinded() throws Exception {
        // 测试已登陆已绑定用户查询
        this.mockMvc.perform(get("/bookCorner/v1/token")
                        .contentType(MediaType.APPLICATION_JSON_UTF8) // 设置报文头
                        .header("Authorization", "Bearer " + tokenBinded)
                        .param("code", "123") // 设置报文参数
//                .content(jsonStr.getBytes()) // 设置报文参数
        )
                .andDo(print())// 打印测试过程
                .andExpect(status().isOk())//判断返回200
                .andExpect(jsonPath("$.token").isString())//会成功获取token
                .andExpect(jsonPath("$.isBinded").value("1"))//返回已经绑定
                .andExpect(jsonPath("$.userNo").value("3693"))//返回测试用的员工号
                .andExpect(jsonPath("$.userName").value("黄实"))//返回测试用的员工名
        ;
    }
}
