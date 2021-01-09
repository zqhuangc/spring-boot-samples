package com.melody.mq.rabbitmq.javaconfig.consumer;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

/**
 * 通过 RabbitMqConfig 设置，消息将被这个类接收
 */
@Component
public class LogMessageListener implements ChannelAwareMessageListener {

    /**
     * 发送消息的生产者在测试包中 MsgProducerTest
     */
    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        String msg = new String(message.getBody());
        System.out.println("收到的消息为 " + msg);
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
        }
    }
}
