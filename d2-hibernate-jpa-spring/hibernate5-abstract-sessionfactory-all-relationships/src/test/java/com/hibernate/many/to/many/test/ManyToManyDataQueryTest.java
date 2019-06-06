package com.hibernate.many.to.many.test;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
 
import org.junit.jupiter.api.TestInfo;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.hibernate.base.service.BaseHibernateDAO;
import com.hibernate.base.service.BaseTestDAO;
import com.hibernate.many.to.many.dao.ManyToManyServiceImpl;
import com.hibernate.many.to.many.model.Group;
import com.hibernate.many.to.many.model.User;
import com.hibernate.one.to.many.dao.OneToManyServiceImpl;
/*
 * Note:
 * 1. If first time run this project , please check run configuration --> delete all items under Junit 
 * 2. Make sure that JRE is java 1.8
 */
@RunWith(JUnitPlatform.class)
public class ManyToManyDataQueryTest extends BaseTestDAO {
	static Logger Log = Logger.getLogger( ManyToManyDataQueryTest.class);
	public ManyToManyDataQueryTest() {
		super(new ManyToManyServiceImpl("validate"));
	}
	@Test
	public void showAllUsersPerGroupsTest() {
		Session session =dao.getSession();
		String hql = "from Group ";
		Query query = session.createQuery(hql);
		Log.info("Showing all user per groups!"); 
		List<Group> list = query.getResultList();
	
		showAllUsersByGroups(list);
	}
	
	@Test
	public void showAllGroupsPerUserTest() {
		Session session =dao.getSession();
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
