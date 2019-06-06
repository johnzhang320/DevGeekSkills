package com.hibernate.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class BaseHibernateDAO  {
	
	public Session getSession() {
		return HibernateSessionFactory.getSession();
	}
	public SessionFactory getSessionFactory() {
		return HibernateSessionFactory.getSessionFactory();
	}
	public void tx_begin() {
		HibernateSessionFactory.getSession().beginTransaction();
	}
	public void tx_commit() {
		HibernateSessionFactory.getSession().getTransaction().commit();
	}
  
}
