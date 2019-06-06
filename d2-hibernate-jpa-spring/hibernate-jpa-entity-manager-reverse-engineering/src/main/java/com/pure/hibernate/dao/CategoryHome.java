package com.pure.hibernate.dao;
// Generated May 21, 2019 1:20:26 PM by Hibernate Tools 5.2.10.Final

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

import com.pure.hibernate.model.Category;
import com.pure.hibernate.utils.BaseEntityDAO;

/**
 * Home object for domain model class Category.
 * @see com.pure.hibernate.dao.Category
 * @author Hibernate Tools
 */
public class CategoryHome extends BaseEntityDAO {

	private static final Log log = LogFactory.getLog(CategoryHome.class);

 
	public void persist(Category transientInstance) {
		log.debug("persisting Category instance");
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

 
 

	public void delete(Category persistentInstance) {
		log.debug("deleting Category instance");
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

	public Category merge(Category detachedInstance) {
		log.debug("merging Category instance");
		try {
			//Category result = (Category) getEntityManager().merge(detachedInstance);
			tx_begin();
			Category result= (Category) getEntityManager().merge(detachedInstance);;
			tx_commit();
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Category findById(int id) {
		log.debug("getting Category instance with id: " + id);
		try {
			Category instance = (Category) getEntityManager().find(Category.class,
					id);
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
