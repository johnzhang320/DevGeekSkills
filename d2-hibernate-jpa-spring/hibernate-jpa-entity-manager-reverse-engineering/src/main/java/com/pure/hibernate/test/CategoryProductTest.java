package com.pure.hibernate.test;

import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;


import com.pure.hibernate.dao.CategoryHome;
import com.pure.hibernate.dao.UserHome;
import com.pure.hibernate.model.Category;
import com.pure.hibernate.model.User;
/*
 * +-------------+----------+
| category_id | name     |
+-------------+----------+
|           1 | Computer |
|           2 | TV       |
|           3 | Clothes  |
|           4 | Wine     |
+-------------+----------+
4 rows in set (0.001 sec)

MariaDB [mysales]> select * from product;
+------------+----------------------+----------------------+-------+-------------+
| product_id | name                 | description          | price | category_id |
+------------+----------------------+----------------------+-------+-------------+
|          1 | Mac Pro 2            | Apple Laptop         |  2500 |           1 |
|          2 | Dell Inspiron 15     | Dell Laptop          |  1500 |           1 |
|          3 | LT Roku 4K           | LT TV                |   460 |           2 |
|          4 | Sam Sung 75          | Sam Sung TV          |  1160 |           2 |
|          5 | Spring Dream 2       | Victory Secret Skirt |   460 |           3 |
|          6 | Vara Dress 2018      | Vara Wang Design     |  1460 |           3 |
|          7 | Napa Valley 2012     | Napa Wine            |    15 |           4 |
|          8 | Red Napa Valley 1980 | Napa Wine            |    45 |           4 |
|          9 | iPhone10X            | Apple Product        |  1100 |           1 |
+------------+----------------------+----------------------+-------+-------------+
9 rows in set (0.000 sec)

 */
/*
 * src/main/resource/META-INF/persistence.xml
 */

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.TestInfo;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
/*
*  before run this test , change below property to 'create'
*      <property name="hibernate.hbm2ddl.auto" value="create"/>  
*    in persistence.xml file
*/
@RunWith(JUnitPlatform.class)
public class CategoryProductTest {
	static Logger Log = Logger.getLogger(CategoryProductTest.class);
	@Test 
	public void querySingleLazy() {
		Log.info("Query Single Category");
		CategoryHome dao = new CategoryHome();
	    try {
	    	Log.info("dao.findById(1) begin");
	    	String sql = "SELECT u from Category u Left join u.products t";
	    	Query query = dao.getEntityManager().createQuery(sql);
			Log.info("dao.findById(1) end ");
			
			List<Category> cats = query.getResultList();
			Log.info("cats.size()= "+cats.size());
			cats.forEach(System.out::println);
	        System.out.println("name="+cats.get(1).getName());
	      // cats.forEach(x->System.out.println(x.toString()));
	    } catch (Exception e) {
	    	Log.error("Failed to fetch "+e.getMessage());
	    }
	}
	
	//@Test 
	public void queryRangeCategorys() {
		Log.info("Query Rangle Categorys");
		CategoryHome dao = new CategoryHome();
		String sql = "SELECT u from Category u where u.name = 'Computer'";
    	Query query = dao.getEntityManager().createQuery(sql);
    	List<Category> cats = query.getResultList();
        cats.forEach(x->System.out.println(x.toString()));
	}
}
