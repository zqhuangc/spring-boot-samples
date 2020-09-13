package com.melody.opensource.springbootjmxdemo.mbean.dynamic;

import javax.management.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * {@link DynamicMBean} 简单实现
 * <p>
 * 动态定义Bean接口：
 * <ul>
 * <li>定义一个名为"value" 属性</li>
 * <li>定义一个名为"displayValue" 操作</li>
 * </ul>
 * @author zqhuangc
 */
public class SimpleDynamicMBean implements DynamicMBean {

    private String data;

    //存储多个值
    private Map<String, Object> values = new ConcurrentHashMap<>();

    @Override
    public Object getAttribute(String attribute) throws AttributeNotFoundException, MBeanException, ReflectionException {
        return data;
    }

    @Override
    public void setAttribute(Attribute attribute) throws AttributeNotFoundException, InvalidAttributeValueException, MBeanException, ReflectionException {
        String attributeName = attribute.getName();
        if("data".equals(attributeName)){
            this.data = (String) attribute.getValue();
        }
    }

    @Override
    public AttributeList getAttributes(String[] attributes) {
        return null;
    }

    @Override
    public AttributeList setAttributes(AttributeList attributes) {
        return null;
    }

    @Override
    public Object invoke(String actionName, Object[] params, String[] signature) throws MBeanException, ReflectionException {
        if ("displayData".equals(actionName)) { //类似于 API 的方法调用
            return data;
        }
        return null;
    }

    @Override
    public MBeanInfo getMBeanInfo() {
        MBeanInfo mBeanInfo = new MBeanInfo(
                this.getClass().getName(),
                "简单的自定义DynamicMBean",
                of(new MBeanAttributeInfo("data", String.class.getName(), "动态属性data", true, true, false)),
                of(new MBeanConstructorInfo(this.getClass().getSimpleName(), "默认构造器", new MBeanParameterInfo[0])),
                of(new MBeanOperationInfo("displayData", "自定义 displayData 方法", new MBeanParameterInfo[0], String.class.getName(), MBeanOperationInfo.ACTION)),
                new MBeanNotificationInfo[0]);
        return mBeanInfo;
    }

    private static <T> T[] of(T... array) {
        return array;
    }
}
