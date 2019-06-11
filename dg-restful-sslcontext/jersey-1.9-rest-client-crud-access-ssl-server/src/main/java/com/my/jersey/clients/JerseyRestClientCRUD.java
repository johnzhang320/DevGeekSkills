package com.my.jersey.clients;
 
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;


import com.my.jersey.model.Constants;
import com.my.jersey.model.Dept;
import com.my.jersey.model.Person;
import com.my.jersey.model.Product;
import com.sslcontext.manager.JerseyClientSingleton;
import com.sslcontext.manager.SSLContextBuilder;
import com.sslcontext.manager.SSLContextManager;
import com.sslcontext.manager.SSLContextSingleton;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;




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
public class JerseyRestClientCRUD  {
	 
	 public static Logger Log =Logger.getLogger(JerseyRestClientCRUD.class.getName());
	 /**
	  * Receive (R)
	 * SSL Client GET test 
	 * @param URL    		 Constants.LOCAL_HOST_SERVICE+"secureServices/get_all_depts";
	 * @param prodId   		 1001 to 1006
	 * @return Product
	 */
	public static String SSLRestClientInitData()  {
		
		 String URL= Constants.LOCAL_HOST_SERVICE+"secureServices/init_data";
		
		 if (null==URL) {    
			 return null;
		 }
		
		 String output = null;
	 
		 Client client=JerseyClientSingleton.getInstance().getJerseyClient(URL);

	 			
		 WebResource webResource = client.resource(URL);
		 
		 // requst methode is GET
		 ClientResponse response = webResource
				 					.accept("application/json")
				 					.type("application/json")
				                    .get(ClientResponse.class);
				             
		 int responseStatus = response.getStatus();
		 if (responseStatus  != 200 ) {
			 Log.info("Failed : HTTP error code : " + response.getStatus());
			
			 return null;
		 } 
		 if (response.getStatus() == 200) {
			output = response.getEntity(String.class);
			Log.info ("Succeed to get response via SSL Connection to Restul Server" );
			Log.info("All Departments as following\n"+JsonPretty(output));
			/*
			 *  output here is JSON String, Jersey Json library included Jockson ObjectMapper
			 *  which can convert json back to Pojo object
			 */
		 
		 }
		 return output;
	 } 	
	 /**
	  * CREATE (C)
	  * SSL Client POST test
	  * @param URL           Constants.LOCAL_HOST_SERVICE+"secureServices/person_join_dept";
	  * @param joinPerson    person object
	  * @return
	  */

	public static Dept SSLRestClientPOSTPersonObject(Person joinPerson)  {
		
		 String URL = Constants.LOCAL_HOST_SERVICE+"secureServices/person_join_dept";
		 if (null==URL) {  
			 return null;
		 }
 		
		 String output = null;
		 Dept dept =null;
		 /*
		  *  We define truststore in Constants interface and getJerseyClient used it
		  */
		 Client client=JerseyClientSingleton.getInstance().getJerseyClient(URL);
		 /*
		  *  clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
		  *  and web.xml 
		  *  <init-param>
		      <param-name>com.sun.jersey.api.json.POJOMappingFeature</param-name>
		      <param-value>true</param-value>
		    </init-param>
		    Configure webResource can accept class POJO object as json object
		  */
 			
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
			output = response.getEntity(String.class);    // Jersey only can return String not pojo
			Log.info ("Succeed to get response via SSL Connection to Restul Server" );
			Log.info("JSON String output is "+JsonPretty(output));
			
			/*
			 *  output here is JSON String, Jersey Json library included Jockson ObjectMapper
			 *  which can convert json back to Pojo object
			 */
			ObjectMapper objectMapper = new ObjectMapper();
			
			try {  
			    dept = objectMapper.readValue(output, Dept.class);
			} catch (Exception e) {
				Log.error("Failed to conver to POJO "+e.getLocalizedMessage());
			}
			
			Log.info("Convert JSON String to POJO is "+dept.toString());
			
		 }
			 
	
		 return dept;
	 }
	
	 /**
	  * CREATE (C)
	  * SSL Client POST test
	  * @param URL           Constants.LOCAL_HOST_SERVICE+/secureServices/create_dept
	  * @param joinPerson    person object
	  * @return
	  */

	public static Dept SSLRestClientPOSTCreateDept(Dept dept)  {
		 String URL = Constants.LOCAL_HOST_SERVICE+"secureServices/create_dept";
		
		 if (null==URL) {  
			 return null;
		 }
		
		 String output = null;
		 
		 /*
		  *  We define truststore in Constants interface and getJerseyClient used it
		  */
		 Client client=JerseyClientSingleton.getInstance().getJerseyClient(URL);
		 /*
		  *  clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
		  *  and web.xml 
		  *  <init-param>
		      <param-name>com.sun.jersey.api.json.POJOMappingFeature</param-name>
		      <param-value>true</param-value>
		    </init-param>
		    Configure webResource can accept class POJO object as json object
		  */
			
		 WebResource webResource = client.resource(URL);
		 
		 // requst methode is POST
		 ClientResponse response = webResource
				 					.accept("application/json")
				 					.type("application/json")
				 					.post(ClientResponse.class,dept);
				                   
		 int responseStatus = response.getStatus();
		 if (responseStatus  != 200 ) {
			 Log.info("Failed : HTTP error code : " + response.getStatus());
			if (responseStatus==403) {
				Log.info ("you provided wrong person ");
			}
			 return null;
		 } 
		 if (response.getStatus() == 200) {
			output = response.getEntity(String.class);    // Jersey only can return String not pojo
			Log.info ("Succeed to get response via SSL Connection to Restul Server" );
			Log.info("JSON String output is "+JsonPretty(output));
			
			/*
			 *  output here is JSON String, Jersey Json library included Jockson ObjectMapper
			 *  which can convert json back to Pojo object
			 */
			ObjectMapper objectMapper = new ObjectMapper();
			
			try {  
			    dept = objectMapper.readValue(output, Dept.class);
			} catch (Exception e) {
				Log.error("Failed to conver to POJO "+e.getLocalizedMessage());
			}
			
			Log.info("Convert JSON String to POJO is "+dept.toString());
			
		 }
			 
	
		 return dept;
	 }
	
	 /**
	  * Receive (R)
	 * SSL Client GET test 
	 * @param URL    		 Constants.LOCAL_HOST_SERVICE+"secureServices/get_all_depts";
	 * @param prodId   		 1001 to 1006
	 * @return Product
	 */
	public static String SSLRestClientGETAllDepts()  {
		
		 String URL= Constants.LOCAL_HOST_SERVICE+"secureServices/get_all_depts";
		
		 if (null==URL) {    
			 return null;
		 }
		
		 String output = null;
	 
		 Client client=JerseyClientSingleton.getInstance().getJerseyClient(URL);

	 			
		 WebResource webResource = client.resource(URL);
		 
		 // requst methode is GET
		 ClientResponse response = webResource
				 					.accept("application/json")
				 					.type("application/json")
				                    .get(ClientResponse.class);
				             
		 int responseStatus = response.getStatus();
		 if (responseStatus  != 200 ) {
			 Log.info("Failed : HTTP error code : " + response.getStatus());
			
			 return null;
		 } 
		 if (response.getStatus() == 200) {
			output = response.getEntity(String.class);
			Log.info ("Succeed to get response via SSL Connection to Restul Server" );
			Log.info("All Departments as following\n"+JsonPretty(output));
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
	 * @param URL    		 Constants.LOCAL_HOST_SERVICE+"secureServices/get_product/"+prodId;
	 * @param prodId   		 1001 to 1006
	 * @return Product
	 */
	public static Product SSLRestClientGETProduct(Integer prodId)  {
		
		 String URL= Constants.LOCAL_HOST_SERVICE+"secureServices/get_product/"+prodId;
		
		 if (null==URL) {    
			 return null;
		 }
		
		 String output = null;
		 Product prod=null;
		 Client client=JerseyClientSingleton.getInstance().getJerseyClient(URL);

	 			
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
				Log.info ("you provided prodId ");
			}
			 return null;
		 } 
		 if (response.getStatus() == 200) {
			output = response.getEntity(String.class);
			Log.info ("Succeed to get response via SSL Connection to Restul Server" );
			/*
			 *  output here is JSON String, Jersey Json library included Jockson ObjectMapper
			 *  which can convert json back to Pojo object
			 */
			ObjectMapper objectMapper = new ObjectMapper();
			
			try {  
			    prod= objectMapper.readValue(output, Product.class);
			} catch (Exception e) {
				Log.error("Failed to conver to POJO "+e.getLocalizedMessage());
			}
			
			Log.info("Convert JSON String to POJO is "+prod.toString());
			
		 }
		 
		
		 return prod;
	 } 	
	 /**
	  * UPDATE (U)
	  * SSL Client PUT test
	  * @param URL           https://localhost:8443/secureServices/join_dept
	  * @param joinPerson    person object
	  * @return
	  */

	public static Dept SSLRestClientPUTPersonObject(Person joinPerson)  {
		 String URL= Constants.LOCAL_HOST_SERVICE+"secureServices/update_dept_person";
		 if (null==URL) {  
			 return null;
		 }
		
		 String output = null;
		 Dept dept =null;
		 /*
		  *  We define truststore in Constants interface and getJerseyClient used it
		  */
		 Client client=JerseyClientSingleton.getInstance().getJerseyClient(URL);
		 /*
		  *  clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
		  *  and web.xml 
		  *  <init-param>
		      <param-name>com.sun.jersey.api.json.POJOMappingFeature</param-name>
		      <param-value>true</param-value>
		    </init-param>
		    Configure webResource can accept class POJO object as json object
		  */
			
		 WebResource webResource = client.resource(URL);
		 
		 // requst methode is POST
		 ClientResponse response = webResource
				 					.accept("application/json")
				 					.type("application/json")
				 					.put(ClientResponse.class,joinPerson);
				                   
		 int responseStatus = response.getStatus();
		 if (responseStatus  != 200 ) {
			 Log.info("Failed : HTTP error code : " + response.getStatus());
			if (responseStatus==403) {
				Log.info ("you provided wrong person ");
			}
			 return null;
		 } 
		 if (response.getStatus() == 200) {
			output = response.getEntity(String.class);    // Jersey only can return String not pojo
			Log.info ("Succeed to get response via SSL Connection to Restul Server" );
			Log.info("JSON String output is "+JsonPretty(output));
			
			/*
			 *  output here is JSON String, Jersey Json library included Jockson ObjectMapper
			 *  which can convert json back to Pojo object
			 */
			ObjectMapper objectMapper = new ObjectMapper();
			
			try {  
			    dept = objectMapper.readValue(output, Dept.class);
			} catch (Exception e) {
				Log.error("Failed to conver to POJO "+e.getLocalizedMessage());
			}
			
			Log.info("Convert JSON String to POJO is "+dept.toString());
			
		 }
			 
	
		 return dept;
	 }
	 /**
	  * DELETE (D)
	  * SSL Client Delete test
	  * @param URL           https://localhost:8443/secureServices/join_dept
	  * @param joinPerson    person object
	  * @return
	  */

	public static Dept SSLRestClientDELETEDepartment(String deptId)  {
		 String URL= Constants.LOCAL_HOST_SERVICE+"secureServices/delete_dept/"+deptId;
		 if (null==URL) {  
			 return null;
		 }
		
		 String output = null;
		 Dept dept =null;
		 /*
		  *  We define truststore in Constants interface and getJerseyClient used it
		  */
		 Client client=JerseyClientSingleton.getInstance().getJerseyClient(URL);
		 /*
		  *  clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
		  *  and web.xml 
		  *  <init-param>
		      <param-name>com.sun.jersey.api.json.POJOMappingFeature</param-name>
		      <param-value>true</param-value>
		    </init-param>
		    Configure webResource can accept class POJO object as json object
		  */
			
		 WebResource webResource = client.resource(URL);
		 
		 // requst methode is POST
		 ClientResponse response = webResource
				 					.accept("application/json")
				 					.type("application/json")
				 					.delete(ClientResponse.class);
				                   
		 int responseStatus = response.getStatus();
		 if (responseStatus  != 200 ) {
			 Log.info("Failed : HTTP error code : " + response.getStatus());
			if (responseStatus==403) {
				Log.info ("you provided wrong deptId ");
			}
			 return null;
		 } 
		 if (response.getStatus() == 200) {
			output = response.getEntity(String.class);    // Jersey only can return String not pojo
			Log.info ("Succeed to get response via SSL Connection to Restul Server" );
			Log.info("JSON String output is "+JsonPretty(output));
			
			 
		 }
	 	 return dept;
	 }
	 public static String JsonPretty(String s) {
		 ObjectMapper mapper = new ObjectMapper() ;
		 String result =s;
		 try {
			 //result = mapper.defaultPrettyPrintingWriter().writeValueAsString(s);
			 result=result.replace(",", ",\n").replace("[","{\n").replace("}","}\n").replace("[","[\n").replace("]","]\n");
			 
		 } catch (Exception e) {
			 Log.info("Failed to convert Json to Pretty Format "+e.getLocalizedMessage());
		 }
		 return result;
	 }
	public static void main(String [] args) {
		Log.info("Test 0 Init Testing Data");
		SSLRestClientInitData();
		Log.info("Test 1 Post Add Person to dept");
		Person joinPerson = new Person(
				1001,"Eric", "Band", "1992-12-21", "male", 125900F);
		
 		SSLRestClientPOSTPersonObject(joinPerson);
 		
 		joinPerson = new Person(
				1001,"Jennifer", "Lopaz", "1985-06-21", "female", 12590000F);
 		SSLRestClientPOSTPersonObject(joinPerson);
 		
 		Log.info("Test 2 get All departments");
	 
 		SSLRestClientGETAllDepts();
 		
 		Log.info("Test 3 Create new department");
 	    joinPerson = new Person(
				1007,"Robort", "Williams", "19672-01-21", "male", 225900F);	 
 	     List<Person> list = new ArrayList<Person>();
 	     list.add(joinPerson);
 	     Dept dept = new Dept(1007, "Accounting", "San Francisco", list);
 	     SSLRestClientPOSTCreateDept(dept);
 	     
 	    Log.info("Test 3 show All departments again");
 		 
 		SSLRestClientGETAllDepts();
 		
 	    Log.info("Test 4 GET Product by id 1002 !");
 		
 		SSLRestClientGETProduct(1002);
 		
 		
 		Log.info("Test 5 PUT rise Jennifer Salary 15005100!");
 		
 		joinPerson.setSalary(15005100F);
 		
 	    SSLRestClientPUTPersonObject(joinPerson);
 	    
 	    Log.info("Test 6 Delete 1002 Sale Market department !");
 		
  		
 		SSLRestClientDELETEDepartment("1002");
 		
 		 
 	    Log.info("Test 7 show All departments again");
 		 
 		SSLRestClientGETAllDepts();
	}
}