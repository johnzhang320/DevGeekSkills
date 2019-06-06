package com.hibernate.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class BaseHibernateDAO  {
	
	public Session getSession() {
		return HibernatePropertiesSessionFactory.getSession();
	}
	public SessionFactory getSessionFactory() {
		return HibernatePropertiesSessionFactory.getSessionFactory();
	}
	public void tx_begin() {
		HibernatePropertiesSessionFactory.getSession().beginTransaction();
	}
	public void tx_commit() {
		HibernatePropertiesSessionFactory.getSession().getTransaction().commit();
	}
  
}
