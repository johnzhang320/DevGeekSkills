package com.websystique.springmvc.configuration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
/**
 *  
 * Add Initialization class to register the HelloWorldConfiguration.class, like web.xml define
 * where web can find spring-servlet.xml

	Add an initializer class implementing WebApplicationInitializer under src/main/java 
	with specified package as shown below(which in this case acts as replacement of any 
	spring configuration defined in web.xml). During Servlet 3.0 Container startup, 
	this class will be loaded and instantiated and its onStartup method will be called by 
	servlet container.  
	
	com.websystique.springmvc.configuration.HelloWorldInitializer
 *
 */
public class HelloWorldInitializer implements WebApplicationInitializer {

	public void onStartup(ServletContext container) throws ServletException {

		AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
		ctx.register(HelloWorldConfiguration.class);
		ctx.setServletContext(container);

		ServletRegistration.Dynamic servlet = container.addServlet(
				"dispatcher", new DispatcherServlet(ctx));

		servlet.setLoadOnStartup(1);
		servlet.addMapping("/");
	}

}
/**
The content above resembles the content of web.xml from previous tutorial as we are using the 
front-controller DispatherServler, assigning the mapping (url-pattern in xml) and instead of 
providing the path to spring configuration file(spring-servlet.xml) , here we are registering 
the Configuration Class. Overall, we are doing the same thing, just the approach is different.

UPDATE: Note that now you can write the above class even more concisely [and it’s the preferred way], 
by extending AbstractAnnotationConfigDispatcherServletInitializer base class, as shown below:
*/