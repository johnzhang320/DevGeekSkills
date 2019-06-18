package com.spring.ssl.rest.client.test;

 
import java.util.Arrays;


import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.spring.rest.ssl.model.Product;
import com.spring.ssl.rest.token.manager.RetrieveTokenManager;
import com.spring.ssl.rest.utils.Constants;
import com.spring.ssl.rest.utils.Utils;
import com.sslcontext.manager.SSLRestTemplateService;

public class ExchangeGET {
	public static Logger Log =Logger.getLogger(ExchangeGET.class.getName());
	
	static  RestTemplate  restTemplate =  SSLRestTemplateService.getInstance().getRestTemplate();;
	static String token = RetrieveTokenManager.getInstance().getDefaultTokenObject().getAccess_token();
	 
	public static void main(String [] args) {
		Log.info("Access Token="+token);
		Log.info("Test 1 Initialize Depts data and show");
		exchangeGETInitData();
		
		Log.info("Test 2 Show all Depts data in String");
		exchangeGETAllDeptsToString();
		
		Log.info("Test 3 Show all Product data in Objects");
		
		exchangeGETAllProductsToArray();
		
		//Log.info("Test 4 Create a new product");
		//exchangeGETProductByRequestBodyPathVariable(1007);
		
		Log.info("Test 4 Show all Product data in Objects again");
		exchangeGETAllProductsToArray();
		
	}
	
	 /**
	  * Receive (R)
	 * SSL Client GET test 
	 * @param URL    		 Constants.LOCAL_HOST_SERVICE+"secureServices/init_data";
	 * @param prodId   		 1001 to 1006
	 * @return Product
	 */
	public static String exchangeGETInitData()  {
		 
		
		 String URL= Constants.LOCAL_HOST_SERVICE+"secureServices/init_data";
	
		 
		 HttpHeaders headers = new HttpHeaders();
	     headers.setContentType(MediaType.APPLICATION_JSON);
	 	 headers.add("Authorization", "bearer " + token);
	 	
	     HttpEntity request = new HttpEntity(headers);
	     
		 String output = null;
		
	 
		 ResponseEntity<String>  response =
				 restTemplate.exchange(URL , HttpMethod.GET, request, String.class);
	        
				             
		 
		 if (response == null ) {
			 Log.info("Failed : HTTP error code : " );
			
			 return null;
		 } 
		 
		 if (response.getStatusCodeValue() == 200) {
			 
			 output = response.getBody();
			Log.info ("Succeed to get response via SSL Connection to Restul Server" );
			Log.info("All Departments as following\n"+Utils.JsonPretty(output));
			/*
			 *  output here is JSON String, Jersey Json library included Jockson ObjectMapper
			 *  which can convert json back to Pojo object
			 */
		 
		 }
		 return output;
	 } 	
	 /**
	  * Receive (R)
	 * SSL Client GET test 
	 * @param URL    		 Constants.LOCAL_HOST_SERVICE+"secureServices/get_all_depts";
	 * @param prodId   		 1001 to 1006
	 * @return Product
	 */
	public static String exchangeGETAllDeptsToString()  {
		
		 String URL= Constants.LOCAL_HOST_SERVICE+"secureServices/get_all_depts";
		
		 HttpHeaders headers = new HttpHeaders();
	     headers.setContentType(MediaType.APPLICATION_JSON);
	 	 headers.add("Authorization", "Bearer " + token);
	     HttpEntity request = new HttpEntity(headers);
				             
	     
		 ResponseEntity<String>  response =
				 restTemplate.exchange(URL , HttpMethod.GET, request, String.class);
	        
		 String output = null;
		 if (response.getStatusCodeValue()== 200) {
			output = response.getBody();
			Log.info("All Departments as following\n"+Utils.JsonPretty(output));
		 }
		 return output;
	 } 	
	
	public static Product[] exchangeGETAllProductsToArray()  {
		
		 String URL= Constants.LOCAL_HOST_SERVICE+"secureServices/get_all_products";
		
		 HttpHeaders headers = new HttpHeaders();
	     headers.setContentType(MediaType.APPLICATION_JSON);
	 	 headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	 	 headers.add("Authorization", "Bearer " + token);
	     HttpEntity request = new HttpEntity(headers);
	     
		 ResponseEntity<Product[]>  response =
				 restTemplate.exchange(URL , HttpMethod.GET, request, Product[].class);
	        
		 Product[] output = null;
		 if (response.getStatusCodeValue()== 200) {
			 output = response.getBody();
			 for (Product p:output) System.out.println(p.toString());
 		 }
		 return output;
	 } 	
	/*
	 *  GET Method , we also can post object to server by @RequestBody in server side
	 */
	public static Product exchangeGETProductByRequestBodyPathVariable(Integer prodId)  {
		
		 String URL= Constants.LOCAL_HOST_SERVICE+"secureServices/get_product/{prodId}";
		 HttpHeaders headers = new HttpHeaders();
	 	 headers.add("Authorization", "Bearer " + token);
	     headers.setContentType(MediaType.APPLICATION_JSON);
	
	 	 Product prod = new Product("Battle Hymm of the Tiger Mother", "Eduction experience to leading two teenage daugthers", 36.99F, "1007");
	 	 
	 	// in server side , using @RequestBody and @RequestHeader to reterieve HttpEntity(RequestBodeyObject, HeaderObject)
 	 	 HttpEntity<Product> entity = new HttpEntity<Product>(prod, headers);  
 	 	 
 	 	 ResponseEntity<Product>  response =
				 restTemplate.exchange(URL , HttpMethod.GET, entity, Product.class,1007);  // last one is prodId
 		 if (response.getStatusCodeValue()== 200) {
 			 return response.getBody();
 		 } 
 		 return null;
   
	}
	
	 
}
