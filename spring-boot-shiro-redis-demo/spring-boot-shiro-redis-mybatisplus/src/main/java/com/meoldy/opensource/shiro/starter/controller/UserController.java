package com.meoldy.opensource.shiro.starter.controller;


import com.meoldy.opensource.shiro.starter.common.ResponseResult;
import com.meoldy.opensource.shiro.starter.entity.User;
import com.meoldy.opensource.shiro.starter.service.UserService;
import com.meoldy.opensource.shiro.starter.util.ValidationUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author zqhuagngc
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    /**
     * mybatis plus 测试
     */
    @GetMapping(value = "{id}")
    public Object test(@PathVariable("id") Long id) {
        return userService.getById(1L);
    }


    /**
     * 实体 测试
     */
    @ResponseBody
    @PostMapping("/register")
    public ResponseResult register(User user) {
        ValidationUtil.ValidResult validResult = ValidationUtil.validateBean(user);
        if (validResult.hasErrors()) {
            return ResponseResult.fail(validResult.getErrors());
        }

        return ResponseResult.ok(user);
    }
}
