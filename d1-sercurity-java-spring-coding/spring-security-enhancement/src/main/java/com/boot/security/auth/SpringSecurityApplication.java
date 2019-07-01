package com.boot.security.auth;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
 
import org.springframework.transaction.annotation.Transactional;

import com.boot.security.auth.model.Permission;
import com.boot.security.auth.model.Role;
import com.boot.security.auth.model.User;
import com.boot.security.auth.model.UserRole;
import com.boot.security.auth.repository.service.RepositoryService;
import com.boot.security.auth.repository.service.RolePermissionMatrixService;
  
 

@SpringBootApplication
public class SpringSecurityApplication extends SpringBootServletInitializer implements CommandLineRunner{
	@Autowired
	RepositoryService repositoryService;
	
	@Autowired
	RolePermissionMatrixService  rolePermissionMatrixService;
	
	@Autowired
	EntityManager entityManager;
	// server.port=8099  Oauth Client  
	// http://localhost:8099/spring-security-enhancement/registration
	// http://localhost:8099/spring-security-enhancement/safebox/dashboard.html
	// http://localhost:8099/spring-security-enhancement/safebox/bankaccount.html
	// http://localhost:8099/spring-security-enhancement/getKeyPair.html
	// http://localhost:8099/spring-security-enhancement/safebox/getEmployeesList
	// http://localhost:8099/spring-security-enhancement/home
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
    /**
     * Initialize DATA for database , tables data
     */
    @Transactional
    @Override
	public void run(String args[]) {
    	/**
    	 *  once initialized, we have to set property : spring.jpa.generate-ddl=false  
    	 *  and set spring.jpa.hibernate.ddl-auto=none or update !!!!!!
    	 */
    	
    	 boolean myuser = repositoryService.getUserRepository().existsByUsername("johnzhang320");
    	 boolean role =  repositoryService.getRoleRepository().existsByName("USER");
    	 boolean permission =  repositoryService.getPermissionRepository().existsByName("View files/folders");
    	 
    	 System.out.println("Role="+role+",myuser="+myuser+",permission ="+permission); 
    	 
    	 /**
    	  *  All below code check and avoid duplication to create
    	  */
 	 
		for (int i=0;i<InitializeData.ROLE_NAMES.length;i++) {
			Role r = new Role();
			r.setName(InitializeData.ROLE_NAMES[i]);
 			repositoryService.saveRole(r);
		}
	    for (int i=0; i<InitializeData.File_management.length;i++) {
	    	Permission p = new Permission();
	    	p.setName(InitializeData.File_management[i]);
	    	p.setCategory("c1_File_management");
	    	
	    	repositoryService.savePermission(p);
	    }
	   for (int i=0; i<InitializeData.Connectivity.length;i++) {
	    	Permission p = new Permission();
	    	p.setName(InitializeData.Connectivity[i]);
	    	p.setCategory("c2_Connectivity");
	    	repositoryService.savePermission(p);
	    }
	    for (int i=0; i<InitializeData.Account_management.length;i++) {
	    	Permission p = new Permission();
	    	p.setName(InitializeData.Account_management[i]);
	    	p.setCategory("c3_Account_management");
	    	repositoryService.savePermission(p);
	    }
	    for (int i=0; i<InitializeData.User_management.length;i++) {
	    	Permission p = new Permission();
	    	p.setName(InitializeData.User_management[i]);
	    	p.setCategory("c4_User_management");
	    	repositoryService.savePermission(p);
	    }
	   for (int i=0; i<InitializeData.Billing.length;i++) {
	    	Permission p = new Permission();
	    	p.setName(InitializeData.Billing[i]);
	    	p.setCategory("c5_Billing");
	    	repositoryService.savePermission(p);
	    }
		//saving brypt library encoded password
	 	
		User user1 = new User();
		user1.setUsername("johnzhang320");
		user1.setPassword("$2a$10$JEYf4X.2dfxZa3GcM3ESJOZlIa.6BpKEvPyVmMdqrd2papwZQ/V7W");
		repositoryService.saveNewUser(user1);
 
		User user2 = new User();
		user2.setUsername("Alex123");
		user2.setPassword("$2a$10$qavsaY7U4QaUVf.Ey0P.F.bSLpsL9.geOER.rNlofOyLeYY9ClgPK");
		repositoryService.saveNewUser(user2);
		
		User user3 = new User();
		user3.setUsername("larry123");
		user3.setPassword("$2a$10$YeOA5u4yLNWzP6dYc8grH.3r39zIkdvw4SbQrohPN3k/Jiw5rSxAq");
		repositoryService.saveNewUser(user3);
		
		User user4 = new User();
		user4.setUsername("henry456");
		user4.setPassword("$2a$10$2pfy7A9RkKjrRCSTLXXPeunTrzZK.LEZsiPVm93y4BfjC8psNC9km");
		repositoryService.saveNewUser(user4);
		 	
		repositoryService.addNewRoleToUser("Alex123","ADMIN");
	
 		
		repositoryService.addNewRoleToUser("johnzhang320","ADMIN");
		
		// avoid duplicated test
	 	repositoryService.addNewRoleToUser("Alex123","ADMIN");
	 	
	  //--------------Primary Owner--------------
	 	List<Permission> primary_owner_perms = repositoryService.getPermissionRepository().findAll();
	 	
	 	List<String> all_perms= primary_owner_perms.stream().map(x->x.getName()).collect(Collectors.toList());
	 	all_perms.forEach(System.out::println);
	 	repositoryService.assignNewPermissionsToRole("Primary Owner", primary_owner_perms);
	 	
	   //-------------- Owner--------------
	 	
	 	List<Permission> owner_perms = primary_owner_perms.stream()
	 			.filter(x->!x.equals("Transfer account ownership") && !x.equals("Close account")).collect(Collectors.toList());
	 	
		repositoryService.assignNewPermissionsToRole("Owner", owner_perms);
		
		//--------------Admin--------------
		List<Permission> admin_perms = new ArrayList<>();
		for (String s: InitializeData.File_management) admin_perms.add(repositoryService.getPermissionRepository().findByName(s));
		
		for (String s: InitializeData.Connectivity) admin_perms.add(repositoryService.getPermissionRepository().findByName(s));
		
		for (String s:InitializeData.Account_management) {
			if (s.equalsIgnoreCase("Transfer account ownership")) break;
			admin_perms.add(repositoryService.getPermissionRepository().findByName(s));
		}
		for (String s:InitializeData.Account_management) {
			if (s.equalsIgnoreCase("Change account name")) break;
			admin_perms.add(repositoryService.getPermissionRepository().findByName(s));
		}
		for (String s:InitializeData.User_management) {
			if (s.equalsIgnoreCase("Appoint owners")) break;
			admin_perms.add(repositoryService.getPermissionRepository().findByName(s));
		}
				
		
		
		repositoryService.assignNewPermissionsToRole("Admin", admin_perms);
		
		//--------------Editor--------------
		 
		for (String s : InitializeData.File_management) {
			if (s.equals("Empty trash")) break;
			repositoryService.addNewPermissionToRole("Editor", s);
		}
		
		//-------------Contributor--------------
	 
		for (String s : InitializeData.File_management) {
			if (s.equals("Move files/folders")) break;
			repositoryService.addNewPermissionToRole("Contributor", s);
		}
		
		//-------------Viewer-------------
 		 
		for (String s : InitializeData.File_management) {
			if (s.equals("Create folders")) break;
			repositoryService.addNewPermissionToRole("Viewer", s);
		}
		//-------------Billing-------------
		for (String s : InitializeData.File_management) {
			if (s.equals("Create folders")) break;
			repositoryService.addNewPermissionToRole("Billing", s);
		}
		for (String s : InitializeData.Billing) { 
			repositoryService.addNewPermissionToRole("Billing", s);
		}
 	 
 		repositoryService.addNewRoleToUser("johnzhang320","ADMIN");
 		
 		repositoryService.addNewRoleToUser("Alex123","ADMIN");
 		
 		repositoryService.addNewRoleToUser("Alex123","Owner");
 		
 		repositoryService.addNewRoleToUser("larry123","Editor");
 		
 	 
 		repositoryService.addNewRoleToUser("Alex123","Contributor"); 
 		
 		List<Role> roles = repositoryService.findRolesByUsername("Alex123");
 		
 		if (roles!=null) {
 			System.out.println("User Alex123 's roles are:");
 			roles.forEach(x->System.out.print(x.getName()+" "));
 		}
 		System.out.println("\n");
 		
 		List<User> users = repositoryService.findUsersByRole("ADMIN");
 		
 		if (roles!=null) {
 			System.out.println("Role Admin 's User are:");
 			users.forEach(x->System.out.print(x.getUsername()+" "));
 		}
 		System.out.println("\n");
 		
 		List<Role> roles2 = repositoryService.findRolesByUsername("larry123");
 		
 		if (roles2!=null) {
 			System.out.println("User larry123 's roles are:");
 			roles2.forEach(x->System.out.print(x.getName()+" "));
 		}
 		System.out.println("\n");
 		
 		repositoryService.assignUserNewRoles("larry123", roles);  // add ADMIN , SUPERUSER and KEEP USER and Delete EMPLOYEE
 		List<Role> roles3 = repositoryService.findRolesByUsername("larry123");
 		
 		if (roles3!=null) {
 			System.out.println("After re-assigned role, User larry123 's roles are:");
 			roles3.forEach(x->System.out.print(x.getName()+" "));
 		}
 		
 		System.out.println("\n\n All user information are:");
 		repositoryService.DiaplayAllUsers();
 		
 		
 		System.out.println("\n");
 		
 		
 		System.out.println("\n\n All Role Permission information are:");
 		repositoryService.DiaplayAllRolesAndPermission(); 
 		System.out.println("\n");
 		
 		Long role_count = repositoryService.getRoleRepository().count();
 		Long perm_count = repositoryService.getPermissionRepository().count();
 		
 		System.out.println("role_count="+role_count+".perm_count="+perm_count);
 		 
 		String matrix[][] =rolePermissionMatrixService.findPermissionsRolesMatrix();
 		
 		//rolePermissionMatrixService.diaplayMatrix(matrix);
    }
   
}
