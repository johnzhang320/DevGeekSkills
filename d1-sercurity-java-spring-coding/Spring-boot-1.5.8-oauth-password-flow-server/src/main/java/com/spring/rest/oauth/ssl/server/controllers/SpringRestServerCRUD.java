package com.spring.rest.oauth.ssl.server.controllers;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.websocket.server.PathParam;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spring.rest.oauth.ssl.server.model3.Dept;
import com.spring.rest.oauth.ssl.server.model3.Person;
import com.spring.rest.oauth.ssl.server.model3.Product;


@RestController
@RequestMapping("/secureServices")
public class SpringRestServerCRUD {
	public static Logger Log =Logger.getLogger(SpringRestServerCRUD.class.getName());
	
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
 		map.put(1005, new Dept(1005, "Shipping Large Package", "527 Market St, San Francisco, CA 94105", new ArrayList<Person>()));
 		map.put(1006, new Dept(1006, "Pack Center", "525 Market St, San Francisco, CA 94105", new ArrayList<Person>()));
 		
 		prods.put(1001, new Product("Mac Pro 2", "Apple Mac Laptop", 2599.99F, "1001"));
 		prods.put(1002, new Product("LP4500", "Roku TV", 799.99F, "1002"));
 		prods.put(1003, new Product("Mac Pro 2", "Apple Mac Laptop", 2599.99F, "1003"));
 		prods.put(1004, new Product("Iphone X", "Apple Smart phone", 999.99F, "1004"));
 		prods.put(1005, new Product("Dell 200", "Dell Laptop", 1599.99F, "1005"));
 		prods.put(1006, new Product("HP 1200", "HP Laptop", 899.99F, "1006"));
	 
 		
	}
   // -------------------GET iniitalize data and get List<Dept> --------------------------
	@RequestMapping(value="/init_data", method=RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Dept>> initData() {
		init();
		Log.debug("Return all depts");
		List<Dept> values = map.values().stream().collect(Collectors.toList());
	                
		return new ResponseEntity<List<Dept>>(values,HttpStatus.OK);
	}	 
	
	// -------------------GET get List<Dept> --------------------------
	
	@RequestMapping(value="/get_all_depts",method=RequestMethod.GET,
	produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Dept>> getAllDepts() {

		List<Dept> values = map.values().stream().collect(Collectors.toList());
		
		return new ResponseEntity<List<Dept>>(values , HttpStatus.OK);
	}
	 
	// -------------------GET Dept by Id --------------------------
	
	@RequestMapping(value="/get_dept/{Id}",method=RequestMethod.GET,
	produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	public Dept getDeptById(@PathVariable(value="Id") Integer Id) {

		Dept values = map.get(Id);
		
		return values;
	}
	// -------------------Accept the new Product by id --------------------------
	
		@RequestMapping(value= "/get_one_product/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
		public Product getOneProductById(
 				@PathVariable(value = "id") Integer id) {
				prods.get(id);
			return prods.get(id);
		} 
	// -------------------GET get List<Product> and Client directly get product[]--------------------------
	
	@RequestMapping(value="/get_all_products",method=RequestMethod.GET,
	produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Product>> getAllProducts() {

		List<Product> values = prods.values().stream().collect(Collectors.toList());
		
		return new ResponseEntity<List<Product>>(values , HttpStatus.OK);
	}
	
	// -------------------Accept the new Product by @RequestBody and get product by @PathVariable --------------------------
	
	@RequestMapping(value= "/get_product/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Product getProductNewOne(
			@RequestBody Product product,
			@PathVariable(value = "id") Integer id) {
			prods.put(Integer.valueOf(product.getPartNumber()), product);
		return prods.get(id);
	}
	
	// -------------------Accept the new person by @RequestBody to join to Dept --------------------------
	@RequestMapping(value="/person_join_dept",method=RequestMethod.POST,
	produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity JoinDepartment(@RequestBody Person person) {
		
		if (person.getDeptId()<1000 || map.get(person.getDeptId())==null) {
			Log.info("person joining department id overflow");
			return new ResponseEntity<>("person joining department id overflow", HttpStatus.NO_CONTENT);
		}
		Dept output=joinDepartment(person) ;
		
		Log.info("Joint Department \n"+output);
 		
		return new ResponseEntity<Person>(person,HttpStatus.OK);
	}
	
	// -------------------Accept the new Dept in POST by @RequestBody and return all depts --------------------------

	@RequestMapping(value="/create_dept",method=RequestMethod.POST,
	produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity createDepartment(@RequestBody Dept dept) {
 		
		if (dept.getDeptId()<1000 ) {
			Log.info(" department id overflow");
			return new ResponseEntity<>(" department id overflow", HttpStatus.NO_CONTENT);
		}
		map.put(dept.getDeptId(), dept);
		
		List<Dept> values = map.values().stream().collect(Collectors.toList());
			
		return new ResponseEntity<List<Dept>>(values , HttpStatus.OK);
	}


	// -------------------Accept the new Product in POST by @RequestBody -------------------------- 
	@RequestMapping(value="/create_product",method=RequestMethod.POST,
	produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity getProduct(@RequestBody Product product) {
		
	 
		prods.put(Integer.valueOf(product.getPartNumber()), product);
		
		List<Product> list = prods.values().stream().collect(Collectors.toList());
		
		return  new ResponseEntity<List<Product>>(list , HttpStatus.OK);
	}
	

	// -------------------Update Product in PUT by @RequestBody -------------------------- 
	@RequestMapping(value="/update_dept_person",method=RequestMethod.PUT,
	produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity UpdateDepartment(@RequestBody Person person) {
 		
		if (person.getDeptId()<1000 ) {
			Log.info("person joining department id overflow");
			return new ResponseEntity<>("person joining department id overflow",HttpStatus.NO_CONTENT);
		}
		Dept output=joinDepartment(person) ;
		
		Log.info("Joint Department \n"+output);
 		
		return new ResponseEntity<Dept>(output,HttpStatus.OK);
	}
	// -------------------Update Product in PUT by @RequestBody -------------------------- 
	@RequestMapping(value="/update_product",method=RequestMethod.PUT,
	produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity updateProduct(@RequestBody Product product) {
		
	 
		prods.put(Integer.valueOf(product.getPartNumber()), product);
		
		List<Product> list = prods.values().stream().collect(Collectors.toList());
		
		return  new ResponseEntity<List<Product>>(list , HttpStatus.OK);
	}
	
	// -------------------Delete Dept in DELETE by @PathVariable -------------------------- 
	@RequestMapping(value="/delete_dept/{deptId}",method=RequestMethod.DELETE,
	produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity DeleteDepartment(@PathVariable(value="deptId") int deptId) {
 		
		if (deptId<1000) {
			Log.info("Deleting department id overflow");
			return new ResponseEntity<>("deleting department id overflow",HttpStatus.NO_CONTENT);
		}
		map.remove(deptId) ;
  		
		List<Dept> values = map.values().stream().collect(Collectors.toList());
		
		return new ResponseEntity<List<Dept>>(values , HttpStatus.OK);
	}
	
	// -------------------Delete Person from Dept in DELETE by @PathVariable -------------------------- 
	@RequestMapping(value="/delete_person",method=RequestMethod.DELETE,
	produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity DeletePersonFromDepartment(@RequestBody Person person) {
 		
		if (person.getDeptId()<1000) {
			Log.info("Deleting department id overflow");
			return new ResponseEntity<>("deleting department id overflow",HttpStatus.NO_CONTENT);
		}
		
		Dept dept = map.get(person.getDeptId());
		
		List<Person> list = dept.getPersons();
		   list.remove(person);
		   dept.setPersons(list);
  		
		List<Dept> values = map.values().stream().collect(Collectors.toList());
		
		return new ResponseEntity<List<Dept>>(values , HttpStatus.OK);
	}
	
	// -------------------Delete Product in DELETE by @PathVariable -------------------------- 
	@RequestMapping(value="/delete_product/{prodId}",method=RequestMethod.DELETE,
	produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity DeleteProduct(@PathVariable(value="prodId") int prodId) {
 		
		if (prodId<1000) {
			Log.info("Deleting Product id overflow");
			return new ResponseEntity<>("deleting Product id overflow",HttpStatus.NO_CONTENT);
		}
		map.remove(prodId) ;
  		
		List<Product> values = prods.values().stream().collect(Collectors.toList());
		
		return new ResponseEntity<List<Product>>(values , HttpStatus.OK);
	}
}
