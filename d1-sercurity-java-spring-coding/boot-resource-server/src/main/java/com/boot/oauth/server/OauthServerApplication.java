package com.boot.oauth.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class OauthServerApplication extends SpringBootServletInitializer {
	//server.port=8083
	//localhost:8083/
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(OauthServerApplication.class);
	}
    /**
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(OauthServerApplication.class, args);
    }
}