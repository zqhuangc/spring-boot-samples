package com.meoldy.opensource.shiro.starter.service.impl;

import com.meoldy.opensource.shiro.starter.entity.User;
import com.meoldy.opensource.shiro.starter.mapper.UserMapper;
import com.meoldy.opensource.shiro.starter.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 服务实现类
 * @author zqhuangc
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
