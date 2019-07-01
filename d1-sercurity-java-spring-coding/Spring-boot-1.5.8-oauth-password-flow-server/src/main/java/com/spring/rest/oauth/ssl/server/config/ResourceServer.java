package com.spring.rest.oauth.ssl.server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

@Configuration
@EnableResourceServer
class ResourceServer extends ResourceServerConfigurerAdapter {
    /*
     *  Configuration is roughly same as WebSecurityConfigure besides no form login and adding scope
     */
	@Override
	public void configure(HttpSecurity http) throws Exception {
	
		 http.anonymous().disable()
			.requestMatchers().antMatchers("/safebox/**")   // Resource located on /user/** /SpringSecurityOAuth2Example/user/?access_token=d18321e0-fba0-4c9f-853e-af9d62ff0bbc
			.requestMatchers().antMatchers("/data/**") 
			.requestMatchers().antMatchers("/user/**") 
			.requestMatchers().antMatchers("/secureServices/**") 
			.and().authorizeRequests()
			.antMatchers("/safebox/**").access("hasRole('USER')")
			.antMatchers("/user/**").access("hasRole('USER')")
			.antMatchers("/secureServices/**").access("hasRole('ADMIN')")
			.antMatchers("/data/**").access("hasRole('USER')")
			.and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
		
 	    
	}
}