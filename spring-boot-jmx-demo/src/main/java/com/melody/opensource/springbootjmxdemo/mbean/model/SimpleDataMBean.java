package com.melody.opensource.springbootjmxdemo.mbean.model;

/**
 * @author zqhuangc
 */
public interface SimpleDataMBean {

    /**
     * Setter
     * Property
     * 属性
     * @param data
     */
    void setData(String data);

    /**
     * Getter
     * @return
     */
    String getData();

    /**
     * 展示数据
     * 操作(Operation)
     * @return
     */
    String displayData();
}
