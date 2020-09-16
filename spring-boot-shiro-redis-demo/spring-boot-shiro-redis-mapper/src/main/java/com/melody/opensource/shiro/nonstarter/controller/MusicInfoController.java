package com.melody.opensource.shiro.nonstarter.controller;

import com.melody.opensource.shiro.nonstarter.common.RequestUtils;
import com.melody.opensource.shiro.nonstarter.model.domain.SysUser;
import com.melody.opensource.shiro.nonstarter.service.UserService;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Set;


@Controller
@RequestMapping(value = "/music")
public class MusicInfoController {

    @Autowired
    private UserService userService;

    //shiro权限校验
    //@RequiresRoles({"xcxcxc"})//角色校验
    @RequiresPermissions({"/usersPage"}) //权限校验
    @RequestMapping("/login")
    @ResponseBody
    public String login(){
        SysUser user = RequestUtils.currentLoginUser();
        Set<String> authorization = userService.findPermissionsByUserId(user.getId());
        return "该用户有如下权限" + authorization;
    }

}
