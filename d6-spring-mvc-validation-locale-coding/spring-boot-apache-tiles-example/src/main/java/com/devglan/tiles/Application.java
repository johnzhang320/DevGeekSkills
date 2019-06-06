package com.devglan.tiles;

import java.io.File;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import com.devglan.tiles.controller.Utils;
import com.devglan.tiles.controller.WorkHome;

 

@SpringBootApplication
public class Application extends SpringBootServletInitializer{
	static Logger Log=Logger.getLogger(Application.class);
 	private static Class<Application> applicationClass = Application.class;
	 
    public static void main(String[] args) {
    	  
    	    SpringApplication.run(Application.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(applicationClass);
    }

}