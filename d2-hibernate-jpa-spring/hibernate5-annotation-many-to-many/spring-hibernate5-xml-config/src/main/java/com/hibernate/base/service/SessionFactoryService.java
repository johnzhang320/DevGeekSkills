package com.hibernate.base.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.service.ServiceRegistry;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

public interface SessionFactoryService  {
	public SessionFactory getSessionFactory();
	public Session getSession();
 
}
