package com.spring.ssl.rest.client2;

import org.springframework.web.client.RestTemplate;

import com.spring.rest.ssl.model.Constants;
import com.spring.rest.ssl.model2.Company;
import com.sslcontext.manager.SSLRestTemplateService;
 

public class GetForObjectDemoWithXML {
	static  RestTemplate  restTemplate =  SSLRestTemplateService.getInstance().getRestTemplate();
    public static void main(String args[]) {
        
        Company company = restTemplate.getForObject(Constants.LOCAL_HOST_SERVICE+"data/fetchxml/{id}", Company.class, 200);
        System.out.println("ID: " + company.getId());
        System.out.println("Company: " + company.getCompanyName());
        System.out.println("CEO: " + company.getCeoName());
    }
}
