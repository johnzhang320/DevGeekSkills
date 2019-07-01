package com.boot.security.auth.service;

import com.boot.security.auth.model.User;

public interface UserService {
    public void save(User user);

    public User findByUsername(String username);
    
   // public void updateUser(User newUser,boolean oldPassword);
}
