package com.apple.cert.rest.api;
/**
 * 
SpringBootServletInitializer enables process used in Servlet 3.0 using web.xml


@SpringBootApplication: This is a convenience annotation that is equivalent to declaring 
@Configuration 

 
@EnableAutoConfiguration and @ComponentScan.


 */
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class SpringBootRestApiAppInitializer implements WebApplicationInitializer {

    public void onStartup(ServletContext container) throws ServletException {

        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.setServletContext(container);
        ServletRegistration.Dynamic servlet = container.addServlet("dispatcher", new DispatcherServlet(ctx));
        servlet.setLoadOnStartup(1);
        servlet.addMapping("/");
    }
  
   
}