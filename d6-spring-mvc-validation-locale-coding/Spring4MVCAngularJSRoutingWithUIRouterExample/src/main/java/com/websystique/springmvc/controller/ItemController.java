package com.websystique.springmvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.websystique.springmvc.service.ItemService;

@Controller
public class ItemController {//Serves Data.

    @Autowired
    ItemService itemService;  //Service which will do all data retrieval/manipulation work
 
    @RequestMapping("/categories")
    public ResponseEntity<List> listAllCategories() {
		System.out.println("*************************************ListAllItems");
    	List<String> categories = 	itemService.populateAllCategories();
        if(categories.isEmpty()){
            return new ResponseEntity<List>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List>(categories, HttpStatus.OK);
    }

	@RequestMapping(value="/item/Computers")
    public ResponseEntity<List> listAllComputers() {
		System.out.println("*************************************ListAllComputers");
    	List items = 	itemService.findItemsByCategory("computers");
        if(items.isEmpty()){
            return new ResponseEntity<List>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List>(items, HttpStatus.OK);
    }

	@RequestMapping(value="/item/Computers/{id}")
    public ResponseEntity<Object> findSpecificComputer(@PathVariable("id") long id) {
		System.out.println("*************************************findSpecificComputer");
    	Object item = 	itemService.findItemById(id, "computers");
        if(item == null){
            return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<Object>(item, HttpStatus.OK);
    }

	
	@RequestMapping(value="/item/Phones")
    public ResponseEntity<List> listAllPhones() {
		System.out.println("*************************************ListAllPhones");
    	List items = 	itemService.findItemsByCategory("phones");
        if(items.isEmpty()){
            return new ResponseEntity<List>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List>(items, HttpStatus.OK);
    }

	@RequestMapping(value="/item/Phones/{id}")
    public ResponseEntity<Object> findSpecificPhone(@PathVariable("id") long id) {
		System.out.println("*************************************findSpecificPhone");
    	Object item = 	itemService.findItemById(id, "phones");
        if(item == null){
            return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<Object>(item, HttpStatus.OK);
    }

	@RequestMapping(value="/item/Printers")
    public ResponseEntity<List> listAllPrinters() {
		System.out.println("*************************************ListAllPrinters");
    	List items = 	itemService.findItemsByCategory("printers");
        if(items.isEmpty()){
            return new ResponseEntity<List>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List>(items, HttpStatus.OK);
    }

	@RequestMapping(value="/item/Printers/{id}")
    public ResponseEntity<Object> findSpecificPrinter(@PathVariable("id") long id) {
		System.out.println("*************************************findSpecificPrinter");
    	Object item = 	itemService.findItemById(id, "printers");
        if(item == null){
            return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<Object>(item, HttpStatus.OK);
    }

}
