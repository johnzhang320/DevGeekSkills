package com.oauth.client.controllers;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.oauth.client.model.AuthCodeForm;
import com.oauth.client.model2.BankAccount;
import com.oauth.client.model2.User;
import com.oauth.client.utils.OauthUtils;

@Controller
public class GetAuthCodeController {
	static final String ACCESS_TOKEN_URI[]= {
			"http://localhost:8080/oauth_server/oauth/token",
			"http://localhost:8080/oauth_server/oauth/token",
			"https://localhost:8443/oauth/token"
 	};
	static final String AUTHORIZE_URI[]= {
			"http://localhost:8080/oauth_server/oauth/authorize",
			"http://localhost:8080/oauth_server/oauth/authorize",
			"https://localhost:8443/oauth/authorize"
 	};
	static final String[] REDIRECT_URI= {
			"http://localhost:8080/oauth_client/showDashboard.html",
			"http://localhost:8080/oauth_client/showBankAccount.html",
			"https://localhost:8080/oauth_client/marshall.html"
	};
	
	static final String[] RESOURCE_SERVICE_URI= {
			"http://localhost:8080/oauth_server/safebox/getDashboardList",
			"http://localhost:8080/oauth_server/safebox/getBankAccountList",
			"https://localhost:8443/marshall/renewone.html"
	};
	
	static Logger Log = Logger.getLogger(GetAuthCodeController.class);
	/*
	 *  
	 * Show Demo Page for fetch authorization code
	 */
	@RequestMapping(value = "/getDashboard", method = RequestMethod.GET)
	public ModelAndView getDashboard() {
		ModelAndView model= new ModelAndView("getAuthorizationCode");
		AuthCodeForm form = new AuthCodeForm();
		form.setResponse_type("code");
		form.setClient_id("my-trusted-client");
		form.setRedirect_uri(REDIRECT_URI[0]);
		form.setResource_uri(RESOURCE_SERVICE_URI[0]);
		form.setAuthorize_uri(AUTHORIZE_URI[0]);
		form.setScope("read");
		model.addObject("authCodeForm",form);
		model.addObject("authorize_uri",form.getAuthorize_uri());
		return model;
	}
	
	@RequestMapping(value = "/getBankAccount", method = RequestMethod.GET)
	public ModelAndView getBankAccount() {
		ModelAndView model= new ModelAndView("getAuthorizationCode");
		AuthCodeForm form = new AuthCodeForm();
		form.setResponse_type("code");
		form.setClient_id("my-trusted-client");
		form.setRedirect_uri(REDIRECT_URI[1]);
		form.setResource_uri(RESOURCE_SERVICE_URI[1]);
		form.setAuthorize_uri(AUTHORIZE_URI[1]);
		form.setScope("read");
		model.addObject("authCodeForm",form);
		model.addObject("authorize_uri",form.getAuthorize_uri());
		return model;
	}
	@RequestMapping(value = "/getMarshall", method = RequestMethod.GET)
	public ModelAndView getMarshall() {
		ModelAndView model= new ModelAndView("getAuthorizationCode");
		AuthCodeForm form = new AuthCodeForm();
		form.setResponse_type("code");
		form.setClient_id("my-trusted-client");
		form.setRedirect_uri(REDIRECT_URI[2]);
		form.setResource_uri(RESOURCE_SERVICE_URI[2]);
		form.setAuthorize_uri(AUTHORIZE_URI[2]);
		form.setScope("read");
		model.addObject("authCodeForm",form);
		model.addObject("authorize_uri",form.getAuthorize_uri());
		return model;
	}
	/*
	 *  After Demo Form Submit to Authorization Server
	 *  Once User Authorized , the Server call back this service by redirect_url
	 */
	@RequestMapping(value = "/showDashboard.html", method = RequestMethod.GET)
	public ModelAndView showDashboard(
			@RequestParam("code") String code
			) throws JsonProcessingException, IOException {
		
		Log.info("Authorization Code------" + code);
	 
		String fullTokenString[]=new String[1];
		String token =OauthUtils.fetchTokenByAuthorizationCode(
				"my-trusted-client",
				"secret",
				code,
				REDIRECT_URI[0],
				ACCESS_TOKEN_URI[0],
				fullTokenString
				);    // this time we fetch token , must use oauth/token

		Log.info("Received Access Token="+token);

		

		// Use the access token for authentication
		HttpHeaders headers1 = new HttpHeaders();
		headers1.add("Authorization", "Bearer " + token);
		
		headers1.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		
		HttpEntity<String> entity = new HttpEntity<>(headers1);
		
		RestTemplate restTemplate = new RestTemplate();
		String url =RESOURCE_SERVICE_URI[0];
		Log.info("Resource URL ="+url);
		
		ResponseEntity<User[]> users = restTemplate.exchange(url, HttpMethod.GET, entity, User[].class);
		User[] userArray = users.getBody();
		List<User> list =Arrays.asList(userArray);
		list.forEach(x->System.out.println(x.getName()+","+x.getAddress()+","+x.getEmail()));

		ModelAndView model = new ModelAndView("dashboard");
		model.addObject("users", list);
		model.addObject("fullTokenString", "Authorization Code="+code+"<br/><br/>Rediret URL:<br/>"+REDIRECT_URI[1]+"<br/><br/>Resource Server URL:<br/>"+url+"<br/><br/>"+"Oauth Access Token Information:<br/>"
				+fullTokenString[0].replace(",", ",<br/>").replace("{","{<br/>").replace("}", "<br/>}"));
		return model;
	}
	
	
	/*
	 *  After Demo Form Submit to Authorization Server
	 *  Once User Authorized , the Server call back this service by redirect_url
	 */
	@RequestMapping(value = "/showBankAccount.html", method = RequestMethod.GET)
	public ModelAndView showBankAccounts(
			@RequestParam("code") String code
			) throws JsonProcessingException, IOException {
		
		Log.info("Authorization Code------" + code);
		 
		String fullTokenString[]=new String[1];
		String token =OauthUtils.fetchTokenByAuthorizationCode(
				"my-trusted-client",
				"secret",
				code,
				REDIRECT_URI[1],
				ACCESS_TOKEN_URI[1],
				fullTokenString);    // this time we fetch token , must use oauth/token

		Log.info("Received Access Token="+token);

		// Use the access token for authentication
		HttpHeaders headers1 = new HttpHeaders();
		headers1.add("Authorization", "Bearer " + token);
		
		headers1.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		
		HttpEntity<String> entity = new HttpEntity<>(headers1);
		String url =RESOURCE_SERVICE_URI[1];
		Log.info("Resource URL ="+url);
		RestTemplate restTemplate = new RestTemplate();
		 
		
		ResponseEntity<BankAccount[]> users = restTemplate.exchange(url, HttpMethod.GET, entity, BankAccount[].class);
		BankAccount[] userArray = users.getBody();
		List<BankAccount> list =Arrays.asList(userArray);
		list.forEach(x->System.out.println(x.getName()+","+x.getAddress()+","+x.getEmail()));

		ModelAndView model = new ModelAndView("bankaccount");
		model.addObject("bankAccount", list);
		model.addObject("fullTokenString","Authorization Code="+code+"<br/><br/>Rediret URL:<br/>"+REDIRECT_URI[1]+"<br/><br/>Resource Server URL:<br/>"+url+"<br/><br/>"+"Oauth Access Token Information:<br/>"
				+fullTokenString[0].replace(",", ",<br/>").replace("{","{<br/>").replace("}", "<br/>}"));
		return model;
	}
	
	/*
	 *  After Demo Form Submit to Authorization Server
	 *  Once User Authorized , the Server call back this service by redirect_url
	 */
	@RequestMapping(value = "/marshall.html", method = RequestMethod.GET)
	public ModelAndView marshall(
			@RequestParam("code") String code
			) throws JsonProcessingException, IOException {
		
		Log.info("Authorization Code------" + code);
		 
		String fullTokenString[]=new String[1];
		String token =OauthUtils.fetchTokenByAuthorizationCode(
				"my-trusted-client",
				"secret",
				code,
				REDIRECT_URI[2],
				ACCESS_TOKEN_URI[2],
				fullTokenString);    // this time we fetch token , must use oauth/token

		Log.info("Received Access Token="+token);

		// Use the access token for authentication
		HttpHeaders headers1 = new HttpHeaders();
		headers1.add("Authorization", "Bearer " + token);
		
		headers1.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		
		HttpEntity<String> entity = new HttpEntity<>(headers1);
		String url =RESOURCE_SERVICE_URI[2];
		Log.info("Resource URL ="+url);
		RestTemplate restTemplate = new RestTemplate();
		 
		
		ResponseEntity<BankAccount[]> users = restTemplate.exchange(url, HttpMethod.GET, entity, BankAccount[].class);
		BankAccount[] userArray = users.getBody();
		List<BankAccount> list =Arrays.asList(userArray);
		list.forEach(x->System.out.println(x.getName()+","+x.getAddress()+","+x.getEmail()));

		ModelAndView model = new ModelAndView("bankaccount");
		model.addObject("bankAccount", list);
		model.addObject("fullTokenString","Authorization Code="+code+"<br/><br/>Rediret URL:<br/>"+REDIRECT_URI[1]+"<br/><br/>Resource Server URL:<br/>"+url+"<br/><br/>"+"Oauth Access Token Information:<br/>"
				+fullTokenString[0].replace(",", ",<br/>").replace("{","{<br/>").replace("}", "<br/>}"));
		return model;
	}
}