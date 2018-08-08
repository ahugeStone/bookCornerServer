package com.ahuang.bookCornerServer;

import java.lang.reflect.Modifier;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import com.ahuang.bookCornerServer.entity.BookBaseInfoEntity;
import com.ahuang.bookCornerServer.entity.CustBindUsersEntity;
import com.ahuang.bookCornerServer.entity.MessageBaseInfoEntity;
import com.ahuang.bookCornerServer.exception.BaseException;
import com.ahuang.bookCornerServer.mapper.BookBaseInfoMapper;
import com.ahuang.bookCornerServer.mapper.MessageBaseInfoMapper;
import com.ahuang.bookCornerServer.servise.BookService;
import com.ahuang.bookCornerServer.servise.CommonService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import lombok.extern.slf4j.Slf4j;

/**
 * @author ahuang
 * @version V1.0
 * @ClassName: WebLogAspect
 * @Description: 日志打印切片
 * @date 2018年6月9日 下午7:32:27
 */
@Slf4j
@Aspect
@Order(-99) // 控制多个Aspect的执行顺序，越小越先执行
@Component
public class WebLogAspect {
    @Autowired
    private BookService bookService;

    @Autowired
    private CommonService commonService;

    /**
     * @return void    返回类型
     * @Title: logPointCut
     * @Description: 正常输入返回切入点
     * @author ahuang
     * @date 2018年6月9日 下午8:15:39
     * @version V1.0
     */
    @Pointcut("execution(public * com.ahuang.bookCornerServer.controller.*.*(..))")//两个..代表所有子目录，最后括号里的两个..代表所有参数
    public void logPointCut() {
    }


    /**
     * @return void    返回类型
     * @Title: logExceptionCut
     * @Description: 异常返回切入点
     * @author ahuang
     * @date 2018年6月9日 下午8:16:11
     * @version V1.0
     */
    @Pointcut("execution(public * com.ahuang.bookCornerServer.ExceptionAdvice.*(..))")//两个..代表所有子目录，最后括号里的两个..代表所有参数
    public void logExceptionCut() {
    }

    /**
     * @param joinPoint 设定文件
     * @return void    返回类型
     * @Title: doBefore
     * @Description: 打印接口请求报文
     * @author ahuang
     * @date 2018年6月9日 下午8:16:31
     * @version V1.0
     */
    @Before("logPointCut()")
    public void doBefore(JoinPoint joinPoint) {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        log.info("RequestBody>>>>>>>:[" + request.getMethod() + "]"
                + Arrays.toString(joinPoint.getArgs())
                + ", ToURL:" + request.getRequestURL().toString());
    }

    /**
     * @param res 设定文件
     * @return void    返回类型
     * @Title: doAfter
     * @Description: 正常返回打印接口返回报文
     * @author ahuang
     * @date 2018年6月9日 下午8:16:55
     * @version V1.0
     */
    @AfterReturning(returning = "res", pointcut = "logPointCut()")// returning的值和doAfterReturning的参数名一致
    public void doAfter(Object res) {
        log.info("ResponseBody<<<<<<<:" + res);
    }

    /**
     * @param res 设定文件
     * @return void    返回类型
     * @Title: doAfterException
     * @Description: 异常返回打印返回报文
     * @author ahuang
     * @date 2018年6月9日 下午8:17:12
     * @version V1.0
     */
    @AfterReturning(returning = "res", pointcut = "logExceptionCut()")// returning的值和doAfterReturning的参数名一致
    public void doAfterException(Object res) {
        log.info("ResponseBody<<<<<<<:" + res);
    }


    /**
     * @return void    返回类型
     * @Title: insertMessageInfo
     * @Description: 在用户借、还、评论操作时往公告栏表中插入一条记录
     * @author lct
     * @date 2018年8月3日 下午5:16:11
     * @version V1.0
     */
    @Pointcut("execution(* com.ahuang.bookCornerServer.servise.impl.BookServiceImpl.addCommentRecord(..))||execution(* com.ahuang.bookCornerServer.servise.impl.BookServiceImpl.borrowBookById(..))||execution(* com.ahuang.bookCornerServer.servise.impl.BookServiceImpl.returnBookById(..))")
//两个..代表所有子目录，最后括号里的两个..代表所有参数
    public void insertMessageInfo() {
    }


   /* @AfterReturning(returning = "res", pointcut = "insertMessageInfo()")
    public void bbb(Object res){

        System.out.println("+++++++++++++++++++++++++++++"+res);}*/

    @After("insertMessageInfo()")
    public void eee(JoinPoint joinPoint) throws BaseException {

        Object[] args = joinPoint.getArgs();
        try {
            //评论 传进来三个参数 bookId CustBindUsersEntity comment
            if (args.length == 3) {
                for (int i = 0; i < args.length; i++) {
                    System.out.println("第" + (i + 1) + "个参数为:" + args[i]);
                }

                int bookId = (int) args[0];
                CustBindUsersEntity bindUser = (CustBindUsersEntity) args[1];
                String operationContent = (String) args[2];


                String openId = bindUser.getOpenid();
                Map<String, Object> param = new HashMap<>();
                param.put("id", bookId);
                param.put("openid", openId);
                BookBaseInfoEntity bo = bookService.queryBookDetailById(param);
                String bookName = bo.getBookName();

                String userName = bindUser.getUserName();
                String operationType = "2";

                MessageBaseInfoEntity m = bookService.insertMessage(bookId, bookName, operationContent, operationType, userName);


                log.info("插入信息表成功：" + m);
            }

            //借、还 传进来两个参数

            else if (args.length == 2) {
                for (int i = 0; i < args.length; i++) {
                    System.out.println("第" + (i + 1) + "个参数为:" + args[i]);
                }
                int bookId = (int) args[0];

                String openid = (String) args[1];
                Map<String, Object> param = new HashMap<>();
                param.put("id", bookId);
                param.put("openid", openid);
                BookBaseInfoEntity bo1 = bookService.queryBookDetailById(param);

                String bookName = bo1.getBookName();

                CustBindUsersEntity user = commonService.getUserByOpenid(openid);
                String userName = user.getUserName();


                //操作类型(0是不在库，1是在库)(借为0，还为1)
                String operationType = "";
                if (bo1.getBookStatus().equals("1") ) {
                    operationType = "1";
                } else if (bo1.getBookStatus().equals("0") ) {
                    operationType = "0";
                }

                //操作内容（评论内容，借/还为空）
                String operationContent = "";

                MessageBaseInfoEntity m = bookService.insertMessage(bookId, bookName, operationContent, operationType, userName);


                log.info("插入信息表成功：" + m);

            }
        } catch(Exception e) {
            e.printStackTrace();
            throw new BaseException("insertMessage.failed", "信息没有插入成功");
        }


    }


}
