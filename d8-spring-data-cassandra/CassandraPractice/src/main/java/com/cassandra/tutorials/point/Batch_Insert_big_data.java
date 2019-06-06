package com.cassandra.tutorials.point;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
/**
 * 
 * create keyspace user_auth_test with replication={'class': 'NetworkTopologyStrategy','datacenter1':3};
 * 
 * 
 * Note: 
 * 1. Cassandra Insert table (column1, column2....columnN) values (data1, data2, .... dataN);
 * it can not know Insert table (data1, data2, .... dataN);
 * 2. in one batch, the command buffer is small, 
 *    INSERT INTO zipcodes  (id,zip,latitude,longitude,city,state,county,type) VALUES (1,'00501','+40.922326','-072.637078','HOLTSVILLE','NY','SUFFOLK','UNIQUE');
 *    Such character number in one command line, each batch allows 100 commands
 *    we have to close session after run 100 similar command lines
 * 3. Table structures (desc zipcodes):
 * 
		CREATE TABLE johnz148_mortgage_agency.zipcodes (
		    id int PRIMARY KEY,
		    city text,
		    county text,
		    latitude text,
		    longitude text,
		    state text,
		    type text,
		    zip text
		) WITH bloom_filter_fp_chance = 0.1
		    AND caching = {'keys': 'ALL', 'rows_per_partition': 'NONE'}
		    AND comment = ''
		    AND compaction = {'class': 'org.apache.cassandra.db.compaction.SizeTieredCompactionStrategy', 'max_threshold': '32', 'min_threshold': '4'}
		    AND compression = {'chunk_length_in_kb': '64', 'class': 'org.apache.cassandra.io.compress.LZ4Compressor'}
		    AND crc_check_chance = 1.0
		    AND dclocal_read_repair_chance = 0.1
		    AND default_time_to_live = 0
		    AND gc_grace_seconds = 864000
		    AND max_index_interval = 2048
		    AND memtable_flush_period_in_ms = 0
		    AND min_index_interval = 128
		    AND read_repair_chance = 0.0
		    AND speculative_retry = '99PERCENTILE';
		CREATE INDEX zipcodes_longitude_idx ON johnz148_mortgage_agency.zipcodes (longitude);
		CREATE INDEX zipcodes_county_idx ON johnz148_mortgage_agency.zipcodes (county);
		CREATE INDEX zipcodes_city_idx ON johnz148_mortgage_agency.zipcodes (city);
		CREATE INDEX zipcodes_latitude_idx ON johnz148_mortgage_agency.zipcodes (latitude);
		CREATE INDEX zipcodes_state_idx ON johnz148_mortgage_agency.zipcodes (state);
		CREATE INDEX zipcodes_zip_idx ON johnz148_mortgage_agency.zipcodes (zip);
 *    
 */
public class Batch_Insert_big_data {
	private static final Logger Log = LogManager.getLogger(Batch_Insert_big_data.class);

	public static final int MAX_NUMBER_OF_COMMANDS_PER_BATCH=100;
	public static void main(String args[]){
	    
	      //query
		//query
		String begin_batch = "begin batch ";
		
		String apply_batch = " apply batch;";
		
		String truncate = "truncate table zipcodes;\n";
		 
		String insertStr = " INSERT INTO zipcodes (id,zip,latitude,longitude,city,state,county,type) VALUES ";
	
		File file =new File("C:/AdvancedPractice/CassandraPractice/src/big_data_file.txt");
		
		Cluster cluster = Utils.getCluster(null);
		Session session = Utils.getSession(cluster, Utils.KEYSPACE);
	    
		FileInputStream fis = null;
        DataInputStream dis = null;
        BufferedReader br = null;
        StringBuffer buffer = new StringBuffer();  
        buffer.append(begin_batch);
      //  buffer.append(truncate);
        
        try {

            fis = new FileInputStream(file);
            dis = new DataInputStream(fis);
            br = new BufferedReader(new InputStreamReader(dis));
            
            String line = null;
            int count=1;
            while((line = br.readLine()) != null){              
            	String buf[] = line.split("\\|");
            	int len = buf.length-1;            	 
            	Log.info("line"+count+"="+buf.length);
            	count =0;
            	for (int i=0;i<len;i++) {
            		if (count<MAX_NUMBER_OF_COMMANDS_PER_BATCH) {
	            		if (null!=buf[i] && ""!=buf[i] && 0!=buf[i].length()) {
	            			buffer.append(insertStr+"("+buf[i]+");\n");	            		 
	            		}
            		} else {
	            		count=0;
	            		buffer.append(apply_batch);
	            		runQuery(buffer.toString(), cluster);
	            		buffer = new StringBuffer();
	            		buffer.append(begin_batch);
	            		if (null!=buf[i] && ""!=buf[i] && 0!=buf[i].length()) {
	            			buffer.append(insertStr+"("+buf[i]+");\n");
	            			
	            		}
	            		break;
            		}
            		count++;
            	}
              
            }
          
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            try{if(br != null) br.close();}catch(Exception ex){}
        }
        
        buffer.append(apply_batch);
        
       // Log.info(buffer);
  	        
	     //Creating Cluster object
	   
	    
	    Log.info("Batch has beed done!\n");
 	    
	    cluster.close();
	}
	
	public static void runQuery(String str, Cluster cluster) {
		   Log.info("Create one session !");
	 	     //Creating Session object
		 
			Session session = Utils.getSession(cluster, Utils.KEYSPACE);
	    /**
		     *  line1=12775
				line2=12841
				line3=12882
				line4=4243
		     *  Put all inserts one batch command
		     *  Exception in thread "main" com.datastax.driver.core.exceptions.InvalidQueryException: Batch too large
		     */
		    
		      //Executing the query
		    session.execute(str);

		    session.close();
		    
		    Log.info("Create one session !");
	}

}
