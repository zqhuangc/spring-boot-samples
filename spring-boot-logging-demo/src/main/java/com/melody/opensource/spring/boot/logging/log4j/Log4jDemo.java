package com.melody.opensource.spring.boot.logging.log4j;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.apache.log4j.xml.DOMConfigurator;

import java.net.URL;

import static org.apache.log4j.LogManager.DEFAULT_CONFIGURATION_KEY;

/**
 * @description
 * @author zqhuangc
 */
public class Log4jDemo {

    public static void main(String[] args) throws Exception {

        System.setProperty(DEFAULT_CONFIGURATION_KEY, "log4j-conf.xml");

        Logger logger = Logger.getLogger(Log4jDemo.class.getName());

        logger.setLevel(Level.INFO);

        logger.info("Hello,World");

        // 重新加载 log4j的配置

        URL url = Thread.currentThread().getContextClassLoader().getResource("log4j-api.xml");

        DOMConfigurator.configure(url);

        MDC.put("requestURI","https://segmemtfault.com");

        logger = Logger.getLogger(Log4jDemo.class.getName());

        // 调整级别后输出
        logger.info("Hello,World");

        logger.error("大家好");


    }
}
