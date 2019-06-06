package com.course.api.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course.api.dao.TopicRepository;
import com.course.api.dto.Topic;

// adding @Service annotation , controller can inject it when web startup
@Service
public class TopicService {
	
	@Autowired
	private TopicRepository topicRepository;
	
	

	public List<Topic> getTopics() {
		List<Topic> topics = new ArrayList<Topic>();
		topicRepository.findAll().forEach(topics::add);
		return topics;
	}
	public Optional<Topic> getTopic(String id) {
		//if (id==null || id.length()==0) return new Topic();
		//return topics.stream().filter(t->t.getId().equals(id)).findFirst().get();
		return topicRepository.findById(id);
	}
	
	public void addTopic(Topic topic) {
		topicRepository.save(topic);
		 
	}
	
	public void updateTopic(Topic topic,String id) {
		/*for (int i=0; i<topics.size();i++) {
			if (topics.get(i).getId().equals(id)) {
				topics.set(i,topic);
			}
		}*/
		topicRepository.save(topic); // if topic contains id inside, we do not need specifically pass id
	}
	public void deleteTopic(String id) {
		//topics.removeIf(t->t.getId().equals(id));
		topicRepository.deleteById(id);
	}
}
