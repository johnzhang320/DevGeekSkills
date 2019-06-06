package com.websystique.springmvc.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
/**
 * Add Configuration Class
	Add the below mentioned class under src/main/java with specified package as shown below. 
	This configuration class can be treated as a replacement of spring-servlet.xml as it 
	contains all the information required for component-scanning and view resolver.
 *  
	@Configuration indicates that this class contains one or more bean methods annotated with 
	@Bean producing bean manageable by spring container
	@EnableWebMvc is equivalent to mvc:annotation-driven in XML.
	@ComponentScan is equivalent to context:component-scan base-package="..." providing with 
	               where to look for spring managed beans/classes.
 */
@Configuration   // one or more bean method
@EnableWebMvc    // enable annotation same as mvc:annotation-driven in XML
@ComponentScan(basePackages = "com.websystique.springmvc")  // scan base package where look for spring managed bean
public class HelloWorldConfiguration {
	
	@Bean(name="HelloWorld")    // defined the Bean in XML
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");

		return viewResolver;
	}

}

/**
 *  Above Configuration class is equivalent to following XML counterpart:
 *  
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:context="http://www.springframework.org/schema/context"
    xmlns:mvc="http://www.springframework.org/schema/mvc"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-4.0.xsd
    http://www.springframework.org/schema/mvc http://www.springframework.org/schema/mvc/spring-mvc-4.0.xsd
    http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-4.0.xsd">
 
    <context:component-scan base-package="com.websystique.springmvc" />
 
    <mvc:annotation-driven />
     
    <bean
        class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <property name="prefix">
            <value>/WEB-INF/views/</value>
        </property>
        <property name="suffix">
            <value>.jsp</value>
        </property>
    </bean>
 
</beans>

*/