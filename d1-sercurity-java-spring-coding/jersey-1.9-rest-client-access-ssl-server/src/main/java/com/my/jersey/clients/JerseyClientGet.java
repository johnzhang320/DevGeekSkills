package com.my.jersey.clients;

import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.my.jersey.model.Constants;
import com.sslcontext.manager.ClientSSLContextManager;
import com.sslcontext.manager.FindResourceFilePath;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class JerseyClientGet {
  static Logger Log = Logger.getLogger(JerseyClientGet.class);
  
  public static void main(String[] args) {
	try {
				
		
		//String URL ="https://localhost:8443/jersey-1.9-rest-client-access-ssl-server/rest/json/metallica/get";
	
		String URL = Constants.LOCAL_HOST_SERVICE+"json/metallica/get";	
		
		Client client=JerseyClientSingleton.getInstance().getJerseyClient(URL);
		
	 
		
		WebResource webResource = client
		   .resource(URL);

		ClientResponse response = webResource.accept("application/json")
                   .get(ClientResponse.class);

		if (response.getStatus() != 200) {
		   throw new RuntimeException("Failed : HTTP error code : "
			+ response.getStatus());
		}

		//String output = response.getEntity(String.class);
		String output = response.getEntity(String.class);
		System.out.println("Output from Server .... \n");
		System.out.println(output);

	  } catch (Exception e) {

		e.printStackTrace();

	  }

	}
}