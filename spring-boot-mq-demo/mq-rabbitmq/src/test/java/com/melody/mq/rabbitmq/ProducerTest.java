package com.melody.mq.rabbitmq;

import com.melody.mq.rabbitmq.spring.annotation.config.MessageSender;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @description
 *
 * @author zqhuangc
 */
@SpringBootTest
public class ProducerTest {


    @Autowired
    private MessageSender messageSender;

    @Value("${log.exchange}")
    private String exchange;
    @Value("${log.info.binding-key}")
    private String routingKey;

    /**
     * 测试失败通知
     */
    @SneakyThrows
    @Test
    public void sendErrorMsg() {
        for (int i = 0; i < 3; i++) {
            String message = "this is error message " + i;
            messageSender.convertAndSend(exchange, "test", message);
        }
        System.in.read();
    }

    /**
     * 测试发布者确认
     */
    @SneakyThrows
    @Test
    public void sendInfoMsg() {
        for (int i = 0; i < 3; i++) {
            String message = "this is info message " + i;
            messageSender.convertAndSend(exchange, routingKey, message);
        }
        System.in.read();
    }
}
