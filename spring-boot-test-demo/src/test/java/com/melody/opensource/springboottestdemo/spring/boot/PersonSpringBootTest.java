package com.melody.opensource.springboottestdemo.spring.boot;

import com.melody.opensource.springboottestdemo.configuration.PersonConfiguration;
import com.melody.opensource.springboottestdemo.domain.Person;
import org.junit.Assert;
import org.junit.Test;
//import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * TODO
 *
 * @author zqhuangc
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PersonConfiguration.class)
public class PersonSpringBootTest {

    @Autowired
    private Person person;

    @Test
    public void testPrimaryPerson() {

        Assert.assertEquals(Long.valueOf(1L), person.getId());
        Assert.assertEquals("小明", person.getName());
        Assert.assertEquals(Integer.valueOf(32), person.getAge());

    }

}
