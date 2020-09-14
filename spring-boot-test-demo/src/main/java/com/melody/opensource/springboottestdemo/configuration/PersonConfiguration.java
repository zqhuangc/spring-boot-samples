package com.melody.opensource.springboottestdemo.configuration;

import com.melody.opensource.springboottestdemo.domain.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * {@link Person} Bean 配置
 *
 * @author zqhuangc
 */
@Configuration
public class PersonConfiguration {


    @Bean("primaryPerson")
    @Primary
    public Person person() {

        Person person = new Person();

        person.setId(1L);
        person.setName("小明");
        person.setAge(32);

        return person;

    }
}
