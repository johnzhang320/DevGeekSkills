package com.boot.security.auth.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.boot.security.auth.model.Role;
import com.boot.security.auth.model.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Long>{

	public List<UserRole> findAll();
	
	public UserRole findByUserRolePair(String userRolePair);
	
	@Query(value="DELETE FROM USERS_ROLES WHERE USER_ROLE_PAIR=?1",nativeQuery=true)
	public void deleteByUserRolePair(String userRolePair);
	
	@Query(value="insert into users_roles (user_id,role_id,user_role_pair) values (?1,?2,?3)",nativeQuery=true)
	public void insertUserRole(Long userId, Long roleId, String userRolePair);

}
