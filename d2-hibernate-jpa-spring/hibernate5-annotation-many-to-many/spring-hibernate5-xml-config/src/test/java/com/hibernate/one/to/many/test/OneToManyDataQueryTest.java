package com.hibernate.one.to.many.test;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.hibernate.base.service.BaseHibernateDAO;
import com.hibernate.base.service.BaseTestDAO;
import com.hibernate.one.to.many.dao.OneToManyServiceImpl;
import com.hibernate.one.to.many.model.Category;
import com.hibernate.one.to.many.model.Order;
import com.hibernate.one.to.many.model.Product;
import com.hibernate.utils.SpringSessionFactoryValidate;

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

@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration(classes = { SpringSessionFactoryValidate.class }, loader = AnnotationConfigContextLoader.class)
public class OneToManyDataQueryTest  {
	static Logger Log = Logger.getLogger(OneToManyDataQueryTest.class);
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
	public void Run1_CriteriaApplication() {  // use Criteria and Restriction
		Log.info( "Run 1 CriteriaApplication");
		Criteria criteria = session.createCriteria(Product.class);
	 
		criteria.add(Restrictions.gt("price", 1200F));
		 
		List<Product> products = criteria.list(); 
	 
		showProducts(products);
		
		Criteria criteria2 = session.createCriteria(Category.class)
		.add(Restrictions.like("name", "Compu%"));
		
		List<Category> categorys = criteria2.list(); 
		
		showCategory(categorys);
	 
	}
	@Test
	public void Run2_testListAllQuery() {
		Log.info( "Run 1 estListAllQuery");
		
		String hql = "from Category";
		Query query = session.createQuery(hql);
		List<Category> listCategories = query.list();
		Log.info( "from Category");
		for (Category c : listCategories) {
			Log.info(c.getId()+","+c.getName()+",prod.size="+c.getProducts().size());
			for (Product p:c.getProducts()) {
				Log.info("Category fetch:"+p.getId()+","+p.getName()+","+p.getDescription()+","+p.getPrice()+",cat_id="+p.getCategory().getId());
			}
		}
	}
	@Test
	public void Run3_testSearchQuery() {
		Log.info( "Run_3 estListAllQuery");
		String hql = "from Product where category.name = 'Computer'";
		Query query = session.createQuery(hql);
		List<Product> listProducts = query.list();
		
		for (Product p : listProducts) {
			Log.info(p.getId()+","+p.getName()+","+p.getDescription()+","+p.getPrice()+",cat_id="+p.getCategory().getId());
		}
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
	
	public void showProducts(List<Product> list) {
		for (Product p:list) {
			Log.info("show Products:"+p.getId()+","+p.getName()+","+p.getDescription()+","+p.getPrice()+",cat name="+p.getCategory().getName());
			for (Order o:p.getOrder()) {
				Log.info("show Orders:"+o.getId()+","+o.getCustomerName()+","+o.getPurchaseDate()+", prod price "+p.getPrice()+",prod name="+p.getName());
			}
		}
		Log.info("Complete Shown Products");
	}
}
