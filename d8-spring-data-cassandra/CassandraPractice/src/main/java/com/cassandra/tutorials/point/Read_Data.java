package com.cassandra.tutorials.point;

import org.apache.log4j.Logger;
import static com.datastax.driver.core.querybuilder.QueryBuilder.add; 
import static com.datastax.driver.core.querybuilder.QueryBuilder.batch; 
import static com.datastax.driver.core.querybuilder.QueryBuilder.bindMarker; 
import static com.datastax.driver.core.querybuilder.QueryBuilder.desc; 
import static com.datastax.driver.core.querybuilder.QueryBuilder.asc; 
import static com.datastax.driver.core.querybuilder.QueryBuilder.eq; 
import static com.datastax.driver.core.querybuilder.QueryBuilder.in; 
import static com.datastax.driver.core.querybuilder.QueryBuilder.gt; 
import static com.datastax.driver.core.querybuilder.QueryBuilder.prepend; 
import static com.datastax.driver.core.querybuilder.QueryBuilder.put; 
import static com.datastax.driver.core.querybuilder.QueryBuilder.set; 
import static com.datastax.driver.core.querybuilder.QueryBuilder.ttl;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.ResultSetFuture;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.querybuilder.QueryBuilder;

public class Read_Data {
   private static Logger Log = Logger.getLogger(Read_Data.class);
   
   public static void main(String args[])throws Exception{
    
      //queries
      String query = "SELECT * FROM emp";

		Cluster cluster = Utils.getCluster(null);
		Session session = Utils.getSession(cluster, Utils.KEYSPACE);

    
      //Getting the ResultSet
      ResultSet result = session.execute(query);
    
      Log.info(result.all());
	   query = "select * from emp;";
	   result = session.execute(query);
	   Log.info(query);
	   for (Row row: result) {
	       	  
	      	//  Log.info(row.getString("emp_id")+","+row.getString("deptnos")+","+row.getString("emp_city")+","+row.getString("emp_name"));
	    	Log.info(row.getInt("emp_id")+","+row.getString("emp_city")+","+row.getString("emp_name"));
	      	 
	    }
	   
	   query = "select * from emp where deptnos contains 104;";
	   result = session.execute(query);
	   Log.info(query); 
	   for (Row row: result) {
	       	  
	      	//  Log.info(row.getString("emp_id")+","+row.getString("deptnos")+","+row.getString("emp_city")+","+row.getString("emp_name"));
	    	Log.info(row.getInt("emp_id")+","+row.getString("emp_city")+","+row.getString("emp_name"));
	      	 
	    } 
	   Statement stmt = QueryBuilder
	            .select()
	            .all()
	            .from("emp")	  
	            //.orderBy(asc("emp_id"))
	            .where(in("emp_id",1,2,3,4))
	            .setFetchSize(100);
	   		 
	
	   ResultSet rs = session.execute(stmt); 
	   Log.info("SetFetchSize(100)"); 
	 
	   for (Row row: rs) {	       	  
	      	//  Log.info(row.getString("emp_id")+","+row.getString("deptnos")+","+row.getString("emp_city")+","+row.getString("emp_name"));
	    	Log.info(row.getInt("emp_id")+","+row.getString("emp_city")+","+row.getString("emp_name")+","+row.getSet("deptnos", Integer.class));
       } 
    // Search by value of set<Int>
	  session.close();
	  
	  session = cluster.connect("johnz148_mortgage_agency");
	  
	  stmt = QueryBuilder
	            .select()
	            .all()
	            .from("zipcodes")	            
	            .setFetchSize(100);
	   		 
	   query = "select * from zipcodes where county='SANTA CLARA' limit 100;" ;
	   rs = session.execute(query); 
	   Log.info("SetFetchSize(100) from zipcodes"); 
	   int count=1;
	   for (Row row: rs) {	       	  
	      	//  Log.info(row.getString("emp_id")+","+row.getString("deptnos")+","+row.getString("emp_city")+","+row.getString("emp_name"));
	    	Log.info(count+","+row.getInt("id")+","+
	    			 row.getString("city")+","+
	    			 row.getString("county")+","+
	    			 row.getString("latitude")+","+
	    			 row.getString("longitude")+","+
	    			 row.getString("state")+","+
	    			 row.getString("type")+","+
	    			 row.getString("zip"));
	    	count++;
     } 
	   
	 /**
	  *  PraparedStatement could provide dynamic binding parameter  
	  *  using asynchronize way
	  */
	  PreparedStatement pstmt = session.prepare("select * from zipcodes where county=?;");
	  BoundStatement bstmt = new BoundStatement(pstmt);
	  
	  session.executeAsync(bstmt.bind("SANTA CLARA"));
	  
	  PreparedStatement pstmt2 = session.prepare("select * from zipcodebycounty where hash_prefix in (?,?,?);");
	  BoundStatement bstmt2 = new BoundStatement(pstmt2);
	  
	  ResultSetFuture future =session.executeAsync(bstmt2.bind(3,2,1));
	  
	  Log.info("Async Bonding Execution");
	  ResultSet rs2 =null;
	  try {
	       rs2 = future.get(150, TimeUnit.MILLISECONDS);   // process 30000 rows successfully within 150ms 
	       int k=0;
	       for(Row row: rs2) {
	    	   k++;
	    	   if (k<=500) {
	    		   Log.info("row="+k+",Zip="+row.getString("zip")+","+row.getString("county"));
	    	   } else {
	    		   break;
	    	   }
	    	   
	       }
	   } catch (TimeoutException e) {
	       future.cancel(true); // Ensure any resource used by this query driver
	                            // side is released immediately
	       Log.error("Process timeout!");
	   }
	 
	  
	  Log.info("ResultSetFuture rs2="+future.isDone());
	  
      session.close();
      
      cluster.close();
   }
}