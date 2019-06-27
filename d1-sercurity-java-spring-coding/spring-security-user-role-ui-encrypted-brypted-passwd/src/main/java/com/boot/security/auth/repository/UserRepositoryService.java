package com.boot.security.auth.repository;

 
import java.util.List;
 
import org.springframework.stereotype.Service;

import com.boot.security.auth.model.Role;
import com.boot.security.auth.model.User;
 

@Service
public interface UserRepositoryService {
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
}
