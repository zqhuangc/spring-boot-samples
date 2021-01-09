package com.melody.mq.rabbitmq.api.chapter_07_publisherConfirm;

import com.melody.mq.rabbitmq.api.util.ConnectionUtil;
import com.rabbitmq.client.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 发送方要配合 mandatory 参数或者备份交换器一起使用来提高
 * 消息传输的可靠性
 *
 * @author zqhuangc
 */
@Slf4j
public class ImmediateDemo {

    public static void main( String[] args ) {
        String exchangeName = "confirmExchange";
        String routingKey = "confirmRoutingKey";
        int count = 3;

        // 获取连接
        try {
            Connection connection = ConnectionUtil.getConnection();
            Channel channel = connection.createChannel();
            // 创建exchange
            channel.exchangeDeclare(exchangeName, "direct", true, false, null);
            // 发送持久化消息
            for (int i = 0; i < count; i++) {
                /**
                 *  exchangeName:默认情况下代理服务器端是存在一个""名字的exchange的,
                 *  因此如果不创建exchange的话我们可以直接将该参数设置成"",
                 *  如果创建了exchange的话我们需要将该参数设置成创建的exchange的名字
                 *  routingKey:路由键
                 *  mandatory:true 表示如果没有路由会返回（return） false：丢弃
                 *  immediate: 参数告诉服务器，如果该消息关联的队列上有消费者，则立刻投递：
                 *  如果所有匹配的队列上都没有消费者，则直接将消息返还给生产者，不用将消息存入队列而等
                 *  待消费者了。官方不再支持该参数，推荐使用 ttl dlx 替代
                 *  启用该参数，会报错
                 *  WARN com.rabbitmq.client.impl.ForgivingExceptionHandler -
                 *  An unexpected connection driver error occured (Exception message: Connection reset)
                 */
                channel.basicPublish(exchangeName, routingKey, true, true,
                        MessageProperties.PERSISTENT_BASIC, ("第" + (i + 1) + "条消息").getBytes());
            }

            channel.addReturnListener(new ReturnListener() {
                /**
                 * broker(代理服务器)发出basic.return方法之后，就会回调handleReturn方法，
                 * 在这个方法里面我们就可以进行消息的重新发布操作
                 * @param replyCode 312
                 * @param replyText NO_ROUTE：表示消息并没有路由到合适的队列中
                 * @param exchange 交换器
                 * @param routingKey 路由键
                 * @param properties 相关属性
                 * @param body 消息体
                 * @throws IOException
                 */
                @Override
                public void handleReturn(int replyCode, String replyText, String exchange, String routingKey,
                                         AMQP.BasicProperties properties, byte[] body ) throws IOException {
                    //此处便是执行Basic.Return之后回调的地方
                    String message = new String(body);
                    System.out.println("Basic.Return返回的结果:  " + message);
                }
            });

            channel.close();
        } catch (IOException e) {
            log.info("catch IOException");
        } catch (TimeoutException e) {
            log.info("catch TimeoutException");
        }
    }

}
