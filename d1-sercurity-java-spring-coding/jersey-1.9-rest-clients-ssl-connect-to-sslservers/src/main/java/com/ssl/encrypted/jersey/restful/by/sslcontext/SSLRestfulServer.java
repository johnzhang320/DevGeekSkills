package com.ssl.encrypted.jersey.restful.by.sslcontext;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.ssl.encrypted.jersey.restful.by.sslcontext.model.Dept;
import com.ssl.encrypted.jersey.restful.by.sslcontext.model.Person;
import com.ssl.encrypted.jersey.restful.by.sslcontext.model.Product;
 


@Path("/secureServices")
public class SSLRestfulServer {
	public static Logger Log =Logger.getLogger(SSLRestfulServer.class.getName());
	
	static Map<Integer, Dept> map = new HashMap<>();
	static Map<Integer, Product> prods = new HashMap<>();
	static {
 		
 		map.put(1001, new Dept(1001, "Public Relationship", "525 Market St, San Francisco, CA 94105", new ArrayList<Person>()));
 		map.put(1002, new Dept(1002, "Sales Market", "559 Clay Street 2nd Floor San Francisco, CA 94111", new ArrayList<Person>()));
 		map.put(1003, new Dept(1003, "R & D Division", "525 Market St, San Francisco, CA 94105", new ArrayList<Person>()));
 		map.put(1004, new Dept(1004, "Human Resourse", "1084 Foxworthy Ave, San Jose, CA 95118", new ArrayList<Person>()));
 		map.put(1005, new Dept(1005, "Shipping", "525 Market St, San Francisco, CA 94105", new ArrayList<Person>()));
 		map.put(1006, new Dept(1006, "Pack Center", "525 Market St, San Francisco, CA 94105", new ArrayList<Person>()));
 		
 		prods.put(1001, new Product("Mac Pro 2", "Apple Mac Laptop", 2599.99F, "1001"));
 		prods.put(1002, new Product("LP4500", "Roku TV", 799.99F, "1002"));
 		prods.put(1003, new Product("Mac Pro 2", "Apple Mac Laptop", 2599.99F, "1003"));
 		prods.put(1004, new Product("Mac Pro 2", "Apple Mac Laptop", 2599.99F, "1004"));
 		prods.put(1005, new Product("Mac Pro 2", "Apple Mac Laptop", 2599.99F, "1005"));
 		prods.put(1006, new Product("Mac Pro 2", "Apple Mac Laptop", 2599.99F, "1006"));
	};
	
	public Dept joinDepartment(Person person ) {
 		
 		Dept dept = map.get(person.getDeptId());
 		dept.getPersons().add(person);
 		return dept;
 		
	}
	 
	@POST
	@Path("/join_dept")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response JoinDepartment(Person person,@Context HttpServletResponse response) {
 		
		if (person.getDeptId()<1000 || person.getDeptId()>1006) {
			Log.info("person joining department id overflow");
			return Response.status(403).entity("person joining department id overflow").build();
		}
		Dept output=joinDepartment(person) ;
		
		Log.info("Joint Department \n"+output);
 		
		return Response.status(200).entity(output).build();
	}
 
	@GET
	@Path("/get_product/{prodId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProduct(@PathParam("prodId") int prodId, HttpServletResponse response) {
		
		if (prodId<1000 || prodId>1006) {
			Log.info("Prod id overflow");
			return Response.status(403).entity("Prod id overflow").build();
		}
	 
		Product output=prods.get(prodId);
		Log.debug("Deliver All queued message for this host \n"+output);
		return Response.status(200).entity(output).build();
	}
}
