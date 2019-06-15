package com.spring.ssl.rest.configure;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.log4j.Logger;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.springframework.web.client.RestTemplate;

import com.spring.rest.ssl.model.Constants;
import com.sslcontext.manager.HttpsClientManager;

import javax.net.ssl.SSLContext;

@Configuration
@Component 
public class SSLRestTemplateConfiguration {
	static Logger Log = Logger.getLogger(SSLRestTemplateConfiguration.class);
    private String allPassword = "allpassword";
    
    @Bean 
    public RestTemplateBuilder restTemplatebuilder() {
    	return new RestTemplateBuilder();
    }

    @Bean
    public RestTemplate restTemplate() {
    	RestTemplate ret=null;
    	try {
     	HttpsClientManager clientMgr = new HttpsClientManager(
        		Constants.trustStoreFilename,
        		Constants.trustStorePassword,
        		Constants.keystoreFilename,
        		Constants.keystorePassword
        	);
         HttpClient client =clientMgr.getHttpsClientConfig();
         ret= restTemplatebuilder()
                .requestFactory(new HttpComponentsClientHttpRequestFactory(client))
                .build();
    	} catch (Exception e) {
    		Log.info("Failed to create restTemplate! "+e.getLocalizedMessage());
    	}
    	return ret;
    }
}