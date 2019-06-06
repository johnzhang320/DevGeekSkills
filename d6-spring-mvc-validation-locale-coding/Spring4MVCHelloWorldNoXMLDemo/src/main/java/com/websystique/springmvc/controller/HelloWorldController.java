package com.websystique.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
/**
 * 
 * @Controller indicates that this class is a controller handling the requests with pattern mapped 
 * by @RequestMapping.Here with ‘/’, it is serving as default controller. Method newRegistration is 
 * fairly simple, annotated with @RequestMethod.GET serving default GET requests, adding the model 
 * object to serve as data-holder of form , and presenting the page containing the blank form.

	Method initializeSections, initializeCountries & initializeSubjects are simply creating request 
	level objects whose values will be used in view/jsp.
	
	Method saveRegistration is annotated with @RequestMethod.POST, and will handle the form-submission
	 POST requests.Notice the parameters and their orders in this method. @Valid asks spring to validate
	  the associated object(student). BindingResult contains the outcome of this validation and any 
	  error that might have occurred during this validation. Notice that BindingResult must come right 
	  after the validated object else spring won’t be able to validate and an exception been thrown.
	
	Note that in case of validation failure, default/ generalized error messages are shown on screen 
	which may not be desirable. Instead, you can override this behavior providing internationalized 
	messages specific to each field. To do that, we need to configure MessageSource in application 
	configuration class and provide properties files containing actual messages which we will do next.
 *
 */
@Controller
@RequestMapping("/")
public class HelloWorldController {

	@RequestMapping(method = RequestMethod.GET)
	public String sayHello(ModelMap model) {
		model.addAttribute("greeting", "Hello World from Spring 4 MVC");
		return "welcome";
	}

	@RequestMapping(value = "/helloagain", method = RequestMethod.GET)
	public String sayHelloAgain(ModelMap model) {
		model.addAttribute("greeting", "Hello World Again, from Spring 4 MVC");
		return "welcome";
	}

}