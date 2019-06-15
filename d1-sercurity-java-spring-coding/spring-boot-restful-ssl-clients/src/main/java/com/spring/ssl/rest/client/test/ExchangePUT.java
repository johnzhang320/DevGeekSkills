package com.spring.ssl.rest.client.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
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

public class ExchangePUT {
	public static Logger Log =Logger.getLogger(ExchangePUT.class.getName());	
	static  RestTemplate  restTemplate =  SSLRestTemplateService.getInstance().getRestTemplate();
	
	 /**
	  * UPDATE (U)
	  * SSL Client PUT test
	  * @param URL           https://localhost:8443/secureServices/join_dept
	  * @param joinPerson    person object
	  * @return
	  */

	public static Dept exchangePUTPerson(Person joinPerson)  {
		 String URL= Constants.LOCAL_HOST_SERVICE+"secureServices/update_dept_person";
	 	 
		 HttpHeaders headers = new HttpHeaders();
	     headers.setContentType(MediaType.APPLICATION_JSON);
	        
	     HttpEntity<Person> entity = new HttpEntity<Person>(joinPerson,headers);
				                   
	     ResponseEntity<Dept> response = restTemplate
    		  .exchange(URL, HttpMethod.PUT, entity, Dept.class);
    
	     if (response.getStatusCodeValue()== 200) {
	    	Dept dept = response.getBody();
	    	 
			return dept;
		 } 
		 return null;

	 }
	
 
	public static Product[] exchangePUTProduct(Product product)  {
		
		 String URL = Constants.LOCAL_HOST_SERVICE+"secureServices/create_product";
		 if (null==URL) {  
			 return null;
		 }
		
		 String output = null;
		 
		 HttpHeaders headers = new HttpHeaders();
	     headers.setContentType(MediaType.APPLICATION_JSON);
	        
	     HttpEntity<Product> request = new HttpEntity<Product>(product,headers);
				                   
	     ResponseEntity<Product[]> response = restTemplate
    		  .exchange(URL, HttpMethod.PUT, request, Product[].class);
    
	     if (response.getStatusCodeValue()== 200) {
	    	Product[] array = response.getBody();
	    	for (Product d: array) System.out.println(d);
			return array;
		 } 
		 return null;
	 
	 }
	
	
	public static void main(String[] args) {
		 
		Log.info("Test 1 Initialize Depts data and show");
		ExchangeGET.exchangeGETInitData();
		
		Log.info("Test 2 Update Person(J.Lo)join to Dept");
		
		Person joinPerson = new Person(
				1001,"Jennifer", "Lopaz", "1985-06-21", "female", 12590000F);
		exchangePUTPerson(joinPerson);
		
	/*	Log.info("Test 3 Create a new product");
		Product prod = new Product("Battle Hymm of the Tiger Mother", "Eduction experience to leading two teenage daugthers", 36.99F, "1007");
			
		ExchangePOST.exchangePOSTNewProduct(prod);
		
		Log.info("Test 4 Update Product "); 
		prod = new Product("Battle of Midway", "Describe Turn Point War Midway in World War II", 34.99F, "1007");
		
		exchangePUTProduct(prod);*/
	}

	 
	 
}
