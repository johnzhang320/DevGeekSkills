package com.loan.agent.mvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
//import org.springframework.boot.web.support.SpringBootServletInitializer;
/*
 * creating a spring mvc app with spring boot 1.5 using apache tiles 3 layout. 
 * We will be creating different reusable page fragments such as header, body, 
 * footer using jsp and assemble them in a single jsp page at runtime using configuration file tiles.xml.
 * Also, we will check how to make use of ModelAndView attributes in JSP while using apache tiles.
 */
/*
 * SpringBootServletInitializer configure deloy war to tomcat provider
 * SpringBootServletInitializer enables process used in Servlet 3.0 using web.xml
 * 
 * @SpringBootApplication Equivalent to using @Configuration, @EnableAutoConfiguration 
 * and @ComponentScan with their default attributes:
 * <=Springboot 1.5.9, add  extends SpringBootServletInitializer to make sure deploying war to tomcat to be able to run
 * >Spring 2.1.3 do not need it any more and Spring 2.1.3 can run spring boot's cloud oauth2 and security 
 * 
 */
@EnableOAuth2Sso
@SpringBootApplication
public class OktaApplication extends SpringBootServletInitializer{
	/*
	 * server.port=8092
       http://localhost:8092/SpringbootOAuth2OktaSSO
	 */
	 private static Class<OktaApplication> applicationClass = OktaApplication.class;

    public static void main(String[] args) {
    	//System.setProperty("server.servlet.context-path", "/loanAgent");
        SpringApplication.run(OktaApplication.class, args);
    }

   @Override  // after Spring boot 2.0 do not need it 
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(applicationClass);
    }

}