package com.zgr.security.jwt.springsecurity_jwt.comm;

/**
 * @author zgr
 * @version 1.0
 * @date 2022/3/28 14:23
 * jwtUtil使用
 */


public class Const {
    // 5天(以毫秒ms计)
    public static final long EXPIRATION_TIME = 432_000_000;
    //1分钟
   // public static final long EXPIRATION_TIME = 1*60;
    // JWT密码
    public static final String SECRET = "CodeSheepSecret";
    // Token前缀
    public static final String TOKEN_PREFIX = "Bearer";
    // 存放Token的Header Key
    public static final String HEADER_STRING = "token";
}
