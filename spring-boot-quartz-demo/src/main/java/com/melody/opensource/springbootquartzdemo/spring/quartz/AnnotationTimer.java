package com.melody.opensource.springbootquartzdemo.spring.quartz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


/**
 * @author zqhuangc
 */
@Component
public class AnnotationTimer {

    private Logger LOG = LoggerFactory.getLogger(AnnotationTimer.class);

    //@Scheduled(cron = "0/10 * * * * ?")
    @Async
    public void schedule(){
        LOG.info("注解配置任务执行");
    }
}
