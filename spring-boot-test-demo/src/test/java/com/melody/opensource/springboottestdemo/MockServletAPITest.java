package com.melody.opensource.springboottestdemo;

import org.junit.Test;
import org.springframework.mock.web.MockServletContext;

import javax.servlet.ServletContext;

/**
 *
 * @author zqhuangc
 */
public class MockServletAPITest {

    /**
     * {@link ServletContext}
     */
    @Test
    public void testMockServletContext() {
        // 不支持 Servlet 3.0 的注册 API
        MockServletContext servletContext = new MockServletContext();

        servletContext.setInitParameter("abc", "def");


    }
}
