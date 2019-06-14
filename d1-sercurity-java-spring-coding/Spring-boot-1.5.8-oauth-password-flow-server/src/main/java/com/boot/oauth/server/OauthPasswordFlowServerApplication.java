package com.boot.oauth.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
 

@SpringBootApplication
public class OauthPasswordFlowServerApplication  extends SpringBootServletInitializer {
	 
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(OauthPasswordFlowServerApplication.class);
	}
	//server.port=8084
	//localhost:8084/
    //localhost:8084/SpringSecurityOAuth2Example
    /**
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(OauthPasswordFlowServerApplication.class, args);
    }
}