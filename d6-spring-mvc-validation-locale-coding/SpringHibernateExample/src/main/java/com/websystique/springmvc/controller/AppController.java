package com.websystique.springmvc.controller;

import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.websystique.springmvc.model.Employee;
import com.websystique.springmvc.service.EmployeeService;
/**
 * 
 * It’s a pretty straight-forward Spring based controller.@Controller indicates that this class is a controller handling 
 * the requests with pattern mapped by @RequestMapping.Here with ‘/’, it is serving as default controller. 
	Method listEmployees , annotated with @RequestMethod.GET, handling both the default URL ‘/’ as well as ‘/list’. 
	It acts as handle for initial page of application, showing a list of existing employees.
	Method newEmployee is handling the GET request for the new employee registration page, showing page backed by a 
	model Employee object.
	Method saveEmployee is annotated with @RequestMethod.POST, and will handle the form-submission POST requests for 
	new employee registration (‘/new’). Notice the parameters and their orders in this method. @Valid asks spring to 
	validate the associated object(Employee). BindingResult contains the outcome of this validation and any error 
	that might have occurred during this validation. Notice that BindingResult must come right after the validated 
	object else spring won’t be able to validate and an exception been thrown. In case of validation failure, custom 
	error messages(as we have configured in step 4) are shown.
	We have also included code to check for SSN uniqueness as it is declared to be unique in database. Before 
	saving/updating an employee, we are checking if the SSN is unique.If not, we generate validation error and 
	redirect to registration page. This peace of code demonstrate a way to
	fill it custom errors outside the validation framework as well while still using internationalized messages.
	Method editEmployee takes you to registration page with employee details filled in, while updateEmployee gets 
	called when you click on update button after possible updation on gui.
	Method deleteEmployee is handling the deletion of an employee by it’s SSN number. Notice @PathVariable , which 
	indicates that this parameter will be bound to variable in URI template (SSN in our case).
	As for as Annotation based configuration goes,this is all we need to do. Now to make the application complete, 
	we will add service layer, dao layer, views, Domain object, sample database schema and run the application.


 *
 */
@Controller
@RequestMapping("/")
public class AppController {

	@Autowired
	EmployeeService service;
	
	@Autowired
	MessageSource messageSource;

	/*
	 * This method will list all existing employees.
	 */
	@RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET)
	public String listEmployees(ModelMap model) {

		List<Employee> employees = service.findAllEmployees();
		model.addAttribute("employees", employees);
		return "allemployees";
	}

	/*
	 * This method will provide the medium to add a new employee.
	 */
	@RequestMapping(value = { "/new" }, method = RequestMethod.GET)
	public String newEmployee(ModelMap model) {
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		model.addAttribute("edit", false);
		return "registration";
	}

	/*
	 * This method will be called on form submission, handling POST request for
	 * saving employee in database. It also validates the user input
	 */
	@RequestMapping(value = { "/new" }, method = RequestMethod.POST)
	public String saveEmployee(@Valid Employee employee, BindingResult result,
			ModelMap model) {

		if (result.hasErrors()) {
			return "registration";
		}

		/*
		 * Preferred way to achieve uniqueness of field [ssn] should be implementing custom @Unique annotation 
		 * and applying it on field [ssn] of Model class [Employee].
		 * 
		 * Below mentioned peace of code [if block] is to demonstrate that you can fill custom errors outside the validation
		 * framework as well while still using internationalized messages.
		 * 
		 */
		if(!service.isEmployeeSsnUnique(employee.getId(), employee.getSsn())){
			FieldError ssnError =new FieldError("employee","ssn",messageSource.getMessage("non.unique.ssn", new String[]{employee.getSsn()}, Locale.getDefault()));
		    result.addError(ssnError);
			return "registration";
		}
		
		service.saveEmployee(employee);

		model.addAttribute("success", "Employee " + employee.getName() + " registered successfully");
		return "success";
	}


	/*
	 * This method will provide the medium to update an existing employee.
	 */
	@RequestMapping(value = { "/edit-{ssn}-employee" }, method = RequestMethod.GET)
	public String editEmployee(@PathVariable String ssn, ModelMap model) {
		Employee employee = service.findEmployeeBySsn(ssn);
		model.addAttribute("employee", employee);
		model.addAttribute("edit", true);
		return "registration";
	}
	
	/*
	 * This method will be called on form submission, handling POST request for
	 * updating employee in database. It also validates the user input
	 */
	@RequestMapping(value = { "/edit-{ssn}-employee" }, method = RequestMethod.POST)
	public String updateEmployee(@Valid Employee employee, BindingResult result,
			ModelMap model, @PathVariable String ssn) {

		if (result.hasErrors()) {
			return "registration";
		}

		if(!service.isEmployeeSsnUnique(employee.getId(), employee.getSsn())){
			FieldError ssnError =new FieldError("employee","ssn",messageSource.getMessage("non.unique.ssn", new String[]{employee.getSsn()}, Locale.getDefault()));
		    result.addError(ssnError);
			return "registration";
		}

		service.updateEmployee(employee);

		model.addAttribute("success", "Employee " + employee.getName()	+ " updated successfully");
		return "success";
	}

	
	/*
	 * This method will delete an employee by it's SSN value.
	 */
	@RequestMapping(value = { "/delete-{ssn}-employee" }, method = RequestMethod.GET)
	public String deleteEmployee(@PathVariable String ssn) {
		service.deleteEmployeeBySsn(ssn);
		return "redirect:/list";
	}

}
