package com.boot.security.auth.service;

import com.boot.security.auth.model.Role;
import com.boot.security.auth.model.User;
import com.boot.security.auth.repository.RoleRepository;
import com.boot.security.auth.repository.UserRepository;
import com.boot.security.auth.repository.service.RepositoryService;
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
    private RepositoryService repositoryService;
    
    @Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
    @Override
    public void save(User user) {
        //user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    	user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    	Role role = roleRepository.findByName("USER");
    	if (role!=null) {
	    	List<Role> setRole = new ArrayList<Role>();
	    	setRole.add(role);
	        //user.getUserRoles().add(setRole);
    	}
    	repositoryService.saveNewUser(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}
