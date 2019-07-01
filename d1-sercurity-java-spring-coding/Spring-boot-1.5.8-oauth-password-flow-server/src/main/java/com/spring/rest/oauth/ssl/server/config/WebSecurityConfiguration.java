package com.spring.rest.oauth.ssl.server.config;

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
        .antMatchers("/","/login","/getKeyPair.html","/oauth/authorize","/oauth/token","/secureServices","/data","/user").permitAll()   
        .antMatchers("/safebox/getDashboardList").hasAnyRole("USER")
        .antMatchers("/safebox/getBankAccountList").hasAnyRole("ADMIN")
         .anyRequest()
        .authenticated();
        /*.and()
        .formLogin()
        .loginPage("/login")
        .permitAll()
        .and()
        .logout()
        .permitAll();*/

        
    }
   
	@Autowired
	private ClientDetailsService clientDetailsService;   
	
    
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
      	// configure customer authentication provider which check both username and password
    	// provided username , password and the roles which username has
  		auth.authenticationProvider(customAuthenticationProvider);       
  	  
     }
	@Bean   // this bean is applied in AuthorizationServerConfiguration
	public TokenStore tokenStore() {
		return new InMemoryTokenStore();
	}

	@Bean // this bean is applied in AuthorizationServerConfiguration
	@Autowired
	public TokenStoreUserApprovalHandler userApprovalHandler(TokenStore tokenStore){
		TokenStoreUserApprovalHandler handler = new TokenStoreUserApprovalHandler();
		handler.setTokenStore(tokenStore);
		handler.setRequestFactory(new DefaultOAuth2RequestFactory(clientDetailsService));
		handler.setClientDetailsService(clientDetailsService);
		return handler;
	}
	
	@Bean // this bean is applied in AuthorizationServerConfiguration
	@Autowired
	public ApprovalStore approvalStore(TokenStore tokenStore) throws Exception {
		TokenApprovalStore store = new TokenApprovalStore();
		store.setTokenStore(tokenStore);
		return store;
	}    
}
