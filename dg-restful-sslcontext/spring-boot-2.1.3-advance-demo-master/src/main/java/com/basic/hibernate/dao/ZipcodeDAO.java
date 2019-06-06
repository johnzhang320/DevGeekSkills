package com.basic.hibernate.dao;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.log4j.Logger;

import com.basic.hibernate.pure.pojo.ZipcodePojo;
 /*
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
import com.locale.message.utils.CassandraUtils;
*/

public class ZipcodeDAO {
	/*static Logger Log = Logger.getLogger(ZipcodeDAO.class);
	private final static String KEY_SPACE="johnz148_mortgage_agency";
	private final static String MY_CQL = "select * from zipcodes ";
	private String getQuery(String base) {
		return base.toUpperCase()+" limit 5000";
	}
	public List<ZipcodePojo> findAll() {
		return findByQuery(MY_CQL);
	}
	public List<ZipcodePojo> findById(String id) {
		return findByQuery(MY_CQL+" where id="+id);
	}
	public List<ZipcodePojo> findByCityAndState(String state , String city) {
		return findByQuery(MY_CQL+" where state="+state+" and city="+city);
	}
	public List<ZipcodePojo> findByCounty(String county) {
		return findByQuery(MY_CQL+" where county="+county);
	}
	public List<ZipcodePojo> findByState(String state) {
		return findByQuery(MY_CQL+" where state="+state);
	}
	public List<ZipcodePojo> findByZip(String zip) {
		return findByQuery(MY_CQL+" where zip="+zip);
	}
	public List<ZipcodePojo> findByCity(String city) {
		return findByQuery(MY_CQL+" where city="+city);
	}
	public List<ZipcodePojo> findByQuery(String query) {
		Session session = null;
		List<ZipcodePojo> results = new ArrayList<ZipcodePojo>();
		try {
			
			CassandraUtils clusterUtils = new CassandraUtils();
			Cluster cluster = clusterUtils.getCluster();
			if (null==cluster ) {
				Log.info("Cluster not be getten!");
				return results;
			} else{
				Log.info("Cluster Name obtained:"+cluster.getClusterName());
			}
			session = clusterUtils.getCluster().connect(KEY_SPACE);
			Log.info("Session created , query="+query+";");
			ResultSet cassResultSet = session.execute(query);
			Log.info("ResultSet created! size of fetched records="+cassResultSet.all().toString());
			List<Row> rows = cassResultSet.all(); 
			for (Row row: rows ) {
				ZipcodePojo pj = new ZipcodePojo();
				pj.setId(row.getInt("id"));
				pj.setCity(row.getString("city"));			
				pj.setCounty(row.getString("county"));
				pj.setLatitude(row.getString("latitude"));
				pj.setLongitude(row.getString("longitude"));
				pj.setState(row.getString("state"));
				pj.setType(row.getString("type"));
				pj.setZip(row.getString("zip"));
				
				 
				results.add(pj);
				Log.info(row.getString("city"));
			}
		} catch (Exception e) {
			Log.error (e.getMessage());
			e.printStackTrace();
		} finally {
			session.close();
		}
		return results;
	}*/
	
}
