package com.melody.opensource.springboot.autoconfiguredemo.auto.annotation;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 *  激活 HelloWorld 模块
 *
 * @author zqhuangc
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
//@Import(HelloWorldConfiguration.class)
@Import(HelloWorldImportSelector.class)
public @interface EnableHelloWorld {
}
