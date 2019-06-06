package com.pure.hibernate.utils;

import javax.persistence.EntityManager;

import org.hibernate.Session;

public class BaseEntityDAO {
	
	public EntityManager getEntityManager() {
		return JPAEntityManagerFactory.getEntityManager();
	}
	public void tx_begin() {
		JPAEntityManagerFactory.getEntityManager().getTransaction().begin(); 
	}
	public void tx_commit() {
		JPAEntityManagerFactory.getEntityManager().getTransaction().commit();
	}
 
}
