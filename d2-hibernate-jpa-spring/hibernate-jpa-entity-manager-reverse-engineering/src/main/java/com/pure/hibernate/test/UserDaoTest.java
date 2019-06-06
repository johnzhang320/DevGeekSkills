package com.pure.hibernate.test;

import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
 
import org.junit.Test;

import com.pure.hibernate.dao.UserHome;
import com.pure.hibernate.model.User;

 

 
import org.junit.jupiter.api.TestInfo;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
/*
*  before run this test , change below property to 'create'
*    <property name="hibernate.hbm2ddl.auto" value="create"/>  
*    in persistence.xml file
*/
@RunWith(JUnitPlatform.class)
public class UserDaoTest {
	static Logger Log = Logger.getLogger( UserDaoTest.class);
	 
	
	@Test 
	public void addUserGeneratedDao() {
	 
		User user = new User();
    	
		user.setEmail("CatheringHelgo@hellywood.com");
    	user.setFullname("Cathering Helgo");
    	user.setPassword("godness");
    	
    	UserHome dao = new UserHome();
    	
    	Log.info("Adding User");
    	 
    	dao.persist(user);
    	 
    	Log.info("Added User");
    	
	}
	@Test 
	public void UpdateUserGeneratedDao() {
	 
	 
    	
    	UserHome dao = new UserHome();
    	User user2 = dao.findById(7);
    	Log.info("Update User");
    	user2.setFullname("Alex123");
		user2.setPassword("5e884898da28047151d0e5");
		user2.setEmail("AlixZhao0@gmail.com");
		User user3 = dao.findById(8);
		user3.setFullname("larry123");
		user3.setPassword("936a185caaa266bb9cbe9");
		user3.setEmail("larry1230@gmail.com");
		User user4 = dao.findById(9);
		user4.setFullname("henry456");
		user4.setPassword("14f8f4bb8c0e79a");
		user4.setEmail("henry4561@gmail.com");
		dao.merge(user2); 
		dao.merge(user3); 
		dao.merge(user4); 
    	Log.info("Added User");
    	
	}
	@Test 
	public void querySingleUser() {
		Log.info("Query Single User");
		UserHome dao = new UserHome();
		String sql = "SELECT u from User u where u.email = 'CatheringHelgo@hellywood.com'";
    	Query query = dao.getEntityManager().createQuery(sql);
    	User user = (User) query.getSingleResult();
    	Log.info(user.toString());
	}
	
	@Test 
	public void queryRangeUsers() {
		Log.info("Query Rangle Users");
		UserHome dao = new UserHome();
		String sql = "SELECT u from User u where u.password like 'billionaire%'";
    	Query query = dao.getEntityManager().createQuery(sql);
    	List<User> users = query.getResultList();
        users.forEach(x->System.out.println(x.toString()));
	}
	
}
