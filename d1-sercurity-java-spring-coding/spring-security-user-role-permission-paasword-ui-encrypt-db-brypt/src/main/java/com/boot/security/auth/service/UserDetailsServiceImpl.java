package com.boot.security.auth.service;

import com.boot.security.auth.model.Role;
import com.boot.security.auth.model.User;
import com.boot.security.auth.repository.UserRepository;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;
//AuthenticationProvider call  public UserDetails loadUserByUsername(String username) 
@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	private static final Logger logger = Logger.getLogger(UserDetailsServiceImpl.class);
	   
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) {
    	logger.info("loadUserByUsername begin");
        User user = userRepository.findByUsername(username);
        if (user == null) throw new UsernameNotFoundException(username);
       
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
      /*  for (Role role : user.getRoles()){
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }*/
        
        logger.info("loadUserByUsername end");
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }
}
