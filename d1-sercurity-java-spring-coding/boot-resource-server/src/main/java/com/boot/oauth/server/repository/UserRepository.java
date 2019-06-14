package com.boot.oauth.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.boot.oauth.server.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	 
    User findByUsername(String username);
    
}
