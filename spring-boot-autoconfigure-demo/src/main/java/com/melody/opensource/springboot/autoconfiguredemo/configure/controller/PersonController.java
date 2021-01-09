package com.melody.opensource.springboot.autoconfiguredemo.configure.controller;

import com.melody.opensource.springboot.autoconfiguredemo.configure.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * {@link Person} {@link RestController}
 *
 * @author zqhuangc
 */
@RestController
public class PersonController implements EnvironmentAware {

    @Autowired
    @Qualifier("person")
    private Person person;

    @Value("${person.id}")
    private Long id;

    @Value("${person.name:小明}")
    private String name;

    private Integer age;


    @GetMapping("/person/xiaoming")
    public Person xiaoming() {
        return person;
    }

    @GetMapping("/person/xiaoming/data")
    public Map<String, Object> xiaomingData() {

        Map<String, Object> data = new LinkedHashMap<>();

        data.put("id", id);
        data.put("name", name);
        data.put("age", age);

        return data;
    }

    @Override
    public void setEnvironment(Environment environment) {

        this.age = environment.getProperty("person.age", Integer.class);

    }

}
