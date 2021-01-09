package com.melody.mq.rabbitmq.api.rpc;

import com.melody.mq.rabbitmq.api.util.ConnectionUtil;
import com.rabbitmq.client.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.UUID;

/**
 * @description
 *
 * @author zqhuangc
 */
@Slf4j
public class RPCClient {

    /**
     * 连接
     */
    private Connection connection;
    /**
     * 信道
     */
    private Channel channel;
    /**
     * 请求队列
     */
    private String requestQueueName = "rpc_queue";
    /**
     * 响应队列
     */
    private String replyQueueName;
    /**
     * 消费者
     */
    private DefaultConsumer consumer;
    /**
     * 请求 id
     */
    private String corrId;
    /**
     * 结果
     */
    private String response;

    public RPCClient() throws Exception {
        // 创建消息和信道
        connection = ConnectionUtil.getConnection(false);
        channel = connection.createChannel();
        // 响应队列是匿名队列
        replyQueueName = channel.queueDeclare().getQueue();
        // 创建一个消费者
        consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                // 接收客户端发过来的消息，接收前判断CorrelationId是否一致
                if (properties.getCorrelationId().equals(corrId)) {
                    String response = new String(body);
                    log.info("【客户端】获取消息: " + response);
                    callback(response);
                }
            }
        };
        // 消费消息，推模式，监听
        channel.basicConsume(replyQueueName, true, consumer);
    }

    public void call(String message) throws IOException,
            ShutdownSignalException, ConsumerCancelledException {
        // 使用UUID创建CorrelationId
        corrId = UUID.randomUUID().toString();
        // props 携带CorrelationId 和 replyQueueName
        AMQP.BasicProperties props = new AMQP.BasicProperties.Builder()
                .correlationId(corrId)
                .replyTo(replyQueueName)
                .build();
        // 发送消息到服务端，这里绑定的是默认exchange
        channel.basicPublish("", requestQueueName, props, message.getBytes());
    }

    public void close() throws Exception {
        channel.close();
        connection.close();
    }

    public String getResult(){
        return response;
    }

    public String callback(String response){
        return this.response = response;
    }

    public static void main( String[] args ) throws Exception {
        // 创建客户端
        RPCClient fibRpc = new RPCClient();
        log.info(" [x] Requesting fib(30)");
        // 请求服务端计算斐波那契数值
        fibRpc.call("30");
        String response = fibRpc.getResult();
        log.info(" [.] Got '" + response + "'");
        fibRpc.close();
    }
}
