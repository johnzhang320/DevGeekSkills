package com.hibernate.many.to.many.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hibernate.many.to.many.model.User;
 
public interface UserRepository extends JpaRepository<User, Long>{
	public User findByUserId(int groupId);
	public User findByName(String name);
}
