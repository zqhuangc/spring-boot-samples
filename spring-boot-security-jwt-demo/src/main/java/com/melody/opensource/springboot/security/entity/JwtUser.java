package com.melody.opensource.springboot.security.entity;

import com.melody.opensource.springboot.web.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * 用户信息
 *
 * @author zqhuangc
 */
public class JwtUser implements UserDetails {

    private Integer id;
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public JwtUser() {
    }

    /**
     * 通过自定义 user 对象创建 jwtUser
     * @param user
     */
    public JwtUser(User user){
        username = user.getUsername();
        password = user.getPassword();
        authorities = user.getRoles();
    }


    /**
     * 返回授予用户的权限。不能返回 null
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    /**
     * 返回用于验证用户身份的密码
     * @return
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * 返回用于验证用户的用户名.不能返回 null
     * @return
     */
    @Override
    public String getUsername() {
        return username;
    }

    /**
     * 指示用户的帐户是否已过期。过期的帐户无法验证
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 指示用户是被锁定还是未被锁定。无法对锁定的用户进行身份验证
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 指示用户的凭据(密码)是否已过期。过期的凭证阻止身份验证
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 指示用户是启用还是禁用。无法验证已禁用的用户
     * @return
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "JwtUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", authorities=" + authorities +
                '}';
    }
}
