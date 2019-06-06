package com.course.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
// SpringBootServletInitializer support deploy war file to tomcat seperated server
@SpringBootApplication
public class CourseApiDataApplication extends SpringBootServletInitializer {
	   @Override
	   protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	      return application.sources(CourseApiDataApplication.class);
	   }

	public static void main(String[] args) {
		SpringApplication.run(CourseApiDataApplication.class, args);
	}

}

