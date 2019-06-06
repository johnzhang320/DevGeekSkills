package com.hibernate.one.to.one.primary.test;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;


import com.hibernate.one.to.one.primary.model.Product121;
import com.hibernate.one.to.one.primary.model.ProductDetail;
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
public class OneToOnePrimaryDataQueryTest extends BaseTestDAO {
	static Logger Log = Logger.getLogger( OneToOnePrimaryDataQueryTest.class);

 
 
	@Test
	public void QueryOneTOOnePrimary() {
		
		Session session =dao.getSession();
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
