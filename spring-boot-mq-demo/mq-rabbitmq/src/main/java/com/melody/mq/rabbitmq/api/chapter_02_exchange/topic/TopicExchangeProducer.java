package com.melody.mq.rabbitmq.api.chapter_02_exchange.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
public class TopicExchangeProducer {

    public static final String EXCHANGE_NAME = "topic_exchange";

    public static void main(String[] args) throws Exception {

        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        String[] logLevel = {"info", "warning", "error"};
        String[] module = {"driver", "login", "crm"};
        String[] score = {"A" , "B", "C"};
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    String routingKey = String.join(",", Arrays.asList(logLevel[i % 3], module[j % 3], score[k % 3]));
                    String message = "hello rabbitmq routingKey is " + routingKey;
                    channel.basicPublish(EXCHANGE_NAME, routingKey, null, message.getBytes());
                    log.info("send message, routingKey: {}, message: {}", routingKey, message);
                }
            }
        }

        channel.close();
        connection.close();
    }
}
