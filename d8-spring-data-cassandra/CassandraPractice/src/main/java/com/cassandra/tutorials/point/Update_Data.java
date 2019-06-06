package com.cassandra.tutorials.point;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

public class Update_Data {
	 public static void main(String args[]){
	      
	      //query
	      String query = " UPDATE emp SET emp_city='Delhi',emp_sal=50000";
	          
			Cluster cluster = Utils.getCluster(null);
			Session session = Utils.getSession(cluster, Utils.KEYSPACE);

	         
	      //Executing the query
	      session.execute(query);

	      System.out.println("Data updated");
	      
	      session.close();
	      
	      cluster.close();
	   }
}
