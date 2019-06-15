package com.spring.ssl.rest;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
 
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
 
 

@SpringBootApplication
public class RestulSSLServerApplication extends SpringBootServletInitializer{
	
	// private static Class<AdvanceDemoApplication> applicationClass = AdvanceDemoApplication.class;

    public static void main(String[] args) {
    	
        SpringApplication.run(RestulSSLServerApplication.class, args);
      
    }

    @Override  // after Spring boot 2.0 do not need it 
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(RestulSSLServerApplication.class);
    }

}