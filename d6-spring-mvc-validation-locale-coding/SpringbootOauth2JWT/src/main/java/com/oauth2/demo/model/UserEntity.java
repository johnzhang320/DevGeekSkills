package com.oauth2.demo.model;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
/**
 * 
 * Use the following code to define the POJO class to store the User information for authentication
 *
 */
 
public class UserEntity {
	 
 
    private String username;
    
	private String password;
	
   private Collection<GrantedAuthority> grantedAuthoritiesList = new ArrayList<>();
   
   public String getPassword() {
      return password;
   }
   public void setPassword(String password) {
      this.password = password;
   }
   public Collection<GrantedAuthority> getGrantedAuthoritiesList() {
      return grantedAuthoritiesList;
   }
   public void setGrantedAuthoritiesList(Collection<GrantedAuthority> grantedAuthoritiesList) {
      this.grantedAuthoritiesList = grantedAuthoritiesList;
   }
   public String getUsername() {
      return username;
   }
   public void setUsername(String username) {
      this.username = username;
   }
}
