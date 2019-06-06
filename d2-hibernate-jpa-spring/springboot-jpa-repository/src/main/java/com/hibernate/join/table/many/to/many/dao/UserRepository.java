package com.hibernate.join.table.many.to.many.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hibernate.join.table.many.to.many.model.User;
 
public interface UserRepository extends JpaRepository<User, Long>{
	public User findByUserId(int groupId);
	public User findByName(String name);
}
