package com.zgr.security.jwt.springsecurity_jwt.service;

import com.zgr.security.jwt.springsecurity_jwt.entity.Role;
import org.springframework.stereotype.Service;

/**
 * @author zgr
 * @version 1.0
 * @date 2022/3/28 14:51
 */


public interface RoleService {
    Role getRoleById(Long roleId);
}
