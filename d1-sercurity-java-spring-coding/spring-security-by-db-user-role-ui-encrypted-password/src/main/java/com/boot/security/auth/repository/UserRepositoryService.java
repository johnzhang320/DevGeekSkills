package com.boot.security.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.boot.security.auth.model.User;

public interface UserRepositoryService {
    public User findByUsername(String username);
    public User findById(Long Id); 
    public void save(User user);
    
}
