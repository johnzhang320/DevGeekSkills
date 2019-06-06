package com.hibernate.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;


import com.hibernate.custom.type.model.*;
 
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata; 
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder; 
import org.hibernate.service.ServiceRegistry; 
import com.hibernate.custom.type.model.Phone;

import com.hibernate.custom.type.model.Address;

public class HibernatePropertiesSessionFactory {
    private static SessionFactory sessionFactory;
    private static String PROPERTY_FILE_NAME="hibernate-customtypes.properties";
    private static Session session;

    public static SessionFactory getSessionFactory() {
        return getSessionFactory(PROPERTY_FILE_NAME);
    }

    public static SessionFactory getSessionFactory(String propertyFileName)  {
    	try {
	        PROPERTY_FILE_NAME = propertyFileName;
	        if (sessionFactory == null) {
	            ServiceRegistry serviceRegistry = configureServiceRegistry();
	            sessionFactory = makeSessionFactory(serviceRegistry);
	        }
    	}catch (Exception e) {
    		System.out.println("Failed to get SessionFactory:"+e.getMessage());
    		
    	}
        return sessionFactory;
    }
    public static Session getSession()  {
    	try {
	    	if (session == null || !session.isOpen()) {
				if (sessionFactory == null) {
					getSessionFactory();
				}
				session = (sessionFactory != null) ? sessionFactory.openSession()
						: null;
				 
			}
    	} catch (Exception e) {
    		System.out.println("Failed to get Session:"+e.getMessage());
    		
    	}
        return session;
    }
    public static SessionFactory getSessionFactoryByProperties(Properties properties) throws IOException {
        ServiceRegistry serviceRegistry = configureServiceRegistry(properties);
        return makeSessionFactory(serviceRegistry);
    }

    private static SessionFactory makeSessionFactory(ServiceRegistry serviceRegistry) {
        MetadataSources metadataSources = new MetadataSources(serviceRegistry);

        metadataSources.addPackage("com.hibernate.custom.type.model");

        metadataSources.addAnnotatedClass(OfficeEmployee.class);
        metadataSources.addAnnotatedClass(Phone.class);

        Metadata metadata = metadataSources.getMetadataBuilder()
                .applyBasicType(LocalDateStringType.INSTANCE)
                .build();

        return metadata.getSessionFactoryBuilder()
                .build();

    }

    private static ServiceRegistry configureServiceRegistry() throws IOException {
        return configureServiceRegistry(getProperties());
    }

    private static ServiceRegistry configureServiceRegistry(Properties properties) throws IOException {
        return new StandardServiceRegistryBuilder().applySettings(properties)
                .build();
    }

    public static Properties getProperties() throws IOException {
        Properties properties = new Properties();
        URL propertiesURL = Thread.currentThread()
          .getContextClassLoader()
          .getResource(StringUtils.defaultString(PROPERTY_FILE_NAME, "hibernate.properties"));
        try (FileInputStream inputStream = new FileInputStream(propertiesURL.getFile())) {
            properties.load(inputStream);
        }
        return properties;
    }
}