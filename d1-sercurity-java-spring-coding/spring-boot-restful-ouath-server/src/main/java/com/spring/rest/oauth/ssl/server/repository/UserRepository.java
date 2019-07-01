package com.spring.rest.oauth.ssl.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.spring.rest.oauth.ssl.server.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	 
    User findByUsername(String username);
    
}
