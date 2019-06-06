package com.oauth2.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpringbootOath2Controller {
	  @RequestMapping(value = "/products")
	  public String getProductName() {
	      return "Honey";   
	  }
}
