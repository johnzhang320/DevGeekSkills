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

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.hibernate.base.service.BaseHibernateDAO;
import com.hibernate.base.service.BaseCacheTestDAO;
import com.hibernate.base.service.SessionFactoryServiceImpl;
import com.hibernate.one.to.many.model.Category;
import com.hibernate.one.to.many.model.Order;
import com.hibernate.one.to.many.model.Product;

import org.junit.jupiter.api.Test;
 
import org.junit.jupiter.api.TestInfo;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
/*
 * Note:
 * 1. If first time run this project , please check run configuration --> delete all items under Junit 
 * 2. Make sure that JRE is java 1.8
 */
@RunWith(JUnitPlatform.class)
public class OneToManyDataQueryTest extends BaseCacheTestDAO {
	static Logger Log = Logger.getLogger(OneToManyDataQueryTest.class);
	 
	public OneToManyDataQueryTest() {
		super(new SessionFactoryServiceImpl());
	}
	@Test 
	public void CriteriaApplication() {  // use Criteria and Restriction
		Log.info( "Test 1 CriteriaApplication begin");
		Criteria criteria = dao.getSession().createCriteria(Product.class);
	 
		criteria.add(Restrictions.gt("price", 1200F));
		criteria.setCacheRegion("com.hibernate.one.to.many.model.Category");
		criteria.setCacheable(true);
		List<Product> products = criteria.list(); 
	 
		showProducts(products);
		
		Criteria criteria2 = dao.getSession().createCriteria(Category.class)
		.add(Restrictions.like("name", "Compu%"));
		criteria.setCacheRegion("com.hibernate.one.to.many.model.Category");
		criteria.setCacheable(true);
		List<Category> categorys = criteria2.list(); 
		
		showCategory(categorys);
		Log.info( "Test 1 CriteriaApplication end\n");
	}
	@Test
	public void testListAllQuery() {
		Log.info( "Test2 testListAllQuery begin");
		
		String hql = "from Category";
		Query query = dao.getSession().createQuery(hql);
		query.setCacheable(true);
		query.setCacheRegion("com.hibernate.one.to.many.model.Category");
		List<Category> listCategories = query.list();
		Log.info( "from Category");
		for (Category c : listCategories) {
			Log.info(c.getId()+","+c.getName()+",prod.size="+c.getProducts().size());
			for (Product p:c.getProducts()) {
				Log.info("Category fetch:"+p.getId()+","+p.getName()+","+p.getDescription()+","+p.getPrice()+",cat_id="+p.getCategory().getId());
			}
		}
		Log.info( "Test 2 testListAllQuery end\n");
	}
	@Test
	public void testSearchQuery() {
		Log.info( "Test 3 testSearchQuery begin");
		String hql = "from Product where category.name = 'Computer'";
		Query query = dao.getSession().createQuery(hql);
		
		query.setCacheable(true);
		query.setCacheRegion("com.hibernate.one.to.many.model.Product");
		List<Product> listProducts = query.setCacheable(true).list();
		
		for (Product p : listProducts) {
			Log.info(p.getId()+","+p.getName()+","+p.getDescription()+","+p.getPrice()+",cat_id="+p.getCategory().getId());
		}
		Log.info( "Test 3 testSearchQuery end\n");
	}
/*	@Test
	public void testCriteriaQuery() {
		Log.info( "Test 4 testCriteriaQuery begin");
		CriteriaBuilder cb  =dao.getSession().getCriteriaBuilder();
		CriteriaQuery<Category> cr = cb.createQuery(Category.class);
		Root<Category> root = cr.from(Category.class);
		cr.select(root);
		Query<Category> query = dao.getSession().createQuery(cr);
		query.setCacheable(true);
		query.setCacheRegion("com.hibernate.one.to.many.model.Category");
		List<Category> results = query.getResultList();
		showCategory(results);
		Log.info( "Test 4 testCriteriaQuery end\n");
	}*/
 
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
