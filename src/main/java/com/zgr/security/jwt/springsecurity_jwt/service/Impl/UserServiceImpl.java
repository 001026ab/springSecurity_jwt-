package com.zgr.security.jwt.springsecurity_jwt.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zgr.security.jwt.springsecurity_jwt.entity.User;
import com.zgr.security.jwt.springsecurity_jwt.mapper.UserMapper;
import com.zgr.security.jwt.springsecurity_jwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zgr
 * @version 1.0
 * @date 2022/3/28 14:42
 */

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User getUserByName(String userName) {
        return userMapper.selectOne(new QueryWrapper<User>().lambda().eq(User::getUsername, userName));
    }

    @Override
    public int save(User user) {
        return userMapper.insert(user);
    }
}
