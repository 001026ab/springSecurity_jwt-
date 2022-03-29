package com.zgr.security.jwt.springsecurity_jwt.entity;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author ufi
 * @www.codesheep.cn 20190312
 */

@Data
public class User implements Serializable {
    private Long id;

    private String username;

    private String password;



}
