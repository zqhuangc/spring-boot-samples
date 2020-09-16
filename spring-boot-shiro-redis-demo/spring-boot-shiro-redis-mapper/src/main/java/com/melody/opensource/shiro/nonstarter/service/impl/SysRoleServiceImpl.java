package com.melody.opensource.shiro.nonstarter.service.impl;

import com.melody.opensource.shiro.nonstarter.model.mapper.SysRoleMapper;
import com.melody.opensource.shiro.nonstarter.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public Set<String> findRoleNameByUserId(int userId) {
        return sysRoleMapper.findRoleNameByUserId(userId);
    }
}
