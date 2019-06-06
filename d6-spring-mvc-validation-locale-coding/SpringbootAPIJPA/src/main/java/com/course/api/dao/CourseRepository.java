package com.course.api.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.course.api.dto.Course;

// JPA provide getEntity, createEntity, UpdateEntity, DeleteEntity,CrudRepository<Topic,String> , String here is Id
public interface CourseRepository extends CrudRepository<Course,String>{
	 public List<Course> findByTopicId(String topicId);
	 public List<Course> findByName(String name);
	 public List<Course> findByDescription(String description);
}
