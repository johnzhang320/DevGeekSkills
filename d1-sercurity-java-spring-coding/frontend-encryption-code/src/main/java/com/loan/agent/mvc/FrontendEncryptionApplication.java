package com.loan.agent.mvc;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

 /*  chrome
  *  localhost:8089/password-credit-socialsecurity-frontend-encryption
  */

@SpringBootApplication
public class FrontendEncryptionApplication extends SpringBootServletInitializer{
 
    public static void main(String[] args) {
    	 
        SpringApplication.run(FrontendEncryptionApplication.class, args);
      
    }

    @Override  // after Spring boot 2.0 do not need it 
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(FrontendEncryptionApplication.class);
    }

}