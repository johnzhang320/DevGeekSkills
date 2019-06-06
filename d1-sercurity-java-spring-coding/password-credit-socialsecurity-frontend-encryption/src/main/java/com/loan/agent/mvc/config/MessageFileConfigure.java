package com.loan.agent.mvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
 
public class MessageFileConfigure {
	@Bean
	public ResourceBundleMessageSource messageSource() {
		 
		ResourceBundleMessageSource resourceBundleMessageSource = new ResourceBundleMessageSource();
		resourceBundleMessageSource.setBasename("application");  // message file application_en.properties
		return resourceBundleMessageSource;
	}
 	    
}
