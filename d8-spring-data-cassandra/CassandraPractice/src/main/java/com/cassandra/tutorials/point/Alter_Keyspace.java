package com.cassandra.tutorials.point;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

public class Alter_Keyspace {
	private static final Logger Log = LogManager.getLogger(Alter_Keyspace.class);
	 public static void main(String args[]){

	      //Query
	      String query = "ALTER KEYSPACE tp WITH replication " 
	     // + "= {'class':'NetworkTopologyStrategy', 'datacenter1':3} AND DURABLE_WRITES = false;";
	      + "= {'class':'SimpleStrategy', 'replication_factor':1} AND DURABLE_WRITES = true;";
	      //Creating Cluster object
	      Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1").build();
	   
	      //Creating Session object
	      Session session = cluster.connect();
	 
	      //Executing the query
	      session.execute(query);
	 
	      Log.info("Keyspace altered");
	      
	      session.close();
	      
	      cluster.close();
	   }

}
