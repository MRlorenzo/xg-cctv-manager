package com.xg.cctv.exception.handler;

import com.xg.cctv.common.StatusCode;
import com.xg.cctv.common.util.R;
import com.xg.cctv.exception.RRException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;


@ControllerAdvice //该注解定义全局异常处理类
@ResponseBody
@Order(value = 99) // 一般来讲这个处理器最后处理。
public class GlobalExceptionHandler {
    private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * RRException
     * @param e
     * @return
     */
    @ExceptionHandler(value = RRException.class)
    public R handleRRException(RRException e){
        logger.error(e.getMsg());
        return R.error()
                .put("code" , e.getCode())
                .put("msg" , e.getMsg());
    }

    @ExceptionHandler(value = IOException.class)
    public R handleIOException(IOException e){
        logger.error("输入输出流异常",e);
        return R.error()
                .put("code" , StatusCode.SERVER_EXCEPTION)
                .put("msg" , e.getMessage());
    }

    /**
     * 未知异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public R handleUnkonwException(Exception e){
        logger.error("未知异常:" , e);
        return R.error().put("msg" , e.getMessage());
    }

}
