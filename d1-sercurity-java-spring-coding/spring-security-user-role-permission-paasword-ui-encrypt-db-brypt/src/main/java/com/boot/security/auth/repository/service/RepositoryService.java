package com.boot.security.auth.repository.service;

 
import java.util.List;
 
import org.springframework.stereotype.Service;

import com.boot.security.auth.model.Permission;
import com.boot.security.auth.model.Role;
import com.boot.security.auth.model.User;
import com.boot.security.auth.repository.PermissionRepository;
import com.boot.security.auth.repository.RolePermissionRepository;
import com.boot.security.auth.repository.RoleRepository;
import com.boot.security.auth.repository.UserRepository;
import com.boot.security.auth.repository.UserRoleRepository;
 

@Service
public interface RepositoryService {
	  public User saveNewUser(User user);
	  public User assignUserNewRoles(String username,List<Role> roles) ;
 	  public List<Role> findRolesByUsername(String username);
	  public String findRolesStringByUsername(String username) ;
	  public List<User> findUsersByRole(String roleName) ;
 	  public void DiaplayAllUsers() ;
 	  public User addNewRoleToUser(String username,String roleName);
 	  public User deleteRoleFromUser(String username,String roleName);
 	  public RoleRepository getRoleRepository() ;
 	  public UserRepository getUserRepository() ;
 	  public UserRoleRepository getUserRoleRepository() ;
 	  
 	  public Role addNewPermissionToRole(String roleName,String permissionName);
 	  public Role deletePermissionFromRole(String roleName,String permissionName);
 	  public Role assignNewPermissionsToRole(String roleName,List<Permission> permissions);
 	  public List<Permission> findPermissionsByRoleName(String roleName);
 	  public List<Role> findRolesByPermission(String permissionName);
 	  public void DiaplayAllRolesAndPermission();
 	 
 	  public PermissionRepository getPermissionRepository() ;
 	  public RolePermissionRepository getRolePermissionRepository();
 	  
 	  public void saveRole(Role r) ;
 	  public void savePermission(Permission r) ;
 	  public void saveUser(User r) ;
}
