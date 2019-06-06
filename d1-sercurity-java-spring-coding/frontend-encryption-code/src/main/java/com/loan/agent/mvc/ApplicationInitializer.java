package com.loan.agent.mvc;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
/*
 * This configuration is responsible to initialize spring based web application. We have implemented 
 * WebApplicationInitializer.java to configure the ServletContext programmatically. 
 * No body explain this more , I figure out it means web.xml
 * 
 *  <servlet>
    <servlet-name>dispatcher</servlet-name>
    <servlet-class>
			org.springframework.web.servlet.DispatcherServlet
		</servlet-class>
    <load-on-startup>1</load-on-startup>
  </servlet>
  
   <servlet-mapping>
    <servlet-name>dispatcher</servlet-name>
    <url-pattern>/</url-pattern>
  </servlet-mapping>
  
   <context-param>
    <param-name>contextConfigLocation</param-name>
    <param-value>/WEB-INF/dispatcher-servlet.xml</param-value>
  </context-param>
  
   <listener>
    <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
  </listener>
 */
/*
 *  if main program name is Application, here must be ApplicationInitializer
 *  if main program name is MyApplication , here must be MyApplicationInitializer, no body tell this , cause disaster
 *  
 */

public class ApplicationInitializer implements WebApplicationInitializer {

    public void onStartup(ServletContext container) throws ServletException {

        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.setServletContext(container);
        ServletRegistration.Dynamic servlet = container.addServlet("dispatcher", new DispatcherServlet(ctx));
        servlet.setLoadOnStartup(1);
        servlet.addMapping("/");
    }
}