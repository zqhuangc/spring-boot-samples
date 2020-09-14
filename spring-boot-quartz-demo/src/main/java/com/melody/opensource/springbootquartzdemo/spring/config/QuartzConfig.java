package com.melody.opensource.springbootquartzdemo.spring.config;

import com.melody.opensource.springbootquartzdemo.springboot.task.Scheduletask;
import org.quartz.Trigger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * Spring Boot 1.x
 * @author zqhuangc
 */
//@Configuration
public class QuartzConfig {

    /**
     * 配置定时任务
     */
    @Bean(name = "jobDetail")
    public MethodInvokingJobDetailFactoryBean detailFactoryBean(Scheduletask task) {// ScheduleTask为需要执行的任务
        MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();

        jobDetail.setConcurrent(false);// 是否并发执行
        jobDetail.setName("overTimeNoticeJob");// 设置任务的名字
        jobDetail.setGroup("overTimeNoticeJobGroup");// 设置任务的分组，这些属性都可以存储在数据库中，在多任务的时候使用
        /*
         * 为需要执行的实体类对应的对象
         */
        jobDetail.setTargetObject(task);
        /*
         * 添加需要执行的方法
         * 通过这几个配置，告诉JobDetailFactoryBean我们需要执行定时执行ScheduleTask类中的需要执行方法
         */
        jobDetail.setTargetMethod("overTimeNotice");
        return jobDetail;
    }

    /**
     * 配置定时任务的触发器，也就是什么时候触发执行定时任务
     */
    @Bean(name = "jobTrigger")
    public CronTriggerFactoryBean cronJobTrigger(MethodInvokingJobDetailFactoryBean jobDetail) {
        CronTriggerFactoryBean trigger = new CronTriggerFactoryBean();
        trigger.setJobDetail(jobDetail.getObject());
        trigger.setCronExpression("* 0/5 * * * ?");// 初始时的cron表达式  ，没5分钟执行一次
        trigger.setName("overTimeNoticeTrigger");// trigger的name
        return trigger;

    }

    /**
     * 定义quartz调度工厂
     */
    @Bean(name = "scheduler")
    public SchedulerFactoryBean schedulerFactory(Trigger cronJobTrigger) {
        SchedulerFactoryBean bean = new SchedulerFactoryBean();
        // 用于quartz集群,QuartzScheduler 启动时更新己存在的Job
        bean.setOverwriteExistingJobs(true);
        // 延时启动，应用启动1秒后
        bean.setStartupDelay(1);
        // 注册触发器
        bean.setTriggers(cronJobTrigger);
        bean.setConfigLocation(new ClassPathResource("/quartz.properties"));
        return bean;
    }

}
