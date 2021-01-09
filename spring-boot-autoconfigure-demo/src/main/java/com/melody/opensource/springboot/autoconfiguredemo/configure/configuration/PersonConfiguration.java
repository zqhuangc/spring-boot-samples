package com.melody.opensource.springboot.autoconfiguredemo.configure.configuration;

import com.melody.opensource.springboot.autoconfiguredemo.configure.domain.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * TODO
 *
 * @author zqhuangc
 */
@Configuration
public class PersonConfiguration {

    @Bean
    @Profile("prod")
    public Person zhangxueyou() {
        Person person = new Person();
        return person;
    }

    @Bean
    @Profile("test")
    public Person zhangkai() {
        Person person = new Person();
        return person;
    }

}
