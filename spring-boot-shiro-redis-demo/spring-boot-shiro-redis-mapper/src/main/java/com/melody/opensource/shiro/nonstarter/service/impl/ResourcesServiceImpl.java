package com.melody.opensource.shiro.nonstarter.service.impl;

import com.melody.opensource.shiro.nonstarter.model.domain.SysResources;
import com.melody.opensource.shiro.nonstarter.model.mapper.SysResourcesMapper;
import com.melody.opensource.shiro.nonstarter.service.ResourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ResourcesServiceImpl implements ResourcesService {
    @Autowired
    private SysResourcesMapper sysResourcesMapper;

    @Override
    public List<SysResources> selectAll() {
        List<SysResources> resourcesList = sysResourcesMapper.selectAll();
        return resourcesList;
    }
}
