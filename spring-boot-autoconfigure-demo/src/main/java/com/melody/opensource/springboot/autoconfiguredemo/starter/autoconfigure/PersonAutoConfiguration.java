package com.melody.opensource.springboot.autoconfiguredemo.starter.autoconfigure;

import com.melody.opensource.springboot.autoconfiguredemo.starter.domain.Person;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.web.embedded.EmbeddedWebServerFactoryCustomizerAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * {@link Person} 自动装配
 *
 * @author zqhuangc
 */
//@Configuration
@ConditionalOnWebApplication
@ConditionalOnProperty(prefix = "person", name = "enabled", havingValue = "true")
//@AutoConfigureAfter(EmbeddedServletContainerAutoConfiguration.class)
@AutoConfigureAfter(EmbeddedWebServerFactoryCustomizerAutoConfiguration.class)
public class PersonAutoConfiguration {


    @ConfigurationProperties(prefix = "person")
    @Bean
    public Person person() {
        return new Person();
    }

//    @Bean
//    public PersonRestController personRestController(Person person) {
//        return new PersonRestController(person);
//    }

}
