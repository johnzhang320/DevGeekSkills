package com.test.database;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.boot.security.auth.model.UserRole;
import com.boot.security.auth.repository.UserRepositoryService;

public class TestDatabaseService {
	static Logger Log = Logger.getLogger(TestDatabaseService.class);
	@Autowired
	UserRepositoryService userRepositoryService;
	public void testone() {
		
	//	UserRole entity = userRepositoryService.getUserRoleRepository()..getOne(7); //.findByUserRolePair("Alex123_ADMIN");
		Long id =7L;
		userRepositoryService.getUserRoleRepository().deleteById(id);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Log.info("Test Main ()=");
		TestDatabaseService ref = new TestDatabaseService();
		ref.testone();
	}

}
