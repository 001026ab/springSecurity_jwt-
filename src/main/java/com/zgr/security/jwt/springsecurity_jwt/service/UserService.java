package com.zgr.security.jwt.springsecurity_jwt.service;

import com.zgr.security.jwt.springsecurity_jwt.entity.User;
import org.springframework.stereotype.Service;

/**
 * @author zgr
 * @version 1.0
 * @date 2022/3/28 14:41
 */


public interface UserService {
    /**
     *
     * @param userName
     * @return
     */
     User getUserByName(String userName);

    int save(User user);
}
