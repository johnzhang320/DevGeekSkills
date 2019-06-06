package com.websystique.springmvc.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
/**
 * 
 * Here this class is mainly providing the component-scanning and annotation support.
 * Note that we don’t have any view-resolvers configured as we don’t need one in Rest case.
 *
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.websystique.springmvc")
public class HelloWorldConfiguration {
	

}