package com.ahuang.bookCornerServer;

import com.ahuang.bookCornerServer.exception.AuthException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ahuang.bookCornerServer.controller.req.CommonResponse;
import com.ahuang.bookCornerServer.exception.BaseException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
@ResponseBody
public class ExceptionAdvice {
	@ExceptionHandler(Exception.class)
    public ResponseEntity<CommonResponse<?>> handleException(Exception e) {
		log.debug("into handleException");
		e.printStackTrace();
		CommonResponse<?> res = new CommonResponse<>(true);
		res.setMessage(e.getMessage());
		res.setCode(e.getMessage());
		res.setType(Exception.class.getName());
		log.debug("out handleException");
        return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
    * 一般失败处理
    * @params  [e]
    * @return: org.springframework.http.ResponseEntity<com.ahuang.bookCornerServer.controller.req.CommonResponse<?>>
    * @Author: ahuang
    * @Date: 2018/7/7 下午10:15
    */
	@ExceptionHandler(BaseException.class)
    public ResponseEntity<CommonResponse<?>> handleBaseException(BaseException e) {
		log.debug("into handleBaseException");
		log.error(e.getCode() + "" + e.getMsg());
		log.error(e.getMessage(), e);
//		e.printStackTrace();
		CommonResponse<?> res = new CommonResponse<>(true);
		res.setMessage(e.getMsg());
		res.setCode(e.getCode());
		res.setType(BaseException.class.getName());
		log.debug("out handleBaseException");
        return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
    * 身份校验失败处理
    * @params  [e]
    * @return: org.springframework.http.ResponseEntity<com.ahuang.bookCornerServer.controller.req.CommonResponse<?>>
    * @Author: ahuang
    * @Date: 2018/7/7 下午10:16
    */
    @ExceptionHandler(AuthException.class)
    public ResponseEntity<CommonResponse<?>> handleAuthException(BaseException e) {
        log.debug("into handleAuthException");
        log.error(e.getCode() + "" + e.getMsg());
        log.error(e.getMessage(), e);
        CommonResponse<?> res = new CommonResponse<>(true);
        res.setMessage(e.getMsg());
        res.setCode(e.getCode());
        res.setType(AuthException.class.getName());
        log.debug("out handleAuthException");
        return new ResponseEntity<>(res, HttpStatus.FORBIDDEN);
    }

    /**
    * 校验失败处理
    * @params  [e]
    * @return: com.ahuang.bookCornerServer.controller.req.CommonResponse<?>
    * @Author: ahuang
    * @Date: 2018/7/7 下午10:15
    */
	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CommonResponse<?>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		log.debug("into handleMethodArgumentNotValidException");
		e.printStackTrace();
		CommonResponse<?> res = new CommonResponse<>(true);
		res.setMessage(e.getBindingResult().getFieldError().getField() + ":" + e.getBindingResult().getFieldError().getDefaultMessage());
		res.setCode(e.getBindingResult().getFieldError().getCode());
		res.setType(MethodArgumentNotValidException.class.getName());
		log.debug("out handleMethodArgumentNotValidException");
        return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<CommonResponse<?>> handleMethodArgumentNotValidException(BindException e) {
        log.debug("into handleMethodArgumentNotValidException");
        e.printStackTrace();
        CommonResponse<?> res = new CommonResponse<>(true);
        res.setMessage(e.getBindingResult().getFieldError().getField() + ":" + e.getBindingResult().getFieldError().getDefaultMessage());
        res.setCode(e.getBindingResult().getFieldError().getCode());
        res.setType(BindException.class.getName());
        log.debug("out handleMethodArgumentNotValidException");
        return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
    }
}
