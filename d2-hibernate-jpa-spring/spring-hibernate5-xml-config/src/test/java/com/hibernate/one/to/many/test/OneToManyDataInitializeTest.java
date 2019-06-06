package com.hibernate.one.to.many.test;

 
import java.util.HashSet;
import java.util.Set;

 
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.hibernate.base.service.ProxyDAO;
import com.hibernate.one.to.many.dao.CategoryDAO;
import com.hibernate.one.to.many.dao.OneToManyServiceImpl;

import com.hibernate.one.to.many.model.Category;
import com.hibernate.one.to.many.model.Product;
import com.hibernate.utils.SpringSessionFactoryCreate;
import com.hibernate.utils.XMLConfigLoader;
 
 

 /*
  *  Spring Junit 4 running test method on order by alphabet of methods, add RunX to sort it
  *  
  */
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration(classes = { XMLConfigLoader.class }, loader = AnnotationConfigContextLoader.class)
public class OneToManyDataInitializeTest  {
	static Logger Log = Logger.getLogger(  OneToManyDataInitializeTest.class);

	Session session;
	
	@Autowired
	ProxyDAO proxyDao;
	
	
    @Test
    public final void Run1_createOneCategoryProduct() {
    	Log.info("1 Run createOneCategoryProduct()");
		Category category = new Category("Computer");
         
        Product pc = new Product("DELL PC", "Quad-core PC", 1200, category);
         
        Product laptop = new Product("MacBook", "Apple High-end laptop", 2100, category);
         
        Product phone = new Product("iPhone 5", "Apple Best-selling smartphone", 499, category);
         
        Product tablet = new Product("iPad 3", "Apple Best-selling tablet", 1099, category);
         
        Set<Product> products = new HashSet<Product>();
        products.add(pc);
        products.add(laptop);
        products.add(phone);
        products.add(tablet);
        try { 
        category.setProducts(products);
        Log.info("Saving Category and Product");  
        
        CategoryDAO categoryDao = proxyDao.getCategoryDAO();
        
        categoryDao.attachDirty(category);
        session.persist(category);
        
        Log.info("Saved Category and Product"); 
         
        } catch (Exception e) {
        	Log.error("Error on "+e.getMessage());
        }  
        
	}
	//@Test 
	public void Run2_createTwoCategoryProduct() {
			// TODO Auto-generated method stub
		   Log.info("2 Run createTwoCategoryProduct()");	 
	        Category category = new Category("Car");
	         
	        Product pc = new Product("BMW", "BMW 502", 1200, category);
	         
	        Product laptop = new Product("Lexus", "Lexus E350", 2100, category);
	         
	        Product phone = new Product("Nisson", "Nissan Sentra", 499, category);
	         
	        
	         
	        Set<Product> products = new HashSet<Product>();
	        products.add(pc);
	        products.add(laptop);
	        products.add(phone);
	       
	        try { 
		        category.setProducts(products);
		        Log.info("Saving Category and Product");  
		        
		        CategoryDAO categoryDao = proxyDao.getCategoryDAO();
		        
		        categoryDao.attachDirty(category);
		        
		        Log.info("Saved Category and Product"); 
		         
	        } catch (Exception e) {
	        	Log.error("Error on "+e.getMessage());
	        }  
		} 
	//@Test 
	public void Run3_nativeSQLUpdateProduct() {   // Using native SQL and session.createSQLQuery, it works
		Log.info("3 nativeSQLUpdateProduct()");
		 
		Query query = session.createSQLQuery("update product set price=:price where name=:prodname");
		 
		query.setParameter("price",42500.0F);
	
		query.setParameter("prodname","BMW");
	 
		
	 
		int result = query.executeUpdate();
		
		Log.info("changed BMW price to $42500");
		
	}
}
