package com.melody.opensource.springbootjmxdemo.mbean.model;

import javax.management.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author zqhuangc
 */
public class SimpleData extends NotificationBroadcasterSupport implements SimpleDataMBean, NotificationListener, NotificationFilter {

    private String data;

    private static final AtomicLong sequenceNumber = new AtomicLong();

    public SimpleData() {
        this.addNotificationListener(this, this, null);
    }

    @Override
    public void setData(String data) {
        String oldData = this.data;
        this.data = data;

        AttributeChangeNotification notification = new AttributeChangeNotification(this ,
                sequenceNumber.incrementAndGet(),
                System.currentTimeMillis(),
                "Data has been changed from " + oldData + " to " + data,
                "data",
                String.class.getName() ,
                oldData,
                data
        );
        sendNotification(notification);
    }

    @Override
    public String getData() {
        return data;
    }

    @Override
    public String displayData() {
        return data;
    }

    /**
     * 处理通知/事件
     *jconsole
     * @param notification
     * @param handback
     */
    @Override
    public void handleNotification(Notification notification, Object handback) {
        AttributeChangeNotification changeNotification = AttributeChangeNotification.class.cast(notification);
        String oldData = (String) changeNotification.getOldValue();
        String newData = (String) changeNotification.getNewValue();
        System.out.printf("The notification of data's attribute  - old data : %s , new data : %s \n", oldData, newData);

    }

    @Override
    public boolean isNotificationEnabled(Notification notification) {
        // 只关心 AttributeChangeNotification 通知，并且仅限于字段/属性名称为 "data"
        if(AttributeChangeNotification.class.isAssignableFrom(notification.getClass())){

            AttributeChangeNotification changeNotification = AttributeChangeNotification.class.cast(notification);
            return "data".equals(changeNotification.getAttributeName());
        }
        return false;
    }


    /**
     *
     * @return
     */
    public MBeanNotificationInfo[] getNotificationInfo() {
        return new MBeanNotificationInfo[]{
                new MBeanNotificationInfo(new String[]{AttributeChangeNotification.ATTRIBUTE_CHANGE
                }, "Data Change Notification",
                        "数据改变通知")
        };
    }
}
