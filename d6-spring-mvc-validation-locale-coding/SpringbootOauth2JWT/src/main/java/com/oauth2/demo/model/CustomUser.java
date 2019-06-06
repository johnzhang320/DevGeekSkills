package com.oauth2.demo.model;

import org.springframework.security.core.userdetails.User;
/**
 * 
 * Now, use the following code and define the CustomUser class that extends the org.springframework.security.core.userdetails.User 
 * class for Spring Boot authentication. *
 */
public class CustomUser extends User {
   private static final long serialVersionUID = 1L;
   public CustomUser(UserEntity user) {
      super(user.getUsername(), user.getPassword(), user.getGrantedAuthoritiesList());
   }
}