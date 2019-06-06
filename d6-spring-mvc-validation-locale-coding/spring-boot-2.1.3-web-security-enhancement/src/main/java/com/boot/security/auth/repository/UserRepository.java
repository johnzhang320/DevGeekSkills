package com.boot.security.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.boot.security.auth.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	 
    User findByUsername(String username);
    
}
