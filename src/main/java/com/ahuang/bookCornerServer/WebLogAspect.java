package com.ahuang.bookCornerServer;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import lombok.extern.slf4j.Slf4j;

/**
 * 
* @ClassName: WebLogAspect
* @Description: 日志打印切片
* @author ahuang
* @date 2018年6月9日 下午7:32:27
* @version V1.0
 */
@Slf4j
@Aspect
@Order(-99) // 控制多个Aspect的执行顺序，越小越先执行
@Component
public class WebLogAspect {
	/**
	 * 
	* @Title: logPointCut
	* @Description: 正常输入返回切入点
	* @param     设定文件
	* @return void    返回类型
	* @author ahuang  
	* @date 2018年6月9日 下午8:15:39
	* @version V1.0
	* @throws
	 */
	@Pointcut("execution(public * com.ahuang.bookCornerServer.controller.*.*(..))")//两个..代表所有子目录，最后括号里的两个..代表所有参数
    public void logPointCut() {}
	
	/**
	 * 
	* @Title: logExceptionCut
	* @Description: 异常返回切入点
	* @param     设定文件
	* @return void    返回类型
	* @author ahuang  
	* @date 2018年6月9日 下午8:16:11
	* @version V1.0
	* @throws
	 */
	@Pointcut("execution(public * com.ahuang.bookCornerServer.ExceptionAdvice.*(..))")//两个..代表所有子目录，最后括号里的两个..代表所有参数
    public void logExceptionCut() {}

	/**
	 * 
	* @Title: doBefore
	* @Description: 打印接口请求报文
	* @param @param joinPoint    设定文件
	* @return void    返回类型
	* @author ahuang  
	* @date 2018年6月9日 下午8:16:31
	* @version V1.0
	* @throws
	 */
    @Before("logPointCut()")
    public void doBefore(JoinPoint joinPoint){
    	// 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        log.info("RequestBody>>>>>>>:[" + request.getMethod()+ "]" 
        + Arrays.toString(joinPoint.getArgs())
        + ", ToURL:" + request.getRequestURL().toString()) ;
    }

    /**
     * 
    * @Title: doAfter
    * @Description: 正常返回打印接口返回报文
    * @param @param res    设定文件
    * @return void    返回类型
    * @author ahuang  
    * @date 2018年6月9日 下午8:16:55
    * @version V1.0
    * @throws
     */
    @AfterReturning(returning = "res", pointcut = "logPointCut()")// returning的值和doAfterReturning的参数名一致
    public void doAfter(Object res){
        log.info("ResponseBody<<<<<<<:" + res);
    }
    
    /**
     * 
    * @Title: doAfterException
    * @Description: 异常返回打印返回报文
    * @param @param res    设定文件
    * @return void    返回类型
    * @author ahuang  
    * @date 2018年6月9日 下午8:17:12
    * @version V1.0
    * @throws
     */
    @AfterReturning(returning = "res", pointcut = "logExceptionCut()")// returning的值和doAfterReturning的参数名一致
    public void doAfterException(Object res){
        log.info("ResponseBody<<<<<<<:" + res);
    }
}
