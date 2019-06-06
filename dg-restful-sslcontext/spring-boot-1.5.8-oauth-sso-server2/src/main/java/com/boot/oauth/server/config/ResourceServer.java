package com.boot.oauth.server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
class ResourceServer extends ResourceServerConfigurerAdapter {
    //Here we specify to allow the request to the url /user/getEmployeesList with valid access token and scope read 
    //Could not obtain user details from token , we adding below
	@Override
	public void configure(HttpSecurity http) throws Exception {
		  http
	        .antMatcher("/user/me")
	        .antMatcher("/safebox/getDashboardList")
	        .antMatcher("/safebox/getBankAccountList")
	        .authorizeRequests()
	        .anyRequest()
	        .authenticated();
	    
	}
}