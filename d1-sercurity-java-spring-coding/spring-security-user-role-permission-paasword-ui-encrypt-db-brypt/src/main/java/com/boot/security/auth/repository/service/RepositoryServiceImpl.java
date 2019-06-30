package com.boot.security.auth.repository.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.boot.security.auth.model.Permission;
import com.boot.security.auth.model.Role;
import com.boot.security.auth.model.RolePermission;
import com.boot.security.auth.model.User;
import com.boot.security.auth.model.UserRole;
import com.boot.security.auth.repository.PermissionRepository;
import com.boot.security.auth.repository.RolePermissionRepository;
import com.boot.security.auth.repository.RoleRepository;
import com.boot.security.auth.repository.UserRepository;
import com.boot.security.auth.repository.UserRoleRepository;
  
@Repository
public class RepositoryServiceImpl implements RepositoryService {
	static Logger Log = Logger.getLogger(RepositoryServiceImpl.class);
	@Autowired 
	RoleRepository roleRepository;
	
	@Autowired 
	PermissionRepository permissionRepository;
	
	@Autowired  
	UserRepository userRepository ;
	
	@Autowired  
	UserRoleRepository userRoleRepository ;
	
	@Autowired  
	RolePermissionRepository rolePermissionRepository ;
	
	private static final Long DELETE=-1L;
	private static final Long KEEP=-2L;
	private static final Long INSERT=-3L;
	
	
	  public RepositoryServiceImpl(RoleRepository roleRepository, UserRepository userRepository,
			UserRoleRepository userRoleRepository) {
		super();
		this.roleRepository = roleRepository;
		this.userRepository = userRepository;
		this.userRoleRepository = userRoleRepository;
	}
	@Transactional
	public User saveNewUser(User user) {
		    User old = userRepository.findByUsername(user.getUsername());
		    if (old!=null && old.getUsername()!=null) {
		    	Log.info("User exists! as "+old.getUsername());
		    	return old;
		    }
			Role role = roleRepository.findByName("Viewer");
			UserRole ur = new UserRole();
			ur.setRole(role);
			ur.setUser(user);
			ur.createUserRolePair();
			user.getUserRoles().add(ur);
			userRoleRepository.save(ur);	
			return userRepository.findByUsername(user.getUsername());
	  }
	 @Transactional
	 public User addNewRoleToUser(String username,String roleName) {
 	 		 
 	 		 
 	 		 UserRole userRole = userRoleRepository.findByUserRolePair(username+"_"+roleName);
 	 		 
			 if (userRole==null) {
				Role r2 = roleRepository.findByName(roleName);
			    User user = userRepository.findByUsername(username);
		 		UserRole ur = new UserRole();
				ur.setRole(r2);
				ur.setUser(user);
				ur.createUserRolePair();
			
				user.getUserRoles().add(ur);
				userRoleRepository.save(ur);
			 }
		 return userRepository.findByUsername(username);
	 }
	 
	 @Transactional
	 public Role addNewPermissionToRole(String roleName,String permissionName) {
		 
 	 		 Role role = roleRepository.findByName(roleName);
 	 		 RolePermission rolePermission = role.getRolePermissions().stream()
 	 				 .filter(x->x.getRolePermissionPair().equals(roleName+"_"+permissionName))
 	 				 .findAny().orElse(null);
	 		 
			 if (rolePermission==null) {
				Permission p2 = permissionRepository.findByName(permissionName);
			 
		 		RolePermission ur = new RolePermission();
		 		ur.setPermission(p2);
		 		ur.setRole(role);
 				ur.createRolePermissionPair();
 				role.getRolePermissions().add(ur);
				rolePermissionRepository.save(ur);
			 }
		 return roleRepository.findByName(roleName);
	 }
	 
	 
	 @Transactional
	 public User deleteRoleFromUser(String username,String rolename) {
		
		 userRoleRepository.deleteByUserRolePair(username+"_"+rolename);
	 	 
		 return userRepository.findByUsername(username);
	 }
	 @Transactional
	 public Role deletePermissionFromRole(String roleName,String permissionName) {
		
		 rolePermissionRepository.deleteByRolePermissionPair(roleName+"_"+permissionName);
 	  
		 return roleRepository.findByName(roleName);
	 }
	 /**
	  *  refresh user's roles by merge way, current roles exists in old roles , keep , not exists , add, old role not exists in current role, deleted
	  */
	 @Transactional
	 public User assignUserNewRoles(String username,List<Role> roles) {
		  User user = userRepository.findByUsername(username);
		  Long userId = user.getId();
		  List<UserRole> oldUserRoles = user.getUserRoles();
		  Map<String,Long> map = new HashMap<>();
		  if (oldUserRoles!=null) {
 			  oldUserRoles.forEach(x->{
				  String pair = x.getUserRolePair();
				  map.put(pair, DELETE);
			  });
		  }
		  if (roles!=null) {
			  roles.forEach(x->{
			  String key = username+"_"+x.getName();	  
			  if (map.containsKey(key)) {
				  map.put(key, KEEP);
			  } else {
				  map.put(key, x.getId());   // put roleId
			  }
			  });
		  }
	      map.keySet().stream().forEach(k->{  // k is userRolePair
	    	  Long choice = map.get(k);
	    	  if (map.get(k)==DELETE) {
	    		  userRoleRepository.deleteByUserRolePair(k);
	    	  } else if (choice>0L) {
	    		  userRoleRepository.insertUserRole(userId, choice, k);
	    	  }
	      });
	      return userRepository.findByUsername(username); 
	  }
	 /**
	  * refresh permissions in role by merge way
	  * @param roleName
	  * @param permissions
	  * @return
	  */
	 @Transactional
	 public Role assignNewPermissionsToRole(String roleName,List<Permission> permissions) {
		  Role role = roleRepository.findByName(roleName);
		  
		  Long roleId = role.getId();
		  List<RolePermission> oldRolePermissions = role.getRolePermissions();
		  Map<String,Long> map = new HashMap<>();   // key is Role_Permission_Pair 
		  if (oldRolePermissions !=null) {
			  oldRolePermissions.forEach(x->{
				  String pair = x.getRolePermissionPair();
				  map.put(pair, DELETE);
			  });
		  }
		  if (permissions!=null) {
			  permissions.forEach(x->{
			  String key = roleName+"_"+x.getName();	  
			  if (map.containsKey(key)) {
				  map.put(key, KEEP);
			  } else {
				  map.put(key, x.getId());   // put roleId as new role to be added
			  }
			  });
		  }
	      map.keySet().stream().forEach(k->{  // k is rolePermissionPair
	    	  Long choice = map.get(k);
	    	  if (map.get(k)==DELETE) {
	    		  rolePermissionRepository.deleteByRolePermissionPair(k);
 	    	  } else if (choice>0L) {
 	    		 rolePermissionRepository.insertRolePermission(roleId, choice, k);
	    	  }
	      });
	      return roleRepository.findByName(roleName); 
	  }
	 
	 
	  public List<Role> findRolesByUsername(String username) {
		  User user = userRepository.findByUsername(username);
		  return user.getUserRoles().stream()
				  .map(x->x.getRole())
				  .collect(Collectors.toList());
	  }
	  
	  public List<Permission> findPermissionsByRoleName(String roleName) {
		  Role role = roleRepository.findByName(roleName);
			  return role.getRolePermissions().stream()
				  .map(x->x.getPermission())
				  .collect(Collectors.toList());
	  }
	  
	  public String findRolesStringByUsername(String username) {
		  User user = userRepository.findByUsername(username);
		  return user.getUserRoles().stream()
				  .map(x->x.getRole().getName())
				  .collect(Collectors.joining(",")); 
	  }
	  public List<User> findUsersByRole(String roleName) {
		  Role role = roleRepository.findByName(roleName);
		  List<UserRole> userroles = role.getUserRoles();
		  List<User> users =  userroles.stream()
   		      .map(x->{
  		    	  User user = new User();
  		    	  user.setUsername(x.getUser().getUsername());
  		    	  user.setPassword(x.getUser().getPassword());
  		    	  user.setId(x.getUser().getId());
  		    	  user.getUserRoles().add(x);
  		    	  return user;
  		      }).collect(Collectors.toList());
		  return users;
	  }
	  
	  public List<Role> findRolesByPermission(String permissionName) {
		  Permission permission = permissionRepository.findByName(permissionName);
		  
		  List<RolePermission> rolePermissions = permission.getRolePermissions();
		  List<Role> roles =  rolePermissions.stream()
   		      .map(x->{
  		    	  Role role = new Role();
  		    	  role.setName(x.getRole().getName());
  		    	  role.setId(x.getRole().getId());
  		    	  role.getRolePermissions().add(x);
    		      return role;
  		      }).collect(Collectors.toList());
		  return roles;
	  }  
	  
	  
 
	  public void DiaplayAllUsers() {
		  List<User> users = userRepository.findAll();
		  users.forEach(x->{
			  System.out.print(x.getId()+" "+x.getUsername()+" "+x.getPassword()+" ");
			  if (x.getUserRoles()!=null) {
				  x.getUserRoles().forEach(y->{
					  System.out.print("\""+y.getRole().getName()+ "\", ");
				  });
			  }
			  System.out.println(" ");
		  });
	 }
	 public void DiaplayAllRolesAndPermission() {
		  List<Role> roles = roleRepository.findAll();
		  roles.forEach(x->{
			  System.out.print(x.getId()+" "+x.getName()+" privileges are: ");
			  if (x.getRolePermissions()!=null) {
				  x.getRolePermissions().forEach(y->{
					  System.out.print("\""+y.getPermission().getName()+"\", ");
				  });
			  }
			  System.out.println(" ");
		  });
	 }  
	 
	public void saveRole(Role r) {
		Role old = roleRepository.findByName(r.getName());
		if (old==null) {
			roleRepository.save(r);
		}
	}
	public void savePermission(Permission r) {
		Permission old = permissionRepository.findByName(r.getName());
		if (old==null) {
			permissionRepository.save(r);
		}
	}
	public void saveUser(User r) {
		User old = userRepository.findByUsername(r.getUsername());
		if (old==null) {
			userRepository.save(r);
		}
	}
	
	public RoleRepository getRoleRepository() {
		return roleRepository;
	}
	public UserRepository getUserRepository() {
		return userRepository;
	}
	public UserRoleRepository getUserRoleRepository() {
		return userRoleRepository;
	}
	public PermissionRepository getPermissionRepository() {
		return permissionRepository;
	}
	
	public RolePermissionRepository getRolePermissionRepository() {
		return rolePermissionRepository;
	}

	  
}
