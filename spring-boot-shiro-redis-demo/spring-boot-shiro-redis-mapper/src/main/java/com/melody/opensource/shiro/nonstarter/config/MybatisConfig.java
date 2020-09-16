package com.melody.opensource.shiro.nonstarter.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @description
 *
 * @author zqhuangc
 */
@Configuration
@MapperScan(basePackages = "com.melody.opensource.shiro.nonstarter.model.mapper")
public class MybatisConfig {
}
