package com.melody.opensource.springboot.autoconfiguredemo.auto.repository;


import com.melody.opensource.springboot.autoconfiguredemo.auto.annotation.FirstLevelRepository;
import com.melody.opensource.springboot.autoconfiguredemo.auto.annotation.SecondLevelRepository;

/**
 * 我的 {@link FirstLevelRepository}
 *
 * @author zqhuangc
 */
@SecondLevelRepository(value = "myFirstLevelRepository") // Bean 名称
public class MyFirstLevelRepository {
}
