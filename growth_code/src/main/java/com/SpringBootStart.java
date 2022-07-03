package com;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootStart {

    private static Logger logger = (Logger) LoggerFactory.getLogger(SpringBootStart.class);

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication();
        springApplication.run(SpringBootStart.class, args);
        logger.info("Spring Boot Start Success!");
    }
}
