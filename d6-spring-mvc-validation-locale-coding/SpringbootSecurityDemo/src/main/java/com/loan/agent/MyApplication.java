package com.loan.agent;



import org.springframework.boot.SpringApplication;
 
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.builder.SpringApplicationBuilder;

 


/**
 * 
 * @author john zhang john_zhang3@apple.com
 * 
 * This is start point of CME service which start web server and provides all service 
 *
 */
/** The @EnableCaching annotation triggers a post processor that inspects every Spring bean for the presence of 
* caching annotations on public methods. If such an annotation is found, a proxy is automatically created to 
* intercept the method call and handle the caching behavior accordingly.

	The annotations that are managed by this post processor are Cacheable, CachePut and CacheEvict. You can refer 
	to the javadocs and the documentation for more details.
	
	Spring Boot automatically configures a suitable CacheManager to serve as a provider for the relevant cache. 
	See the Spring Boot documentation for more details.
	
	Our sample does not use a specific caching library so our cache store is the simple fallback that uses 
	ConcurrentHashMap. The caching abstraction supports a wide range of cache library and is fully compliant 
	with JSR-107 (JCache).
	*/
//@ComponentScan("com.loan.agent.mvc")
@SpringBootApplication //(scanBasePackages={"com.loan.agent.mvc"})// same as @Configuration @EnableAutoConfiguration @ComponentScan combined 
public class MyApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyApplication.class, args);
	}
	 
    
} 
