package com.basic.hibernate.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
 

 
 

public class CreateCountryDB {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
    /*	Country country = new Country();
		country.setCountryName("United States");
		country.setLocation("North America");
		 
		City cityLA = new City(country,"San Jose","CA");
		City citySFO = new City(country,"Santa Clara","CA");
		City cityNY = new City(country,"Sunnyvale","CA");
		City cityPitt = new City(country,"Cuppertino","CA");
		 
		country.getCities().add(cityLA);
		country.getCities().add(citySFO);
		country.getCities().add(cityNY);
		country.getCities().add(cityPitt);
		*/
		
	/* 
		Country country = new Country();
		country.setCountryName("China");
		country.setLocation("Asia");
		City cityLA = new City(country,"Hung Zhou","East China");
		City citySFO = new City(country,"Ji Ning","Northeast China");
		City cityNY = new City(country,"Gui Zhou","SouthWest China");
		City cityPitt = new City(country,"Chang Sha","Hunan");
		country.getCities().add(cityLA);
		country.getCities().add(citySFO);
		country.getCities().add(cityNY);
		country.getCities().add(cityPitt);
		 
		  
	*/
		Country country = new Country();
		country.setCountryName("United Kingdom");
		country.setLocation("Eroupe");
		City cityLA = new City(country,"Bristol","UK");
		City citySFO = new City(country,"York","UK");
		City cityNY = new City(country,"Shefield","Ireland");
		City cityPitt = new City(country,"NottingHam","England");
		country.getCities().add(cityLA);
		country.getCities().add(citySFO);
		country.getCities().add(cityNY);
		country.getCities().add(cityPitt);
		 
		
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		
		Session session = sessionFactory.openSession();
		
		session.beginTransaction();
		// First time, if table does not exist, use save to create table and data
		// create different record using save as well
		
		session.saveOrUpdate(country);		 
		 
		
		session.getTransaction().commit();
		
		session.close();
		sessionFactory.close();
		
	}
	 

}
