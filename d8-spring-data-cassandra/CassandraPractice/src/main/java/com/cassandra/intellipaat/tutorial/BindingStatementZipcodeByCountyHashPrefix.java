package com.cassandra.intellipaat.tutorial;

import com.cassandra.tutorials.point.Utils;
import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ConsistencyLevel;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

public class BindingStatementZipcodeByCountyHashPrefix {
	/**
	 *  Cassandra write is cheaper and read is expensive
	 *  Table data modeling rules:
	 *  Rule 1: spread data cross nodes as evenly as possible 
	 *  Rule 2: minimize read-partitions and read partition as few as possible , saving coordinator forward
	 *          many partitioned data to different nodes, which create overhead inside cluster
	 *  If large read a lot of data, we need partition nodes as few as possible
	 *  But we also need to prevent all data in one node, which take all huge burden in one node
	 *  We use            
	 */
	private final static int NODES_IN_CLUSTER = 4;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Cluster cluster = Utils.getCluster(null);
		Session session = Utils.getSession(cluster, Utils.KEYSPACE);
		
		
		String zipcodeByCounty="zipcodebycounty";
		
		PreparedStatement stmt = session.prepare(
				"insert into "+ zipcodeByCounty+
				"("+
				 	"city,"+
				    "county,"+
				    "latitude,"+
				    "longitude,"+
				    "state,"+
				    "type,"+
				    "zip,"+
				    "hash_prefix"
				    + ") values(?,?,?,?,?,?,?,?)"
				); 
		BoundStatement bStmt = new BoundStatement(stmt);
		
		String query = "Select * from zipcodes;";
		
		// write quorum means here is consistency because current replication_factor is 3 , QUORUM is 3/2+1=2
		// bStmt.setConsistencyLevel(ConsistencyLevel.QUORUM); cause timeout when 37000 
		
		bStmt.setConsistencyLevel(ConsistencyLevel.ONE);   // large batch write, we 'd better use consistency level one to prevent the timeout write
		/**
		 *  alter table zipcodebycounty with
		 *  bloom_filter_fp_chance=0.2 
		 *  and memtable_flush_period_in_ms=2000 
		 *  and read_repair_chance =0.5;
		 *  
				Row : 42682
				Row : 42683
				Row : 42684
				Row : 42685
				Row : 42686
				Row : 42687
				Row : 42688
				Row : 42689
				Row : 42690
				Row : 42691
				Row : 42692
				Row : 42693
				Row : 42694
				Row : 42695
				Statement executed

		 */
		/**
		 *  2 seconds to flush one time to SSTable to reduce pressure of Memtable
		 */
		
		ResultSet rs = session.execute(query);
		int i=0;
		for (Row row:rs) {
			System.out.println("Row : "+(++i));		 
			String county = row.getString("county");
			HashGenerator ref = new HashGenerator(county);
			int hashPrefix = ref.Hash_Prefix(NODES_IN_CLUSTER); 	
			session.execute(bStmt.bind(					
					row.getString("city"),
					county,
					row.getString("latitude"),
					row.getString("longitude"),
					row.getString("state"),
					row.getString("type"),
					row.getString("zip"),
					hashPrefix
					));		
					 
		}
		
		session.close();
		System.out.println("Statement executed");
	}

}
