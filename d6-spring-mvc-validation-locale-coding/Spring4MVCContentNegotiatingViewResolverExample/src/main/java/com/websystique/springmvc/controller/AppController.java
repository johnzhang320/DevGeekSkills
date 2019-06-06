package com.websystique.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.websystique.springmvc.model.Pizza;
/**
 * http://websystique.com/springmvc/spring-4-mvc-contentnegotiatingviewresolver-example/
 * run as maven-> maven install, create war file in target folder, deploy it to webapp of tomcat
 * 
 * http://localhost:8080/Spring4MVCContentNegotiatingViewResolverExample/pizzavalley/johnzhang.xml
 * http://localhost:8080/Spring4MVCContentNegotiatingViewResolverExample/pizzavalley/johnzhang.json
 * http://localhost:8080/Spring4MVCContentNegotiatingViewResolverExample/pizzavalley/johnzhang.pdf
 * http://localhost:8080/Spring4MVCContentNegotiatingViewResolverExample/pizzavalley/johnzhang.xls
 */
@Controller 
public class AppController {

	@RequestMapping(value="/pizzavalley/{pizzaName}", method = RequestMethod.GET)
	public String getPizza(@PathVariable String pizzaName, ModelMap model) {
 
		Pizza pizza = new Pizza(pizzaName);
		model.addAttribute("pizza", pizza);
 
		return "pizza";
 
	}
	
}
