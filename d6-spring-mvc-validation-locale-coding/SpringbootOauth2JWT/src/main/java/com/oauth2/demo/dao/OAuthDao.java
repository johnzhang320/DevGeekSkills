package com.oauth2.demo.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;

import com.oauth2.demo.model.UserEntity;
import com.oauth2.demo.model.Users;
/**
 * 
 * You can create the @Repository class to read the User information from the database and 
 * send it to the Custom user service and also add the granted authority “ROLE_SYSTEMADMIN”.
 * table USERS contain user information
 */
@Repository
public class OAuthDao {
   @Autowired
   private UsersRepository usersRepository;

   public UserEntity getUserDetails(String username) {
	   
     Collection<GrantedAuthority> grantedAuthoritiesList = new ArrayList<>();
   
     Users user = usersRepository.findByUsername(username);
        
     GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_SYSTEMADMIN");
     grantedAuthoritiesList.add(grantedAuthority);
     UserEntity userEntity = new UserEntity();
     userEntity.setPassword(user.getPassword());
     userEntity.setUsername(user.getUsername());
     
     userEntity.setGrantedAuthoritiesList(grantedAuthoritiesList);
     return userEntity;
       
       
   }
}
