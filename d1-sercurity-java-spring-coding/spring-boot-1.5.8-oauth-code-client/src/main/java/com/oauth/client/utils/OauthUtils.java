package com.oauth.client.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oauth.client.model.AuthCodeForm;

import java.util.Arrays;
 
import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.apache.commons.codec.binary.Base64;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
public class OauthUtils {
	static Logger Log = Logger.getLogger(OauthUtils.class);
	
	public static String fetchTokenByPassword(
			String clientId,
			String clientSecret,
			String username,
			String password,
			String base_token_url
			
			) {
			return fetchTokenByPassword(
					clientId,
					clientSecret,
					username,
					password,
					base_token_url,
					null);
	}
	public static String fetchTokenByAuthorizationCode(
			String clientId,
			String clientSecret,
			String authCode,
			String redirectUrl,
			String base_token_url
 			) {
		return fetchTokenByAuthorizationCode(
				clientId,
				clientSecret,
				authCode,
				redirectUrl,
				base_token_url,
				null);
	}
	/*
	 *  Client provide grant_type=code, and clientId, redirect_uri and scope
	 *  Server provide login page to authenticate user, if OK , redirect client
	 *  application uri
	 */
	
	public static String fetchTokenByAuthorizationCode(
			String clientId,
			String clientSecret,
			String authCode,
			String redirectUrl,
			String base_token_url,
			String fullTokeString[]
			) {
		ResponseEntity<String> response = null;
		RestTemplate restTemplate = new RestTemplate();
		String credentials = clientId+":"+clientSecret;
		String encodedCredentials = new String(Base64.encodeBase64(credentials.getBytes()));

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.add("Authorization", "Basic " + encodedCredentials);

		HttpEntity<String> request = new HttpEntity<String>(headers);

		StringBuffer access_token_url = new StringBuffer();
		access_token_url.append(base_token_url);
		access_token_url.append("?code="+authCode);
		access_token_url.append("&grant_type=authorization_code");
		access_token_url.append("&redirect_uri="+redirectUrl);
		Log.info("access_token_url="+access_token_url.toString());

		response = restTemplate.exchange(access_token_url.toString(), HttpMethod.POST, request, String.class);

		String tokenString = "Token Not Found";
		
		if (response.getStatusCode()==HttpStatus.OK) {
		
		   tokenString = response.getBody();
		   
		   Log.info("Responsed Token Info ="+tokenString);
		   if (fullTokeString!=null) {
			   fullTokeString[0] = tokenString;
		   }
		   tokenString = getJsonValue(tokenString, "access_token");
		  
		}  
		return tokenString;
	}
	
	/*
	 *  Directly get token by client's provided username and password
	 */
	public static String fetchTokenByPassword(
			String clientId,
			String clientSecret,
			String username,
			String password,
			String base_token_url,
			String fullTokeString[]
			) {
		ResponseEntity<String> response = null;
		RestTemplate restTemplate = new RestTemplate();
		String credentials = clientId+":"+clientSecret;
		String encodedCredentials = new String(Base64.encodeBase64(credentials.getBytes()));

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.add("Authorization", "Basic " + encodedCredentials);

		HttpEntity<String> request = new HttpEntity<String>(headers);

		StringBuffer access_token_url = new StringBuffer();
		access_token_url.append(base_token_url);
		access_token_url.append("?grant_type=password");
		access_token_url.append("&username="+username);
		access_token_url.append("&password="+password);
		Log.info("access_token_url="+access_token_url.toString());

		response = restTemplate.exchange(access_token_url.toString(), HttpMethod.POST, request, String.class);

		String tokenString = "Token Not Found";
		
		if (response.getStatusCode()==HttpStatus.OK) {
		
		   tokenString = response.getBody();
		   
		   Log.info("Responsed Token Info ="+tokenString);
		   if (fullTokeString!=null) {
			   fullTokeString[0] = tokenString;
		   }
		   tokenString = getJsonValue(tokenString, "access_token");
		  
		}  
		return tokenString;
	}
	
	 
	
	public static String getJsonValue(String jsonString, String key) {
		String token = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			JsonNode node = mapper.readTree(jsonString);
			token = node.path(key).asText();
		} catch (Exception e) {
			Log.error("Failed to fetch value "+e.getMessage());
			 
		}
		return token;
	}
	
	 
}
