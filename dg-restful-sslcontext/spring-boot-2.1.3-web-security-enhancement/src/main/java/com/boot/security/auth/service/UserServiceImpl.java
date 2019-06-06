package com.boot.security.auth.service;

import com.boot.security.auth.model.Role;
import com.boot.security.auth.model.User;
import com.boot.security.auth.repository.RoleRepository;
import com.boot.security.auth.repository.UserRepository;
import com.frontend.encrypt.utils.Sha256Encoder;
import com.frontend.encrypt.utils.Sha256EncoderImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;  // weird code same value two time can be different !
    
    @Autowired
    private Sha256Encoder sha256Encoder;

    @Override
    public void save(User user) {
        //user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    	user.setPassword(sha256Encoder.hashEncoder(user.getPassword()));
    	Role role = roleRepository.findByName("USER");
    	if (role!=null) {
	    	List<Role> setRole = new ArrayList<Role>();
	    	setRole.add(role);
	        user.setRoles(setRole);
    	}
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    /**
     *  before calling, 
     *  newUser roles, password, username must be set
     *  password must be set to plainText format if oldPassword = false
     */
    
    @Override
    public void updateUser(User newUser,boolean oldPassword) {
    	String username = newUser.getUsername();
    	User existUser  = findByUsername(username);
    	if (existUser!=null) {
    		existUser.setUsername(username);
    		if (!oldPassword) {
	    		String password = sha256Encoder.hashEncoder(newUser.getPassword());
	       		existUser.setPassword(password);
    	    }
       		existUser.setRoles(newUser.getRoles());
     	    save(existUser);
    	}
    }
    
}
