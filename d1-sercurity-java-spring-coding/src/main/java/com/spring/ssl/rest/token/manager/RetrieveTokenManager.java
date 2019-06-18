package com.spring.ssl.rest.token.manager;

 
import java.util.Arrays;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import com.sslcontext.manager.SSLRestTemplateService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.ssl.rest.utils.Constants;
import com.spring.ssl.rest.utils.Utils;


public class RetrieveTokenManager {
	public static Logger Log =Logger.getLogger(RetrieveTokenManager.class.getName());	
	static  RestTemplate  restTemplate =  SSLRestTemplateService.getInstance().getRestTemplate();
	private final static RetrieveTokenManager handler = new RetrieveTokenManager();
	private TokenVO defaultTokenObject;
	
	public static RetrieveTokenManager getInstance() {
		return handler;
	}
	private RetrieveTokenManager() {
		defaultTokenObject = getAccessTokenVO();
	}
	
	
	public TokenVO getDefaultTokenObject() {
		return defaultTokenObject;
	}
	public void setDefaultTokenObject(TokenVO defaultTokenObject) {
		this.defaultTokenObject = defaultTokenObject;
	}
	public String getAccessTokenPasswordGrantType() { 
		return getAccessTokenPasswordGrantType(
					Constants.LOCAL_HOST_SERVICE,
					Constants.TRUSTED_CLIENT_ID,
					Constants.TRUSTED_CLIENT_SECRET,
					Constants.RESOURCE_OWNER_USERNAME,
					Constants.RESOURCE_OWNER_PASSWORD
				);
	}
	private TokenVO getAccessTokenVO() {
		return getAccessTokenVO(
				Constants.LOCAL_HOST_SERVICE,
				Constants.TRUSTED_CLIENT_ID,
				Constants.TRUSTED_CLIENT_SECRET,
				Constants.RESOURCE_OWNER_USERNAME,
				Constants.RESOURCE_OWNER_PASSWORD
			);
	}
	
	
	public TokenVO getAccessTokenVO(String serverURI,      // such as https://localhost:8092/spring-boot-restful-ouath-ssl-server/
									String clientId,
									String clientSecret,
									String resourceOwnerUsername,
									String resourceOwnerPassword) { 
			String output=getAccessTokenPasswordGrantType(
					serverURI,      // such as https://localhost:8092/spring-boot-restful-ouath-ssl-server/
					clientId,
					clientSecret,
					resourceOwnerUsername,
					resourceOwnerPassword
				);
			ObjectMapper mapper = new ObjectMapper();
			TokenVO vo  = new TokenVO();
			try {
				vo =mapper.readValue(output, TokenVO.class);
			} catch (Exception e) {
				Log.info("Failed to Convert to TokenVO "+e.getMessage());
				return null;
			}
			return vo;
	}
	public String getAccessTokenPasswordGrantType(
				String serverURI,      // such as https://localhost:8092/spring-boot-restful-ouath-ssl-server/
				String clientId,
				String clientSecret,
				String resourceOwnerUsername,
				String resourceOwnerPassword
			
			) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));    // client receives Json format, only way to set List 
		
		String credentials = clientId+":"+clientSecret;
		
		String encodedCredentials = new String(Base64.encodeBase64(credentials.getBytes()));

		headers.add("Authorization", "Basic " + encodedCredentials);
		
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		
		StringBuffer sb = new StringBuffer();
		sb.append(serverURI);
		sb.append("oauth/token?grant_type=password");
		sb.append("&username="+resourceOwnerUsername);
		sb.append("&password="+resourceOwnerPassword);
		
		Log.info("TokenRetrieve URL="+sb.toString());
		
		String output=null;
		//ResponseEntity<String[]> response=restTemplate.exchange(sb.toString(),HttpMethod.POST,entity,String[].class);
		ResponseEntity<String> response=restTemplate.exchange(sb.toString(),HttpMethod.POST,entity,String.class);
		if (response.getStatusCodeValue()==200) {
			output = response.getBody();
			//output = result[0];
			Log.info("SUcceed to retrieve access token as following:\n"+Utils.JsonPretty(output));
		} else {
			Log.info("Failed to get access token and error code="+response.getStatusCodeValue());
		}
		
		
		return output;
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
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TokenVO vo = RetrieveTokenManager.getInstance().getDefaultTokenObject();
		//Log.info("Token Object is "+vo.toString());
	}

}
