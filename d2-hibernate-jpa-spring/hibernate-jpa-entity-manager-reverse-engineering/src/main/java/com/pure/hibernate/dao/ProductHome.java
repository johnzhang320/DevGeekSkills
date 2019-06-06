package com.pure.hibernate.dao;
// Generated May 21, 2019 1:20:26 PM by Hibernate Tools 5.2.10.Final

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

import com.pure.hibernate.model.Product;
import com.pure.hibernate.utils.BaseEntityDAO;

/**
 * Home object for domain model class Product.
 * @see com.pure.hibernate.dao.Product
 * @author Hibernate Tools
 */
public class ProductHome extends BaseEntityDAO {

	private static final Log log = LogFactory.getLog(ProductHome.class);

/*	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext().lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException("Could not locate SessionFactory in JNDI");
		}
	}
*/
	public void persist(Product transientInstance) {
		log.debug("persisting Product instance");
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

 

	 

	public void delete(Product persistentInstance) {
		log.debug("deleting Product instance");
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

	public Product merge(Product detachedInstance) {
		log.debug("merging Product instance");
		try {
			tx_begin();
			Product result = (Product) getEntityManager().merge(detachedInstance);
			
			 
			tx_commit();
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Product findById(int id) {
		log.debug("getting Product instance with id: " + id);
		try {
			Product instance = (Product) getEntityManager().find(Product.class, id);
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
