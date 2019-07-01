package com.boot.security.auth.repository.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.boot.security.auth.model.Permission;
@Service
public interface RolePermissionMatrixService {
	public void updateRolePermissionMatrix(String matrix[][]);
	public List<Permission> findAllPermissionsByUser(String userName);
	public String[][] findPermissionsRolesMatrix();
	public void diaplayMatrix(String matrix[][]);
}
