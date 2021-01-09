package com.melody.mq.rabbitmq.spring.topic;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

// 注入为一个Bean
@Component
// 监听队列：topic.B
@RabbitListener(queues = "topic.B")
public class RabbitMQTopicTotalListener {

    /**
     * 接收消息并处理
     *
     * @param map
     */
    @RabbitHandler
    public void receiveMessage(Map map) {
        System.out.println("TopicBReceiver消费者收到消息  : " + map.toString());
    }

}
