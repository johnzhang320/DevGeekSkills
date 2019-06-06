package com.oauth2.demo.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
/**
 * 
 * Now, define the OAuth2 Configuration class to add the Client ID, Client Secret, Define the JwtAccessTokenConverter, 
 * Private key and Public key for token signer key and verifier key, and configure the ClientDetailsServiceConfigurer
 *  for the Token validity with scopes.
 * 
 *  Now, create a Private key and public key by using openssl.

	You can use the following commands for generating private key.
	
	openssl genrsa -out jwt.pem 2048
	openssl rsa -in jwt.pem 
	You can use For public key generation use the below commands.
	
	openssl rsa -in jwt.pem -pubout 
	For the version of Spring Boot latter than 1.5 release, add the below property in your application.properties 
	file to define OAuth2 Resource filter order.
	
	security.oauth2.resource.filter-order=3 
	YAML file users can add the below property in YAML file.

security:
   oauth2:
      resource:
         filter-order: 3 
 *  
 */
@Configuration
public class OAuth2Config extends AuthorizationServerConfigurerAdapter {
   private String clientid = "tutorialspoint";
   private String clientSecret = "my-secret-key";
   private String privateKey = "jwt.pem";
   private String publicKey = "jwt_public pem";

   @Autowired
   @Qualifier("authenticationManagerBean")
   private AuthenticationManager authenticationManager;
   
   @Bean
   public JwtAccessTokenConverter tokenEnhancer() {
      JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
      converter.setSigningKey(privateKey);
      converter.setVerifierKey(publicKey);
      return converter;
   }
   @Bean
   public JwtTokenStore tokenStore() {
      return new JwtTokenStore(tokenEnhancer());
   }
   @Override
   public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
      endpoints.authenticationManager(authenticationManager).tokenStore(tokenStore())
      .accessTokenConverter(tokenEnhancer());
   }
   @Override
   public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
      security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
   }
   @Override
   public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
      clients.inMemory().withClient(clientid).secret(clientSecret).scopes("read", "write")
         .authorizedGrantTypes("password", "refresh_token").accessTokenValiditySeconds(20000)
         .refreshTokenValiditySeconds(20000);

   }
}
