package com.melody.opensource.springbootvalidationdemo.controller;

import com.melody.opensource.springbootvalidationdemo.domain.Person;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * {@link Person} Controller
 *
 * @author zqhuangc
 */
@RestController
public class PersonController {


    @PostMapping("/person/save")
    public Person save(@Valid @RequestBody Person person) {

        return person;

    }

}
