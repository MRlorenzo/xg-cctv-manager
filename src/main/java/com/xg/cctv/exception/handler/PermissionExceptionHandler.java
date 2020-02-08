package com.xg.cctv.exception.handler;

import com.xg.cctv.common.StatusCode;
import com.xg.cctv.common.util.R;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice //该注解定义全局异常处理类
@ResponseBody
@Order(value = 1)
public class PermissionExceptionHandler {
    private static Logger logger = LoggerFactory.getLogger(PermissionExceptionHandler.class);

    /**
     * 认证异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = UnauthorizedException.class)
    public R handleUnauthorizedException( UnauthorizedException e){
        logger.error("权限认证异常");
        return R.error()
                .put("code" , StatusCode.DENIAL_OF_SERVICE)
                .put("msg" , e.getMessage());
    }


    /**
     * 账号或者密码错误
     * @param e
     * @return
     */
    @ExceptionHandler(value = IncorrectCredentialsException.class)
    public R handIncorrectCredentialsException( IncorrectCredentialsException e){
        logger.error("账号或者密码错误");
        return R.error()
                .put("code" , StatusCode.USER_LOGIN_ERROR)
                .put("msg" , "账号或者密码错误");
    }

    /**
     * 未知异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = UnknownAccountException.class)
    public R handUnknownAccountException( UnknownAccountException e){
        logger.error("UnknownAccountException" , e);
        return R.error()
                .put("code" , StatusCode.USER_LOGIN_ERROR)
                .put("msg" , e.getMessage());
    }

}
