package com.websystique.springmvc.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
/**
 * 
 * 1. Resource Server

	Resource Server hosts the resources [our REST API] the client is interested in. Resources 
	are located on /user/. @EnableResourceServer annotation, applied on OAuth2 Resource Servers, 
	enables a Spring Security filter that authenticates requests using an incoming OAuth2 token. 
	Class ResourceServerConfigurerAdapter implements ResourceServerConfigurer providing methods 
	to adjust the access rules and paths that are protected by OAuth2 security.
 *
 */

@Configuration
@EnableResourceServer // applied on OAuth2 Resource Servers, enables a Spring Security filter that authenticates requests using an incoming OAuth2 token
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

	private static final String RESOURCE_ID = "my_rest_api";
	
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) {
		resources.resourceId(RESOURCE_ID).stateless(false);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.
		anonymous().disable()
		.requestMatchers().antMatchers("/user/**")   // Resource located on /user/**
		.and().authorizeRequests()
		.antMatchers("/user/**").access("hasRole('ADMIN')")
		.and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
	}

}