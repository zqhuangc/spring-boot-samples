package com.melody.opensource.springboottestdemo.domain;

import org.junit.Assert;
import org.junit.Test;

/**
 * {@link Person} Test
 *
 * @author zqhuangc
 */
public class PersonTest {

    @Test
    public void testPerson() {
        Person person = new Person();
        person.setAge(10);
        Assert.assertEquals(Integer.valueOf(10), person.getAge());
        Assert.assertNotNull(person);
    }
}
