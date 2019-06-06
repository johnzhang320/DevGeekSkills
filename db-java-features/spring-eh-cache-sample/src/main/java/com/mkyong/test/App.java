package com.mkyong.test;
 
import org.apache.log4j.Logger;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.mkyong.movie.MovieDao;
/**
 * 
 *  Spring supports caching since version 3.1
	Spring cache has been significantly improved since version 4.1
 *  The Spring caching is in the spring-context.jar, to support Ehcache 
 *  caching, you need to include the spring-context-support.jar as well.
 */
public class App {
	
	private static final Logger log = Logger.getLogger(App.class);
	
	public static void main(String[] args) {
     
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
	    MovieDao obj = (MovieDao) context.getBean("movieDao");
	    
	    log.debug("Result : {}"+ obj.findByDirector("Robert Zemeckis"));
	    log.debug("Result : {}"+ obj.findByDirector("Robert Zemeckis"));
	    log.debug("Result : {}"+ obj.findByDirector("Robert Zemeckis"));
	    log.debug("Result : {}"+ obj.findByDirector("Steve burgh"));
	    log.debug("Result : {}"+ obj.findByDirector("Steve burgh"));
	    log.debug("Result : {}"+ obj.findByDirector("Steve burgh"));
	    log.debug("Result : {}"+ obj.findByDirector("Steve burgh"));
	    
	    log.debug("Result : {}"+ obj.findByDirector("Andy Lee"));
	    log.debug("Result : {}"+ obj.findByDirector("Andy Lee"));
	    log.debug("Result : {}"+ obj.findByDirector("Andy Lee"));
	    log.debug("Result : {}"+ obj.findByDirector("Andy Lee"));
	    
	    
	    //shut down the Spring context so that Ehcache got chance to shut down as well
	   // ((ConfigurableApplicationContext)context).close();
	    
	}
}