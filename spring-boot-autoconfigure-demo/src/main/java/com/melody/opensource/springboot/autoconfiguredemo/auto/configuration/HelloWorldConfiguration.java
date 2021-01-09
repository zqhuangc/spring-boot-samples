package com.melody.opensource.springboot.autoconfiguredemo.auto.configuration;


import org.springframework.context.annotation.Bean;

/**
 * HelloWorld 配置
 *
 * @author zqhuangc
 */
public class HelloWorldConfiguration {

    @Bean
    public String helloWorld() { // 方法名即 Bean 名称
        return "Hello,World 2018";
    }

}
