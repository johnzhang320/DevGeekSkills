package com.controller.test;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.web.client.RestTemplate;

import com.locale.message.utils.Constants;

public class DmarcControllerTest {
	
	static String REST_SERVICE_URI =Constants.REST_SERVICE_URI;	    /* GET */
	    @SuppressWarnings("unchecked")
	    private static void listMasters(){
	        System.out.println("Testing list Masters API begin-----------");
	         
	        RestTemplate restTemplate = new RestTemplate();
	        List<LinkedHashMap<String, Object>> usersMap = restTemplate.getForObject(REST_SERVICE_URI+"/masters.html/", List.class);
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
	//	listMasters();
	}

}
