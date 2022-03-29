package com.zgr.security.jwt.springsecurity_jwt.service.Impl;


import com.zgr.security.jwt.springsecurity_jwt.entity.User;
import com.zgr.security.jwt.springsecurity_jwt.service.AuthService;
import com.zgr.security.jwt.springsecurity_jwt.service.UserService;
import com.zgr.security.jwt.springsecurity_jwt.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.util.ArrayList;

/**
 * @www.codesheep.cn 20190312
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private UserService userService;


    @Override
    public String login(String username, String password) {
        User userByName = userService.getUserByName(username);
        if (userByName == null) {
            return "账号错误";
        }
        //校验密码是否正确
        //进行密码的比较，将用户输入的密码进行加密与数据库查出的密码进行比较是否相同
        if (BCrypt.checkpw(password, userByName.getPassword())) {
            final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            final String token = jwtTokenUtil.generateToken(userDetails);
            return token;
        }
        return "密码错误";
    }


    @Override
    public User register(User userToAdd) {

        final String username = userToAdd.getUsername();
        if (userService.getUserByName(username) != null) {
            return null;
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        final String rawPassword = userToAdd.getPassword();
        userToAdd.setPassword(encoder.encode(rawPassword));
        userService.save(userToAdd);
        return userToAdd;
    }
}
