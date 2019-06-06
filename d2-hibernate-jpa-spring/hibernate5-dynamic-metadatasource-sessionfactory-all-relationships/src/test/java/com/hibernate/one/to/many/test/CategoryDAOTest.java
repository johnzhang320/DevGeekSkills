package com.hibernate.one.to.many.test;
import java.util.List;

import org.apache.log4j.Logger;
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
import com.hibernate.one.to.many.dao.OneToManyServiceImpl;
import com.hibernate.one.to.many.model.Category;
import com.hibernate.one.to.many.model.Order;
import com.hibernate.one.to.many.model.Product;
import com.hibernate.one.to.many.dao.CategoryDAO;
/*
 *  If you want to create create the table , change below property to 'create'
 *    <property name="hibernate.hbm2ddl.auto">create</property>  
 *    in hibernate.cfg.xml
 *  Keep test order, junit 5 run on the order in which methods present
 */
@RunWith(JUnitPlatform.class)
public class CategoryDAOTest {
	static Logger Log = Logger.getLogger( CategoryDAOTest.class);
	static CategoryDAO dao;
	@BeforeAll
	public static void setup() {
		dao = new CategoryDAO(new OneToManyServiceImpl("validate"));
	}
	@Test
	public void findAll() {
		List<Category> list=dao.findAll();
		showCategory(list);
	}
	
	
	public void showCategory(List<Category> list) {
		for (Category c:list) {
			Log.info("show Products:"+c.getId()+",category name="+c.getName());
			for (Product p:c.getProducts()) {
				Log.info("show Products:"+p.getId()+","+p.getName()+","+p.getDescription()+","+p.getPrice()+",cat name="+p.getCategory().getName());
				for (Order o:p.getOrder()) {
					Log.info("show Orders:"+o.getId()+","+o.getCustomerName()+","+o.getPurchaseDate()+", prod price "+p.getPrice()+",prod name="+p.getName());
				}
			} 
		}
		Log.info("Complete Category");
	}
}
