package com.my.jersey.clients;

import javax.ws.rs.core.MediaType;

import com.my.jersey.model.Constants;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class JerseyClientGet {

  public static void main(String[] args) {
	try {

		Client client = Client.create();

		WebResource webResource = client
		   .resource(Constants.LOCAL_HOST_SERVICE+"json/metallica/get");

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