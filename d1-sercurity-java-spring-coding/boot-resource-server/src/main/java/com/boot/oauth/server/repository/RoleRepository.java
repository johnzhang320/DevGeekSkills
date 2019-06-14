package com.boot.oauth.server.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boot.oauth.server.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
	public Role findByName(String name);
	public List<Role> findAll();
	
}
