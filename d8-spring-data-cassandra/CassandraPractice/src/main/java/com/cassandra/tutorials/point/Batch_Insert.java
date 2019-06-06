package com.cassandra.tutorials.point;
 

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

public class Batch_Insert {
	private static final Logger Log = LogManager.getLogger("Create_Table");
	   
	public static void main(String args[]){
	    
	      //query
		//query
	      String query =" BEGIN BATCH "
	         +"INSERT INTO emp (emp_id, emp_city,emp_name, emp_phone, emp_sal) values( 1,'Pune','rajeev',9848022331, 30000);"
	    
	         + "INSERT INTO emp (emp_id, emp_city,emp_name, emp_phone, emp_sal) values( 2,'John','Zhang',4082347798, 54000);"
	         + "INSERT INTO emp (emp_id, emp_city,emp_name, emp_phone, emp_sal) values( 3,'Dennie','Cha',4085070718, 40000);"
	         + "INSERT INTO emp (emp_id, emp_city,emp_name, emp_phone, emp_sal) values( 4,'Ganesh','Saich',4085170718, 43000);"
	         
	         + "APPLY BATCH;"; 
	    
	        
			Cluster cluster = Utils.getCluster(null);
			Session session = Utils.getSession(cluster, Utils.KEYSPACE);

	 
	      //Executing the query
	      session.execute(query);

	      Log.info("Changes done");
	      
	      session.close();
	      
	      cluster.close();
	   }

}
