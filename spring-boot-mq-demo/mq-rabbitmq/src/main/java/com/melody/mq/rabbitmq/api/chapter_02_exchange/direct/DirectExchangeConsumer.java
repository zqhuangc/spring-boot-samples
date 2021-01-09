package com.melody.mq.rabbitmq.api.chapter_02_exchange.direct;

import com.melody.mq.rabbitmq.api.util.ConnectionUtil;
import com.rabbitmq.client.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class DirectExchangeConsumer {

    public static void main(String[] args) throws Exception {

        ConnectionFactory connectionFactory = new ConnectionFactory();

        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(DirectExchangeProducer.EXCHANGE_NAME, BuiltinExchangeType.DIRECT);

        String queueName = "directExchangeErrorQueue";
        String bindingKey = "error";
        // 队列名
        // durable 设置是否持久化。为true则设置队列为持久化。持久化的队列会存盘
        // exclusive 设置是否排他。为true则设置队列为排他的，如果一个队列被声明为排他队列，该队列仅对首次声明它的连接可见，并在连接断开时自动删除
        // autoDelete 设置是否自动删除。为true则设置队列为自动删除
        // arguments 设置队列的其他一些参数
        channel.queueDeclare(queueName, false, false, false ,null);
        channel.queueBind(queueName, DirectExchangeProducer.EXCHANGE_NAME, bindingKey);

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                log.info("get message, routingKey: {}, message: {}", envelope.getRoutingKey(), message);
            }
        };

        channel.basicConsume(queueName , true, consumer);

    }



     /**
      * 死信队列，用于当一个队列的消息不可用之后，那么变成死信，这时候可以把它重新发送到另一个队列用于保存.
     * 存储死信的队列就叫做死信队列.
     */
    public static void test() throws Exception {
        Connection connection = ConnectionUtil.getConnection(false);
        Channel channel = connection.createChannel();
        channel.exchangeDeclare("DLX-exchange", "direct", true);
        channel.exchangeDeclare("TTL-exchange", "direct", true);

        // 死信队列
        channel.queueDeclare("DLX-queue", false, false, false, null);
        channel.queueBind("DLX-queue", "DLX-exchange", "DLX-routingKey");

        // 普通队列
        Map<String, Object> configs = new HashMap<String, Object>();
        configs.put("x-message-ttl", 10000);
        configs.put("x-dead-letter-exchange", "DLX-exchange");
        // 这里的 routingKey 对应死信队列的 routingKey
        configs.put("x-dead-letter-routing-key", "DLX-routingKey");
        channel.queueDeclare("TTL-queue", false, false, false, configs);
        channel.queueBind("TTL-queue", "TTL-exchange", "TTL-routingKey");

        byte[] messages = "test Mandatory...".getBytes();
        channel.basicPublish("TTL-exchange", "TTL-routingKey", true, MessageProperties.TEXT_PLAIN, messages);

        channel.close();
        connection.close();
    }


}
