package com.pure.hibernate.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.HibernateException;



/**
 * Configures and provides access to Hibernate entityManagers, tied to the
 * current thread of execution.  Follows the Thread Local EntityManager
 * pattern, see {@link http://hibernate.org/42.html }.
 */
public class JPAEntityManagerFactory {

    /** 
     * Location of hibernate.cfg.xml file.
     * Location should be on the classpath as Hibernate uses  
     * #resourceAsStream style lookup for its configuration file. 
     * The default classpath location of the hibernate config file is 
     * in the default package. Use #setConfigFile() to update 
     * the location of the configuration file for the current entityManager.   
     */

	private static final ThreadLocal<EntityManager> threadLocal = new ThreadLocal<EntityManager>();
    private static final String persistence_unit="UsersDB";
    
    protected static EntityManager entityManager;
    protected static EntityManagerFactory factory;
	static {
		 factory = Persistence.createEntityManagerFactory(persistence_unit);
    }
    private JPAEntityManagerFactory() {
    	
    }
	
	/**
     * Returns the ThreadLocal EntityManager instance.  Lazy initialize
     * the <code>EntityManagerFactory</code> if needed.
     *
     *  @return EntityManager
     *  @throws HibernateException
     */
    public static EntityManager getEntityManager() throws HibernateException {
        EntityManager entityManager = (EntityManager) threadLocal.get();

		if (entityManager == null || !entityManager.isOpen()) {
			if (factory == null) {
				rebuildEntityManagerFactory();
			}
			entityManager = (factory != null) ?  entityManager = factory.createEntityManager()
					: null;
			threadLocal.set(entityManager);
		}

        return entityManager;
    }

	/**
     *  Rebuild hibernate entityManager factory
     *
     */
	public static void rebuildEntityManagerFactory() {
		try {
			 factory = Persistence.createEntityManagerFactory( persistence_unit);
			 entityManager = factory.createEntityManager();
		} catch (Exception e) {
			System.err
					.println("%%%% Error Creating EntityManagerFactory %%%%");
			e.printStackTrace();
		}
	}

	/**
     *  Close the single hibernate entityManager instance.
     *
     *  @throws HibernateException
     */
    public static void closeEntityManager() throws HibernateException {
        EntityManager entityManager = (EntityManager) threadLocal.get();
        threadLocal.set(null);

        if (entityManager != null) {
            entityManager.close();
        }
    }

	 
}