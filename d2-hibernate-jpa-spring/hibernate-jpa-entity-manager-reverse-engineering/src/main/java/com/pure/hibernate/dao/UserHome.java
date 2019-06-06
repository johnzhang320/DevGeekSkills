package com.pure.hibernate.dao;
// Generated May 21, 2019 1:20:26 PM by Hibernate Tools 5.2.10.Final

 
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
 

import com.pure.hibernate.model.User;
import com.pure.hibernate.utils.BaseEntityDAO;
 


/**
 * Home object for domain model class User.
 * @see com.pure.hibernate.dao.User
 * @author Hibernate Tools
 */
public class UserHome extends BaseEntityDAO {

	private static final Log log = LogFactory.getLog(UserHome.class);

	/*private final SessionFactory sessionFactory =HibernateSessionFactory.getSessionFactory();

	public SessionFactory getSessionFactory() {
		 return sessionFactory;
	}*/

	public void persist(User transientInstance) {
		log.debug("persisting User instance");
		try {
			tx_begin();
			getEntityManager().persist(transientInstance);
			tx_commit();
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	 

	 

	public void delete(User persistentInstance) {
		log.debug("deleting User instance");
		try {
			//getEntityManager().delete(persistentInstance);
			tx_begin();
			getEntityManager().remove(persistentInstance);
			tx_commit();
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public User merge(User detachedInstance) {
		log.debug("merging User instance");
		try {
			tx_begin();
			User result = (User) getEntityManager().merge(detachedInstance);
			tx_commit();
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public User findById(int id) {
		log.debug("getting User instance with id: " + id);
		try {
			User instance = (User) getEntityManager().find(User.class,id);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	 
}
