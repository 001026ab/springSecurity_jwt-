package com.zgr.security.jwt.springsecurity_jwt.service;


import com.zgr.security.jwt.springsecurity_jwt.entity.User;

/**
 * @www.codesheep.cn
 * 20190312
 */
public interface AuthService {

    User register(User userToAdd );
    String login( String username, String password );
}
