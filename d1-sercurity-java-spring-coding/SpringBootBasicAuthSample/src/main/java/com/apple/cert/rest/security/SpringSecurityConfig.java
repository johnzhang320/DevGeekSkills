package com.apple.cert.rest.security;
/**
 * Now let us define our main configuration for spring security - SpringSecurityConfig.java.class is annotated 
 * with @EnableWebSecurity to enable Spring Security web security support. we have extended 
 * WebSecurityConfigurerAdapter to override spring features with our custom requirements.Here 
 * we want our every request to be authenticated using HTTP Basic authentication. 
 * If authentication fails, the configured AuthenticationEntryPoint will be used to retry the authentication process.
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private AuthenticationEntryPoint authEntryPoint;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
				.anyRequest().authenticated()
				.and().httpBasic()
				.authenticationEntryPoint(authEntryPoint);
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("john123").password("password").roles("USER");
		auth.inMemoryAuthentication().withUser("bill").password("abc123").roles("ADMIN");
		auth.inMemoryAuthentication().withUser("tom").password("abc123").roles("USER");

	}
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedMethods("GET", "POST", "PUT", "DELETE").allowedOrigins("*")
					.allowedHeaders("*");
			}
		};
	}
}