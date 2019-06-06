package com.cassandra.tutorials.point;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.KeyspaceMetadata;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.TableMetadata;

public class Create_Insert_Collections_Emp {
	private static final Logger Log = LogManager.getLogger(Create_Insert_Collections_Emp.class);
	/**
	 *  create keyspace user_auth_test with replication={'class': 'NetworkTopologyStrategy','datacenter1':3};
	 * 
	 alter table emp add dept_name List<text>;
	 alter table emp add deptnos set<Int> ;
	 
	CREATE TABLE emp (
    emp_id int PRIMARY KEY,
    dept_name list<text>,
    deptnos set<int>,
    emp_city text,
    emp_name text,
    emp_phone varint,
    emp_sal varint
) WITH bloom_filter_fp_chance = 0.01
    AND caching = '{"keys":"ALL", "rows_per_partition":"NONE"}'
    AND comment = ''
    AND compaction = {'class': 'org.apache.cassandra.db.compaction.SizeTieredCompactionStrategy'}
    AND compression = {'sstable_compression': 'org.apache.cassandra.io.compress.LZ4Compressor'}
    AND dclocal_read_repair_chance = 0.1
    AND default_time_to_live = 0
    AND gc_grace_seconds = 864000
    AND max_index_interval = 2048
    AND memtable_flush_period_in_ms = 0
    AND min_index_interval = 128
    AND read_repair_chance = 0.0
    AND speculative_retry = '99.0PERCENTILE';
    
    insert into emp (emp_id,dept_name,deptnos) values (1,['Accounting','Marketing'],{102,104});
	insert into emp (emp_id,dept_name,deptnos) values (2,['Email Security','GMS','HR'],{103,101,105});
	insert into emp (emp_id,dept_name,deptnos) values (4,['IT department','Dev'],{106,109,108});
	insert into emp (emp_id,dept_name,deptnos) values (3,['HR','Dev'],{105,108});

 	 */
	public static void main(String[] args) {   
		// TODO Auto-generated method stub
		Cluster cluster = Utils.getCluster(null);
		Session session = Utils.getSession(cluster, Utils.KEYSPACE);
		 
	      
		// add dept no
		String alter_dept_no = "alter table emp add deptnos set<Int> ;";   // collention data type is cassandra type instead of Java
		String alter_dept_name = "alter table emp add dept_name List<text>;";   // collention data type is cassandra type instead of Java
		//session.execute(alter_dept_no);
		//session.execute(alter_dept_name);
		
		String add_dept_no1 = "insert into emp (emp_id,dept_name,deptnos) values (1,['Accounting','Marketing'],{102,104});";
		String add_dept_no2 = "insert into emp (emp_id,dept_name,deptnos) values (2,['Email Security','GMS','HR'],{103,101,105});";
		String add_dept_no3 = "insert into emp (emp_id,dept_name,deptnos) values (4,['IT department','Dev'],{106,109,108});";
		String add_dept_no4 = "insert into emp (emp_id,dept_name,deptnos) values (3,['HR','Dev'],{105,108});";

		String index_deptnos = "create index on emp(emp_name);";
		// UML command Alter, Create etc should not be placed within Batch
		
		 
	  //  session.execute(index_deptnos);
		Log.info("Alter emp add deptnos");
		StringBuffer buf = new StringBuffer();
		buf.append(Utils.beginBatch);		
		buf.append(add_dept_no1);
		buf.append(add_dept_no2);
		buf.append(add_dept_no3);
		buf.append(add_dept_no4);
		//buf.append(index_deptnos);
		buf.append(Utils.applyBatch);
		session.execute(buf.toString());
		
		String query = "select * from emp;";
	    ResultSet result = session.execute(query);
  	    
	         // Log.info(result.all());
	          
	          //Log.info(result.getColumnDefinitions()); 
	/*    for (Row row: result) {
	       	  
	      	  Log.info(row.getInt("emp_id")+","+row.getObject("deptnos")+","+row.getString("emp_city")+","+row.getString("emp_name"));
	      	 
	    }
	    // Search by value of set<Int>
	    query = "select * from emp where deptnos contains 104;";
	    result = session.execute(query);
  	    
	    Log.info("\nSearch by value of set<Int>:"+query);
	          
	          //Log.info(result.getColumnDefinitions()); 
	    for (Row row: result) {
	       	 Log.info(row.getInt("emp_id")+","+row.getString("deptnos")+","+row.getString("emp_city")+","+row.getString("emp_name"));
	      	 
	    }*/
		Utils.close(cluster, session);
		Log.info("Alter customer Adding ");
		
	}

}
