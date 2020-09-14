package com.melody.opensource.springbootquartzdemo.springboot.task;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 超时通知任务
 *
 * @author zqhuangc
 */
@Configuration
@Component
@EnableScheduling
public class Scheduletask {
    /**
     * 发送告警通知
     */
    public void overTimeNotice() {
        //实际的业务

        System.out.println("OverTime start time"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

        System.out.println("OverTime end time"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    }
}
