package com.boot.oauth.sso.client.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.boot.oauth.sso.client.model.BankAccount;
import com.boot.oauth.sso.client.model.Employee;
import com.boot.oauth.sso.client.model.User;

@Controller
@RequestMapping("/safebox")
public class DashboardController {
	    @RequestMapping(value = "/getEmployeesList", produces = "application/json", method=RequestMethod.GET)
	    @ResponseBody
	    public List<Employee> getEmployeesList() {
	        List<Employee> employees = new ArrayList<>();
	        
	        employees.add(new Employee("1231", "John Zhang", "male", 36, 16500.00));
	        employees.add(new Employee("1232", "Dennie CHa", "female", 23, 12500.00));
	        employees.add(new Employee("1233", "Suresh Ai", "male", 46, 15500.00));
	        employees.add(new Employee("1041", "Bhvan suhe", "male", 36, 17500.00));
	        return employees;

	    }
	    @RequestMapping(value = "/getBankAccountList", produces = "application/json", method=RequestMethod.GET)
	    @ResponseBody
	    public List<BankAccount> getBankAccountList() {
	           
	        return getBankAccounts();

	    }
	  @RequestMapping(value = "/dashboard.html", method = RequestMethod.GET)
	    public ModelAndView dashboard() {
	    	ModelAndView model = new ModelAndView();
	    	model.addObject("users", getUsers());
	    	model.setViewName("dashboard");
	    	return model;
	    }
	 
	  @GetMapping("/bankaccount.html")
	    public ModelAndView bankaccount() {
	    	ModelAndView model = new ModelAndView();
	    	model.addObject("bankAccount", getBankAccounts());
	    	model.setViewName("bankaccount");
	    	return model;
	    }
	 
		private List<User> getUsers() {
			User user = new User();
			user.setEmail("johndoe123@gmail.com");
			user.setName("John Doe");
			user.setAddress("Bangalore, Karnataka");
			User user1 = new User();
			user1.setEmail("amitsingh@yahoo.com");
			user1.setName("Amit Singh");
			user1.setAddress("Chennai, Tamilnadu");
			User user2 = new User();
			user2.setEmail("bipulkumar@gmail.com");
			user2.setName("Bipul Kumar");
			user2.setAddress("Bangalore, Karnataka");
			User user3 = new User();
			user3.setEmail("prakashranjan@gmail.com");
			user3.setName("Prakash Ranjan");
			user3.setAddress("Chennai, Tamilnadu");
			User user4 = new User();
			user4.setEmail("suresha@dell.com");
			user4.setName("Suresh Ai");
			user4.setAddress("Santa Clara, California");
			User user5 = new User();
			user5.setEmail("bhvan@sonicwall.com");
			user5.setName("Bhvan Kumar");
			user5.setAddress("San Jose, California");
			return Arrays.asList(user, user1, user2, user3,user4,user5);
			
		}
	 
		private List<BankAccount> getBankAccounts() {
			BankAccount bankAccount = new BankAccount();
			bankAccount.setEmail("johndoe123@gmail.com");
			bankAccount.setName("John Doe");
			bankAccount.setAddress("Bangalore, Karnataka");
			bankAccount.setChecking("341431");
			bankAccount.setSaving("541442"); 
			
			BankAccount bankAccount1 = new BankAccount();
			bankAccount1.setEmail("amitsingh@yahoo.com");
			bankAccount1.setName("Amit Singh");
			bankAccount1.setAddress("Chennai, Tamilnadu");
			bankAccount1.setChecking("341432");
			bankAccount1.setSaving("541443");
			
			BankAccount bankAccount2 = new BankAccount();
			bankAccount2.setEmail("bipulkumar@gmail.com");
			bankAccount2.setName("Bipul Kumar");
			bankAccount2.setAddress("Bangalore, Karnataka");
			bankAccount2.setChecking("341436");
			bankAccount2.setSaving("541445");
			
			BankAccount bankAccount3 = new BankAccount();
			bankAccount3.setEmail("prakashranjan@gmail.com");
			bankAccount3.setName("Prakash Ranjan");
			bankAccount3.setAddress("Chennai, Tamilnadu");
			bankAccount3.setChecking("341438");
			bankAccount3.setSaving("541447");
			
			BankAccount bankAccount4 = new BankAccount();
			bankAccount4.setEmail("suresha@dell.com");
			bankAccount4.setName("Suresh Ai");
			bankAccount4.setAddress("Santa Clara, California");
			bankAccount4.setChecking("341439");
			bankAccount4.setSaving("541487");
			
			BankAccount bankAccount5 = new BankAccount();
			bankAccount5.setEmail("bhvan@sonicwall.com");
			bankAccount5.setName("Bhvan Kumar");
			bankAccount5.setAddress("San Jose, California");
			bankAccount5.setChecking("341440");
			bankAccount5.setSaving("541489");
			
			return Arrays.asList(bankAccount, bankAccount1, bankAccount2, bankAccount3,bankAccount4,bankAccount5);
			
		}


}
