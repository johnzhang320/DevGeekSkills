package com.hibernate.one.to.one.primary.test;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import com.hibernate.base.service.BaseTestDAO;
import com.hibernate.one.to.one.primary.dao.OneToOneServiceImpl;
import com.hibernate.one.to.one.primary.model.Product121;
import com.hibernate.one.to.one.primary.model.ProductDetail;

import org.junit.jupiter.api.Test;

 
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
/*
 * Note:
 * 1. If first time run this project , please check run configuration --> delete all items under Junit 
 * 2. Make sure that JRE is java 1.8
 */
@RunWith(JUnitPlatform.class)
public class OneToOnePrimaryDataQueryTest extends BaseTestDAO {
	static Logger Log = Logger.getLogger( OneToOnePrimaryDataQueryTest.class);

	public OneToOnePrimaryDataQueryTest() {
		super(new OneToOneServiceImpl("validate"));
	}
 
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
