package com.spring.ssl.rest.client2;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.client.RestTemplate;

import com.spring.rest.ssl.model.Constants;
import com.sslcontext.manager.SSLRestTemplateService;

public class DeleteDemo {
	
	static  RestTemplate  restTemplate =  SSLRestTemplateService.getInstance().getRestTemplate();
	
    public static void main(String args[]) {
        
        String url = Constants.LOCAL_HOST_SERVICE+"data/delete/{name}/{village}";
        Map<String, String> map = new HashMap<String, String>();
        map.put("name", "Mahesh");
        map.put("village", "Dhananjaypur");
        restTemplate.delete(url, map);
    }
}
