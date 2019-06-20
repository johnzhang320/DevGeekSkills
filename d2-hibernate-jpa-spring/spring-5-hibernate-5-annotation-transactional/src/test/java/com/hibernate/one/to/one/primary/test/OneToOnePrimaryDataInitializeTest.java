package com.hibernate.one.to.one.primary.test;

import java.util.List;

import org.apache.log4j.Logger;
 
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

import com.hibernate.base.service.BaseHibernateDAO;
import com.hibernate.base.service.BaseTestDAO;
import com.hibernate.one.to.one.primary.dao.OneToOneServiceImpl;
import com.hibernate.one.to.one.primary.model.Product121;
import com.hibernate.one.to.one.primary.model.ProductDetail;
import com.hibernate.utils.SpringSessionFactoryCreate;
import com.hibernate.utils.SpringSessionFactoryValidate;

@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration(classes = { SpringSessionFactoryCreate.class }, loader = AnnotationConfigContextLoader.class)
public class OneToOnePrimaryDataInitializeTest {
	static Logger Log = Logger.getLogger( OneToOnePrimaryDataInitializeTest.class);
	// set @Component for Class SpringSessionFactory , class level Autowired , dynamically set bernate_Hbm2ddl_Auto and scam package
	@Autowired
	SessionFactory sessionFactory;
	
	Session session;
	
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
	public void Run1_createOneTOOnePrimaryBrandNew() {
		
		 
		 // creates a new product
        Product121 product = new Product121();
        product.setName("Civic");
        product.setDescription("Comfortable, fuel-saving car");
        product.setPrice(20000);
         
        // creates product detail
        ProductDetail detail = new ProductDetail();
        detail.setPartNumber("ABCDEFGHIJKL");
        detail.setDimension("2,5m x 1,4m x 1,2m");
        detail.setWeight(1000);
        detail.setManufacturer("Honda Automobile");
        detail.setOrigin("Japan");
         
        // sets the bi-directional association
        product.setProductDetail(detail);
        detail.setProduct(product);
          
        // persists the product
        session.save(product);
       
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
	@Test
	public void Run2_QueryOneTOOnePrimary() {
		
		 
		 // creates a new product
       
       
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
