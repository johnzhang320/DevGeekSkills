package com.boot.oauth.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
 	
	private static String REALM="MY_OAUTH_REALM";
	
	@Autowired
	private TokenStore tokenStore;   //The token store is used to store the token

	@Autowired
	private UserApprovalHandler userApprovalHandler;

	@Autowired
//	@Qualifier("authenticationManagerBean")
	private AuthenticationManager authenticationManager;
	 /*
	  * Outh2 move to cloud oauth2 of Spring boot 2.1.3, if we did not encoder our password 
	  * we have issue o.s.s.c.bcrypt.BCryptPasswordEncoder     : Encoded password does not look like BCrypt
      *
	  */
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        /**
         * 
		    Registers a client with client-id ‘my-trusted-client’ and password ‘secret’ and 
		    roles & scope he is allowed for.
		    Specifies that any generated access token will be valid for only 120 seconds
		    Specifies that any generated refresh token will be valid for only 600 seconds

         */
		clients.inMemory()
	        .withClient("my-trusted-client")  // Client Id
            .authorizedGrantTypes("password", "authorization_code", "refresh_token", "implicit")
            .authorities("CLIENT","ROLE_CLIENT", "ROLE_TRUSTED_CLIENT")
            .scopes("read", "write", "trust")
            .secret("secret")  // password is secret Client Secret, move to Spring 2.1.3 and cloud oauth2 , must encoder it
            .autoApprove(true) 
            .accessTokenValiditySeconds(600).//Access token is only valid for 20 minutes.
            refreshTokenValiditySeconds(1200);//Refresh token is only valid for 20 minutes.
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.tokenStore(tokenStore).userApprovalHandler(userApprovalHandler)
				.authenticationManager(authenticationManager);
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
		//oauthServer.realm(REALM+"/client");
		 oauthServer
         .tokenKeyAccess("permitAll()")
         .checkTokenAccess("isAuthenticated()");
	}
    

}