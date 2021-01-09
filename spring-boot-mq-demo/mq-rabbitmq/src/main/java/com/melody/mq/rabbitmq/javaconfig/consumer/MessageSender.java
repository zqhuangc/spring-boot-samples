package com.melody.mq.rabbitmq.javaconfig.consumer;

import com.melody.mq.rabbitmq.javaconfig.config.RabbitMqConfig;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;


@Component
public class MessageSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public final Map<String, String> unAckMsgQueue = new ConcurrentHashMap<>();

    public void convertAndSend(String message) {
        String msgId = UUID.randomUUID().toString();
        CorrelationData correlationData = new CorrelationData();
        correlationData.setId(msgId);
        rabbitTemplate.convertAndSend(RabbitMqConfig.LOG_EXCHANGE, RabbitMqConfig.LOG_ALL_BINDING_KEY, message, correlationData);
        unAckMsgQueue.put(msgId, message);
    }

    public String dequeueUnAckMsg(String msgId) {
        return unAckMsgQueue.remove(msgId);
    }

}
