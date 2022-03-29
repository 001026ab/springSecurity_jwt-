package com.zgr.security.jwt.springsecurity_jwt.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author zgr
 * @version 1.0
 * @date 2022/3/28 14:53
 */

@Data
@TableName(value = "user_roles")
public class UserRoles implements Serializable {
    private Long userId;
    private Long rolesId;
}
