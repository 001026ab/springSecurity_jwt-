package com.zgr.security.jwt.springsecurity_jwt.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zgr.security.jwt.springsecurity_jwt.entity.UserRoles;
import com.zgr.security.jwt.springsecurity_jwt.mapper.UserRoleMapper;
import com.zgr.security.jwt.springsecurity_jwt.service.UserRoleService;
import org.springframework.stereotype.Service;

/**
 * @author zgr
 * @version 1.0
 * @date 2022/3/28 14:52
 */

@Service
public class UserRoleServiceImpl implements UserRoleService {
    private final UserRoleMapper userRoleMapper;

    public UserRoleServiceImpl(UserRoleMapper userRoleMapper) {
        this.userRoleMapper = userRoleMapper;
    }

    @Override
    public UserRoles getUserRoleById(Long id) {
        return userRoleMapper.selectOne(new QueryWrapper<UserRoles>().lambda().eq(UserRoles::getUserId,id));
    }
}
