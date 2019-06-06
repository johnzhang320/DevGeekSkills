package com.oauth2.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.oauth2.demo.dao.OAuthDao;
import com.oauth2.demo.model.CustomUser;
import com.oauth2.demo.model.UserEntity;
/**
 * 
 *You can create a Custom User detail service class that extends the 
 *org.springframework.security.core.userdetails.UserDetailsService to call the DAO repository class as shown.
 *
 */
@Service
public class CustomDetailsService implements UserDetailsService {
   @Autowired
   OAuthDao oauthDao;

   @Override
   public CustomUser loadUserByUsername(final String username) throws UsernameNotFoundException {
      UserEntity userEntity = null;
      try {
         userEntity = oauthDao.getUserDetails(username);
         CustomUser customUser = new CustomUser(userEntity);
         return customUser;
      } catch (Exception e) {
         e.printStackTrace();
         throw new UsernameNotFoundException("User " + username + " was not found in the database");
      }
   }
}
