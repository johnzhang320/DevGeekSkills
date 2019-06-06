package com.boot.security.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenStoreUserApprovalHandler;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

import com.frontend.encrypt.utils.Sha256Encoder;
import com.frontend.encrypt.utils.Sha256EncoderImpl;
 

 

 

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public Sha256Encoder sha256Encoder() {
        return new Sha256EncoderImpl();
    }
    @Autowired
    private CustomAuthenticationProviderDatabase customAuthenticationProvider;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
        .authorizeRequests()
        .antMatchers("/","/getKeyPair.html","/oauth/authorize","/oauth/token").permitAll()
         .antMatchers("/user/me").hasRole("USER")
        .antMatchers("/user/getEmployeesList").hasAnyRole("ADMIN")
        .antMatchers("/safebox/getDashboardList").hasAnyRole("USER")
        .antMatchers("/safebox/getBankAccountList").hasAnyRole("ADMIN")
        .antMatchers("/marshall/renewall.html").hasRole("USER")
        .anyRequest()
        .authenticated()
        .and()
        .formLogin()
        .loginPage("/login")
        .permitAll()
        .and()
        .logout()
        .permitAll();

       
    }
   
 	
    
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
      	// configure customer authentication provider which check both username and password
    	// provided username , password and the roles which username has
  		auth.authenticationProvider(customAuthenticationProvider);       
  	  
     }
	 

 
}
