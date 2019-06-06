package com.loan.agent.mvc.utils;

import java.util.Timer;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class FixedDelayScheduling {
	
	public static void main(String[] args) throws Exception {
		ApplicationContext ctx =new ClassPathXmlApplicationContext("applicationContext.xml");
		 
		//Timer t = new Timer();
		//t.schedule(new AgentOnDutyTask(), 1000, 8000);
		System.in.read();
		
	}
}
