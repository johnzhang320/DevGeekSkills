package com.boot.security.auth.config;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.frontend.encrypt.utils.Sha256Encoder;
import com.frontend.encrypt.utils.Sha256EncoderImpl;
 
 
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public Sha256Encoder sha256Encoder() {
        return new Sha256EncoderImpl();
    }
    
    @Autowired
    private CustomAuthenticationProviderDatabase customAuthenticationProvider;
    
    @Autowired
    private AccessDeniedHandler accessDeniedHandler;
    
    /*
     *  Use Case: we want to access Methods with  GET 
     *  /safebox/bankaccount.html only for ADMIN
     *  /safebox/getEmployeesList only for USER
     *  /safebox/dashboard.html for USER
     *  "/registration","/getKeyPair.html","/resources/**" only for USER
     *  if directly using /home, after authenticate user, go to home page
     *  if login incorrect, go /login?error=true
     *  if logout go back to /login
     *  if any exception such as login as ROLE USER but access banckaccount , exception to 403
     *  block all other accesses
     *  
     *  below solution are successfully
     */
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
        .authorizeRequests()
        .antMatchers("/safebox/bankaccount.html").hasAnyRole("Billing","Owner")    // need role ADMIN logins  //GET
        .antMatchers("/safebox/assign_roles.html").hasAnyRole("Admin", "Owner", "Primary Owner")    // need role ADMIN logins  //GET
        .antMatchers("/safebox/find_roles.html").hasAnyRole("Admin", "Owner", "Primary Owner")    // need role ADMIN logins  //GET
        .antMatchers("/safebox/getEmployeesList").hasAnyRole("Editor","Contributor")   //GET
        .antMatchers("/safebox/dashboard.html").hasAnyRole("Viewer","Editor","Contributor","Billing","Admin", "Owner", "Primary Owner")  // need role USER logins  //GET
        .antMatchers(
        		"/registration",
        		"/getKeyPair.html",
        		"/resources/**",
        		"/home",
        		"/oauth/fetch_token"     // directly get 
        		 
        		).permitAll() //GET
         .and()
        .formLogin()
            .loginPage("/login").defaultSuccessUrl("/home")
            .failureUrl("/login?error=true")
            .and()
            .logout()
            .and()
            .exceptionHandling().accessDeniedHandler(accessDeniedHandler);
        
        http.csrf().disable();
        
    }
    
    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManager();
    }
      
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
      	// configure customer authentication provider which check both username and password
    	// provided username , password and the roles which username has
  		auth.authenticationProvider(customAuthenticationProvider);       
  	  
     }
           
   
}