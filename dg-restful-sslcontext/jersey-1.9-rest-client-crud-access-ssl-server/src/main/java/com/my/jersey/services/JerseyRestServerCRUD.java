package com.my.jersey.services;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.my.jersey.model.Dept;
import com.my.jersey.model.Person;
import com.my.jersey.model.Product;


 


@Path("/secureServices")
public class JerseyRestServerCRUD {
	public static Logger Log =Logger.getLogger(JerseyRestServerCRUD.class.getName());
	
	static Map<Integer, Dept> map = new HashMap<>();
	static Map<Integer, Product> prods = new HashMap<>();
	 
	
	public Dept joinDepartment(Person person ) {
 		
 		Dept dept = map.get(person.getDeptId());
 		dept.getPersons().add(person);
 		return dept;
 		
	}
	
	public void init() {
 		
	    map = new HashMap<>();
	    prods = new HashMap<>();
 	    map.put(1001, new Dept(1001, "Public Relationship", "525 Market St, San Francisco, CA 94105", new ArrayList<Person>()));
	 	map.put(1002, new Dept(1002, "Sales Market", "559 Clay Street 2nd Floor San Francisco, CA 94111", new ArrayList<Person>()));
 		map.put(1003, new Dept(1003, "R & D Division", "525 Market St, San Francisco, CA 94105", new ArrayList<Person>()));
 		map.put(1004, new Dept(1004, "Human Resourse", "1084 Foxworthy Ave, San Jose, CA 95118", new ArrayList<Person>()));
 		map.put(1005, new Dept(1005, "Shipping", "525 Market St, San Francisco, CA 94105", new ArrayList<Person>()));
 		map.put(1006, new Dept(1006, "Pack Center", "525 Market St, San Francisco, CA 94105", new ArrayList<Person>()));
 		
 		prods.put(1001, new Product("Mac Pro 2", "Apple Mac Laptop", 2599.99F, "1001"));
 		prods.put(1002, new Product("LP4500", "Roku TV", 799.99F, "1002"));
 		prods.put(1003, new Product("Mac Pro 2", "Apple Mac Laptop", 2599.99F, "1003"));
 		prods.put(1004, new Product("Iphone X", "Apple Smart phone", 999.99F, "1004"));
 		prods.put(1005, new Product("Dell 200", "Dell Laptop", 1599.99F, "1005"));
 		prods.put(1006, new Product("HP 1200", "HP Laptop", 899.99F, "1006"));
	 
 		
	}
	@GET
	@Path("/init_data")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response initData() {
		init();
		Log.debug("Return all depts");
		return  Response.status(200).entity(map).build();
	}	 
	@POST
	@Path("/person_join_dept")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response JoinDepartment(Person person) {
		
		if (person.getDeptId()<1000 || map.get(person.getDeptId())==null) {
			Log.info("person joining department id overflow");
			return Response.status(403).entity("person joining department id overflow").build();
		}
		Dept output=joinDepartment(person) ;
		
		Log.info("Joint Department \n"+output);
 		
		return Response.status(200).entity(output).build();
	}
	
	@POST
	@Path("/create_dept")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createDepartment(Dept dept) {
 		
		if (dept.getDeptId()<1000 ) {
			Log.info(" department id overflow");
			return Response.status(403).entity(" department id overflow").build();
		}
		map.put(dept.getDeptId(), dept);
			
		return Response.status(200).entity(map).build();
	}
	@GET
	@Path("/get_all_depts")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllDepts() {

		Log.debug("Return all depts");
		return  Response.status(200).entity(map).build();
	}
	@GET
	@Path("/get_product/{prodId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProduct(@PathParam("prodId") int prodId) {
		
		if (prodId<1000 || prodId>1006) {
			Log.info("Prod id overflow");
			return Response.status(403).entity("Prod id overflow").build();
		}
	 
		Product output=prods.get(prodId);
		Log.debug("Deliver All queued message for this host \n"+output);
		return Response.status(200).entity(output).build();
	}
	
	@PUT
	@Path("/update_dept_person")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response UpdateDepartment(Person person) {
 		
		if (person.getDeptId()<1000 ) {
			Log.info("person joining department id overflow");
			return Response.status(403).entity("person joining department id overflow").build();
		}
		Dept output=joinDepartment(person) ;
		
		Log.info("Joint Department \n"+output);
 		
		return Response.status(200).entity(output).build();
	}
	
	@DELETE
	@Path("/delete_dept/{deptId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response DeleteDepartment(@PathParam ("deptId") int deptId) {
 		
		if (deptId<1000) {
			Log.info("Deleting department id overflow");
			return Response.status(403).entity("deleting department id overflow").build();
		}
		map.remove(deptId) ;
  		
		return Response.status(200).entity(map).build();
	}
}
