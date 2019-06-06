package com.hibernate.join.table.many.to.many.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.service.ServiceRegistry;

import com.hibernate.join.table.many.to.many.model.UserGroup;
import com.hibernate.utils.HibernateAbstractSessionFactory;
import com.hibernate.base.service.SessionFactoryService;
import com.hibernate.join.table.many.to.many.model.Group;
import com.hibernate.join.table.many.to.many.model.User;

public class JoinTableManyToManyServiceImpl extends HibernateAbstractSessionFactory implements SessionFactoryService{
	 
	public JoinTableManyToManyServiceImpl() {}
	public JoinTableManyToManyServiceImpl(String Hibernate_Hbm2ddl_Auto) {
		super.Hibernate_Hbm2ddl_Auto=Hibernate_Hbm2ddl_Auto;
	}
	//override getSessionFactory() in SessionFactoryService
	 @Override
	 public SessionFactory getSessionFactory() {
		 return super.getSessionFactory("hibernate-base.properties");
	 }
	//override getSession() in SessionFactoryService
	 @Override
	 public Session getSession() {
		 return super.getSession();
	 }
	 //override getPropertiesFile() in HibernateAbstractSessionFactory
	 @Override
	 public String getPropertiesFile() {
		 return "hibernate-base.properties";
	 }
	 //override getMetadataSources() in HibernateAbstractSessionFactory
	 @Override
	 public Metadata getMetadataSources(ServiceRegistry serviceRegistry) {
		 MetadataSources metadataSources = new MetadataSources(serviceRegistry);

        metadataSources.addPackage("com.hibernate.join.table.many.to.many.model");

        metadataSources.addAnnotatedClass(Group.class);
        metadataSources.addAnnotatedClass(User.class);
        metadataSources.addAnnotatedClass(UserGroup.class);
         
       
        Metadata metadata = metadataSources.getMetadataBuilder()
                //.applyBasicType(LocalDateStringType.INSTANCE)
                .build();
        return metadata;
	 }
}
