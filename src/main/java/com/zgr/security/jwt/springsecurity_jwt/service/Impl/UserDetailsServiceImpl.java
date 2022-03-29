package com.zgr.security.jwt.springsecurity_jwt.service.Impl;

import com.zgr.security.jwt.springsecurity_jwt.entity.JwtUser;
import com.zgr.security.jwt.springsecurity_jwt.entity.Role;
import com.zgr.security.jwt.springsecurity_jwt.entity.User;
import com.zgr.security.jwt.springsecurity_jwt.entity.UserRoles;
import com.zgr.security.jwt.springsecurity_jwt.service.RoleService;
import com.zgr.security.jwt.springsecurity_jwt.service.UserRoleService;
import com.zgr.security.jwt.springsecurity_jwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zgr
 * @version 1.0
 * @date 2022/3/28 14:41
 * 获取用户权限
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserRoleService userRoleService;

    /**
     * 这里返回的UserDetails其实就是
     * 返回实现了UserDetails接口的实体类
     * 在这个实体类中设置了用户权限的列表
     *
     * @param s
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userService.getUserByName(s);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        JwtUser jwtUser = new JwtUser();
        jwtUser.setUsername(user.getUsername());
        jwtUser.setPassword(user.getPassword());
        //获取用户权限
        jwtUser.setRoles(authorities(s));
        System.out.println("用户权限列表：" + jwtUser.getRoles());
        return jwtUser;
    }

    /**
     * 一个用户有多个权限-获取用户权限列表,根据自己的数据库逻辑获取划分
     *
     * @param
     * @return
     */
    private List<String> authorities(String userName) {
        User userByName = userService.getUserByName(userName);
        if (userByName == null) {
            return null;
        }
        UserRoles userRoleById = userRoleService.getUserRoleById(userByName.getId());
        if (userRoleById == null) {
            return null;
        }

        Role roleById = roleService.getRoleById(userRoleById.getRolesId());
        if (roleById == null) {
            return null;
        }
        String[] split = roleById.getName().split(";");
        return new ArrayList<>(Arrays.asList(split));
    }
}
