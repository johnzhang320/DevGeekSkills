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
public class OneToManyDataQueryTest extends BaseTestDAO {
	static Logger Log = Logger.getLogger(OneToManyDataQueryTest.class);
	 
	
	@Test 
	public void CriteriaApplication() {  // use Criteria and Restriction
		Log.info( "CriteriaApplication");
		Criteria criteria = dao.getSession().createCriteria(Product.class);
	 
		criteria.add(Restrictions.gt("price", 1200F));
		 
		List<Product> products = criteria.list(); 
	 
		showProducts(products);
		
		Criteria criteria2 = dao.getSession().createCriteria(Category.class)
		.add(Restrictions.like("name", "Compu%"));
		
		List<Category> categorys = criteria2.list(); 
		
		showCategory(categorys);
	 
	}
	@Test
	public void testListAllQuery() {
		
		
		String hql = "from Category";
		Query query = dao.getSession().createQuery(hql);
		List<Category> listCategories = query.list();
		Log.info( "from Category");
		for (Category c : listCategories) {
			Log.info(c.getId()+","+c.getName()+",prod.size="+c.getProducts().size());
			for (Product p:c.getProducts()) {
				Log.info("Category fetch:"+p.getId()+","+p.getName()+","+p.getDescription()+","+p.getPrice()+",cat_id="+p.getCategory().getId());
			}
		}
	}
	//@Test
	public void testSearchQuery() {
		String hql = "from Product where category.name = 'Computer'";
		Query query = dao.getSession().createQuery(hql);
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
