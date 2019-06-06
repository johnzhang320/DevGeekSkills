package com.boot.oauth.server;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

 



@SpringBootApplication
public class OauthMasterApplication extends SpringBootServletInitializer {
	//server.port=8080
	// http://localhost:8080/oauth_master/home
    public static void main(String[] args) {
    	 SpringApplication.run(OauthMasterApplication.class);
		  
    }
    
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(OauthMasterApplication.class);
	}
}