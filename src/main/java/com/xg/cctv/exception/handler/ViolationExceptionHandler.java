package com.xg.cctv.exception.handler;

import com.xg.cctv.common.StatusCode;
import com.xg.cctv.common.util.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

@ControllerAdvice //该注解定义全局异常处理类
@ResponseBody
@Order(value = 2)
public class ViolationExceptionHandler {
    private static Logger logger = LoggerFactory.getLogger(ViolationExceptionHandler.class);

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public R handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        BindingResult bindingResult = e.getBindingResult();
        logger.error("表单校验异常");
        return R.error()
                .put("code" , StatusCode.SERVER_EXCEPTION)
                .put("msg" , "表单校验异常")
                .put("errors", bindingResult.getAllErrors());
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    public R handleConstraintViolationException(ConstraintViolationException e){
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        logger.error("表单校验异常");
        return R.error()
                .put("code" , StatusCode.SERVER_EXCEPTION)
                .put("msg" , "表单校验异常")
                .put("errors" , constraintViolations);
    }

}
