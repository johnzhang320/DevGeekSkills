package com.basic.hibernate.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.json.JSONObject;

import com.basic.hibernate.dao.BaseHibernateDAO;
import com.basic.hibernate.dao.DmarcDetailReportsDAO;
import com.basic.hibernate.dao.DmarcMasterReports;
import com.basic.hibernate.dao.DmarcMasterReportsDAO;
import com.basic.hibernate.service.CountryCityService;
import com.basic.hibernate.service.CountryCityServiceImpl;
import com.basic.hibernate.service.DmarcReportServiceImpl;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;

import junit.framework.Assert;
import junit.framework.TestCase;

public class DmarcReportsTest extends TestCase {
	Logger Log = Logger.getLogger(DmarcReportsTest.class);
	BaseHibernateDAO getSession = null;
	DmarcMasterReportsDAO parentDao =null;
	DmarcDetailReportsDAO childDao = null;
	DmarcReportServiceImpl dbService = null;
	AgentTableDAO agentTableDao =null;
	CityDAO cityDao=null;
	CountryCityService countryCityService =null;
	CountryDAO countryDao = new CountryDAO();
	public void setUp() {
		getSession = new BaseHibernateDAO();
		parentDao = new  DmarcMasterReportsDAO();
		childDao = new  DmarcDetailReportsDAO();
		dbService = new DmarcReportServiceImpl();
		agentTableDao = new AgentTableDAO();
		cityDao = new CityDAO();
		countryDao = new CountryDAO();
		countryCityService=new CountryCityServiceImpl();
		
	}
	public void testGetSession() {
		Session session = getSession.getSession();
		Assert.assertNotNull(session);
		 
	}
	
	
/*	public void testParentTable() {
		Log.info("Test Parent Table");
		List<DmarcMasterReports> list = parentDao.findByOrgName("xs4all.nl");
		list.forEach((v)->Log.info(v.getMasterId()+","+v.getOrgName()+","+v.getEmail()+","+v.getHostName())); 
		Log.info("Test Parent Table using Creteria restriction between");
		list = dbService.getMasterReportsByMasterIdRange(10357, 10500);
		list.forEach((v)->Log.info(v.getMasterId()+","+v.getOrgName()+","+v.getEmail()+","+v.getHostName()+","+v.getAdkim()+","+v.getAspf())); 
		DmarcMasterReports v =parentDao.findById(10357);
		Log.info("Test Parent Table using findById");
		Log.info(v.getMasterId()+","+v.getOrgName()+","+v.getEmail()+","+v.getHostName()+","+v.getAdkim()+","+v.getAspf());
		     
	}
	
 	public void testChildTable() {
		Log.info("Test Child Table");
		int masterId = 10357;
	//	DmarcMasterReports v1 = parentDao.findById(masterId);
	//	Log.info("Find current Master Record="+v1.getMasterId()+","+v1.getOrgName()+","+v1.getEmail()+","+v1.getHostName()); 
		List<DmarcDetailReports> list = dbService.getDetailReportsByMasterId(masterId);
		list.forEach((v)->Log.info(v.getDmarcMasterReports().getMasterId()+","
		             +v.getDmarcMasterReports().getOrgName()+","+v.getSourceIp()+","+v.getSpfDomain()
		             +","+v.getDkimDomain()+","+v.getFromHeaderDomain()+","+v.getAlignment())); 
	}
	 
 	public void testAgentTableFindById() {
 		Log.info("Test Agent Table findbyId");
 		AgentTable agent = agentTableDao.findById(6);
 		
 		Log.info("agentname="+agent.getFullName()+",address="+agent.getAddress()+",email="+agent.getEmailAddress());

 		
 	}
 	public void testAgentTableFindByEmail() {
 		Log.info("Test Agent Table FindByRmail ");
 		List<AgentTable> agents = agentTableDao.findByEmailAddress("john.zhang320@gmail.com");
 		
 		agents.forEach((agent)->Log.info("agentname="+agent.getFullName()+",address="+agent.getAddress()+",email="+agent.getEmailAddress()));

 		
 	}
 	public void testCityTable() {
 		Log.info("Test City Table all ");
 		List<City> list = cityDao.findAll();
 		list.forEach((city)->Log.info(city.getCityId()+","+city.getCityName()+","+city.getState()+","+city.getCountry().getCountryName()));
 	}
 	
 	public void testCountyTable() {
 		Log.info("Test Country Table all ");
 		List<Country> list = countryDao.findAll();
 		list.forEach((c)->Log.info(c.getCountryId()+","+c.getCountryName()+","+c.getLocation()+","+c.getCities()));
 	}
 	public void testCountryJSON() {
 		Log.info("Test Country JSON Table ");
 		Country c = countryDao.findById(1);
 		Log.info(c.getCountryId()+","+c.getCountryName()+","+c.getLocation()+","+c.getCities().toString());
 		JSONObject json = countryCityService.getCountryJSON(c);
 		Log.info("Json="+json.getString("countryName"));
 	}
 	public void testMongoDB() {
 		Log.info("Test testMongoDB() ");
 	// Old version, uses Mongo
    	Mongo mongo = new Mongo("localhost", 27017);
    	DB db = mongo.getDB("dmarcdb");
    	DBCollection table = db.getCollection("dmarcreports");
    	BasicDBObject searchQuery = new BasicDBObject();
    	searchQuery.put("masterId", new BasicDBObject("$gt",10356).append("$lt", 10450));
    	 
    	DBCursor cursor = table.find(searchQuery);
    	List<DBObject> result = new ArrayList<DBObject>();
    	while (cursor.hasNext()) {
    		System.out.println(cursor.next());
    		result.add(cursor.next());
    		
    	}
    	//result.forEach((v)->System.out.println(v));
 	}*/
}
