package com.demo.controllers;

import com.demo.engine.Producer;
import com.demo.models.Course;
import com.demo.models.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/kafka")
public class KafkaController {
	
	@Autowired
    private Producer producer;

    @PostMapping(value = "/publish")
    public void sendMessageToKafkaTopic(@RequestParam("message") String message) {
        this.producer.sendUserMessage(message);
    }
    
    @RequestMapping(method=RequestMethod.POST, value="/publish/user") 
    public User sendUserMessage(@RequestBody User user ) {
    	this.producer.sendUserMessage(user.toString());
    	return user;
    }
    @RequestMapping(method=RequestMethod.POST, value="/publish/course") 
    public Course sendCourseMessage(@RequestBody Course course ) {
    	this.producer.sendCourseMessage(course.toString());
    	return course;
    }
}
