package com.my.jersey.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;


/*
 *  http://localhost:8080/jersey-1.9-rest-service-and-clients/rest/hello/jzhang
 *  
 *  https://localhost:8443/jersey-1.9-rest-service-and-clients/rest/hello/jzhang
 *  
 *  Copy the 
 */
@Path("/hello")
public class HelloWorldService {
 
	@GET
	@Path("/{param}")
	public Response getMsg(@PathParam("param") String msg) {
 
		String output = "Jersey say : " + msg;
 
		return Response.status(200).entity(output).build();
 
	}
}