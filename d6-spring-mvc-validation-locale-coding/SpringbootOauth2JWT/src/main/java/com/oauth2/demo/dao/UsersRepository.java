package com.oauth2.demo.dao;

 
import org.springframework.data.repository.CrudRepository;
import com.oauth2.demo.model.Users;
 
public interface UsersRepository extends CrudRepository<Users, Long>{
	 public Users findByUsername(String username);
}
