package com.boot.security.auth.config;

 
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
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


/*
 *  CustomerAuthenticaationProvider: 
 *  (1) Implements AuthenticationProvider interface which will be interface AuthenticationManagerBuilder.authenticationProvider(AuthenticationProvider provu=ider)
 *  (2) verify the username , password which user input to database stored ones, 
 *  (3) return UsernamePasswordAuthenticationToken(username, password, grantAuthenty role) where username and password == user input one
 *  (4) Based on role this user has, allow corresponding page them can access in WebSecurityConfigurerAdapter     
 */
@Service
public class CustomAuthenticationProviderHardCode implements AuthenticationProvider {
	private static final Logger logger = Logger.getLogger(CustomAuthenticationProviderHardCode.class);
	
	//@Autowired
	//BCryptPasswordEncoder bCryptPasswordEncoder;   // so funny that this encoder encodes same password two times and gets different encoded values
	 											   // we never ever use it if we needs to check if password is correct so weird Spring security uses it
	//@Autowired                                     
	//Sha256Encoder sha256Encoder;                   // using my own sha256 service
	
	//@Autowired
	//private UserRepository userRepository;
	
	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
	    String username = auth.getName();
	    String password = auth.getCredentials().toString();

	    if(username.equals("larry123") && password.equals("helloworld")) {
	        List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
	        grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
	        System.out.println(" grantedAuths.get(0)="+ grantedAuths.get(0).toString());
	        return new UsernamePasswordAuthenticationToken(username, password, grantedAuths);
	    } else if(username.equals("Alex123") && password.equals("password")) {
	        List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
	        grantedAuths.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
	        grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
	        return new UsernamePasswordAuthenticationToken(username, password, grantedAuths);
	    } else {
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
