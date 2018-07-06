package com.ahuang.bookCornerServer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public CommonResponse<?> handleException(Exception e) {
		log.debug("into handleException");
		e.printStackTrace();
		CommonResponse<?> res = new CommonResponse<>(true);
		res.setMessage(e.getMessage());
		res.setCode(e.getMessage());
		res.setType(Exception.class.getName());
		log.debug("out handleException");
        return res;
    }
	
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
        return new ResponseEntity(res, HttpStatus.INTERNAL_SERVER_ERROR);
    }
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
    public CommonResponse<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		log.debug("into handleMethodArgumentNotValidException");
		e.printStackTrace();
		CommonResponse<?> res = new CommonResponse<>(true);
		res.setMessage(e.getBindingResult().getFieldError().getField() + ":" + e.getBindingResult().getFieldError().getDefaultMessage());
		res.setCode(e.getBindingResult().getFieldError().getCode());
		res.setType(MethodArgumentNotValidException.class.getName());
		log.debug("out handleMethodArgumentNotValidException");
        return res;
    }
}
