package com.melody.opensource.springbootquartzdemo.spring.quartz;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.lang.reflect.Method;


/**
 * @author zqhuangc
 */
public class TriggerProxy implements Job {

    public static final String DATA_TARGET_KEY = "target";		//目标对象，实例
    public static final String DATA_TRIGGER_KEY = "trigger";	//方法名
    public static final String DATA_TRIGGER_PARAMS_KEY = "trigger_params";//方法的参数值
    public static final String DATA_TASK_KEY = "task";			//自己封装的任务对象

    private ThreadLocal<Entry> local = new ThreadLocal<>();

    // 调度器自动调用的
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        try {
            JobDataMap jobDataMap = context.getTrigger().getJobDataMap();
            Object target = jobDataMap.get(DATA_TARGET_KEY);
            Method method = (Method) jobDataMap.get(DATA_TRIGGER_KEY);
            Object[] params = (Object[]) jobDataMap.get(DATA_TRIGGER_PARAMS_KEY);

            //修改任务执行次数
            Task task = (Task) jobDataMap.get(DATA_TASK_KEY);
            //任务没执行一次，需要累加1
            task.setExecute(task.getExecute() + 1);

            local.get().start = System.currentTimeMillis();

            //调用触发器，用反射调用我们自己定义的方法
            method.invoke(target,params);

            local.get().end = System.currentTimeMillis();

            //记录任务的最后一次执行时间
            task.setLastExeTime(local.get().start);
            //记录任务完成的时间
            task.setLastFinishTime(local.get().end);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class Entry{
        public long start = 0L;
        public long end = 0L;
    }
}
