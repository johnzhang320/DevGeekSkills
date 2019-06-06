package com.boot.oauth.sso.client;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;


@SpringBootApplication
@EnableOAuth2Sso
public class OauthSsoClientApplication  {

 //server_port=8084
 //localhost:8084//oauth_sso/demosso

   public static void main(String[] args) {
       SpringApplication.run(OauthSsoClientApplication.class, args);
   }
}
