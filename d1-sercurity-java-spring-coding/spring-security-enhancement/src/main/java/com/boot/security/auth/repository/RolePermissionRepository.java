package com.boot.security.auth.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.boot.security.auth.model.Role;
import com.boot.security.auth.model.RolePermission;
import com.boot.security.auth.model.RolePermission;

public interface RolePermissionRepository extends JpaRepository<RolePermission, Long>{
	
	public List<RolePermission> findAll();
	
	public RolePermission findByRolePermissionPair(String rolePermissionPair);
	
	@Query(value="DELETE FROM ROLES_PERMISSIONS WHERE ROLE_PERMISSION_PAIR=?1",nativeQuery=true)
	public void deleteByRolePermissionPair(String rolePermissionPair);
	
	@Query(value="insert into roles_permissions (role_id,permission_id,role_permission_pair) values (?1,?2,?3)",nativeQuery=true)
	public void insertRolePermission(Long roleId, Long permissionId, String rolePermissionPair);

	@Query(value="SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END FROM roles_permissions where permission_id=?1 and role_id=?2",nativeQuery=true)
	int existsByPermissionIdAndRoleId(Long permission_id, Long role_id);   // 1 -- exist and 0-- not exist
	
}
