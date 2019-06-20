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
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hibernate.base.service.BaseTestDAO;
import com.hibernate.one.to.many.dao.OneToManyServiceImpl;

import com.hibernate.one.to.many.model.Category;
import com.hibernate.one.to.many.model.Product;
import com.hibernate.utils.SpringSessionFactoryCreate;
 
 

 /**
  *  Spring Junit 4 running test method on order by alphabet of methods, add RunX to sort it
  *  @ContextConfiguration load the SpringSessionFactoryCreate.class which make possible Autowired the SessionFactory
  */
/**
 * 
 * @Transactional
 *  
 
		****************Propagation******************
		
		Defines how transactions relate to each other. Common options
		
		Required: Code will always run in a transaction. Create a new transaction or reuse one if available.
		Requires_new: Code will always run in a new transaction. Suspend current transaction if one exist.
		Support: 
		@Transactional( propagation = Propagation.SUPPORTS,readOnly = true )
		@Transactional( propagation = Propagation.SUPPORTS,readOnly = true )
		
		*********************Isolation*******************
		
		Defines the data contract between transactions.
		
		Read Uncommitted: Allows dirty reads, means Specifies that statements can read rows that have been modified by
		 				  other transactions but not yet committed.Transactions running at the READ UNCOMMITTED level
		 				  do not issue shared locks to prevent other transactions from modifying data read by the current transaction.
		 				  
		Read Committed: Does not allow dirty reads, means Specifies that statements cannot read data that has been modified but not 
						committed by other transactions
						
		Repeatable Read: If a row is read twice in the same transaction, result will always be the same,means Specifies that 
						 statements cannot read data that has been modified but not yet committed by other transactions and
						 that no other transactions can modify data that has been read by the current transaction until the 
						 current transaction completes.
						 
		Serializable: 	Performs all transactions in a sequence, means Statements cannot read data that has been modified but 
						not yet committed by other transactions.
						No other transactions can modify data that has been read by the current transaction until the current 
						transaction completes.
						Other transactions cannot insert new rows with key values that would fall in the range of keys read by 
						any statements in the current transaction until the current transaction completes.
		
		Example:
		@Transactional(isolation = Isolation.SERIALIZABLE)

 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration(classes = { SpringSessionFactoryCreate.class }, loader = AnnotationConfigContextLoader.class)
public class OneToManyDataInitializeTest  {
	static Logger Log = Logger.getLogger(  OneToManyDataInitializeTest.class);

	Session session;
	@Autowired
	SessionFactory sessionFactory;
	
	@Before
	public void before() {
		
		session = sessionFactory.openSession();
		//session.beginTransaction();
		
	}
	@After
	public void after() {
		 
		//session.getTransaction().commit();
		session.close();
		
	}
	
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.SERIALIZABLE)
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
        session.persist(category);
        Log.info("Saved Category and Product"); 
         
        } catch (Exception e) {
        	Log.error("Error on "+e.getMessage());
        }  
        
	}
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)
	@Test 
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
		        session.save(category);
		        Log.info("Saved Category and Product"); 
		         
	        } catch (Exception e) {
	        	Log.error("Error on "+e.getMessage());
	        }  
		} 
	 
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_UNCOMMITTED)
	@Test 
	public void Run3_nativeSQLUpdateProduct() {   // Using native SQL and session.createSQLQuery, it works
		Log.info("3 nativeSQLUpdateProduct()");
		 
		Query query = session.createSQLQuery("update product set price=:price where name=:prodname");
		 
		query.setParameter("price",42500.0F);
	
		query.setParameter("prodname","BMW");
	 
		
	 
		int result = query.executeUpdate();
		
		Log.info("changed BMW price to $42500");
		
	}
}
