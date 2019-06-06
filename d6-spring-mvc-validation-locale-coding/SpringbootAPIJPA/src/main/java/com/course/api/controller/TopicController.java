package com.course.api.controller;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.course.api.dto.Topic;
import com.course.api.service.TopicService;



@RestController
public class TopicController {
	
	// Inject topic service as instance by Autowired
	@Autowired
	private TopicService topicService;
	
	@RequestMapping("/topics")
	public List<Topic> getAllTopic() {
	 	
		return topicService.getTopics();
				 
	}
	
	@RequestMapping("/topic/{id}")
	public Optional<Topic> getOneTopic(@PathVariable String id) {
	 	
		return topicService.getTopic(id);
				 
	}
	@RequestMapping(method=RequestMethod.POST, value="/topics")
	public List<Topic> addTopic(@RequestBody Topic topic) {
		topicService.addTopic(topic);
		return topicService.getTopics();
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/topics/{id}")
	public List<Topic> updateTopic(@RequestBody Topic topic,@PathVariable String id ) {
		topicService.updateTopic(topic,id);
		return topicService.getTopics();
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/topics/{id}")
	public List<Topic> deleteTopic(@PathVariable String id ) {
		topicService.deleteTopic(id);
		return topicService.getTopics();
	}
}
