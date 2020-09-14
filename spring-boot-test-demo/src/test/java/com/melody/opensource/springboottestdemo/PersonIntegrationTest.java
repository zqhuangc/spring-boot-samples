package com.melody.opensource.springboottestdemo;

import com.melody.opensource.springboottestdemo.configuration.PersonConfiguration;
import com.melody.opensource.springboottestdemo.domain.Person;
import com.melody.opensource.springboottestdemo.listener.PersonIntegrationTestListener;
import org.junit.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestPropertySource;

import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

/**
 * @author zqhuangc
 */
//@ExtendWith(SpringExtension.class)  // junit5
@RunWith(SpringRunner.class)
@ContextHierarchy(
        @ContextConfiguration(

                classes = PersonConfiguration.class
        )
)
@TestExecutionListeners(listeners = {
        PersonIntegrationTestListener.class,
        DependencyInjectionTestExecutionListener.class
})
@TestPropertySource(properties = {"name = 小明"})
public class PersonIntegrationTest {

    @Value("${name}")
    private String name;

    @Autowired
    private Person person;

    @BeforeClass
    public static void beforeClass() {
        System.err.println("beforeClass()");
    }

    @Before
    public void before() {
        System.err.println("before()");
    }

    @Test
    public void testPrimaryPerson() {

        Assert.assertEquals(Long.valueOf(1L), person.getId());
        Assert.assertEquals("小明", person.getName());
        Assert.assertEquals(Integer.valueOf(32), person.getAge());

    }

    @Test
    public void testName(){
        Assert.assertEquals("小明",name);
    }


    @After
    public void after() {
        System.err.println("after()");
    }

    @AfterClass
    public static void afterClass() {
        System.err.println("afterClass()");
    }

}

