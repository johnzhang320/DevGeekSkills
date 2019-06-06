package com.cassandra.tutorials.point;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.Cluster.Builder;

public class Utils {
	public static String beginBatch = "BEGIN BATCH ";
	public static String applyBatch = " APPLY BATCH;";
	public static final String USERNAME="cassandra";
	public static final String PASSWORD="cassandra";	
	public static final String DEPT="dept";
	public static final String EMP="emp";
	public static final String ZIPCODES="zipcodes";
	public static final String ZIPCODEBYCOUNTY="zipcodebycounty";	
	public static final String KEYSPACE="johnz148_mortgage_agency";  // tp, test authorization
	
	public static Cluster getCluster(String hostIP) {
		if (null==hostIP) {
			hostIP="127.0.0.1";
		}
	    Builder builder =Cluster.builder().withCredentials(USERNAME, PASSWORD);
		Cluster cluster = builder.addContactPoint(hostIP).build();
		return cluster;
	}
	public static Cluster getCluster(String hostIP,String username,String password) {
		if (null==hostIP) {
			hostIP="127.0.0.1";
		}
		
	    Builder builder =Cluster.builder().withCredentials(username, password);
		Cluster cluster = builder.addContactPoint(hostIP).build();
		return cluster;
	}
	public static Session getSession(Cluster cluster, String Keyspace) {
		return cluster.connect(Keyspace);
	}
	public static void close(Cluster cluster, Session session) {
		session.close();
		cluster.close();
	}
}
