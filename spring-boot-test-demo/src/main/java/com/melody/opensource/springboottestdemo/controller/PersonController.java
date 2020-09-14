package com.melody.opensource.springboottestdemo.controller;

import com.melody.opensource.springboottestdemo.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 *
 * @author zqhuangc
 */
@RestController
public class PersonController {

    @Autowired
    private Person person;

    @GetMapping
    public Person person() {
        return person;
    }

}
