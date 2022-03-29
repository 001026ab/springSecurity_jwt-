package com.zgr.security.jwt.springsecurity_jwt.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author zgr
 * @version 1.0
 * @date 2022/3/28 14:34
 * jwt获取权限列表，
 * 在实现了UserDetailsService接口的类中的loadUserByUsername方法中使用
 */

@Data
public class JwtUser implements UserDetails {

    private Long id;

    private String username;

    private String password;

    private List<String> roles;

    /**
     * 将roles中的权限弄进springSecurity中，实现鉴权中权限的获取
     *
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }


}
