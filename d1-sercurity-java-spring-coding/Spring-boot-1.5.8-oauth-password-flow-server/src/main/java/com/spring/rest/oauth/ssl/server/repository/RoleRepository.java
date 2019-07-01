package com.spring.rest.oauth.ssl.server.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.rest.oauth.ssl.server.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
	public Role findByName(String name);
	public List<Role> findAll();
	
}
