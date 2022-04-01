package com.zgr.security.jwt.springsecurity_jwt.service.Impl;


import com.zgr.security.jwt.springsecurity_jwt.entity.User;
import com.zgr.security.jwt.springsecurity_jwt.returnResult.MyResult;
import com.zgr.security.jwt.springsecurity_jwt.returnResult.ResultUtil;
import com.zgr.security.jwt.springsecurity_jwt.service.AuthService;
import com.zgr.security.jwt.springsecurity_jwt.service.UserService;
import com.zgr.security.jwt.springsecurity_jwt.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author ufi
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
    public MyResult<String> login(String username, String password) {
        User userByName = userService.getUserByName(username);
        if (userByName == null) {
            return ResultUtil.error("账号错误") ;
        }
        //校验密码是否正确
        //进行密码的比较，将用户输入的密码进行加密与数据库查出的密码进行比较是否相同
        if (BCrypt.checkpw(password, userByName.getPassword())) {
            final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            final String token = jwtTokenUtil.generateToken(userDetails);
            return ResultUtil.success(token);
        }
        return ResultUtil.error("密码错误");
    }


    @Override
    public MyResult<User> register(User userToAdd) {

        final String username = userToAdd.getUsername();
        //username不允许同名
        if (userService.getUserByName(username) != null) {
            return ResultUtil.error("该用户已经存在");
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        final String rawPassword = userToAdd.getPassword();
        userToAdd.setPassword(encoder.encode(rawPassword));
        userService.save(userToAdd);
        return ResultUtil.success(userToAdd);
    }
}
