package com.hibernate.many.to.many.test;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.hibernate.base.service.BaseHibernateDAO;
import com.hibernate.base.service.BaseTestDAO;
import com.hibernate.many.to.many.dao.ManyToManyServiceImpl;
import com.hibernate.many.to.many.model.Group;
import com.hibernate.many.to.many.model.User;
import com.hibernate.one.to.many.dao.OneToManyServiceImpl;
import com.hibernate.utils.SpringSessionFactoryCreate;
import com.hibernate.utils.SpringSessionFactoryValidate;
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration(classes = { SpringSessionFactoryValidate.class }, loader = AnnotationConfigContextLoader.class)
public class ManyToManyDataQueryTest {
	static Logger Log = Logger.getLogger( ManyToManyDataQueryTest.class);
	Session session;
	@Autowired
	SessionFactory sessionFactory;
	
	@Before
	public void before() {
		
		session = sessionFactory.openSession();
		session.beginTransaction();
		
	}
	@After
	public void after() {
		 
		session.getTransaction().commit();
		session.close();
		
	}
	@Test
	public void Run1_showAllUsersPerGroupsTest() {
	
		String hql = "from Group ";
		Query query = session.createQuery(hql);
		Log.info("Showing all user per groups!"); 
		List<Group> list = query.getResultList();
	
		showAllUsersByGroups(list);
	}
	
	@Test
	public void Run2_showAllGroupsPerUserTest() {
	
		String hql = "from User ";
		Query query = session.createQuery(hql);
		Log.info("Showing all group per users!"); 
		List<User> list = query.getResultList();
	
		showAllGroupsByUsers(list);
	}
	public void showAllUsersByGroups(List<Group> grps) {
		for (Group g:grps) {
			Log.info("Group "+g.getId()+","+g.getName());
			for (User u: g.getUsers()) {
				Log.info("User "+u.getId()+", username="+u.getUsername()+",password="+u.getPassword()+",email="+u.getEmail());
			}
		}
		Log.info("Completed Show all user per groups!");
	}
	
	public void showAllGroupsByUsers(List<User> users) {
	
		for (User u: users) {
			Log.info("User "+u.getId()+", username="+u.getUsername()+",password="+u.getPassword()+",email="+u.getEmail());
			for (Group g:u.getGroups()) {
				Log.info("Group "+g.getId()+","+g.getName());
			}
		}
		 
		Log.info("Completed Show all groups per users!");
	}
}
