package com.zgr.security.jwt.springsecurity_jwt.service.Impl;

import com.zgr.security.jwt.springsecurity_jwt.entity.Role;
import com.zgr.security.jwt.springsecurity_jwt.mapper.RoleMapper;
import com.zgr.security.jwt.springsecurity_jwt.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zgr
 * @version 1.0
 * @date 2022/3/28 14:52
 */

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleMapper roleMapper;

    public RoleServiceImpl(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    @Override
    public Role getRoleById(Long roleId) {
        return roleMapper.selectById(roleId);
    }
}
