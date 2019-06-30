package com.boot.security.auth.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.boot.security.auth.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
	public Role findByName(String name);
	public List<Role> findAll();
	@Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Role c WHERE c.name = :roleName")
	boolean existsByName(@Param("roleName") String roleName);
	
	@Query(value="insert into roles (name) values (?1)",nativeQuery=true)
	public void insertRole(String role);
	 
	
}
