package com.melody.mq.rabbitmq.api.util;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.amqp.utils.JavaUtils;
import org.springframework.util.ObjectUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

/**
 * @description
 *
 * @author zqhuangc
 */
public class ConnectionUtil {

    private static ThreadLocal<Connection> connectionThreadLocal = new InheritableThreadLocal<>();

    public static Connection getConnection(){

        return getConnection(false);
    }

    public static Connection getConnection(boolean useUri) {
        Connection connection = connectionThreadLocal.get();
        if(!ObjectUtils.isEmpty(connection)){
            return connection;
        }

        ConnectionFactory factory = new ConnectionFactory();
        // 通过 URI 的方式建立连接
        if (useUri) {
            try {
                factory.setUri("amqp://guest:guest@127.0.0.1:15672");
            } catch (URISyntaxException e) {
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (KeyManagementException e) {
                e.printStackTrace();
            }
        } else {
            // 通过设置参数的方式
            factory.setUsername("guest");
            factory.setPassword("guest");
            factory.setHost("127.0.0.1");
            factory.setVirtualHost("/");
            factory.setPort(5672);
        }
        try {
            connection = factory.newConnection();
            JavaUtils.INSTANCE.acceptIfNotNull(connection, connectionThreadLocal::set);
            return connection;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
