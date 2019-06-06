package com.loan.agent.mvc.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import com.basic.hibernate.service.AgentTableService;
import com.basic.hibernate.service.AgentTableServiceImpl;
import com.basic.hibernate.service.CountryCityService;
import com.basic.hibernate.service.CountryCityServiceImpl;
import com.basic.hibernate.service.DmarcReportService;
import com.basic.hibernate.service.DmarcReportServiceImpl;

/**
 *  Very important when we use different package not be scanned 
 */
@Configuration
public class BeanConfiguration {
	@Bean
	public AgentTableService agentTableService() {
		return new AgentTableServiceImpl();
	}
	@Bean
	public CountryCityService countryCityService() {
		return new CountryCityServiceImpl();
	}
	@Bean
	public DmarcReportService dmarcReportService() {
		return new DmarcReportServiceImpl();
	}
	
}
