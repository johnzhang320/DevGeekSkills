package com.boot.security.auth.repository.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.boot.security.auth.model.Permission;
import com.boot.security.auth.model.Role;
import com.boot.security.auth.model.User;
import com.boot.security.auth.model.UserRole;
@Repository
public class RolePermissionMatrixServiceImpl implements RolePermissionMatrixService{
	static Logger Log = Logger.getLogger(RolePermissionMatrixServiceImpl.class);
	@Autowired
	RepositoryService repositoryService;
	private static final int CHECKED=-1;
	private static final int UNCHECKED=-2;
	 
	/**
	 *  matrix[i][j] = 1 checked , i is permission id and j is role id 
	 *  o row and o col contains names of permissions and roles
	 */
	public void updateRolePermissionMatrix(String matrix[][]) {
		if (null==matrix) return;
		int rows = matrix.length;
		int cols = matrix[0].length;
		for (int j=1;j<cols;j++) {
			List<Permission> perms = new ArrayList<>();
			for (int i=1;i<rows;i++) {
				int ii= i-1;
				int jj = j-1;
				if (matrix[ii][jj].equals("CHECKED")) {
					Long permId = Long.valueOf(ii);
					Optional<Permission> perm = repositoryService.getPermissionRepository().findById(permId);
					if (perm.isPresent()) {
						Permission p=perm.get();
						perms.add(p);
					}
					
				}
			}
			if (!perms.isEmpty()) {
				Long roleId = Long.valueOf(j-1);
				Optional<Role> role = repositoryService.getRoleRepository().findById(roleId);
				if (role.isPresent()) {
					repositoryService.assignNewPermissionsToRole(role.get().getName(), perms);
				}
			}
		}
	}
	
	public String[][] findPermissionsRolesMatrix() {
		Long role_count = repositoryService.getRoleRepository().count();
 		Long perm_count = repositoryService.getPermissionRepository().count();
		if (role_count==0 || perm_count==0) {
			Log.info("Role and Permission tables are not initialized");
		}
		String row_str = String.valueOf(perm_count);
		String col_str = String.valueOf(role_count);
		int rows = Integer.valueOf(row_str);     // column 0 is names of permissions
		int cols = Integer.valueOf(col_str);     // line 0 is names of roles
		String[][] matrix= new String[rows+1][cols+1];
		List<Permission> perms =   repositoryService.getPermissionRepository().findAll();
		List<Role> roles =   repositoryService.getRoleRepository().findAll();
		matrix[0][0]="Roles / Permissions";
				
		for (int i=0;i<perms.size();i++) {
			matrix[i+1][0] = perms.get(i).getName();
		}
		for (int j=0;j<roles.size();j++) {
			matrix[0][j+1] = roles.get(j).getName();
		}
		for (int i=1;i<rows+1;i++) {
			for (int j=1;j<cols+1;j++) {
				Long permId =Long.valueOf(i);
				Long roleId =Long.valueOf(j);
				int exist=repositoryService.getRolePermissionRepository().existsByPermissionIdAndRoleId(permId, roleId);
				 
				matrix[i][j] = exist==1 ? "CHECKED" : "UNCHECKED";
				 
			}
		}
		return matrix;
	}
	
	public List<Permission> findAllPermissionsByUser(String userName) {
		User user = repositoryService.getUserRepository().findByUsername(userName);
		List<Permission> result = new ArrayList<>();
		if (user!=null) {
			
			List<UserRole> userRoles = user.getUserRoles();
			if (userRoles!=null) {
				List<Role> roles = userRoles.stream().map(x->x.getRole()).collect(Collectors.toList());
				Set<String> uniqueSet =new HashSet<String>();
				roles.forEach(x->{
					List<Permission> perms = x.getRolePermissions().stream().map(y->y.getPermission()).collect(Collectors.toList());
					perms.forEach(z->{
						if (!uniqueSet.contains(z.getName())) {
							result.add(z);
							uniqueSet.add(z.getName());
						}
					});
				});
 			}
 		}
		return result;
	}
	
	public void diaplayMatrix(String matrix[][]) {
		if (matrix.length==0) return;
		for (int i=0;i<matrix.length;i++) {
			System.out.println(" ");
			for (int j=0; j<matrix[0].length;j++) {
				System.out.print(matrix[i][j]+"\t\t");
			}
			
		}
	}
}
