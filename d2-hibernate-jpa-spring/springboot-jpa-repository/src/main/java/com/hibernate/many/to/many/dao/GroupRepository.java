package com.hibernate.many.to.many.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hibernate.many.to.many.model.Group;
 
public interface GroupRepository extends JpaRepository<Group, Long>{
	public Group findByGroupId(int groupId);
	public Group findByName(String name);
}
