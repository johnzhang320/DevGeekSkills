package com.hibernate.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata; 

import org.hibernate.boot.registry.StandardServiceRegistryBuilder; 
import org.hibernate.service.ServiceRegistry; 

/*
 *  Please find below abstract methods and implements in your child classes
 */
public abstract class HibernateAbstractSessionFactory {
	static Logger Log = Logger.getLogger(HibernateAbstractSessionFactory.class);

    private  static SessionFactory sessionFactory;
    private  String propertyFileName="hibernate-base.properties";
    private  Session session;
	private static final ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();
	/*
	 *  implement below abstract method with your 
	 *  Current hibernate.hbm2ddl.auto
	 *  MetaDataSource
	 *  Properties file
	 */
	protected String Hibernate_Hbm2ddl_Auto=null;
    protected abstract Metadata getMetadataSources(ServiceRegistry serviceRegistry);  
    protected abstract String getPropertiesFile();  
    
    
    public  SessionFactory getSessionFactory() { 
    	String prop = propertyFileName;
    	if (getPropertiesFile()!=null) {
    		prop= getPropertiesFile();
    		propertyFileName = prop;
    	}
        return getSessionFactory(prop);
    }
   
    public  SessionFactory getSessionFactory(String propertyFileName)  {
    	try {
	        propertyFileName = propertyFileName;
	        if (sessionFactory == null) {
	            ServiceRegistry serviceRegistry = configureServiceRegistry();
	            sessionFactory = makeSessionFactory(serviceRegistry);
	        }
    	}catch (Exception e) {
    		Log.info("Failed to get SessionFactory:"+e.getMessage());
    		
    	}
        return sessionFactory;
    }
    public  Session getSession()  {
    	session = 	threadLocal.get(); 
    	try {
	    	if (session == null || !session.isOpen()) {
				if (sessionFactory == null) {
					getSessionFactory();
				}
				session = (sessionFactory != null) ? sessionFactory.openSession()
						: null;
				threadLocal.set(session);
				 
			}
    	} catch (Exception e) {
    		Log.info("Failed to get Session:"+e.getMessage());
    		
    	}
        return session;
    }
    
 
	public static void closeSession() throws HibernateException {
        Session session = (Session) threadLocal.get();
        threadLocal.set(null);

        if (session != null) {
            session.close();
        }
    }
    public  SessionFactory getSessionFactoryByProperties(Properties properties) throws IOException {
        ServiceRegistry serviceRegistry = configureServiceRegistry(properties);
        return makeSessionFactory(serviceRegistry);
    }

    private  SessionFactory makeSessionFactory(ServiceRegistry serviceRegistry) {
    	Metadata metadata =getMetadataSources(serviceRegistry);

        return metadata.getSessionFactoryBuilder()
                .build();

    }

    private  ServiceRegistry configureServiceRegistry() throws IOException {
        return configureServiceRegistry(getProperties());
    }

    private  ServiceRegistry configureServiceRegistry(Properties properties) throws IOException {
        return new StandardServiceRegistryBuilder().applySettings(properties)
                .build();
    }

    public  Properties getProperties() throws IOException {
        Properties properties = new Properties();
        URL propertiesURL = Thread.currentThread()
          .getContextClassLoader()
          .getResource(StringUtils.defaultString(propertyFileName, "hibernate.properties"));
        try (FileInputStream inputStream = new FileInputStream(propertiesURL.getFile())) {
            properties.load(inputStream);
            if (Hibernate_Hbm2ddl_Auto!=null) {
            	properties.put("hibernate.hbm2ddl.auto", Hibernate_Hbm2ddl_Auto);
            }
            Log.info("current hibernate.hbm2ddl.auto="+properties.get("hibernate.hbm2ddl.auto"));
            
        }
        return properties;
    }
}