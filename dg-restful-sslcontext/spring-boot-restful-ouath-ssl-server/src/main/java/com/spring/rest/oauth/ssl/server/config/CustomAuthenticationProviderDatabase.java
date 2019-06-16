package com.spring.rest.oauth.ssl.server.config;

 
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.frontend.encrypt.utils.KeyPairManager;
import com.frontend.encrypt.utils.Sha256Encoder;
import com.frontend.encrypt.utils.Sha256EncoderImpl;
import com.spring.rest.oauth.ssl.server.model.Role;
import com.spring.rest.oauth.ssl.server.model.User;
import com.spring.rest.oauth.ssl.server.repository.UserRepository;
/*
 *  CustomerAuthenticaationProvider: 
 *  (1) Implements AuthenticationProvider interface which will be interface AuthenticationManagerBuilder.authenticationProvider(AuthenticationProvider provu=ider)
 *  (2) verify the username , password which user input to database stored ones, 
 *  (3) return UsernamePasswordAuthenticationToken(username, password, grantAuthenty role) where username and password == user input one
 *  (4) Based on role this user has, allow corresponding page them can access in WebSecurityConfigurerAdapter     
 */
@Service
public class CustomAuthenticationProviderDatabase implements AuthenticationProvider {
	private static final Logger logger = Logger.getLogger(CustomAuthenticationProviderDatabase.class);
	
 	 											   // we never ever use it if we needs to check if password is correct so weird Spring security uses it
	@Autowired                                     
	Sha256Encoder sha256Encoder;                   // using my own sha256 service
	
	@Autowired
	private UserRepository userRepository;
	
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    	
    	// from authenticatication we can retrieve username and password which user current input in login page
    	// we can decrypt it if it is encrypted, apply BCryptPasswordEncoder() it , comparing with database one
    
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        
          
    	logger.info("from authentication() user iuput username="+username+" ,password="+password);
    	
    	// find it from database by username
    	User user = userRepository.findByUsername(username);
    	// if not found, throw exception
    	if (user == null) {
    		logger.error("Username Not Found "+username+" in database");
    		throw new UsernameNotFoundException(username); 
    	}
        // user private key decrypt your password
    	String decrypted = password;    // this demo running is SSL/TLS , we do not need from end encryption KeyPairManager.getInstance().decrypt(password);	
	 
    	// encode user input password
    	String encodedPassword =sha256Encoder.hashEncoder(decrypted); //(password);
    	
    	logger.info("User input password encoded ="+encodedPassword+", database stored password="+user.getPassword());
    	
    	// for more restriction authentication required , must check if password is correct 
    	if (encodedPassword.equals(user.getPassword())) {
      		// user and role are many to many relationship, fill the grantAuthorities list for role check
            Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
            int count=0;
            for (Role role : user.getRoles()){
            	String dbRole = role.getName();
            	String finalRole = dbRole.indexOf("ROLE_")!=-1 ? dbRole:"ROLE_"+dbRole;
                grantedAuthorities.add(new SimpleGrantedAuthority(finalRole));
                logger.info("User  ="+username+", Role ="+finalRole);
            }
            logger.info( "grantedAuthorities.size()="+grantedAuthorities.size()); 
            return new UsernamePasswordAuthenticationToken(username, password,  grantedAuthorities);
             
        } else {
        	// even username is correct but wrong password, throw exception 
        	logger.error( "Failed by wrong password !"); 
            throw new
              BadCredentialsException("Found wrong password or failed to find roles!");
        }         
    }
 
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(
          UsernamePasswordAuthenticationToken.class);
    }
}
