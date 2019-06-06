package com.course.api.controller;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.course.api.dto.Course;
import com.course.api.dto.Topic;
import com.course.api.service.CourseService;
 


@RestController
public class CourseController {
	
	// Inject topic service as instance by Autowired
	@Autowired
	private CourseService courseService;
	
	@RequestMapping("/topics/{topicId}/courses")
	public List<Course> getAllCourses(@PathVariable String topicId) {
	 	
		return courseService.getCourses(topicId);
				 
	}
	
	@RequestMapping("/course/{id}")
	public Optional<Course> getOneTopic(@PathVariable String id) {
	 	
		return courseService.getCourse(id);
				 
	}
	@RequestMapping(method=RequestMethod.POST, value="/topics/{topicId}/course")
	public List<Course> addTopic(@RequestBody Course course,@PathVariable String topicId ) {
		course.setTopic(new Topic(topicId,"",""));
		courseService.addCourse(course);
		return courseService.getCourses(topicId);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/topics/{topicId}/course")
	public List<Course> updateTopic(@RequestBody Course course,@PathVariable String topicId ) {
		course.setTopic(new Topic(topicId,"",""));
		courseService.updateCourse(course);
		return courseService.getCourses(topicId);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/topics/{topicId}/course/{id}")
	public List<Course> deleteTopic(@PathVariable String topicId ,@PathVariable String id ) {
		courseService.deleteCourse(id);
		return courseService.getCourses(topicId);
	}
}
