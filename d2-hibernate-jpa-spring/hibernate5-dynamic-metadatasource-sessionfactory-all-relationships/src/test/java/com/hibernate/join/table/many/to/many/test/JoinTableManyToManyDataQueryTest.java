package com.hibernate.join.table.many.to.many.test;

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
import com.hibernate.join.table.many.to.many.dao.JoinTableManyToManyServiceImpl;
import com.hibernate.join.table.many.to.many.model.Group;
import com.hibernate.join.table.many.to.many.model.User;
import com.hibernate.join.table.many.to.many.model.UserGroup;
import com.hibernate.many.to.many.dao.ManyToManyServiceImpl;
/*
 * Note:
 * 1. If first time run this project , please check run configuration --> delete all items under Junit 
 * 2. Make sure that JRE is java 1.8
 */
@RunWith(JUnitPlatform.class)
public class JoinTableManyToManyDataQueryTest extends BaseTestDAO {
	static Logger Log = Logger.getLogger( JoinTableManyToManyDataQueryTest.class);
	public JoinTableManyToManyDataQueryTest() {
		super(new JoinTableManyToManyServiceImpl("validate"));
	}
	@Test
	public void showAllUsersPerGroupsTest() {
		Log.info("--------------------showAllUsersPerGroupsTest()-------------------------");
		Session session =dao.getSession();
		String hql = "from Group ";
		Query query = session.createQuery(hql);
		Log.info("Showing all user per groups!"); 
		List<Group> list = query.getResultList();
		Log.info("Group List size="+list.size());
		
		showAllUsersByGroups(list);
	}
	
	@Test
	public void showAllGroupsPerUserTest() {
		Log.info("--------------------showAllGroupsPerUserTest()-------------------------");
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
			for (UserGroup u: g.getUserGroups()) {
				Log.info("User "+u.getId()+", username="+u.getUser().getUsername()+",password="+u.getUser().getPassword()+",email="+u.getUser().getEmail());
			}
		}
		Log.info("Completed Show all user per groups!");
	}
	
	public void showAllGroupsByUsers(List<User> users) {
	
		for (User u: users) {
			Log.info("User "+u.getId()+", username="+u.getUsername()+",password="+u.getPassword()+",email="+u.getEmail());
			for (UserGroup g:u.getUserGroups()) {
				Log.info("Group "+g.getId()+","+g.getGroup().getName());
			}
		}
		 
		Log.info("Completed Show all groups per users!");
	}
}
