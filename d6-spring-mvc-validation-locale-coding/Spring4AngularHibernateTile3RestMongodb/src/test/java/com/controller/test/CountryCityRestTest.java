package com.controller.test;
import java.net.URI;
import java.util.LinkedHashMap;
import java.util.List;
 
import org.springframework.web.client.RestTemplate;
 
 
 
public class CountryCityRestTest {
	static String REST_SERVICE_URI ="http://localhost:8080/Spring4AngularHibernateTile3RestMongodb";
	 
	public static String getCountryCityJSONString() {
		System.out.println("getCountryCityJSONString()-----------");
		
		 RestTemplate restTemplate = new RestTemplate();
		 String retVal = restTemplate.getForObject(REST_SERVICE_URI+"/countries.html/", String.class);
		 System.out.println("Country JSON String :\n"+retVal);
		 return retVal; 
        
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		getCountryCityJSONString();
	}

}
