package com.mongo.cassandra.test;

import java.util.List;

import org.apache.log4j.Logger;

import com.basic.hibernate.dao.ZipcodeDAO;
import com.basic.hibernate.pure.pojo.ZipcodePojo;

import junit.framework.TestCase;

public class CassandraTest extends TestCase {
	Logger Log = Logger.getLogger(MongoDBTest.class);
	private ZipcodeDAO zipRef =null;
	public void setUp() {
		zipRef = new ZipcodeDAO();
	}
	public void testCassandraQuery() {
		List<ZipcodePojo> results = zipRef.findByCounty("'JERSEY'");
		System.out.println("List.size()="+results.size());
		results.forEach((pj)->System.out.println(pj.toString()+"\n"));
	}
}

