package com.melody.opensource.shiro.nonstarter.service;

import com.melody.opensource.shiro.nonstarter.model.domain.SysResources;

import java.util.List;

public interface ResourcesService {
    List<SysResources> selectAll();
}
