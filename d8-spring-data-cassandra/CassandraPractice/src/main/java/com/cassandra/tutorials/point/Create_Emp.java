package com.cassandra.tutorials.point;



import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.KeyspaceMetadata;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.TableMetadata;

public class Create_Emp {
   private static final Logger log = LogManager.getLogger("Create_Table");

   public static final String TABLE="emp";
   public static final String KEYSPACE="user_auth_test";
   public static void main(String args[]){

      //Query
      String query = "CREATE TABLE IF NOT EXISTS emp(emp_id int PRIMARY KEY, "
         + "emp_name text, "
         + "emp_city text, "
         + "emp_sal varint, "
         + "emp_phone varint );";
		
      //Creating Cluster object
		Cluster cluster = Utils.getCluster(null);
		Session session = Utils.getSession(cluster, Utils.KEYSPACE);

      
      KeyspaceMetadata ks = cluster.getMetadata().getKeyspace(Utils.KEYSPACE);
      TableMetadata table = ks.getTable(TABLE);
      
      if (table==null) {
    	  //Executing the query
    	  session.execute(query); 
    	  System.out.println("Table created");
      } else {
    	 System.out.println(TABLE +" table Existed and won't Create again!");
      }
      
      session.close();
      
      cluster.close();
   }
}