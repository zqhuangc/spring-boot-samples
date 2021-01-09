package com.melody.mq.rabbitmq.api.priority_queue;

import com.melody.mq.rabbitmq.api.util.ConnectionUtil;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * @description
 *
 * @author zqhuangc
 */
public class PriorityProducer {

    public static void main( String[] args ) {
        try {
            Connection connection = ConnectionUtil.getConnection(true);
            Channel channel = connection.createChannel();

            // 声明交换器
            channel.exchangeDeclare("exchange_priority", "direct", true);

            // 创建优先级队列
            Map<String, Object> arg = new HashMap<>();
            arg.put("x-max-priority", 10);
            channel.queueDeclare("queue_priority", true, false, false, arg);
            channel.queueBind("queue_priority", "exchange_priority", "rk_priority");

            // 发送带有优先级的消息
            for (int i = 0; i < 10; i++) {
                AMQP.BasicProperties.Builder builder = new AMQP.BasicProperties.Builder();
                if (i % 2 != 0) {
                    builder.priority(5);
                }
                AMQP.BasicProperties properties = builder.build();
                channel.basicPublish("exchange_priority", "rk_priority", properties, ("messages-" + i).getBytes());
            }

            channel.close();
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
