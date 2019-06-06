package com.websystique.springmvc.controller;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import static org.mockito.Mockito.atLeastOnce;

import org.springframework.context.MessageSource;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import com.websystique.springmvc.model.Employee;
import com.websystique.springmvc.service.EmployeeService;
/**
 * 
 * if you revisit AppController class in Previous post, you will see that AppController 
 * basically depends on EmployeeService , MessageSource, Employee, ModelMap & BindingResult 
 * to fullfill all of it’s duties. Each of the AppController method is using only of 
 * these objects to do it’s real job.

	So in order to test AppController, we would need to provide these dependencies. In our 
	example, we do it using Mockito framework. We provide mock of EmployeeService & 
	MessageSource by applying @Mock annotation on them. We also provide spy objects 
	of ModelMap , BindingResult & Employee by applying @Spy annotations on them. 
 *
 */
public class AppControllerTest {
	/**
	 * It’s important to understand that Mockito’s @Mock objects are not real instances, 
	 * they are just bare-bones of instance created using Class of type. But their main 
	 * capability is that they can remember all the interactions [operations performed] on them. 
	 */
	@Mock
	EmployeeService service;
	
	@Mock
	MessageSource message;
	/**
	 * @InjectMocks creates an instance of the class and injects the mocks that are created 
	 * with the @Mock/@Spy objects in it.MockitoAnnotations.initMocks(this); initializes objects
	 * annotated with Mockito annotations [@Mock, @Spy, @Captor, @InjectMocks] 
	 */
	@InjectMocks
	AppController appController;
	/**
	 * @Spy objects are on the other hand real instances, but with additional capabilities 
	 * of remembering all the interactions [operations performed] on them.
	 */
	@Spy
	List<Employee> employees = new ArrayList<Employee>();

	@Spy
	ModelMap model;
	
	@Mock
	BindingResult result;
	/**
	 * Annotations @Test & @BeforeClass are TestNG specific annotations.
	 * MockitoAnnotations.initMocks(this); initializes objects
	 * annotated with Mockito annotations [@Mock, @Spy, @Captor, @InjectMocks] 
	 */
	@BeforeClass
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		employees = getEmployeeList();
	}
	/**
	 * Annotations @Test & @BeforeClass are TestNG specific annotations.
	 * Assert is the TestNG api for doing assertions on expected result and actual result. 
	 * when..then & verify are popular stubbing and verification techniques used in tests to 
	 * define the behavior and then optionally verifying that behavior was indeed executed.
	 */
	@Test
	public void listEmployees(){
		when(service.findAllEmployees()).thenReturn(employees);
		Assert.assertEquals(appController.listEmployees(model), "allemployees");
		Assert.assertEquals(model.get("employees"), employees);
		verify(service, atLeastOnce()).findAllEmployees();
	}
	
	@Test
	public void newEmployee(){
		Assert.assertEquals(appController.newEmployee(model), "registration");
		Assert.assertNotNull(model.get("employee"));
		Assert.assertFalse((Boolean)model.get("edit"));
		Assert.assertEquals(((Employee)model.get("employee")).getId(), 0);
	}


	@Test
	public void saveEmployeeWithValidationError(){
		when(result.hasErrors()).thenReturn(true);
		doNothing().when(service).saveEmployee(any(Employee.class));
		Assert.assertEquals(appController.saveEmployee(employees.get(0), result, model), "registration");
	}

	@Test
	public void saveEmployeeWithValidationErrorNonUniqueSSN(){
		when(result.hasErrors()).thenReturn(false);
		when(service.isEmployeeSsnUnique(anyInt(), anyString())).thenReturn(false);
		Assert.assertEquals(appController.saveEmployee(employees.get(0), result, model), "registration");
	}

	
	@Test
	public void saveEmployeeWithSuccess(){
		when(result.hasErrors()).thenReturn(false);
		when(service.isEmployeeSsnUnique(anyInt(), anyString())).thenReturn(true);
		doNothing().when(service).saveEmployee(any(Employee.class));
		Assert.assertEquals(appController.saveEmployee(employees.get(0), result, model), "success");
		Assert.assertEquals(model.get("success"), "Employee Axel registered successfully");
	}

	@Test
	public void editEmployee(){
		Employee emp = employees.get(0);
		when(service.findEmployeeBySsn(anyString())).thenReturn(emp);
		Assert.assertEquals(appController.editEmployee(anyString(), model), "registration");
		Assert.assertNotNull(model.get("employee"));
		Assert.assertTrue((Boolean)model.get("edit"));
		Assert.assertEquals(((Employee)model.get("employee")).getId(), 1);
	}

	@Test
	public void updateEmployeeWithValidationError(){
		when(result.hasErrors()).thenReturn(true);
		doNothing().when(service).updateEmployee(any(Employee.class));
		Assert.assertEquals(appController.updateEmployee(employees.get(0), result, model,""), "registration");
	}

	@Test
	public void updateEmployeeWithValidationErrorNonUniqueSSN(){
		when(result.hasErrors()).thenReturn(false);
		when(service.isEmployeeSsnUnique(anyInt(), anyString())).thenReturn(false);
		Assert.assertEquals(appController.updateEmployee(employees.get(0), result, model,""), "registration");
	}

	@Test
	public void updateEmployeeWithSuccess(){
		when(result.hasErrors()).thenReturn(false);
		when(service.isEmployeeSsnUnique(anyInt(), anyString())).thenReturn(true);
		doNothing().when(service).updateEmployee(any(Employee.class));
		Assert.assertEquals(appController.updateEmployee(employees.get(0), result, model, ""), "success");
		Assert.assertEquals(model.get("success"), "Employee Axel updated successfully");
	}
	
	
	@Test
	public void deleteEmployee(){
		doNothing().when(service).deleteEmployeeBySsn(anyString());
		Assert.assertEquals(appController.deleteEmployee("123"), "redirect:/list");
	}

	public List<Employee> getEmployeeList(){
		Employee e1 = new Employee();
		e1.setId(1);
		e1.setName("Axel");
		e1.setJoiningDate(new LocalDate());
		e1.setSalary(new BigDecimal(10000));
		e1.setSsn("XXX111");
		
		Employee e2 = new Employee();
		e2.setId(2);
		e2.setName("Jeremy");
		e2.setJoiningDate(new LocalDate());
		e2.setSalary(new BigDecimal(20000));
		e2.setSsn("XXX222");
		
		employees.add(e1);
		employees.add(e2);
		return employees;
	}
}
