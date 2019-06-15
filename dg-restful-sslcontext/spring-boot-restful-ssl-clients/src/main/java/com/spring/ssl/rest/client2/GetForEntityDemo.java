package com.spring.ssl.rest.client2;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.spring.rest.ssl.model.Constants;
import com.spring.rest.ssl.model2.Person;
import com.sslcontext.manager.SSLRestTemplateService;

public class GetForEntityDemo {
	static  RestTemplate  restTemplate =  SSLRestTemplateService.getInstance().getRestTemplate();
    public static void main(String args[]) {
       
        String url = Constants.LOCAL_HOST_SERVICE+"data/fetch/{name}/{village}";
        Map<String, String> map = new HashMap<String, String>();
        map.put("name", "Mahesh");
        map.put("village", "Dhananjaypur");
        ResponseEntity<Person> personEntity = restTemplate.getForEntity(url, Person.class, map);
        System.out.println("Name:"+personEntity.getBody().getName());
        System.out.println("Village:"+personEntity.getBody().getAddress().getVillage());
    }
}
