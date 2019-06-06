package com.course.api.dao;

import org.springframework.data.repository.CrudRepository;

import com.course.api.dto.Topic;
// JPA provide getEntity, createEntity, UpdateEntity, DeleteEntity,CrudRepository<Topic,String> , String here is Id
public interface TopicRepository extends CrudRepository<Topic,String>{

}
