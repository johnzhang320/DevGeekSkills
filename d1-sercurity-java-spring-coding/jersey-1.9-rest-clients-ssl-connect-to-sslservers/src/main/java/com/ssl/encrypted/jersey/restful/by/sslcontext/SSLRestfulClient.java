package com.ssl.encrypted.jersey.restful.by.sslcontext;
 
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;

import com.ssl.encrypted.jersey.restful.by.sslcontext.model.Constants;
import com.ssl.encrypted.jersey.restful.by.sslcontext.model.Dept;
import com.ssl.encrypted.jersey.restful.by.sslcontext.model.Person;
import com.ssl.encrypted.jersey.restful.by.sslcontext.model.Product;
import com.sslcontext.manager.SSLContextBuilder;
import com.sslcontext.manager.SSLContextSingleton;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

import org.apache.log4j.Logger;



/**
 * 
 * SSLRestfulClient check if URL protocol is https, using SSLContext to establish SSL Connection to Restful Server 
 * Configure com.sun.jersey.api.client.config.DefaultClientConfig by using javax.net.ssl.SSLContext
 * In order to run SSL encrypted channel when Restful must be running on HTTPS,
 * Configure com.sun.jersey.api.client.config.DefaultClientConfig by using javax.net.ssl.SSLContext
 * SSLContext object will be created by the customized SSLContextManager
 * 
 * @author john.zhang320@gmail.com
 *
 */
public class SSLRestfulClient  {
	 
	 public static Logger Log =Logger.getLogger(SSLRestfulClient.class.getName());

	 SSLContext sslContext;
	
	 public SSLRestfulClient(SSLContext sslContext) {
		 this.sslContext = sslContext;
	}
	 /**
	  * 
	  * @param URL           https://localhost:8443/secureServices/join_dept
	  * @param joinPerson    person object
	  * @return
	  */

	public Dept SSLRestfulClientPeopleJoinDepartment(String URL,Person joinPerson)  {
		
		 if (null==URL) {  
			 return null;
		 }
 		
		 Dept output = null;
		 Client client = null;
		 String protocol = URL.substring(0,6).toLowerCase(); 
	
		 try {
			 if (protocol.indexOf("https")!=-1) {
					 
				 ClientSSLContextManager ssl = new ClientSSLContextManager(sslContext);
						
				 ClientConfig clientConfig = ssl.getDefaultClientConfig();
					 
				 clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
					 
				 client = Client.create(clientConfig);
					 
				 Log.info("Succeed to create HTTPS Client!");
			 } else {
					 
				 ClientConfig clientConfig = new DefaultClientConfig();
					 
				 clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
					 
				 client = Client.create(clientConfig);
					 
				 Log.info("Succeed to create HTTP Client!");
			 }
	 			
			 WebResource webResource = client.resource(URL);
			 
			 // requst methode is POST
			 ClientResponse response = webResource
					 					.accept("application/json")
					 					.type("application/json")
					 					.post(ClientResponse.class,joinPerson);
					                   
			 int responseStatus = response.getStatus();
			 if (responseStatus  != 200 ) {
				 Log.info("Failed : HTTP error code : " + response.getStatus());
				if (responseStatus==403) {
					Log.info ("you provided wrong person ");
				}
				 return null;
			 } 
			 if (response.getStatus() == 200) {
				output = response.getEntity(Dept.class);
				Log.info ("Succeed to get response via SSL Connection to Restul Server" );
			 }
			 
		 } catch (Exception e) {
			 Log.error("Failed to connect to Web Service Server "+e.getMessage());
			 e.printStackTrace();
		 }
		
		 return output;
	 }
	/**
	 * 
	 * @param URL    		 https://localhost:8443/secureServices/get_product/1001
	 * @param prodId   		 1001 to 1006
	 * @return Product
	 */
	public Product SSLRestfulClientProduct(String URL,Integer prodId)  {
		 if (null==URL) {    
			 return null;
		 }
		
		 Product output = null;
		 Client client = null;
		 String protocol = URL.substring(0,6).toLowerCase(); 
	
		 try {
			 if (protocol.indexOf("https")!=-1) {
					 
				 ClientSSLContextManager ssl = new ClientSSLContextManager(sslContext);
						
				 ClientConfig clientConfig = ssl.getDefaultClientConfig();
					 
				 clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
					 
				 client = Client.create(clientConfig);
					 
				 Log.info("Succeed to create HTTPS Client!");
			 } else {
					 
				 ClientConfig clientConfig = new DefaultClientConfig();
					 
				 clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
					 
				 client = Client.create(clientConfig);
					 
				 Log.info("Succeed to create HTTP Client!");
			 }
	 			
			 WebResource webResource = client.resource(URL);
			 
			 // requst methode is GET
			 ClientResponse response = webResource
					 					.accept("application/json")
					 					.type("application/json")
					                    .get(ClientResponse.class);
					             
			 int responseStatus = response.getStatus();
			 if (responseStatus  != 200 ) {
				 Log.info("Failed : HTTP error code : " + response.getStatus());
				if (responseStatus==403) {
					Log.info ("you provided wrong person ");
				}
				 return null;
			 } 
			 if (response.getStatus() == 200) {
				output = response.getEntity(Product.class);
				Log.info ("Succeed to get response via SSL Connection to Restul Server" );
			 }
			 
		 } catch (Exception e) {
			 Log.error("Failed to connect to Web Service Server "+e.getMessage());
			 e.printStackTrace();
		 }
		
		 return output;
	 } 
	public static void main(String [] args) {
		String URL  = Constants.LOCAL_HOST_SERVICE+"secureServices/join_dept";
		String trustStorePath1 ="";
		String trustPassword1="";
		SSLContextBuilder b = new SSLContextBuilder.Builder().trustStorePath(trustStorePath1, trustPassword1).build();
		SSLContext sslContext = SSLContextSingleton.getInstance().getSSLContext(b);
		SSLRestfulClient ssl=new  SSLRestfulClient(sslContext);
		ssl.SSLRestfulClientProduct(URL,1001);
	}
}