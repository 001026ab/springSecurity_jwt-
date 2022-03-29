package com.zgr.security.jwt.springsecurity_jwt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zgr.security.jwt.springsecurity_jwt.entity.UserRoles;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zgr
 * @version 1.0
 * @date 2022/3/28 15:00
 */

@Mapper
public interface UserRoleMapper extends BaseMapper<UserRoles> {
}
