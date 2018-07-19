package com.ahuang.bookCornerServer;

import com.ahuang.bookCornerServer.util.BookActions;
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
    
    /**
    * 测试图书列表-首页
    * @params  [] 
    * @return: void 
    * @Author: ahuang
    * @Date: 2018/7/12 下午8:50
    */ 
    @Test
    public void custQueryBookListFirstPage() throws Exception {
        this.mockMvc.perform(get("/bookCorner/v1/books")
                        .contentType(MediaType.APPLICATION_JSON_UTF8) // 设置报文头
                        .header("Authorization", "Bearer " + tokenBinded)
                        .param("num", "1") // 设置报文参数
        )
                .andDo(print())// 打印测试过程
                .andExpect(status().isOk())//判断返回200
                .andExpect(jsonPath("$.pageSize").isNumber())
                .andExpect(jsonPath("$.startNum").isNumber())
                .andExpect(jsonPath("$.lastPage").isBoolean())
                .andExpect(jsonPath("$.bookList").isArray())
                .andExpect(jsonPath("$.bookList[0].bookId").isNumber())
                .andExpect(jsonPath("$.bookList[0].bookName").isString())
                ;
    }

    /**
    * 查询图书详情
    * @params  []
    * @return: void
    * @Author: ahuang
    * @Date: 2018/7/12 下午9:01
    */
    @Test
    public void custQueryBookDetail() throws Exception {
        this.mockMvc.perform(get("/bookCorner/v1/books/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8) // 设置报文头
                .header("Authorization", "Bearer " + tokenBinded)
        )
                .andDo(print())// 打印测试过程
                .andExpect(status().isOk())//判断返回200
                .andExpect(jsonPath("$.bookId").isNumber())
                .andExpect(jsonPath("$.bookName").isString())
                ;
    }

    /**
    * 查询特定图书所有评论
    * @params  []
    * @return: void
    * @Author: ahuang
    * @Date: 2018/7/12 下午9:04
    */
    @Test
    public void custQueryBookCommentHistory() throws Exception {
        this.mockMvc.perform(get("/bookCorner/v1/books/1/comments")
                .contentType(MediaType.APPLICATION_JSON_UTF8) // 设置报文头
                .header("Authorization", "Bearer " + tokenBinded)
        )
                .andDo(print())// 打印测试过程
                .andExpect(status().isOk())//判断返回200
                .andExpect(jsonPath("$.commentHistoryList").isArray())
                .andExpect(jsonPath("$.commentHistoryList[0].userName").isString())
                .andExpect(jsonPath("$.commentHistoryList[0].comment").isString())
                ;
    }

    /**
    * 用户评论图书
    * @params  []
    * @return: void
    * @Author: ahuang
    * @Date: 2018/7/12 下午9:07
    */
    @Test
    public void custCommentBook() throws Exception {
        this.mockMvc.perform(post("/bookCorner/v1/books/1/comments")
                .contentType(MediaType.APPLICATION_JSON_UTF8) // 设置报文头
                .header("Authorization", "Bearer " + tokenBinded)
                .param("comment", "测试评论test123😄") // 设置报文参数
        )
                .andDo(print())// 打印测试过程
                .andExpect(status().isOk())//判断返回200
                ;
    }

    /**
    * 查询特定图书借阅历史
    * @params  []
    * @return: void
    * @Author: ahuang
    * @Date: 2018/7/12 下午9:15
    */
    @Test
    public void custQueryBookBorrowHistory() throws Exception {
        this.mockMvc.perform(get("/bookCorner/v1/books/1/history")
                .contentType(MediaType.APPLICATION_JSON_UTF8) // 设置报文头
                .header("Authorization", "Bearer " + tokenBinded)
        )
                .andDo(print())// 打印测试过程
                .andExpect(status().isOk())//判断返回200
                .andExpect(jsonPath("$.borrowHistoryList").isArray())
                .andExpect(jsonPath("$.borrowHistoryList[0].userName").isString())
                .andExpect(jsonPath("$.borrowHistoryList[0].borrowTime").isString())
                ;
    }

    /**
    * 图书的借阅，归还，点赞
    * @params  []
    * @return: void
    * @Author: ahuang
    * @Date: 2018/7/12 下午9:18
    */
    @Test
    public void custHandleBook() throws Exception {
        //未登陆借阅
        this.mockMvc.perform(post("/bookCorner/v1/books/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8) // 设置报文头
//                .header("Authorization", "Bearer " + tokenNotBinded)
                .param("action", BookActions.BORROW.toString()) // 设置报文参数
        )
                .andDo(print())// 打印测试过程
                .andExpect(status().is4xxClientError())//判断返回
        ;
        //未登陆借阅
        this.mockMvc.perform(post("/bookCorner/v1/books/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8) // 设置报文头
                .header("Authorization", "Bearer " + tokenNotBinded)
                .param("action", BookActions.BORROW.toString()) // 设置报文参数
        )
                .andDo(print())// 打印测试过程
                .andExpect(status().is4xxClientError())//判断返回
        ;
        //借阅
        this.mockMvc.perform(post("/bookCorner/v1/books/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8) // 设置报文头
                .header("Authorization", "Bearer " + tokenBinded)
                .param("action", BookActions.BORROW.toString()) // 设置报文参数
        )
                .andDo(print())// 打印测试过程
                .andExpect(status().isOk())//判断返回200
                ;
        //重复借阅报错
        this.mockMvc.perform(post("/bookCorner/v1/books/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8) // 设置报文头
                .header("Authorization", "Bearer " + tokenBinded)
                .param("action", BookActions.BORROW.toString()) // 设置报文参数
        )
                .andDo(print())// 打印测试过程
                .andExpect(status().is5xxServerError())//判断返回500
                .andExpect(jsonPath("$.code").value("can.not.borrow"))
                ;
        //归还
        this.mockMvc.perform(post("/bookCorner/v1/books/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8) // 设置报文头
                .header("Authorization", "Bearer " + tokenBinded)
                .param("action", BookActions.RETURN.toString()) // 设置报文参数
        )
                .andDo(print())// 打印测试过程
                .andExpect(status().isOk())//判断返回200
        ;
        //重复归还报错
        this.mockMvc.perform(post("/bookCorner/v1/books/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8) // 设置报文头
                .header("Authorization", "Bearer " + tokenBinded)
                .param("action", BookActions.RETURN.toString()) // 设置报文参数
        )
                .andDo(print())// 打印测试过程
                .andExpect(status().is5xxServerError())//判断返回500
                .andExpect(jsonPath("$.code").value("can.not.return"))
        ;
    }
    /**
    * 测试图书点赞
    * @params  []
    * @return: void
    * @Author: ahuang
    * @Date: 2018/7/12 下午9:52
    */
    @Test
    public void custHandleBookThumbup() throws Exception {
        this.mockMvc.perform(post("/bookCorner/v1/books/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8) // 设置报文头
                .header("Authorization", "Bearer " + tokenBinded)
                .param("action", BookActions.THUMBUP.toString()) // 设置报文参数
        )
                .andDo(print())// 打印测试过程
                .andExpect(status().isOk())//判断返回200
        ;
    }

    /**
    * 测试获取用户借阅图书的列表
    * @params  []
    * @return: void
    * @Author: ahuang
    * @Date: 2018/7/12 下午9:59
    */
    @Test
    public void custQueryBookBorrowRecord() throws Exception {
        this.mockMvc.perform(get("/bookCorner/v1/books/1/history")
                .contentType(MediaType.APPLICATION_JSON_UTF8) // 设置报文头
                .header("Authorization", "Bearer " + tokenBinded)
        )
                .andDo(print())// 打印测试过程
                .andExpect(status().isOk())//判断返回200
                .andExpect(jsonPath("$.borrowHistoryList").isArray())
                .andExpect(jsonPath("$.borrowHistoryList[0].userName").isString())
        ;
    }

    /**
    * 测试有token用户绑定
    * @params  []
    * @return: void
    * @Author: ahuang
    * @Date: 2018/7/12 下午10:05
    */
    @Test
    public void custBindHasBindedToken() throws Exception {
        this.mockMvc.perform(post("/bookCorner/v1/users/3693")
                .contentType(MediaType.APPLICATION_JSON_UTF8) // 设置报文头
                .header("Authorization", "Bearer " + tokenBinded)
                .param("userNo", "3693")
                .param("nickName", "阿黄")
                .param("userName", "黄实")
                .param("headImgUrl", "")

        )
                .andDo(print())// 打印测试过程
                .andExpect(status().isOk())//判断返回200
                .andExpect(jsonPath("$.isBinded").value("1"))
                .andExpect(jsonPath("$.token").isString())
                .andExpect(jsonPath("$.userNo").isString())
                .andExpect(jsonPath("$.userName").isString())
        ;
    }


    /**
     * 测试用户绑定接口上送接口不全
     * @params  []
     * @return: void
     * @Author: ahuang
     * @Date: 2018/7/12 下午10:05
     */
    @Test
    public void custBindParams() throws Exception {
        //缺少昵称
        this.mockMvc.perform(post("/bookCorner/v1/users/3693")
                        .contentType(MediaType.APPLICATION_JSON_UTF8) // 设置报文头
                        .header("Authorization", "Bearer " + tokenNotBinded)
//                        .param("userNo", "3693")
//                        .param("nickName", "阿黄")
                        .param("userName", "黄实")
                        .param("headImgUrl", "")

        )
                .andDo(print())// 打印测试过程
                .andExpect(status().is4xxClientError())//判断返回
//                .andExpect(jsonPath("$.code").value("not.login"))
        ;
        // 缺少用户名
        this.mockMvc.perform(post("/bookCorner/v1/users/3693")
                        .contentType(MediaType.APPLICATION_JSON_UTF8) // 设置报文头
                        .header("Authorization", "Bearer " + tokenNotBinded)
//                        .param("userNo", "3693")
                        .param("nickName", "阿黄")
//                        .param("userName", "黄实")
                        .param("headImgUrl", "")

        )
                .andDo(print())// 打印测试过程
                .andExpect(status().is4xxClientError())//判断返回
//                .andExpect(jsonPath("$.code").value("not.login"))
        ;
    }
    /**
     * 用户绑定-错误的员工号
     * @params  []
     * @return: void
     * @Author: ahuang
     * @Date: 2018/7/12 下午10:05
     */
    @Test
    public void custBindErrorUserNo() throws Exception {
        this.mockMvc.perform(post("/bookCorner/v1/users/10000")
                        .contentType(MediaType.APPLICATION_JSON_UTF8) // 设置报文头
                .header("Authorization", "Bearer " + tokenNotBinded)
                        .param("userNo", "10000")
                        .param("nickName", "阿黄")
                        .param("userName", "黄实")
                        .param("headImgUrl", "")

        )
                .andDo(print())// 打印测试过程
                .andExpect(status().is5xxServerError())//判断返回
                .andExpect(jsonPath("$.code").value("user.not.allowed"))
        ;
    }
    /**
     * 用户绑定-错误的员工姓名
     * @params  []
     * @return: void
     * @Author: ahuang
     * @Date: 2018/7/12 下午10:05
     */
    @Test
    public void custBindErrorUserName() throws Exception {
        this.mockMvc.perform(post("/bookCorner/v1/users/3693")
                .contentType(MediaType.APPLICATION_JSON_UTF8) // 设置报文头
                .header("Authorization", "Bearer " + tokenNotBinded)
                .param("userNo", "3693")
                .param("nickName", "阿黄")
                .param("userName", "黄实1")
                .param("headImgUrl", "")

        )
                .andDo(print())// 打印测试过程
                .andExpect(status().is5xxServerError())//判断返回
                .andExpect(jsonPath("$.code").value("user.name.not.match"))
        ;
    }
    /**
     * 测试无token用户绑定
     * @params  []
     * @return: void
     * @Author: ahuang
     * @Date: 2018/7/12 下午10:05
     */
    @Test
    public void custBindIsNoToken() throws Exception {
        //已登陆用户尝试绑定-成功
        this.mockMvc.perform(post("/bookCorner/v1/users/3693")
                        .contentType(MediaType.APPLICATION_JSON_UTF8) // 设置报文头
//                .header("Authorization", "Bearer " + tokenNotBinded)
                        .param("userNo", "3693")
                        .param("nickName", "阿黄")
                        .param("userName", "黄实")
                        .param("headImgUrl", "")

        )
                .andDo(print())// 打印测试过程
                .andExpect(status().is4xxClientError())//判断返回
                .andExpect(jsonPath("$.code").value("not.login"))
        ;
    }
    /**
     * 测试有token用户未绑定
     * @params  []
     * @return: void
     * @Author: ahuang
     * @Date: 2018/7/12 下午10:05
     */
    @Test
    public void custBindIsNotBindedToken() throws Exception {
        //已登陆用户尝试绑定-成功
        this.mockMvc.perform(post("/bookCorner/v1/users/3693")
                .contentType(MediaType.APPLICATION_JSON_UTF8) // 设置报文头
                .header("Authorization", "Bearer " + tokenNotBinded)
                .param("userNo", "3693")
                .param("nickName", "阿黄")
                .param("userName", "黄实")
                .param("headImgUrl", "")

        )
                .andDo(print())// 打印测试过程
                .andExpect(status().isOk())//判断返回200
                .andExpect(jsonPath("$.isBinded").value("1"))
                .andExpect(jsonPath("$.token").isString())
                .andExpect(jsonPath("$.userNo").isString())
                .andExpect(jsonPath("$.userName").isString())
        ;
    }
}
