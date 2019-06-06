package com.websystique.springmvc.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.websystique.springmvc.domain.Message;
/**
 * 
 * @PathVariable indicates that this parameter will be bound to variable in URI template.
 *  More interesting thing to note here is that here we are using @RestController annotation, 
 *  which marks this class as a controller where every method returns a domain object/pojo 
 *  instead of a view. It means that we are no more using view-resolvers, we are no more 
 *  directly sending the html in response but we are sending domain object converted into 
 *  format understood by the consumers. In our case, due to jackson library included in class 
 *  path, the Message object will be converted into JSON format[ or in XML if either the 
 *  jackson-dataformat-xml.jar is present in classpath or Model class i annotated with JAXB 
 *  annotations].
 *  http://localhost:8080/Spring4MVCHelloWorldRestServiceDemo/hello/emily
 *  if poi.xml only make jackson-databind available , browser will display JSON format
 *  if poi.xml makes  jackson-dataformat-xml.jar  available it will display XML format
 */
@RestController  // RestController means return Message in JSON or XML depend on availability of jackson-databind or jackson-dataformat-xml.jar
public class HelloWorldRestController {

	@RequestMapping("/")
	public String welcome() {//Welcome page, non-rest
		return "Welcome to RestTemplate Example. host:8080/json/{playname} or host:8080/xml/{playname} ";
	}

	@RequestMapping(value="/json/{player}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public Message messageJson(@PathVariable String player) {//REST Endpoint.

		Message msg = new Message(player, "Hello " + player);
		return msg;
	}
	@RequestMapping(value="/xml/{player}", method = RequestMethod.GET, produces=MediaType.APPLICATION_XML_VALUE)
	public Message messageXML(@PathVariable String player) {//REST Endpoint.

		Message msg = new Message(player, "Hello " + player);
		return msg;
	}
	
}
