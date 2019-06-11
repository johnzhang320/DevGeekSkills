package com.my.jersey.clients;

import org.codehaus.jackson.map.ObjectMapper;

import com.my.jersey.model.Constants;
import com.my.jersey.model.Student;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

public class JerseyClientPostClientConfigPojo {

  public static void main(String[] args) {

	 
        try {
 
            Student st = new Student("Adriana", "Barrer", 12, 9);
 	 		
    		Client client=JerseyClientSingleton.getInstance().getJerseyClient();
    		
            WebResource webResource = client
                    .resource(Constants.LOCAL_HOST_SERVICE+"jsonServices/send");
 
            ClientResponse response = webResource.accept("application/json")
                    .type("application/json").post(ClientResponse.class, st);
 
            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatus());
            }
 
            String output = response.getEntity(String.class);
 
            System.out.println("Server response .... \n");
            System.out.println(output);
 
        } catch (Exception e) {
 
            e.printStackTrace();
 
        }
 
    }

}
