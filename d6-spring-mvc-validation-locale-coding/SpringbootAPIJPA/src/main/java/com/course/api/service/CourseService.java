package com.course.api.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course.api.dao.CourseRepository;
import com.course.api.dto.Course;

// adding @Service annotation , controller can inject it when web startup
@Service
public class CourseService {
	
	@Autowired
	private CourseRepository courseRepository;
	
	

	public List<Course> getCourses(String topicId) {
		List<Course> Courses = new ArrayList<Course>();
		courseRepository.findByTopicId(topicId).forEach(Courses::add);
		return Courses;
	}
	public Optional<Course> getCourse(String id) {
		return courseRepository.findById(id);
	}
	
	public void addCourse(Course Course) {
		courseRepository.save(Course);
		 
	}
	
	public void updateCourse(Course Course) {
		courseRepository.save(Course); // if Course contains id inside, we do not need specifically pass id
	}
	public void deleteCourse(String id) {
		//Courses.removeIf(t->t.getId().equals(id));
		courseRepository.deleteById(id);
	}
}
