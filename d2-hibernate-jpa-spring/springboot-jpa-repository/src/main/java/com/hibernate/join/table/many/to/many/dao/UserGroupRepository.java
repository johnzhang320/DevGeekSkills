package com.hibernate.join.table.many.to.many.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hibernate.join.table.many.to.many.model.UserGroup;
 
public interface UserGroupRepository extends JpaRepository<UserGroup, Long>{
	public UserGroup findByUserGroupId(int groupId);

}
