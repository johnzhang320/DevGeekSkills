package com.spring.ssl.rest.client2.test;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.spring.rest.ssl.model2.Person;
import com.spring.ssl.rest.token.manager.RetrieveTokenManager;
import com.spring.ssl.rest.utils.Constants;
import com.sslcontext.manager.SSLRestTemplateService;

public class GetForEntityDemo {
	static  RestTemplate  restTemplate =  SSLRestTemplateService.getInstance().getRestTemplate();
	static String token = RetrieveTokenManager.getInstance().getDefaultTokenObject().getAccess_token();
    public static void main(String args[]) {
       
        String url = Constants.LOCAL_HOST_SERVICE+"data/fetch/{name}/{village}";
        Map<String, String> map = new HashMap<String, String>();
        map.put("name", "Mahesh");
        map.put("village", "Dhananjaypur");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "bearer " + token);
        HttpEntity<String> entity = new HttpEntity<String>("Hello World!", headers);
        ResponseEntity<Person> personEntity = restTemplate.getForEntity(url, Person.class,entity , map);
        System.out.println("Name:"+personEntity.getBody().getName());
        System.out.println("Village:"+personEntity.getBody().getAddress().getVillage());
    }
}
