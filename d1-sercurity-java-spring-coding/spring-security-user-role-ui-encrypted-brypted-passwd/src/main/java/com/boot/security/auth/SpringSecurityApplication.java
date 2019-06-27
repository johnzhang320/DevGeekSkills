package com.boot.security.auth;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
 
import org.springframework.transaction.annotation.Transactional;

import com.boot.security.auth.model.Role;
import com.boot.security.auth.model.User;
import com.boot.security.auth.model.UserRole;
import com.boot.security.auth.repository.UserRepositoryService;
 
 

@SpringBootApplication
public class SpringSecurityApplication extends SpringBootServletInitializer implements CommandLineRunner{
	@Autowired
	 UserRepositoryService userRepositoryService;
	
 
	
	@Autowired
	EntityManager entityManager;
	// server.port=8091  Oauth Client  
	// http://localhost:8091/registration
	// http://localhost:8091/safebox/dashboard.html
	// http://localhost:8091/safebox/bankaccount.html
	// http://localhost:8091/getKeyPair.html
	// http://localhost:8091/safebox/getEmployeesList
	// http://localhost:8091/home
	// Alex123/password   Admin
	// larry123/helloworld  USER
	// henry456 / abc12345
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringSecurityApplication.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SpringSecurityApplication.class, args);
    }
    /*
     * Initialize DATA for database , tables data
     */
    
    @Override
	public void run(String args[]) {
    	/**
    	 *  once initialized, we have to set property : spring.jpa.generate-ddl=false  
    	 *  and set spring.jpa.hibernate.ddl-auto=none or update !!!!!!
    	 */
    	// Role role =roleRepository.findByName("USER");
    	// User myuser = userRepository.findByUsername("johnzhang320");
    	// UserRepositoryService userRepositoryService = new UserRepositoryServiceImpl(roleRepository, userRepository,
    	//			userRoleRepository);
    	 boolean myuser = userRepositoryService.getUserRepository().existsByUsername("johnzhang320");
    	 boolean role =  userRepositoryService.getRoleRepository().existsByName("USER");
    	 
    	 System.out.println("Role="+role+",myuser="+myuser); 
    	 
    	 
 		 if (!role && !myuser) {
   			 
 			Role r1 = new Role();
 			r1.setName("USER");
 			userRepositoryService.getRoleRepository().save(r1);
 			Role r2 = new Role();
 			r2.setName("ADMIN");
 			userRepositoryService.getRoleRepository().save(r2);
 			Role r3 = new Role();
 			r3.setName("SUPERUSER");
 			userRepositoryService.getRoleRepository().save(r3);
 			Role r4 = new Role();
 			r4.setName("EMPLOYEE");
 			userRepositoryService.getRoleRepository().save(r4);
 			Role r5 = new Role();
 			r5.setName("DIRECTOR");
 			userRepositoryService.getRoleRepository().save(r5);
 			
 			Role r6 = new Role();
 			r6.setName("CFO");
 			userRepositoryService.getRoleRepository().save(r6);
 			
 			Role r7 = new Role();
 			r7.setName("CEO");
 			userRepositoryService.getRoleRepository().save(r7);
 			
 		 
 			//saving brypt library encoded password
 			
 			User user1 = new User();
 			user1.setUsername("johnzhang320");
 			user1.setPassword("$2a$10$JEYf4X.2dfxZa3GcM3ESJOZlIa.6BpKEvPyVmMdqrd2papwZQ/V7W");
 			userRepositoryService.saveNewUser(user1);
 	 
 			User user2 = new User();
 			user2.setUsername("Alex123");
 			user2.setPassword("$2a$10$qavsaY7U4QaUVf.Ey0P.F.bSLpsL9.geOER.rNlofOyLeYY9ClgPK");
 			userRepositoryService.saveNewUser(user2);
 			
 			User user3 = new User();
 			user3.setUsername("larry123");
 			user3.setPassword("$2a$10$YeOA5u4yLNWzP6dYc8grH.3r39zIkdvw4SbQrohPN3k/Jiw5rSxAq");
 			userRepositoryService.saveNewUser(user3);
 			
 			User user4 = new User();
 			user4.setUsername("henry456");
 			user4.setPassword("$2a$10$2pfy7A9RkKjrRCSTLXXPeunTrzZK.LEZsiPVm93y4BfjC8psNC9km");
 			userRepositoryService.saveNewUser(user4);
 			 	
 			userRepositoryService.addNewRoleToUser("Alex123","ADMIN");
 		
 	 		
 			userRepositoryService.addNewRoleToUser("johnzhang320","ADMIN");
 			
 			// avoid duplicated test
 		 	userRepositoryService.addNewRoleToUser("Alex123","ADMIN");
   		 
 	    }
  		
 		
 		//userRepositoryService.getUserRoleRepository().deleteByUserRolePair("Alex123_USER");
 		
 		//userRepositoryService.deleteRoleFromUser("johnzhang320","ADMIN");
 		
 		// userRepositoryService.addNewRoleToUser("Alex123","SUPERUSER");
 		
 	//	List<Role> roles=userRepositoryService.findRolesByUsername("Alex123");
 		 
 	 
 		
 		userRepositoryService.addNewRoleToUser("johnzhang320","ADMIN");
 		
 		userRepositoryService.addNewRoleToUser("Alex123","ADMIN");
 		
 		userRepositoryService.addNewRoleToUser("Alex123","USER");
 		
 		userRepositoryService.addNewRoleToUser("larry123","EMPLOYEE");
 		
 	 
 		userRepositoryService.addNewRoleToUser("Alex123","SUPERUSER"); 
 		
 		List<Role> roles = userRepositoryService.findRolesByUsername("Alex123");
 		
 		if (roles!=null) {
 			System.out.println("User Alex123 's roles are:");
 			roles.forEach(x->System.out.print(x.getName()+" "));
 		}
 		System.out.println("\n");
 		
 		List<User> users = userRepositoryService.findUsersByRole("ADMIN");
 		
 		if (roles!=null) {
 			System.out.println("Role Admin 's User are:");
 			users.forEach(x->System.out.print(x.getUsername()+" "));
 		}
 		System.out.println("\n");
 		
 		List<Role> roles2 = userRepositoryService.findRolesByUsername("larry123");
 		
 		if (roles2!=null) {
 			System.out.println("User larry123 's roles are:");
 			roles2.forEach(x->System.out.print(x.getName()+" "));
 		}
 		System.out.println("\n");
 		
 		userRepositoryService.assignUserNewRoles("larry123", roles);  // add ADMIN , SUPERUSER and KEEP USER and Delete EMPLOYEE
 		List<Role> roles3 = userRepositoryService.findRolesByUsername("larry123");
 		
 		if (roles3!=null) {
 			System.out.println("After re-assigned role, User larry123 's roles are:");
 			roles3.forEach(x->System.out.print(x.getName()+" "));
 		}
 		
 		System.out.println("\n\n All user information are:");
 		userRepositoryService.DiaplayAllUsers();
 		System.out.println("\n");
 		
    }
   
}
