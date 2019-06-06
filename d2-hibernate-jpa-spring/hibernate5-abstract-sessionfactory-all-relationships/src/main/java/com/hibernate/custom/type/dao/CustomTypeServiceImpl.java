package com.hibernate.custom.type.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.service.ServiceRegistry;

import com.hibernate.base.service.SessionFactoryService;
import com.hibernate.custom.type.model.OfficeEmployee;
import com.hibernate.custom.type.model.Phone;
import com.hibernate.utils.HibernateAbstractSessionFactory;
import com.hibernate.utils.LocalDateStringType;

public class CustomTypeServiceImpl extends HibernateAbstractSessionFactory implements SessionFactoryService{
	 String hibernate_hbm2ddl_auto=null;
	 @Override
	 public SessionFactory getSessionFactory() {
		 return super.getSessionFactory("hibernate-base.properties");
	 }
 
	 
	 public  CustomTypeServiceImpl(String hibernate_hbm2ddl_auto) {
		 this.hibernate_hbm2ddl_auto=hibernate_hbm2ddl_auto;
	 } 
	 public  CustomTypeServiceImpl() {
		 this.hibernate_hbm2ddl_auto=null;
	 } 
	 public void setHibernate_hbm2ddl_auto(String hibernate_hbm2ddl_auto) {
		this.hibernate_hbm2ddl_auto = hibernate_hbm2ddl_auto;
	 }

	 @Override
	 public Session getSession() {
		 return super.getSession();
	 }
	 @Override
	 public String getPropertiesFile() {
		 return "hibernate-base.properties";
	 }
	 @Override
	 public Metadata getMetadataSources(ServiceRegistry serviceRegistry) {
		 MetadataSources metadataSources = new MetadataSources(serviceRegistry);

        metadataSources.addPackage("com.hibernate.custom.type.model");

        metadataSources.addAnnotatedClass(OfficeEmployee.class);
        metadataSources.addAnnotatedClass(Phone.class);

        Metadata metadata = metadataSources.getMetadataBuilder()
                .applyBasicType(LocalDateStringType.INSTANCE)
                .build();
        return metadata;
	 }
}
