package com.hibernate.base.service;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.service.ServiceRegistry;

public interface SessionFactoryService  {
	public SessionFactory getSessionFactory();
	public Session getSession();
	public void closeSession() ;
 
}
