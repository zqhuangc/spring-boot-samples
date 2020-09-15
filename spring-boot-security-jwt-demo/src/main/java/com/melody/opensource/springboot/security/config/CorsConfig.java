package com.melody.opensource.springboot.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 跨域配置
 *
 * @author zqhuangc
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry.
                /**
                 * 核心设置
                 * 请求映射路径
                 */
                addMapping("/**")
                /**
                 * 允许请求的来源（域）
                 */
                .allowedOrigins("*")
                /**
                 * 暴露 response header 中的其他属性（非常规）给客户端
                 * 如果不设置这个属性前端无法通过response header获取到
                 * Authorization 也就是 token
                 */
                .exposedHeaders("Authorization")
                .allowCredentials(true)
                /**
                 * 允许的 HTTP methods
                 */
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                /**
                 * 默认允许所有
                 */
                //.allowedHeaders("")
                .maxAge(3000);
    }
}
