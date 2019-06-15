package com.spring.ssl.rest.client2;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.spring.rest.ssl.model.Constants;
import com.spring.rest.ssl.model2.Person;
import com.sslcontext.manager.SSLRestTemplateService;

public class ExchangeDemo {
	static  RestTemplate  restTemplate =  SSLRestTemplateService.getInstance().getRestTemplate();
    public static void main(String args[]) {
        
		String uri = Constants.LOCAL_HOST_SERVICE+"data/exchange/{id}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>("Hello World!", headers);
        ResponseEntity<Person> personEntity = restTemplate.exchange(uri, HttpMethod.GET, entity, Person.class, 100);
        System.out.println("ID:"+personEntity.getBody().getId());
        System.out.println("Name:"+personEntity.getBody().getName());
        System.out.println("Village:"+personEntity.getBody().getAddress().getVillage());
    }
}
