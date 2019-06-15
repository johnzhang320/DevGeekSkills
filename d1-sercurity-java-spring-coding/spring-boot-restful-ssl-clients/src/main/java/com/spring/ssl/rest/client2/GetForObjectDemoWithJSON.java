package com.spring.ssl.rest.client2;

import org.springframework.web.client.RestTemplate;

import com.spring.rest.ssl.model.Constants;
import com.spring.rest.ssl.model2.Person;
import com.sslcontext.manager.SSLRestTemplateService;

public class GetForObjectDemoWithJSON {
	
	static  RestTemplate  restTemplate =  SSLRestTemplateService.getInstance().getRestTemplate();
    public static void main(String args[]) {
        
        Person person = restTemplate.getForObject(Constants.LOCAL_HOST_SERVICE+"data/fetchjson/{id}", Person.class, 200);
        System.out.println("ID: " + person.getId());
        System.out.println("Name: " + person.getName());
        System.out.println("Village Name: " + person.getAddress().getVillage());
    }
}
