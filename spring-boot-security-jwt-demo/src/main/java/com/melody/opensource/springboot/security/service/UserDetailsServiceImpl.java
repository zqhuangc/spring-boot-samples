package com.melody.opensource.springboot.security.service;

import com.melody.opensource.springboot.security.entity.JwtUser;
import com.melody.opensource.springboot.security.filter.JwtAuthorizationFilter;
import com.melody.opensource.springboot.web.entity.User;
import com.melody.opensource.springboot.web.service.UserService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author zqhuangc
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 通过用户名从数据库获取用户信息
        User user = userService.getUserByUserName(username);

        return new JwtUser(user);
    }
}
