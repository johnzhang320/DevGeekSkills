package com.apple.cert.rest.api;
/**
 * 
SpringBootServletInitializer enables process used in Servlet 3.0 using web.xml


@SpringBootApplication: This is a convenience annotation that is equivalent to declaring 
@Configuration 

 
@EnableAutoConfiguration and @ComponentScan.


 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
 
@SpringBootApplication(scanBasePackages={"com.apple.cert"})// same as @Configuration @EnableAutoConfiguration @ComponentScan combined
public class SpringBootRestApiApp {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRestApiApp.class, args);
	}
}
