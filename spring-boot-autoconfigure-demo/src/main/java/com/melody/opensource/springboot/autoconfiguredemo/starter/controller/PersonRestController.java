package com.melody.opensource.springboot.autoconfiguredemo.starter.controller;

import com.melody.opensource.springboot.autoconfiguredemo.starter.domain.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 *
 * @author zqhuangc
 */
@RestController
public class PersonRestController {

    private final Person person;

    public PersonRestController(Person person) {
        this.person = person;
    }

    @GetMapping("/person")
    public Person person() {
        return person;
    }

}
