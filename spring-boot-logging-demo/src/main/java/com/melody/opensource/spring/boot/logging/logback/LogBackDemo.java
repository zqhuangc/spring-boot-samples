package com.melody.opensource.spring.boot.logging.logback;

import ch.qos.logback.classic.BasicConfigurator;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;

/**
 * @author zqhuangc
 */
public class LogBackDemo {
    public static void main(String[] args) {
        LoggerContext loggerContext = new LoggerContext();
        Logger logger = loggerContext.getLogger(LogBackDemo.class);
        //logger.addAppender();
        BasicConfigurator config = new BasicConfigurator();
        config.configure(loggerContext);
        logger.info("log info ");

    }
}
