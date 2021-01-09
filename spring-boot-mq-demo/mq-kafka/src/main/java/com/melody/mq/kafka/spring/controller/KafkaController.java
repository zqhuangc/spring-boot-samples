package com.melody.mq.kafka.spring.controller;

import com.melody.mq.kafka.spring.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * Kafka RestController
 *
 * @author zqhuangc
 */
@RestController
public class KafkaController {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    public KafkaController(KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }


    @GetMapping(value = "/message/send")
    public String sendMessage(@RequestParam String message) {

        kafkaTemplate.send("sb-2", 0, "sb",message);

        return message;
    }

    @PostMapping(value = "/user/save")
    public User saveUser(@RequestBody User user) {

        kafkaTemplate.send("sb-users", 0, "sb",user);

        return user;

    }

}
