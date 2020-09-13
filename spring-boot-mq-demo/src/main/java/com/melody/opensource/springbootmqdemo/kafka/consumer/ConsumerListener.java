package com.melody.opensource.springbootmqdemo.kafka.consumer;

import com.melody.opensource.springbootmqdemo.kafka.domain.User;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * 消费者监听
 *
 * @author zqhuangc
 */
@Component
public class ConsumerListener {


    @KafkaListener(topics = "sb-2")
    public void consumer(String message) {

        System.out.println(message);

    }


    @KafkaListener(topics = "sb-users")
    public void consumer(User user) {

        System.err.println(user);

    }

}
