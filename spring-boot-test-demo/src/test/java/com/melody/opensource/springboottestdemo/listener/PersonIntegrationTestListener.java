package com.melody.opensource.springboottestdemo.listener;

import com.melody.opensource.springboottestdemo.domain.Person;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;

/**
 * TODO
 *
 * @author zqhuangc
 */
public class PersonIntegrationTestListener extends AbstractTestExecutionListener {

    /**
     * The default implementation is <em>empty</em>. Can be overridden by
     * subclasses as necessary.
     */
    @Override
    public void prepareTestInstance(TestContext testContext) throws Exception {
		/* no-op */
        ApplicationContext applicationContext = testContext.getApplicationContext();
        Person person = applicationContext.getBean("primaryPerson", Person.class);
        System.err.println("person : " + person);
    }

    /**
     * The default implementation is <em>empty</em>. Can be overridden by
     * subclasses as necessary.
     */
    @Override
    public void beforeTestMethod(TestContext testContext) throws Exception {
        /* no-op */

        System.err.println("before : " + testContext.getTestMethod());

    }

    /**
     * The default implementation is <em>empty</em>. Can be overridden by
     * subclasses as necessary.
     */
    @Override
    public void afterTestMethod(TestContext testContext) throws Exception {
        /* no-op */
        System.err.println("after : " + testContext.getTestMethod());
    }

    public final int getOrder(){
        return HIGHEST_PRECEDENCE;
    }

}
