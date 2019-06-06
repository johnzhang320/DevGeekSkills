package com.loan.agent.mvc.config;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import  org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.context.support.ResourceBundleMessageSource;
@Configuration
public class LocaleConfigure {
	@Bean
	public SessionLocaleResolver localeResolver() {
		Locale defaultLocale = new Locale("en");
		SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
		sessionLocaleResolver.setDefaultLocale(defaultLocale);
		return sessionLocaleResolver;
	}
	
 
 
	@Bean
	public ResourceBundleMessageSource messageSource() {
		 
		ResourceBundleMessageSource resourceBundleMessageSource = new ResourceBundleMessageSource();
		resourceBundleMessageSource.setBasename("application");  // message file application_en.properties
		return resourceBundleMessageSource;
	}
}
