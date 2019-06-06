package com.boot.oauth.sso.client.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MVCController {
	@RequestMapping(value="/demosso", method=RequestMethod.GET) 
	public ModelAndView demosso() {
		ModelAndView model = new ModelAndView("demosso");
		return model;
	}
}
