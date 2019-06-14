package com.boot.oauth.server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
class ResourceServer extends ResourceServerConfigurerAdapter {
    /*
     *  Configuration is roughly same as WebSecurityConfigure besides no form login and adding scope
     */
	@Override
	public void configure(HttpSecurity http) throws Exception {
	
		 http.csrf().disable()
		 .requestMatchers()
			.antMatchers("/user/me")
			.antMatchers("/safebox/getDashboardList") 
	        .antMatchers("/safebox/getBankAccountList") 
			.and()
			.authorizeRequests().anyRequest()
			.access("#oauth2.hasScope('read')");
 	    
	}
}