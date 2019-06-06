package com.websystique.springmvc.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.websystique.springmvc.model.Student;
/**
 * 
 * we will create a simple application containing a student registration form, on form submission 
 * validating the user-input via JSR-303 validation annotations, overriding default messages using 
 * internationalized validation messages through properties files and also access the static 
 * resources (e.g. applying Bootstrap CSS to our pages).

    Please note that JSR303 is a specification and hibernate-validator we are using in this post 
    is an implementation, which also provides few of it’s own validation annotations not 
    included in specification.
 *
 */
/**
 * 
 * /**
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

	/*
	 * This method will serve as default GET handler.
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String newRegistration(ModelMap model) {
		Student student = new Student();
		model.addAttribute("student", student);
		return "enroll";
	}	 

	/*
	 * This method will be called on form submission, handling POST request It
	 * also validates the user input
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String saveRegistration(@Valid Student student,
			BindingResult result, ModelMap model) {

		if (result.hasErrors()) {
			return "enroll";
		}

		model.addAttribute("success", "Dear " + student.getFirstName()
				+ " , your Registration completed successfully");
		return "success";
	}

	/*
	 * Method used to populate the Section list in view. Note that here you can
	 * call external systems to provide real data.
	 */
	@ModelAttribute("sections")
	public List<String> initializeSections() {

		List<String> sections = new ArrayList<String>();
		sections.add("Graduate");
		sections.add("Post Graduate");
		sections.add("Research");
		return sections;
	}

	/*
	 * Method used to populate the country list in view. Note that here you can
	 * call external systems to provide real data.
	 */
	@ModelAttribute("countries")
	public List<String> initializeCountries() {

		List<String> countries = new ArrayList<String>();
		countries.add("USA");
		countries.add("CANADA");
		countries.add("FRANCE");
		countries.add("GERMANY");
		countries.add("ITALY");
		countries.add("OTHER");
		return countries;
	}

	/*
	 * Method used to populate the subjects list in view. Note that here you can
	 * call external systems to provide real data.
	 */
	@ModelAttribute("subjects")
	public List<String> initializeSubjects() {

		List<String> subjects = new ArrayList<String>();
		subjects.add("Physics");
		subjects.add("Chemistry");
		subjects.add("Life Science");
		subjects.add("Political Science");
		subjects.add("Computer Science");
		subjects.add("Mathmatics");
		return subjects;
	}

}