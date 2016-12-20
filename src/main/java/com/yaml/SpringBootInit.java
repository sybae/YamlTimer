package com.yaml;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringBootInit {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootInit.class, args);
    }
}