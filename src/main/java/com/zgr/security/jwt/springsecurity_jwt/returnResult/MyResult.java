package com.zgr.security.jwt.springsecurity_jwt.returnResult;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zgr
 * @version 1.0
 * @date 2022/3/29 18:33
 * 返回结果处理类
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyResult<T> {
    /**
     * 结果状态 true 成功 false 失败
     */
    private boolean resultStatus;
    /**
     * 如果错误则返回 错误代码
     */
    private String errorCode;
    /**
     * 错误详情描述
     */
    private String errorMessage;
    /**
     * 结果为true 时 业务数据 可为null
     */
    private T data;
}
