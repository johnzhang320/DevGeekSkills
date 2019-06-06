package com.boot.security.auth.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boot.security.auth.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
	public Role findByName(String name);
	public List<Role> findAll();
	
}
