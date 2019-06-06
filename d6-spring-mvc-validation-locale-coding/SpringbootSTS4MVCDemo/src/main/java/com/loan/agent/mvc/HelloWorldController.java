package com.loan.agent.mvc;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
 
@RestController
public class HelloWorldController {
	@GetMapping("/hello")
	public String helloworld() {
		return "helloworld";
	}
}
