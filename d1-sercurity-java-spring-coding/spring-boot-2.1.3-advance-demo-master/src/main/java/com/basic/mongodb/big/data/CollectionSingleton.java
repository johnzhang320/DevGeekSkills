package com.basic.mongodb.big.data;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;

public class CollectionSingleton {
 	private DB countryDb =null;
	private DB dmarcDb=null;
	private Mongo mongo=null;
	private static CollectionSingleton handler=null;
	public static CollectionSingleton getInstance() {
		if (handler==null) {
			handler= new CollectionSingleton();
		}
		return handler;
	}
	private CollectionSingleton() {
		this.mongo = new Mongo("localhost", 27017);
	
 	}
	public DBCollection getCollectionCountry() {
		countryDb = mongo.getDB("countrydb");	
		DBCollection collectionCountry = countryDb.getCollection("countries");
		return collectionCountry;
	}
	 
	public DBCollection getCollectionDmarc() {
		dmarcDb = mongo.getDB("dmarcdb");
		DBCollection collectionDmarc = dmarcDb.getCollection("dmarcreports");
		return collectionDmarc;
	}
	 
	
	
	
	

}
