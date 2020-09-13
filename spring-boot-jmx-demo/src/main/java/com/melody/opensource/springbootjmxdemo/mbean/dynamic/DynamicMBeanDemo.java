package com.melody.opensource.springbootjmxdemo.mbean.dynamic;

import com.melody.opensource.springbootjmxdemo.mbean.model.SimpleData;

import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

/**
 * @author zqhuangc
 */
public class DynamicMBeanDemo {

    public static void main(String[] args) throws Exception {
        // MBean 服务器 - Agent Level
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        // 注册对象
        SimpleDynamicMBean simpleDynamicMBean = new SimpleDynamicMBean();
        // 注册名称
        ObjectName objectName = createObjectName(simpleDynamicMBean);
        // 注册 MBean - SimpleDataMBean
        mBeanServer.registerMBean(simpleDynamicMBean, objectName);

        System.in.read();

    }

    public static ObjectName createObjectName(Object object) throws MalformedObjectNameException {

        Class<?> objectClass = object.getClass();

        String packageName = objectClass.getPackage().getName();

        String className = objectClass.getSimpleName();

        return new ObjectName(packageName + ":type=" + className);
    }
}
