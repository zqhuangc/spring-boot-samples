package com.melody.opensource.springboottestdemo;

import org.junit.Test;
import org.springframework.core.env.Environment;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.mock.env.MockEnvironment;
import org.springframework.web.context.support.StandardServletEnvironment;

import static org.junit.Assert.*;

/**
 * {@link Environment}
 *
 * @author zqhuangc
 */
public class EnvironmentTest {

    @Test
    public void testSystemProperty() {
        assertNotNull(System.getProperty("os.arch"));

//        Environment environment = new StandardEnvironment();
//        Environment webEnvironment = new StandardServletEnvironment();
        MockEnvironment environment = new MockEnvironment();

        environment.setProperty("user.country", "EN");

        assertEquals("EN", environment.getProperty("user.country"));

    }

    @Test
    public void testManagementSecurityEnabled() {

        MockEnvironment environment = new MockEnvironment();

        // spring boot 1.x
        environment.setProperty("management.security.enabled", "true");
        assertTrue(environment.getProperty("management.security.enabled", boolean.class));
        // spring boot 2.x
        environment.setProperty("management.endpoints.web.exposure.include", "*");
        assertEquals("*", environment.getProperty("management.endpoints.web.exposure.include"));

    }

    @Test
    public void testManagementSecurityDisabled() {

        MockEnvironment environment = new MockEnvironment();

        environment.setProperty("management.security.enabled", "false");
        assertFalse(environment.getProperty("management.security.enabled", boolean.class));

        // spring boot 2.x
        environment.setProperty("management.endpoints.web.exposure.exclude", "*");
        assertEquals("*", environment.getProperty("management.endpoints.web.exposure.exclude"));

    }
}
