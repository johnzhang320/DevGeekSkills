package com.boot.security.auth.controller;

import org.apache.log4j.Logger;
 
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.frontend.encrypt.utils.OauthUtils;

import java.util.List;

import com.boot.security.auth.model.Employee;
import com.boot.security.auth.model2.BankAccount;

import java.util.Arrays;

import org.apache.commons.codec.binary.Base64;
/**
 * https://www.programcreek.com/java-api-examples/?api=org.springframework.http.HttpEntity
 * public Client build() {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

    //prepare ROPC request
    MultiValueMap<String, String> params = new LinkedMultiValueMap();
    params.add(CLIENT_ID_PARAM, clientId);
    params.add(CLIENT_SECRET_PARAM, clientSecret);
    params.add(GRANT_TYPE_PARAM, "password");
    params.add(USERNAME_PARAM, username);
    params.add(PASSWORD_PARAM, password);

    RestTemplate template = new RestTemplate();
    HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity(params, headers);
    String tokenUrl = String.format("%s/oauth2/v1/token", baseUrl);

    //obtain access token and return client instance
    Client client = template
            .exchange(tokenUrl, HttpMethod.POST, entity, Client.class)
            .getBody();

    client.init(baseUrl);

    return client;
}

--------------------------------------------

RestTemplate restTemplate = new RestTemplate();

	
 *
 */

@RestController
@Controller
@RequestMapping( value="/oauth")
public class RestfulController {
	
	static final String CRAND_TYPE="password";
 	static final String USERNAME="johnzhang320";//"larry123";
	static final String PASSWORD="new=778899";  //"helloworld";
 	static final String CLIENT_ID="my-trusted-client";
	static final String CLIENT_SECRET="secret";
 	static final String AHTHORIZE_TOKEN_URL = "http://localhost:8092/oauth/token";
	
	static Logger Log = Logger.getLogger(RestfulController.class);
	
	
	@RequestMapping(value="/fetch_token", method=RequestMethod.GET) 
	public String fetchToken() {
		
		String tokenString = OauthUtils.fetchTokenByPassword(CLIENT_ID, CLIENT_SECRET, USERNAME, PASSWORD, AHTHORIZE_TOKEN_URL);
		
		return tokenString;
	}
	
	@RequestMapping(value="/fetch_bankaccount", method=RequestMethod.GET) 
	public ModelAndView fetchBankAccounts() {
		
		RestTemplate restTemplate = new RestTemplate();
		String fullTokenString[] = new String[1];
		String token= OauthUtils.fetchTokenByPassword(CLIENT_ID, CLIENT_SECRET, USERNAME, PASSWORD, AHTHORIZE_TOKEN_URL,fullTokenString);
		 
		String url = "http://localhost:8092/safebox/getBankAccountList";
		// Use the access token for authentication
		HttpHeaders headers1 = new HttpHeaders();
		headers1.add("Authorization", "Bearer " + token);
		HttpEntity<String> entity = new HttpEntity<>(headers1);

		ResponseEntity<BankAccount[]> BankAccounts = restTemplate.exchange(url, HttpMethod.GET, entity, BankAccount[].class);
		System.out.println(BankAccounts);
		BankAccount[] BankAccountArray = BankAccounts.getBody();
        List<BankAccount> list= Arrays.asList(BankAccountArray);
        list.forEach(x->System.out.println(x.toString()));
		ModelAndView model = new ModelAndView("bankaccount");
		model.addObject("bankAccount", list);
		model.addObject("fullTokenString", "Resource Server URL:<br/>"+url+"<br/><br/>"+"Oauth Access Token Information:<br/>"
		+fullTokenString[0].replace(",", ",<br/>").replace("{","{<br/>").replace("}", "<br/>}"));
		return model;
	}
}
