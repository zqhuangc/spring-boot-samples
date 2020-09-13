package com.melody.opensource.springbootjmxdemo.mbean.model;

import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

/**
 * @author zqhuangc
 */
public class MBeanDemo {

    public static void main(String[] args) throws Exception {
        // MBean 服务器 - Agent Level
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        // 注册对象
        SimpleData simpleData = new SimpleData();
        // 注册名称
        ObjectName objectName = createObjectName(simpleData);
        // 注册 MBean - SimpleDataMBean
        mBeanServer.registerMBean(simpleData, objectName);

        System.in.read();

    }

    public static ObjectName createObjectName(Object object) throws MalformedObjectNameException {

        Class<?> objectClass = object.getClass();

        String packageName = objectClass.getPackage().getName();

        String className = objectClass.getSimpleName();

        return new ObjectName(packageName + ":type=" + className);
    }
}
