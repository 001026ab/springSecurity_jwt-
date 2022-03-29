package com.zgr.security.jwt.springsecurity_jwt.service;

import com.zgr.security.jwt.springsecurity_jwt.entity.UserRoles;

/**
 * @author zgr
 * @version 1.0
 * @date 2022/3/28 14:51
 */


public interface UserRoleService {
    UserRoles getUserRoleById(Long id);
}
