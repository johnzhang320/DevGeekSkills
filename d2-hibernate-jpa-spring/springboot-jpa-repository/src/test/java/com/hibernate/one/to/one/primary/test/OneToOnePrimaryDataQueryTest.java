package com.hibernate.one.to.one.primary.test;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.hibernate.base.service.BaseTestDAO;
import com.hibernate.one.to.one.primary.dao.OneToOneServiceImpl;
import com.hibernate.one.to.one.primary.model.Product121;
import com.hibernate.one.to.one.primary.model.ProductDetail;
import com.hibernate.utils.SpringSessionFactoryValidate;

@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration(classes = { SpringSessionFactoryValidate.class }, loader = AnnotationConfigContextLoader.class)
public class OneToOnePrimaryDataQueryTest  {
	static Logger Log = Logger.getLogger( OneToOnePrimaryDataQueryTest.class);
	// set @Component for Class SpringSessionFactory , class level Autowired , dynamically set bernate_Hbm2ddl_Auto and scam package
	 
		Session session;
		@Autowired
		SessionFactory sessionFactory;
		
		@Before
		public void before() {
 			session = sessionFactory.openSession();
			session.beginTransaction();
			
		}
		@After
		public void after() {
 			 
			session.getTransaction().commit();
			session.close();
			
		}
 
	    @Test
	    public void Run1_QueryOneTOOnePrimary() {
	     
        // queries all products
        List<Product121> listProduct121s = session.createQuery("from Product121").list();
        for (Product121 aProd : listProduct121s) {
            String info = "Product121: " + aProd.getName() + "\n";
            info += "\tDescription: " + aProd.getDescription() + "\n";
            info += "\tPrice: $" + aProd.getPrice() + "\n";
             
            ProductDetail aDetail = aProd.getProductDetail();
            info += "\tPart number: " + aDetail.getPartNumber() + "\n";
            info += "\tDimension: " + aDetail.getDimension() + "\n";
            info += "\tWeight: " + aDetail.getWeight() + "\n";
            info += "\tManufacturer: " + aDetail.getManufacturer() + "\n";
            info += "\tOrigin: " + aDetail.getOrigin() + "\n";
             
            Log.info(info);
        }
       
	}
	
}
