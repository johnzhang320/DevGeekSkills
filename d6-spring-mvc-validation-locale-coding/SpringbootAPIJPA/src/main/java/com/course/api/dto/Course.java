package com.course.api.dto;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
//@Entity tell JPA to create table
@Entity
@Table(name = "courseM2O")
public class Course {
	@Id
	private String id;
	@NotNull
	private String name;
	private String description;
	// JPA 
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private Topic topic;
	
	public Course() {
		super();
	}
	public Course(String id, String name, String description,String topicId) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.topic = new Topic(topicId,"","");
	}
	
	public Topic getTopic() {
		return topic;
	}
	public void setTopic(Topic topic) {
		this.topic = topic;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
