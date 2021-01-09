package com.melody.mq.rabbitmq.api.ttl;

import com.melody.mq.rabbitmq.api.util.ConnectionUtil;
import com.rabbitmq.client.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 *
 * @author zqhuangc
 */
@Slf4j
public class TtlDemo {

    /**
     * 队列过期删除
     */
    public static void queueTtl() {

        try {
            Connection connection = ConnectionUtil.getConnection(false);
            Channel channel = connection.createChannel();
            // 创建一个 type="direct" 、持久化的、非自动删除的交换器
            channel.exchangeDeclare("EXCHANGE_NAME", "direct", true, false, null);
            // 创建一个持久化、非排他的、非自动删除的队列
            Map<String, Object> arg = new HashMap<String, Object>();
            // 设置队列过期时间，单位：毫秒
            arg.put("x-expires", 20000);
            channel.queueDeclare("QUEUE_NAME", true, false, false, arg);
            // 将交换器与队列通过路由键绑定
            channel.queueBind("QUEUE_NAME", "EXCHANGE_NAME", "ROUTING_KEY");
            // 发送一条持久化的消息: hello world !
            String message = "Hello World !";
            // 第三个参数为mandatory
            channel.basicPublish("EXCHANGE_NAME", "ROUTING_KEY", false,
                    MessageProperties.PERSISTENT_TEXT_PLAIN,
                    message.getBytes());

            channel.close();
            connection.close();
        } catch (IOException e) {
            log.info("catch IOException");
        } catch (TimeoutException e) {
            log.info("catch TimeoutException");
        }
    }

    /**
     * 消息过期（通过队列设置），过期时间统一
     * 过期消息会立马被删除, 因为过期的消息都在队列头部，RabbitMQ 只要定期检查头部即可.
     *  *
     */
    public static void msgTtlByQueue() {
        try {
            Connection connection = ConnectionUtil.getConnection(false);
            Channel channel = connection.createChannel();
            // 创建一个 type="direct" 、持久化的、非自动删除的交换器
            channel.exchangeDeclare("EXCHANGE_NAME", "direct", true, false, null);
            // 创建一个TTL为20秒的队列，消息在队列中会保存20秒
            Map<String, Object> args = new HashMap<String, Object>();
            args.put("x-message-ttl", 20000);
            channel.queueDeclare("QUEUE_NAME", true, false, false, args);
            // 将交换器与队列通过路由键绑定
            channel.queueBind("QUEUE_NAME", "EXCHANGE_NAME", "ROUTING_KEY");
            // 发送一条持久化的消息: hello world !
            String message = "Hello World !";
            channel.basicPublish("EXCHANGE_NAME", "ROUTING_KEY", null, message.getBytes());

            channel.close();
            connection.close();
        } catch (IOException e) {
            log.info("catch IOException");
        } catch (TimeoutException e) {
            log.info("catch TimeoutException");
        }
    }

    /**
     * 消息过期（消息自身设置），过期时间不统一
     * 过期消息须扫描整个队列删除
     */
    public static void msgTtlBySelf() {

        try {
            Connection connection = ConnectionUtil.getConnection(false);
            Channel channel = connection.createChannel();
            // 创建一个 type="direct" 、持久化的、非自动删除的交换器
            channel.exchangeDeclare("EXCHANGE_NAME", "direct", true, false, null);
            // 创建一个持久化、非排他的、非自动删除的队列
            channel.queueDeclare("QUEUE_NAME", true, false, false, null);
            // 将交换器与队列通过路由键绑定
            channel.queueBind("QUEUE_NAME", "EXCHANGE_NAME", "ROUTING_KEY");
            // 发送一条持久化的消息: hello world !
            String message = "Hello World !";

            AMQP.BasicProperties.Builder builder = new AMQP.BasicProperties.Builder();
            builder.deliveryMode(2);
            builder.expiration("6000");
            AMQP.BasicProperties properties = builder.build();
            // properties中包括了expiration属性
            channel.basicPublish("EXCHANGE_NAME", "ROUTING_KEY", false,
                    properties, message.getBytes());

            channel.close();
            connection.close();
        } catch (IOException e) {
            log.info("catch IOException");
        } catch (TimeoutException e) {
            log.info("catch TimeoutException");
        }
    }

}
