package com.custom.annotation.process;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.custom.annotation.model.Employee;
 
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ObjectToJsonConverter.class }, loader = AnnotationConfigContextLoader.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OrderingByDefaultTest {
	
	@Autowired
	ObjectToJsonConverter objectToJsonConverter;
	
	static Logger Log = Logger.getLogger(OrderingByDefaultTest.class);
	static List<Employee> employees;
	//String firstName, String lastName, String age, String address
	@BeforeClass
	public static void setup() {
		employees = new ArrayList<>();
	}
	@Test
	public void Run1_createEmployee() {
		Employee emp1 = new Employee("John","Smith","36","1232 Broadway Ave");
 		employees.add(emp1);
 		Log.info("Completed create employee 1"); 
 	}
	
	@Test
	public void Run2_createEmployeeTwo() {
		 
		Employee emp2 = new Employee("Leo","Huang","26","123 Flamingo Ave");
	 
		employees.add(emp2);
		Log.info("Completed create employee 2"); 
	}
	
	@Test
	public void Run3_createEmployeeThree() {
	 
		Employee emp3 = new Employee("Trish","Reagan","34","1232 Fox Ave");
		employees.add(emp3);
		Log.info("Completed create employee 3"); 
	}
	@Test 
	public void Run4_convertToJson() throws Exception {
		StringBuffer sb = new StringBuffer();
		for (Employee emp:employees) {
			Log.info("Current emp="+emp.toString());
			sb.append(objectToJsonConverter.convertToJson(emp)+"\n");
		}
		Log.info("output JSON as following\n"+sb.toString());
	}
}
