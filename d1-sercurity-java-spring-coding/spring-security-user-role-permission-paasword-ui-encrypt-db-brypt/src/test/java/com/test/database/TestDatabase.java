package com.test.database;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.boot.security.auth.model.Role;
import com.boot.security.auth.model.User;
import com.boot.security.auth.repository.RoleRepository;
import com.boot.security.auth.repository.UserRepository;
import com.boot.security.auth.service.UserService;
import com.boot.security.auth.service.UserServiceImpl;

public class TestDatabase {
	private static Logger log = Logger.getLogger(TestDatabase.class);
	@Autowired
	UserRepository userRep;
	
	@Autowired
	RoleRepository roleRep;
	
	//@Test 
	public void testFindUserByName() {
		log.info("testFindUserByName()");
		String username="Alex123";
		User user = userRep.findByUsername(username);
		if (user==null) {
			log.info("Failed to find user");
		}
		log.info(user.toString());
	}
	
	//@Test 
	public void testFindUserById() {
		log.info("testFindUserById()");
	 
		Optional<User> user = userRep.findById(1L);
		if (user.get()==null) {
			log.info("Failed to find role");
		}
		log.info(user.toString());
	}
	
	
	public void testFindRoleById() {
		log.info("testFindRoleById()");
	 
		Optional<Role> role = roleRep.findById(1L);
		if (role.get()==null) {
			log.info("Failed to find user");
		}
		log.info(role.toString());
	}

	public static void main(String args[]) {
		TestDatabase u = new TestDatabase();
		u.testFindUserByName();  
		 
	}
}
