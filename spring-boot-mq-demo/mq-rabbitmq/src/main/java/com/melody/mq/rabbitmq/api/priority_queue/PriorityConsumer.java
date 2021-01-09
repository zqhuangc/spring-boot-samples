package com.melody.mq.rabbitmq.api.priority_queue;

import com.melody.mq.rabbitmq.api.util.ConnectionUtil;
import com.rabbitmq.client.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @description
 *
 * @author zqhuangc
 */
@Slf4j
public class PriorityConsumer {

    public static void main( String[] args ) {
        try {
            Connection connection = ConnectionUtil.getConnection(true);
            Channel channel = connection.createChannel();
            // 消费消息
            channel.basicConsume("queue_priority", true, new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope,
                                           AMQP.BasicProperties properties, byte[] body ) throws IOException {
                    // 接收到的消息
                    String message = new String(body);
                    log.info(" [x] Received '" + message + "'");
                }
            });
            channel.close();
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
