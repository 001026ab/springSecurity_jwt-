package com.zgr.security.jwt.springsecurity_jwt.service;


import com.zgr.security.jwt.springsecurity_jwt.entity.User;
import com.zgr.security.jwt.springsecurity_jwt.returnResult.MyResult;

/**
 * @author ufi
 * @www.codesheep.cn 20190312
 */
public interface AuthService {
    /**
     * 注册
     *
     * @param userToAdd
     * @return
     */
    MyResult<User> register(User userToAdd);

    /**
     * 登录
     *
     * @param username
     * @param password
     * @return
     */
    MyResult<String> login(String username, String password);
}
