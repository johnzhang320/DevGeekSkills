package com.my.jersey.clients;

import org.codehaus.jackson.map.ObjectMapper;

import com.my.jersey.model.Constants;
import com.my.jersey.model.Track;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class JerseyClientPostMapperObject {

  public static void main(String[] args) {

	try {

		Client client=JerseyClientSingleton.getInstance().getJerseyClient();

		WebResource webResource = client
		   .resource(Constants.LOCAL_HOST_SERVICE+"json/metallica/post");
		
		Track track = new Track("Metallica","Fade To Black","rock");
		
		ObjectMapper objectMapper=new ObjectMapper();
 	    String jsonInString="" ;
	       
	    ObjectMapper mapper = new ObjectMapper();
	    String input= mapper.writeValueAsString(track);
	            
 		ClientResponse response = webResource.type("application/json")
		   .post(ClientResponse.class, input );

		if (response.getStatus() != 201) {
			throw new RuntimeException("Failed : HTTP error code : "
			     + response.getStatus());
		}

		System.out.println("Output from Server .... \n");
		String output = response.getEntity(String.class);
		System.out.println(output);
		 

	  } catch (Exception e) {

		e.printStackTrace();

	  }

	}

}
