package com.xg.cctv.common;

/**
 * @Author Lorenzo
 * @Date 2020/1/15 15:43
 */
public class StatusCode {

    // 401 未登陆 Not logged in
    public static final Integer NOT_LOGGED_IN = 401;
    // 403 没有权限 Denial of service
    public static final Integer DENIAL_OF_SERVICE = 403;

    // 服务器异常Server exception
    public static final Integer SERVER_EXCEPTION = 500;
    // 登陆异常
    public static final Integer USER_LOGIN_ERROR = 501;

    // 账号已被锁定
    public static final Integer USER_ACCOUNT_LOCKED = 502;
    // 密码过期
    public static final Integer PASSWRD_HAS_OUTTIME = 503;
    // 账号过期
    public static final Integer USER_HAS_OUTTIME = 504;
    // 账号被禁用
    public static final Integer USER_ACCOUNT_FORBIDDEN = 505;
    // 登陆失败
    public static final Integer USER_LOGIN_FAILED = 506;
}
