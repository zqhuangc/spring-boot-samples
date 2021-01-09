package com.melody.opensource.springboot.autoconfiguredemo.auto.configuration;


import com.melody.opensource.springboot.autoconfiguredemo.auto.annotation.EnableHelloWorld;
import com.melody.opensource.springboot.autoconfiguredemo.auto.condition.ConditionalOnSystemProperty;
import org.springframework.context.annotation.Configuration;

/**
 * HelloWorld 自动装配
 *
 * @author zqhuangc
 */
@Configuration // Spring 模式注解装配
@EnableHelloWorld // Spring @Enable 模块装配
@ConditionalOnSystemProperty(name = "user.name", value = "Mercy") // 条件装配
public class HelloWorldAutoConfiguration {
}
