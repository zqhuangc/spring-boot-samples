package com.melody.mq.rabbitmq.api.rpc;

import com.melody.mq.rabbitmq.api.util.ConnectionUtil;
import com.rabbitmq.client.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * @author zqhuangc
 * @description
 */
@Slf4j
public class RPCServer {
    /**
     * 请求队列
     */
    private static final String RPC_QUEUE_NAME = "rpc_queue";

    public static void main(String[] args) {
        init();
    }

    public static void init() {
        try {
            // 创建连接和信道
            Connection connection = ConnectionUtil.getConnection(false);
            Channel channel = connection.createChannel();

            // 声明一个请求队列
            channel.queueDeclare(RPC_QUEUE_NAME, false, false, false, null);
            channel.basicQos(1);
            // 创建一个消费者
            DefaultConsumer consumer = new DefaultConsumer(channel) {

                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    // 解析消息中传过来的属性
                    // 响应消息要携带correlationId
                    AMQP.BasicProperties replyProps = new AMQP.BasicProperties()
                            .builder()
                            .correlationId(properties.getCorrelationId())
                            .build();
                    // 解析消息内容
                    String message = new String(body);
                    int n = Integer.parseInt(message);
                    log.info(" [.] fib(" + message + ")");
                    // 计算后的响应消息
                    String response = "" + fib(n);
                    // 响应队列是props.getReplyTo() ，响应属性replyProps 携带 correlationId
                    channel.basicPublish("", properties.getReplyTo(), replyProps, response.getBytes());
                    // 手动发送确认消息
                    channel.basicAck(envelope.getDeliveryTag(), false);
                }
            };

            // 监听 RPC_QUEUE_NAME 队列中消息
            channel.basicConsume(RPC_QUEUE_NAME, false, consumer);
            log.info(" [x] Awaiting RPC requests");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 功能描述：斐波那契递归算法
     *
     * @param n 计算基数
     * @return int 计算值
     * @throws Exception 抛异常
     * @author cakin
     */
    private static int fib(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return fib(n - 1) + fib(n - 2);
    }
}
