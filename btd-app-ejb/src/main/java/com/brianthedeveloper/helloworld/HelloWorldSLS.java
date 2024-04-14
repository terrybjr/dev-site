package com.brianthedeveloper.helloworld;

import jakarta.ejb.Stateless;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Stateless
public class HelloWorldSLS {
    private static final Logger logger = LogManager.getLogger("HelloWorldSLS");

    public String sayHello() {
        logger.debug("Hello World!");

        return "Hello World!";
    }
}
