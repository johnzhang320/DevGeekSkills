package com.boot.security.auth;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.boot.security.auth.model.Role;
import com.boot.security.auth.model.User;
import com.boot.security.auth.repository.RoleRepository;
import com.boot.security.auth.repository.UserRepository;

@SpringBootApplication
public class SpringSecurityApplication extends SpringBootServletInitializer implements CommandLineRunner{
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	UserRepository userRepository ;
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
    	 Role role =roleRepository.findByName("USER");
    	 User myuser = userRepository.findByUsername("johnzhang320");
    	System.out.println("Role="+role+",myuser="+myuser); 
 		if (role==null && myuser==null) {
 			Role r1 = new Role();
 			r1.setName("USER");
 			roleRepository.save(r1);
 			Role r2 = new Role();
 			r2.setName("ADMIN");
 			roleRepository.save(r2);
 			Role r3 = new Role();
 			r3.setName("SUPERUSER");
 			roleRepository.save(r3);
 			Role r4 = new Role();
 			r4.setName("EMPLOYEE");
 			roleRepository.save(r4);
 			Role r5 = new Role();
 			r5.setName("DIRECTOR");
 			roleRepository.save(r5);
 			
 			Role r6 = new Role();
 			r6.setName("CFO");
 			roleRepository.save(r6);
 			
 			Role r7 = new Role();
 			r7.setName("CEO");
 			roleRepository.save(r7);
 			
 			
 		
 		/*
 		 * INSERT INTO `user` VALUES (1,'c71cf930d34aa9944bc92405279595ac307f595afa6d9c5d73488c86b3841c6f','johnzhang320' ),
 		 * (2,'5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8','Alex123'),
 		 * (3,'936a185caaa266bb9cbe981e9e05cb78cd732b0b3280eb944412bb6f8f8f07af','larry123'),
 		 * (4,'14f8f4bb8c0e79a02670a5fea5682da717a5b3d3dc7b1706f7a4bab9afae18c2','henry456');
 		 */

 			
 			User user1 = new User();
 			user1.setUsername("johnzhang320");
 			user1.setPassword("c71cf930d34aa9944bc92405279595ac307f595afa6d9c5d73488c86b3841c6f");
 			
 			
 			User user2 = new User();
 			user2.setUsername("Alex123");
 			user2.setPassword("5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8");
 			
 			
 			User user3 = new User();
 			user3.setUsername("larry123");
 			user3.setPassword("936a185caaa266bb9cbe981e9e05cb78cd732b0b3280eb944412bb6f8f8f07af");
 			
 			
 			User user4 = new User();
 			user4.setUsername("henry456");
 			user4.setPassword("14f8f4bb8c0e79a02670a5fea5682da717a5b3d3dc7b1706f7a4bab9afae18c2");
 			
 			
 			roleRepository.save(r1);
 			
 			r1 = roleRepository.findByName("USER");
 			List<Role> roles = new ArrayList<Role>();
 			roles.add(r1);
 			user1.setRoles(roles);
 			user2.setRoles(roles);
 			user3.setRoles(roles);
 			user4.setRoles(roles);
 			
 			userRepository.save(user1);
 			userRepository.save(user2);
 			userRepository.save(user3);
 			userRepository.save(user4);
 		   
 		}
 	    if (false) {  // first time it is true, second time is false
	 		 Role r2 = roleRepository.findByName("ADMIN");
	 		 
	 		 User u2 = userRepository.findByUsername("johnzhang320");
	 		 u2.getRoles().add(r2);
	 		 userRepository.save(u2);
 	    }
 		 roleRepository.findAll().forEach(x->System.out.print(x.getName()+" "));
 		 System.out.println("");  
 		 for (User x: userRepository.findAll()) {
 			
 			System.out.print(x.getId()+", " +x.getUsername()+", "+x.getPassword()+", ");
 			String s="";
 			for (Role r:x.getRoles()) {
 				s+=r.getName()+" ";
 			}
 			System.out.println(s);
 		 }
 		// userRepository.findAll().forEach(x->System.out.println(x.getId()+"," +x.getUsername()+", "+x.getPassword()+","+ x.getRoles().forEach(y->System.out.print(y.getName()))));
 		 
    }
   
}
