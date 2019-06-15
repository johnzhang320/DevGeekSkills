package com.spring.ssl.rest.client2;

import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import com.spring.rest.ssl.model.Constants;
import com.sslcontext.manager.SSLRestTemplateService;

public class HeadForHeadersDemo {
	static  RestTemplate  restTemplate =  SSLRestTemplateService.getInstance().getRestTemplate();
    public static void main(String args[]) {
      //  RestTemplate restTemplate = new RestTemplate();
        String url = Constants.LOCAL_HOST_SERVICE+"data/fetch/{id}";
        HttpHeaders httpHeaders = restTemplate.headForHeaders(url, 100);
        System.out.println(httpHeaders.getDate());
        System.out.println(httpHeaders.getContentType());
    }
}
 