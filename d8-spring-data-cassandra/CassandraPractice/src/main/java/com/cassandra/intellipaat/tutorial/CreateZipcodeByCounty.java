package com.cassandra.intellipaat.tutorial;

import com.cassandra.tutorials.point.Utils;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

public class CreateZipcodeByCounty {
	/**
	 *  Cassandra write is cheaper and read is expensive
	 *  Table data modeling rules:
	 *  Rule 1: spread data cross nodes as evenly as possible 
	 *  Rule 2: minimize read-partitions and read partition as few as possible , saving coordinator forward
	 *          many partitioned data to different nodes, which create overhead inside cluster
	 *  If large read a lot of data, we need partition nodes as few as possible
	 *  But we also need to prevent all data in one node, which take all huge burden in one node
	 *  composite primary key (hash_prefix,zip)
	 */
	private static String createZipcodes =
			"CREATE TABLE johnz148_mortgage_agency.zipcodes ("+
				    "id int PRIMARY KEY,"+
				    "city text,"+
				    "county text,"+
				    "latitude text,"+
				    "longitude text,"+
				    "state text,"+
				    "type text,"+
				   " zip text"+
				") WITH bloom_filter_fp_chance = 0.1";
				    
		    		
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Cluster cluster = Utils.getCluster(null);
		Session session = Utils.getSession(cluster, Utils.KEYSPACE);
		 
		String zipcodeByCounty="zipcodebycounty";
		//String zipcodeByCounty="zipcodeCF";
		 	 
		String zipByCounty = "create columnfamily "+ zipcodeByCounty+
							"("+
							 	"city varchar,"+
							    "county varchar,"+
							    "latitude varchar,"+
							    "longitude varchar,"+
							    "state varchar,"+
							    "type varchar,"+
							    "zip varchar,"+
							    "hash_prefix int,"+
							    "primary key(hash_prefix,zip)) with clustering order by (zip ASC);";
		session.execute(zipByCounty);
		//session.execute(createZipcodes);
		session.close();
		System.out.println("Statement executed");
	}

}
