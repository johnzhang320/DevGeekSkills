package com.mongo.cassandra.test;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.basic.hibernate.dao.DmarcReportsTest;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;

import junit.framework.TestCase;

public class MongoDBTest extends TestCase {
	Logger Log = Logger.getLogger(MongoDBTest.class);


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
	}
}