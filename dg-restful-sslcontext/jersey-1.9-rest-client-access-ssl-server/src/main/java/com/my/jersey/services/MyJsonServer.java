package com.my.jersey.services;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.my.jersey.model.Student;
// 
@Path("/jsonServices")
public class MyJsonServer {
 
    @GET
    @Path("/print/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Student produceJSON( @PathParam("name") String name ) {
 
        Student st = new Student(name, "Diaz",22,1);
 
        return st;
 
    }
  
    @POST
    @Path("/send")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response consumeJSON( Student student ) {
 
        String output = student.toString();
 
        return Response.status(200).entity(output).build();
    }
    
}