package com.spring.rest.oauth.ssl.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class RestOauthServerApplication extends SpringBootServletInitializer {
	//server.port=8084
	//localhost:8084/SpringSecurityOAuth2Example/oauth/token
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(RestOauthServerApplication.class);
	}
    /**
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(RestOauthServerApplication.class, args);
    }
}