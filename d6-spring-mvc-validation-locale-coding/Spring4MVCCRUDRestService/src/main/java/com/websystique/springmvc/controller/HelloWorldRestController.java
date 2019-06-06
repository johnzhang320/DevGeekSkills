package com.websystique.springmvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.websystique.springmvc.model.User;
import com.websystique.springmvc.service.UserService;
/**
 * 
 *  
    GET request to /api/user/ returns a list of users
    GET request to /api/user/1 returns the user with ID 1
    POST request to /api/user/ with a user object as JSON creates a new user
    PUT request to /api/user/3 with a user object as JSON updates the user with ID 3
    DELETE request to /api/user/4 deletes the user with ID 4
    DELETE request to /api/user/ deletes all the users
	
	@RestController : First of all, we are using Spring 4′s new @RestController annotation. This 
	annotation eliminates the need of annotating each method with @ResponseBody. Under the hood, 
	@RestController is itself annotated with @ResponseBody, and can be considered as combination 
	of @Controller and @ResponseBody.
	
	@RequestBody : If a method parameter is annotated with @RequestBody, Spring will bind the 
	incoming HTTP request body(for the URL mentioned in @RequestMapping for that method) to that 
	parameter. While doing that, Spring will [behind the scenes] use HTTP Message converters to 
	convert the HTTP request body into domain object [deserialize request body to domain object], 
	based on ACCEPT or Content-Type header present in request.
	
	@ResponseBody : If a method is annotated with @ResponseBody, Spring will bind the return value 
	to outgoing HTTP response body. While doing that, Spring will [behind the scenes] use HTTP 
	Message converters to convert the return value to HTTP response body [serialize the object to 
	response body], based on Content-Type present in request HTTP header. As already mentioned, 
	in Spring 4, you may stop using this annotation.
	
	ResponseEntity is a real deal. It represents the entire HTTP response. Good thing about it is 
	that you can control anything that goes into it. You can specify status code, headers, and body. 
	It comes with several constructors to carry the information you want to sent in HTTP Response.
	
	@PathVariable This annotation indicates that a method parameter should be bound to a URI template
	 variable [the one in '{}'].
	
	Basically, @RestController , @RequestBody, ResponseEntity & @PathVariable are all you need to 
	know to implement a REST API in Spring 4. Additionally, spring provides several support classes 
	to help you implement something customized.
	
	MediaType : With @RequestMapping annotation, you can additionally, specify the MediaType to be 
	produced or consumed (using produces or consumes attributes) by that particular controller 
	method, to further narrow down the mapping.
	    
 *
 */
 
@RestController
public class HelloWorldRestController {

	@Autowired
	UserService userService;  //Service which will do all data retrieval/manipulation work
     
    @RequestMapping("/")
	public String welcome() {//Welcome page, non-rest
		return "Welcome to Spring4MVCCRUDRestService. (1) get /user/ -- all users (2) get /user/1 -- user id 1 or /user/10000 -- user not found (3) post /user/ -- create user , Delete /user/1 -- detete user 1";
	}
	
	//-------------------Retrieve All Users--------------------------------------------------------
	// in PostMan tool selected GET and type URL http://localhost:8080/Spring4MVCCRUDRestService/user/
	@RequestMapping(value = "/user/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<User>> listAllUsers() {
		List<User> users = userService.findAllUsers();
		if(users.isEmpty()){
			return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}


	//-------------------Retrieve Single User--------------------------------------------------------
	// in PostMan tool selected GET and type URL http://localhost:8080/Spring4MVCCRUDRestService/user/1

	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> getUser(@PathVariable("id") long id) {
		System.out.println("Fetching User with id " + id);
		User user = userService.findById(id);
		if (user == null) {
			System.out.println("User with id " + id + " not found");
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	
	
	//-------------------Create a User--------------------------------------------------------
	// in PostMan tool selected POST and type URL http://localhost:8080/Spring4MVCCRUDRestService/user/
    // Choose Body in tool below URL line , then choose raw and select JSON(application/json) drop dwon
	/**
	 * 
	 * {
		    "id": 6,
		    "name": "john zhang",
		    "age": 31,
		    "salary": 175000
		 }
	 */
	// press 'Send' , then change to "GET" and URL http://localhost:8080/Spring4MVCCRUDRestService/user/6
	// you will see the POST data
	@RequestMapping(value = "/user/", method = RequestMethod.POST)
	public ResponseEntity<Void> createUser(@RequestBody User user, 	UriComponentsBuilder ucBuilder) {
		System.out.println("Creating User " + user.getName());

		if (userService.isUserExist(user)) {
			System.out.println("A User with name " + user.getName() + " already exist");
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}

		userService.saveUser(user);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	
	//------------------- Update a User --------------------------------------------------------
	// Select "PUT" and Type URL:http://localhost:8080/Spring4MVCCRUDRestService/user/6
	// Change 6 to 
	/**
	 * {
		  "id": 6,
		  "name": "Dennie Cha",
		  "age": 41,
		  "salary": 145000
		}
	 *  then change to "PUT' and URL:http://localhost:8080/Spring4MVCCRUDRestService/user/6
	 *  you will see the change
	 */
	@RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
	public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user) {
		System.out.println("Updating User " + id);
		
		User currentUser = userService.findById(id);
		
		if (currentUser==null) {
			System.out.println("User with id " + id + " not found");
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}

		currentUser.setName(user.getName());
		currentUser.setAge(user.getAge());
		currentUser.setSalary(user.getSalary());
		
		userService.updateUser(currentUser);
		return new ResponseEntity<User>(currentUser, HttpStatus.OK);
	}

	//------------------- Delete a User --------------------------------------------------------
	// Select "Delete" and type http://localhost:8080/Spring4MVCCRUDRestService/user/6
	// delete id 6 record, select GET and same URL , no data shown
	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<User> deleteUser(@PathVariable("id") long id) {
		System.out.println("Fetching & Deleting User with id " + id);

		User user = userService.findById(id);
		if (user == null) {
			System.out.println("Unable to delete. User with id " + id + " not found");
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}

		userService.deleteUserById(id);
		return new ResponseEntity<User>(HttpStatus.OK);
	}

	
	//------------------- Delete All User --------------------------------------------------------
	// Select "Delete" and type http://localhost:8080/Spring4MVCCRUDRestService/user/
	@RequestMapping(value = "/user/", method = RequestMethod.DELETE)
	public ResponseEntity<User> deleteAllUsers() {
		System.out.println("Deleting All Users");

		userService.deleteAllUsers();
		return new ResponseEntity<User>(HttpStatus.OK);
	}

}
