package com.cassandra.tutorials.point;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.KeyspaceMetadata;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.TableMetadata;

public class Create_Insert_Department {
    private static final Logger Log = LogManager.getLogger("Create_Table");

    public static final String TABLE="dept";
    public static final String KEYSPACE="user_auth_test";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		  //Query
		  String query1 =" BEGIN BATCH ";
	      String query2 = "CREATE TABLE IF NOT EXISTS dept "
	      	 + "("
	      	 + "dept_id int PRIMARY KEY, "
	         + "dept_name text, "
	         + "dept_location text, "
	         + "dept_phone varint "
	         + ");";
		  String insertTitle = "insert into dept(dept_id,dept_name,dept_location,dept_phone) values ";	
		  String insertData[] = {
			  "(101, 'R&D Center','5455 Great America Parkway,Santa Clara,CA',4089328976);",
	          "(102, 'Human Recource','5456 Great America Parkway,Santa Clara,CA',4089328976);",
	          "(103, 'Logistic Center','1472 Kinmberly, San Josea,CA',4082338976);",
	          "(104, 'Marketing','2123 Zenker Ave, San Jose,CA',5109328976);"
		  };
		  String query="Select * from dept;";
		  
		  String apply_batch=" APPLY BATCH;";
       
			Cluster cluster = Utils.getCluster(null);
			Session session = Utils.getSession(cluster, Utils.KEYSPACE);

	      
	      KeyspaceMetadata ks = cluster.getMetadata().getKeyspace(Utils.KEYSPACE);
	      TableMetadata table = ks.getTable(TABLE);
	      StringBuffer buf = new StringBuffer();
	     
	      if (table==null) {
	    	  session.execute(query2);
	    	  Log.info(TABLE +" has been created!");
	      } 	      
	      
	    
    	
          
	      session.close();
	      
	      cluster.close();
	}

}
