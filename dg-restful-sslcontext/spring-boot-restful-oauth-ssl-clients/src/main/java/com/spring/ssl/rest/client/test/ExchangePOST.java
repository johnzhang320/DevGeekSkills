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

import com.spring.rest.ssl.model.Dept;
import com.spring.rest.ssl.model.Person;
import com.spring.rest.ssl.model.Product;
import com.spring.ssl.rest.token.manager.RetrieveTokenManager;
import com.spring.ssl.rest.utils.Constants;
import com.sslcontext.manager.SSLRestTemplateService;

public class ExchangePOST {
	public static Logger Log =Logger.getLogger(ExchangePOST.class.getName());	
	static  RestTemplate  restTemplate =  SSLRestTemplateService.getInstance().getRestTemplate();
	
	static String token = RetrieveTokenManager.getInstance().getDefaultTokenObject().getAccess_token();
	
	public static void main(String [] args) {
		Log.info("Test 1 Initialize Depts data and show");
		ExchangeGET.exchangeGETInitData();
		
		Log.info("Test 2 Person(J.Lo)join to Dept");
		
		Person joinPerson = new Person(
				1001,"Jennifer", "Lopaz", "1985-06-21", "female", 12590000F);
		exchangePOSTPersonToDept(joinPerson);
		
		Log.info("Test 2 Person(Jessic Abel)join to Dept");
		
	    joinPerson = new Person(
				1001,"Jessic", "Abel", "1992-04-14", "female", 10590000F);
		exchangePOSTPersonToDept(joinPerson);
		
		Log.info("Test 4 Show all Depts");
		ExchangeGET.exchangeGETAllDeptsToString();
	 
		Log.info("Test 5 Create new Department data in Objects");
	 
 	    joinPerson = new Person(
				1007,"Robort", "Williams", "1972-01-21", "male", 225900F);	 
 	     List<Person> list = new ArrayList<Person>();
 	     list.add(joinPerson);
 	     Dept dept = new Dept(1007, "Accounting", "San Francisco", list);
		exchangePOSTNewDept(dept);
		
		Log.info("Test 6 Show all Depts again");
		ExchangeGET.exchangeGETAllDeptsToString();
	 
		
		Log.info("Test 7 Create a new product");
		Product prod = new Product("Battle Hymm of the Tiger Mother", "Eduction experience to leading two teenage daugthers", 36.99F, "1007");
			
		exchangePOSTNewProduct(prod);
 		 
	}
	public static Dept exchangePOSTPersonToDept(Person joinPerson)  {
		
		 String URL = Constants.LOCAL_HOST_SERVICE+"secureServices/person_join_dept";
		 
		
		 String output = null;
		 Dept dept =null;
		 HttpHeaders headers = new HttpHeaders();
		 headers.add("Authorization", "bearer " + token);
		 
	     headers.setContentType(MediaType.APPLICATION_JSON);
	   
 	        
	     HttpEntity<Person> request = new HttpEntity<Person>(joinPerson,headers);
				                   
	     ResponseEntity<Dept> response = restTemplate
      		  .exchange(URL, HttpMethod.POST, request, Dept.class);
      
	     if (response.getStatusCodeValue()== 200) {
 			 return response.getBody();
 		 } 
 		 return null;
	 
	 }
	public static Dept[] exchangePOSTNewDept(Dept dept)  {
		
		 String URL = Constants.LOCAL_HOST_SERVICE+"secureServices/create_dept";
	 
		
		 String output = null;
		 
		 HttpHeaders headers = new HttpHeaders();
		 
	     headers.setContentType(MediaType.APPLICATION_JSON);
		 headers.add("Authorization", "bearer " + token);
		 
	        
	     HttpEntity<Dept> request = new HttpEntity<Dept>(dept,headers);
				                   
	     ResponseEntity<Dept[]> response = restTemplate
     		  .exchange(URL, HttpMethod.POST, request, Dept[].class);
     
	     if (response.getStatusCodeValue()== 200) {
	    	Dept[] array = response.getBody();
	    	for (Dept d: array) System.out.println(d);
			return array;
		 } 
		 return null;
	 
	 }
	public static Product[] exchangePOSTNewProduct(Product product)  {
		
		 String URL = Constants.LOCAL_HOST_SERVICE+"secureServices/create_product";
		 
		 
		 HttpHeaders headers = new HttpHeaders();
	     headers.setContentType(MediaType.APPLICATION_JSON);
		 headers.add("Authorization", "bearer " + token);
		 
	        
	     HttpEntity<Product> request = new HttpEntity<Product>(product,headers);
				                   
	     ResponseEntity<Product[]> response = restTemplate
    		  .exchange(URL, HttpMethod.POST, request, Product[].class);
    
	     if (response.getStatusCodeValue()== 200) {
	    	Product[] array = response.getBody();
	    	for (Product d: array) System.out.println(d);
			return array;
		 } 
		 return null;
	 
	 }
	
	
	

	 
	 
}
