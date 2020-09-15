package com.melody.opensource.springboot.web.controller;

import com.melody.opensource.springboot.security.entity.CurrentUser;
import com.melody.opensource.springboot.web.entity.User;
import com.melody.opensource.springboot.web.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author zqhuangc
 */
@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    private final CurrentUser currentUser;

    public UserController(UserService userService, CurrentUser currentUser) {
        this.userService = userService;
        this.currentUser = currentUser;
    }

    /**
     * 可访问的角色为 ROLE_DEV、ROLE_PM
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/get/users")
    @PreAuthorize("hasAnyRole('ROLE_DEV','ROLE_PM')")
    public ResponseEntity<Page<User>> getAllUser(@RequestParam(value = "pageNum", defaultValue = "0") int pageNum
            , @RequestParam(value = "pageSize", defaultValue = "10") int pageSize){
        System.out.println("当前访问该接口的用户为：" + currentUser.getCurrentUser().toString());
        Page<User> allUser = userService.getAllUser(pageNum, pageSize);
        return ResponseEntity.ok().body(allUser);
    }

    /**
     * 可访问角色为 ROLE_ADMIN
     * @param username
     * @return
     */
    @DeleteMapping("/delete/user")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<User> deleteUserByName(@RequestParam("username") String username) {
        userService.deleteUserByUserName(username);
        return ResponseEntity.ok().build();
    }

}
