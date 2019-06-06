package com.websystique.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
/**
 *  Create maven web project:
 *  1.
 *  New--> Maven Project -->Check Use default Workspace location -->maven-archetype-webapp-->
 *  Group Id: com.websystique.springmvc
 *  Artifact Id: Spring4MVCHelloWorldDemo
 *  Package:com.websystique.springmvc
 *  2.
 *  Project-->Properties-->Libraries-->change JRE library to 1.8 from 1.5 to create src/main/java and
 *  src/test/java
 *  
 *  3.
 *  In C:\Users\John Zhang\.m2\repository\org\springframework spring version is 
 *  4.1.4.RELEASE, we must change Spring version from 4.0.0 RELEASE to 4.1.4.RELEASE
 *  in poi.xml 
 *  
 *  
 * @author John Zhang
 *
 */
@Controller
@RequestMapping("/")
public class HelloWorldController {
 /**
  * 
  * 
	Spring 4 MVC HelloWorld Tutorial – Full XML Example
	Created on: August 3, 2014 | Last updated on: December 27, 2016 websystiqueadmin	
	
	This tutorial introduces you to Spring MVC 4 basics, using classic Hello World example
	 without skipping any step. Spring 4 MVC HelloWorld Tutorial – Annotation/JavaConfig 
	 Example contains the Annotations based full example.
  */
    @RequestMapping(method = RequestMethod.GET)
    public String sayHello(ModelMap model) {
        model.addAttribute("greeting", "Hello World from Spring 4 MVC");
        return "welcome";
    }
 
    @RequestMapping(value="/helloagain", method = RequestMethod.GET)
    public String sayHelloAgain(ModelMap model) {
        model.addAttribute("greeting", "Hello World Again, from Spring 4 MVC");
        return "welcome";
    }
 
}
