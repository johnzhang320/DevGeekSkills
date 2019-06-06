package com.hibernate.one.to.one.primary.test;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
 

import com.hibernate.one.to.one.primary.model.Product121;
import com.hibernate.one.to.one.primary.model.ProductDetail;
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
 *  If you want to create create the table , change below property to 'create'
 *    <property name="hibernate.hbm2ddl.auto">create</property>  
 *    in hibernate.cfg.xml
 *  Keep test order, junit 5 run on the order in which methods present
 */
@RunWith(JUnitPlatform.class)
public class OneToOnePrimaryDataInitializeTest extends BaseTestDAO {
	static Logger Log = Logger.getLogger( OneToOnePrimaryDataInitializeTest.class);
 
	@Test
	public void createOneTOOnePrimaryBrandNew() {
		
		Session session =dao.getSession();
		 // creates a new product
        Product121 product = new Product121();
        product.setName("Civic");
        product.setDescription("Comfortable, fuel-saving car");
        product.setPrice(20000);
         
        // creates product detail
        ProductDetail detail = new ProductDetail();
        detail.setPartNumber("ABCDEFGHIJKL");
        detail.setDimension("2,5m x 1,4m x 1,2m");
        detail.setWeight(1000);
        detail.setManufacturer("Honda Automobile");
        detail.setOrigin("Japan");
         
        // sets the bi-directional association
        product.setProductDetail(detail);
        detail.setProduct(product);
          
        // persists the product
        session.save(product);
       
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
