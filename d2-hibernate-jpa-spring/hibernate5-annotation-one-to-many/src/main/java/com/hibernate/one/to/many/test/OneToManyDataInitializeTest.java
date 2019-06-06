package com.hibernate.one.to.many.test;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
 
import com.hibernate.one.to.many.model.Category;
import com.hibernate.one.to.many.model.Order;
import com.hibernate.one.to.many.model.Product;
import com.hibernate.utils.BaseHibernateDAO;
import com.hibernate.utils.BaseTestDAO;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
 
import org.junit.jupiter.api.TestInfo;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
  
/*
 *  before run this test , change below property to 'validate'
 *    <property name="hibernate.hbm2ddl.auto">validate</property>  
 *    in hibernate.cfg.xml file
 */
@RunWith(JUnitPlatform.class)
public class OneToManyDataInitializeTest extends BaseTestDAO {
	static Logger Log = Logger.getLogger(OneToManyDataInitializeTest.class);

	
	public void DataCreate() {
		createOneCategoryProduct();
		createTwoCategoryProduct();
		nativeSQLUpdateProduct();
		HqlCreateOrdersOne();
		HqlCreateOrdersTwo();
		HqlCreateOrdersThree();
	}
	@Test 
	public void createOneCategoryProduct() {
		// TODO Auto-generated method stub
		BaseHibernateDAO dao = new BaseHibernateDAO();
		
		 
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
        dao.getSession().save(category);
        Log.info("Saved Category and Product"); 
         
        } catch (Exception e) {
        	Log.error("Error on "+e.getMessage());
        }  
	}
	@Test 
	public void createTwoCategoryProduct() {
			// TODO Auto-generated method stub
			BaseHibernateDAO dao = new BaseHibernateDAO();
			
			 
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
		        dao.getSession().save(category);
		        Log.info("Saved Category and Product"); 
		         
	        } catch (Exception e) {
	        	Log.error("Error on "+e.getMessage());
	        }  
		}
	@Test 
	public void nativeSQLUpdateProduct() {   // Using native SQL and session.createSQLQuery, it works
		Log.info("changing BMW price to $42500");
		 
		Query query = dao.getSession().createSQLQuery("update product set price=:price where name=:prodname");
		 
		query.setParameter("price",42500.0F);
	
		query.setParameter("prodname","BMW");
	 
		
	 
		int result = query.executeUpdate();
		
		Log.info("changed BMW price to $42500");
		
	}
	
	@Test  
	public void HqlCreateOrdersOne() {  // fetch product by hql to List<Product> then add to orders
		
		String hql = "from Product where name=:prodname";
		
		Query query = dao.getSession().createQuery(hql);
		query.setString("prodname", "DELL PC");
		List<Product> list = query.getResultList();
		Product prod = list.get(0);
		Order order = new Order();
		order.setAmount(2F);
		order.setCustomerName("Suresh Ai");
		order.setPurchaseDate(new Date());
		order.setProduct(prod);
		Log.info("Creating Order !");
		
		dao.getSession().persist(order);
		
		Log.info("Created Order !");
	}
	
	@Test  
	public void HqlCreateOrdersTwo() {  // fetch product by hql to List<Product> then add to orders
		
		String hql = "from Product where name=:prodname";
		
		Query query = dao.getSession().createQuery(hql);
		query.setString("prodname", "BMW");
		List<Product> list = query.getResultList();
		Product prod = list.get(0);
		Order order = new Order();
		order.setAmount(1F);
		order.setCustomerName("Leo Border");
		order.setPurchaseDate(new Date());
		order.setProduct(prod);
		Log.info("Creating Order !");
		
		dao.getSession().persist(order);
		
		Log.info("Created Order !");
	}
	
	@Test  
	public void HqlCreateOrdersThree() {  // fetch product by hql to List<Product> then add to orders
		
		String hql = "from Product where name=:prodname";
		
		Query query = dao.getSession().createQuery(hql);
		query.setString("prodname", "MacBook");
		List<Product> list = query.getResultList();
		Product prod = list.get(0);
		Order order = new Order();
		order.setAmount(2F);
		order.setCustomerName("John Smith");
		order.setPurchaseDate(new Date());
		order.setProduct(prod);
		Log.info("Creating Order !");
		
		dao.getSession().persist(order);
		
		Log.info("Created Order !");
	}
 
}
