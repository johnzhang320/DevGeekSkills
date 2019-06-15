package com.spring.ssl.rest.client.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.spring.rest.ssl.model.Constants;
import com.spring.rest.ssl.model.Dept;
import com.spring.rest.ssl.model.Person;
import com.spring.rest.ssl.model.Product;
import com.sslcontext.manager.SSLRestTemplateService;

public class ExchangeDELETE {
	public static Logger Log =Logger.getLogger(ExchangeDELETE.class.getName());	
	static  RestTemplate  restTemplate =  SSLRestTemplateService.getInstance().getRestTemplate();;
	
	 /**
	  * UPDATE (U)
	  * SSL Client PUT test
	  * @param URL           https://localhost:8443/secureServices/join_dept
	  * @param joinPerson    person object
	  * @return
	  */

	public static Dept[] exchangeDELTEDepartment(int dept)  {
		 String URL= Constants.LOCAL_HOST_SERVICE+"secureServices/delete_dept/"+dept;
	 	 
		 HttpHeaders headers = new HttpHeaders();
	     headers.setContentType(MediaType.APPLICATION_JSON);
	        
	     HttpEntity<Person> entity = new HttpEntity<Person>(headers);
				                   
	     ResponseEntity<Dept[]> response = restTemplate
    		  .exchange(URL, HttpMethod.DELETE, entity, Dept[].class);
    
	     if (response.getStatusCodeValue()== 200) {
	    	 
	    	Dept[] array = response.getBody();
	    	for (Dept d: array) System.out.println(d); 
			return array;
		 } 
		 return null;

	 }
	
	public static Dept[] exchangeDELETEPersonFromDept(Person person)  {
		 String URL= Constants.LOCAL_HOST_SERVICE+"secureServices/delete_person";
	 	 
		 HttpHeaders headers = new HttpHeaders();
	     headers.setContentType(MediaType.APPLICATION_JSON);
	     headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	     
	     HttpEntity<Person> entity = new HttpEntity<Person>(person,headers);
				                   
	     ResponseEntity<Dept[]> response = restTemplate
   		  .exchange(URL, HttpMethod.DELETE, entity, Dept[].class);
   
	     if (response.getStatusCodeValue()== 200) {
	    	 
	    	Dept[] array = response.getBody();
	    	for (Dept d: array) System.out.println(d); 
			return array;
		 } 
		 return null;

	 }
	public static Product[] exchangeDELTEProduct(int prodId)  {
		
		 String URL = Constants.LOCAL_HOST_SERVICE+"secureServices/delete_product/"+prodId;
		 
		
		 String output = null;
		 
		 HttpHeaders headers = new HttpHeaders();
	     headers.setContentType(MediaType.APPLICATION_JSON);
	        
	     HttpEntity<Product> request = new HttpEntity<Product>(headers);
				                   
	     ResponseEntity<Product[]> response = restTemplate
    		  .exchange(URL, HttpMethod.DELETE, request, Product[].class);
    
	     if (response.getStatusCodeValue()== 200) {
	    	Product[] array = response.getBody();
	    	for (Product d: array) System.out.println(d);
			return array;
		 } 
		 return null;
	 
	 }

	public static void main(String [] args) {
		 
		Log.info("Test 1 Initialize Depts data and show");
		ExchangeGET.exchangeGETInitData();
		
		Log.info("Test 2 Update Person(J.Lo and Jennifer Lowerance) join to Dept");
		
		Person joinPerson1 = new Person(
				1005,"Jennifer", "Lopaz", "1985-06-21", "female", 12590000F);
		ExchangePOST.exchangePOSTPersonToDept(joinPerson1);
		
		Person joinPerson2 = new Person(
				1005,"Jennifer", "Lowerance", "1991-07-01", "female", 11590000F);
		
		ExchangePOST.exchangePOSTPersonToDept(joinPerson2); 
		
		Log.info("Test 3 Delete Person(J.Lo) from Dept");
		
		exchangeDELETEPersonFromDept(joinPerson2);
		
		Log.info("Test 4 Delete product");
		exchangeDELTEProduct(1003);
	}

	 
	 
}
