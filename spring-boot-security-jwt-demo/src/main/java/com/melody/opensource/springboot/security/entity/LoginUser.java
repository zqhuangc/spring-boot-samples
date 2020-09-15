package com.melody.opensource.springboot.security.entity;

import lombok.Data;

/**
 * 用户登录信息
 *
 * @author zqhuangc
 */
@Data
public class LoginUser {

    private String username;
    private String password;
    private Boolean rememberme;
}
