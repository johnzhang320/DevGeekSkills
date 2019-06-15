package com.sslcontext.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.spring.ssl.rest.configure.SSLRestTemplateConfiguration;
 
public class SSLRestTemplateService {
	private static RestTemplate restTemplate = null;
	
	private static SSLRestTemplateService handler = null;
	
	public static SSLRestTemplateService getInstance() {
		if (handler==null) {
			handler=new  SSLRestTemplateService();
		}
		return handler;
	}
	
	
	private SSLRestTemplateService() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(SSLRestTemplateConfiguration.class);
		ctx.refresh();
		restTemplate = ctx.getBean(RestTemplate.class);
	 
	}
	
	
	public RestTemplate getRestTemplate() {
		 
		return restTemplate;
	}
	
}
