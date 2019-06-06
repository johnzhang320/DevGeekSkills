package com.boot.security.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootResourceServerApplication {
	//server.port=8087
    public static void main(String[] args) {
        SpringApplication.run(SpringBootResourceServerApplication.class, args);
    }
}