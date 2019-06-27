package com.boot.security.auth.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.boot.security.auth.model.Role;
import com.boot.security.auth.model.User;
import com.boot.security.auth.model.UserRole;
  
@Repository
public class UserRepositoryServiceImpl implements UserRepositoryService {
	@Autowired 
	RoleRepository roleRepository;

	@Autowired  
	UserRepository userRepository ;
	
	@Autowired  
	UserRoleRepository userRoleRepository ;
	
	private static final Long DELETE=-1L;
	private static final Long KEEP=-2L;
	private static final Long INSERT=-3L;
	
	
	  public UserRepositoryServiceImpl(RoleRepository roleRepository, UserRepository userRepository,
			UserRoleRepository userRoleRepository) {
		super();
		this.roleRepository = roleRepository;
		this.userRepository = userRepository;
		this.userRoleRepository = userRoleRepository;
	}
	@Transactional
	public User saveNewUser(User user) {
			Role role = roleRepository.findByName("USER");
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
 	 		 User user = userRepository.findByUsername(username);
 	 		 UserRole userRole = user.getUserRoles().stream().filter(x->x.getUserRolePair().equals(username+"_"+roleName)).findAny().orElse(null);
	 		 
			 if (userRole==null) {
				 Role r2 = roleRepository.findByName(roleName);
			 
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
	 public User deleteRoleFromUser(String username,String rolename) {
		
		 userRoleRepository.deleteByUserRolePair(username+"_"+rolename);
		 
		 User user = userRepository.findByUsername(username);
		 
		 
 		 
		 return userRepository.findByUsername(username);
	 }
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
	 
	  public List<Role> findRolesByUsername(String username) {
		  User user = userRepository.findByUsername(username);
		  return user.getUserRoles().stream()
				  .map(x->x.getRole())
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
	  
	  
 
	  public void DiaplayAllUsers() {
		  List<User> users = userRepository.findAll();
		  users.forEach(x->{
			  System.out.print(x.getId()+" "+x.getUsername()+" "+x.getPassword()+" ");
			  if (x.getUserRoles()!=null) {
				  x.getUserRoles().forEach(y->{
					  System.out.print(y.getRole().getName()+ " ");
				  });
			  }
			  System.out.println(" ");
		  });
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
	  
}
