package com.boot.oauth.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OauthPasswordFlowServerApplication {
	//server.port=8080
	//localhost:8080/
    //localhost:8080/SpringSecurityOAuth2Example
    /**
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(OauthPasswordFlowServerApplication.class, args);
    }
}