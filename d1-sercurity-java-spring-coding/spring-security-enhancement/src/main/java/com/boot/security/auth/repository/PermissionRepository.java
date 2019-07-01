package com.boot.security.auth.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.boot.security.auth.model.Permission;
import com.boot.security.auth.model.Role;

public interface PermissionRepository extends JpaRepository<Permission, Long>{
	
	public Permission findByName(String name);
	
	public List<Permission> findAll();
	
	@Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Permission c WHERE c.name = :pname")
	boolean existsByName(@Param("pname") String pname);
 	
}
