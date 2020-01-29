package com.xg.cctv.exception.handler;

import com.xg.cctv.common.util.R;
import com.xg.cctv.exception.RRException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice //该注解定义全局异常处理类
@ResponseBody
public class GlobalExceptionHandler {
    private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * RRException
     * @param e
     * @return
     */
    @ExceptionHandler(value = RRException.class)
    public R handleRRException(RRException e){
        logger.info("RRException:" , e);
        return R.error()
                .put("code" , e.getCode())
                .put("msg" , e.getMsg());
    }

    /**
     * 未知异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public R handleUnkonwException(Exception e){
        logger.info("未知异常:" , e);
        return R.error().put("msg" , e.getMessage());
    }

}
