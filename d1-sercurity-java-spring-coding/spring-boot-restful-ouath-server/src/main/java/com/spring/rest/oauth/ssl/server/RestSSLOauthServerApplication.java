package com.spring.rest.oauth.ssl.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class RestSSLOauthServerApplication extends SpringBootServletInitializer {
	//server.port=8092
	//localhost:8092/spring-boot-oauth-ssl-restful-server/oauth/token
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(RestSSLOauthServerApplication.class);
	}
    /**
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(RestSSLOauthServerApplication.class, args);
    }
}