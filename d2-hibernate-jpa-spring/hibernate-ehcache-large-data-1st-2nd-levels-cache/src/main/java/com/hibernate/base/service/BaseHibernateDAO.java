package com.hibernate.base.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class BaseHibernateDAO  {
	private SessionFactoryService sessionFactoryservice;
	public BaseHibernateDAO() {}
	public BaseHibernateDAO(SessionFactoryService service) {
		sessionFactoryservice = service;
	}
	public Session getSession() {
		return sessionFactoryservice.getSession();
	}
	public void closeSession() {
		 sessionFactoryservice.closeSession();
	}
	public SessionFactory getSessionFactory() {
		return sessionFactoryservice.getSessionFactory();
	}
	public void tx_begin() {
		sessionFactoryservice.getSession().beginTransaction();
	}
	public void tx_commit() {
		sessionFactoryservice.getSession().getTransaction().commit();
	}
  
}
