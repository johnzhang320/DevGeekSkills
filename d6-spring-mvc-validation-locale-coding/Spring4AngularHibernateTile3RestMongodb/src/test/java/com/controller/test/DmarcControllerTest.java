package com.controller.test;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.web.client.RestTemplate;

public class DmarcControllerTest {
	
	 public static final String REST_SERVICE_URI = "http://localhost:8080/Spring4AngularHibernateTile3RestMongodb";
     
	    /* GET */
	    @SuppressWarnings("unchecked")
	    private static void listMasters(){
	        System.out.println("Testing list Masters API begin-----------");
	         
	        RestTemplate restTemplate = new RestTemplate();
	        List<LinkedHashMap<String, Object>> usersMap = restTemplate.getForObject(REST_SERVICE_URI+"/master/", List.class);
	        System.out.println("Testing list Masters API return-----------"); 
	        if(usersMap!=null){
	            for(LinkedHashMap<String, Object> map : usersMap){
	                System.out.println("MasterId="+map.get("masterId")+", orgName="+map.get("orgName"));
	            }
	        }else{
	            System.out.println("No user exist----------");
	        }
	    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		listMasters();
	}

}
