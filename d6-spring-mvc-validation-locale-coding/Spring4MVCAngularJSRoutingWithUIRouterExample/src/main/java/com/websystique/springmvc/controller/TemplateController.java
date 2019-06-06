package com.websystique.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TemplateController {//Serves Templates.

    @RequestMapping(value="/category")
    public String getMainTemplate() {
    	return "template/all_categories";	
    }

    @RequestMapping(value="/category/Computers")
    public String getComputersTemplate() {
    	return "template/category_computers";	
    }

    @RequestMapping(value="/category/Phones")
    public String getPhonesTemplate() {
    	return "template/category_phones";	
    }

    @RequestMapping(value="/category/Printers")
    public String getPrintersTemplate() {
    	return "template/category_printers";	
    }

    @RequestMapping(value="/category/Computers/{id}")
    public String getComputersDetailTemplate(@PathVariable("id") long id) {
    	return "template/category_computers_detail";	
    }

    @RequestMapping(value="/category/Phones/{id}")
    public String getPhonesDetailTemplate(@PathVariable("id") long id) {
    	return "template/category_phones_detail";	
    }

    @RequestMapping(value="/category/Printers/{id}")
    public String getPrintersDetailTemplate(@PathVariable("id") long id) {
    	return "template/category_printers_detail";	
    }

}

