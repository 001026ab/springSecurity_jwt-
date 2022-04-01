package com.zgr.security.jwt.springsecurity_jwt.returnResult;

/**
 * @author zgr
 * @version 1.0
 * @date 2022/3/29 18:34
 * 结果返回工具类
 */


public class ResultUtil {
    public static <T> MyResult<T> success(T data) {
        return new MyResult<>(true, null, null, data);
    }

    public static <T> MyResult<T> success() {
        return new MyResult<>(true, null, null, null);
    }

    public static <T> MyResult<T> error(String errorCode, String errorMsg, T data) {
        return new MyResult<>(false, errorCode, errorMsg, data);
    }

    public static <T> MyResult<T> error(String data) {
        return error( "400", data, null);
    }


}
