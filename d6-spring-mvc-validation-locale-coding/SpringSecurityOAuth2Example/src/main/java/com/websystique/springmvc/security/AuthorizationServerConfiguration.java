package com.websystique.springmvc.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler;
import org.springframework.security.oauth2.provider.token.TokenStore;
/**
 * OAuth2 mechanism
 * |---------|
 * |         |
 * |         |
 * |         |
 * | client  |
 * |         |
 * |         |
 * |         |
 * |         |
 * |         |
 * |         |
 * |-----------
 * 2. Authorization Server

	Authorization server is the one responsible for verifying credentials and if credentials are OK, 
	providing the tokens[refresh-token as well as access-token]. It also contains information about 
	registered clients and possible access scopes and grant types. The token store is used to store 
	the token. We will be using an in-memory token store.@EnableAuthorizationServer enables an 
	Authorization Server (i.e. an AuthorizationEndpoint and a TokenEndpoint) in the current application 
	context. Class AuthorizationServerConfigurerAdapter implements AuthorizationServerConfigurer 
	which provides all the necessary methods to configure an Authorization server.
 *
 */
@Configuration
@EnableAuthorizationServer // We will be using an in-memory token store.@EnableAuthorizationServer enables an 
						   // Authorization Server (i.e. an AuthorizationEndpoint and a TokenEndpoint)
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

	private static String REALM="MY_OAUTH_REALM";
	
	@Autowired
	private TokenStore tokenStore;   //The token store is used to store the token

	@Autowired
	private UserApprovalHandler userApprovalHandler;

	@Autowired
	@Qualifier("authenticationManagerBean")
	private AuthenticationManager authenticationManager;

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
	        .withClient("my-trusted-client")
            .authorizedGrantTypes("password", "authorization_code", "refresh_token", "implicit")
            .authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT")
            .scopes("read", "write", "trust")
            .secret("secret")  // password is secret
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
		oauthServer.realm(REALM+"/client");
	}

}