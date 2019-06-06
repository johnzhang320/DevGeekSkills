package com.oauth2.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
/**
 *  Authorization Server
	Authorization Server is a supreme architectural component for Web API Security. The Authorization Server acts a centralization 
	authorization point that allows your apps and HTTP endpoints to identify the features of your application.
	
	Resource Server
	Resource Server is an application that provides the access token to the clients to access the Resource Server HTTP Endpoints. 
	It is collection of libraries which contains the HTTP Endpoints, static resources, and Dynamic web pages.
	
	OAuth2
	OAuth2 is an authorization framework that enables the application Web Security to access the resources from the client. 
	To build an OAuth2 application, we need to focus on the Grant Type (Authorization code), Client ID and Client secret.
	
	 
	JWT Token
	JWT Token is a JSON Web Token, used to represent the claims secured between two parties. You can learn more about the JWT
	 token at www.jwt.io/.
	
	Now, we are going to build an OAuth2 application that enables the use of Authorization Server, Resource Server with the 
	help of a JWT Token.
	
	You can use the following steps to implement the Spring Boot Security with JWT token by accessing the database.
 *  
 *
 */
/**
 * 
 * 	In the main Spring Boot application, add the @EnableAuthorizationServer and @EnableResourceServer annotation to act as an 
 * 	Auth server and Resource Server in the same application.

	Also, you can use the following code to write a simple HTTP endpoint to access the API with Spring Security by using JWT Token.
 *
 */
@SpringBootApplication
  
@EnableAuthorizationServer
@EnableResourceServer
public class SpringbootOauth2JwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootOauth2JwtApplication.class, args);
	}

}

