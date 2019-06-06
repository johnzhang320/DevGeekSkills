package com.cassandra.tutorials.point;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

public class Create_KeySpace {
	 private static final Logger Log = LogManager.getLogger(Create_KeySpace.class);


	/**
    * My Cassandra version 3.8.0, then my cassandra-driver-core-3.1.4.jar, it 's ok
    * user cassandra-driver-core-2.0.2.jar will be throw connection failed exception
    * @param args
    */
   public static void main(String args[]){

      //Query
      String query = "CREATE KEYSPACE tp WITH replication "
         + "= {'class':'SimpleStrategy', 'replication_factor':3};";
      
      String query2 = "CREATE KEYSPACE johnz148_mortgage_agency WITH replication "
    	         + "= {'class':'SimpleStrategy', 'replication_factor':3};";
                    
		Cluster cluster = Utils.getCluster(null);
		Session session = Utils.getSession(cluster, Utils.KEYSPACE);

     
      //Executing the query
     // session.execute(query);
      session.execute(query2);
     
      //using the KeySpace
     // session.execute("USE tp");
      Log.info("Keyspace created"); 
      session.close();
      
      cluster.close();
   }
}
