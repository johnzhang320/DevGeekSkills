package com.locale.message.utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.log4j.Logger;

import com.basic.hibernate.dao.ZipcodeDAO;
//import com.datastax.driver.core.Cluster;

public class CassandraUtils {
	/*private final static String IP_ADDRESS="127.0.0.1";
	static Logger Log = Logger.getLogger(ZipcodeDAO.class);
	private static Map<Cluster,Boolean> clusterMap = new ConcurrentHashMap<Cluster,Boolean>();
	private Lock lock = new ReentrantLock();
	public Cluster getCluster() {
		Cluster retVal=null;
		lock.lock();
		try {
			for (Map.Entry<Cluster,Boolean> entry: clusterMap.entrySet()) {
				if (!entry.getValue()) {
					entry.setValue(Boolean.TRUE);
					return entry.getKey();
				}
			}
			// create new cluster
			
			Cluster cluster = Cluster.builder().addContactPoint(IP_ADDRESS).build();
			clusterMap.put(cluster, Boolean.FALSE);
			retVal= cluster;
		} catch (Exception e) {
			Log.error(e.getMessage());
			
		} finally {
			lock.unlock();
		}
		return retVal;
	}
	public void closeAllClusters() {
		lock.lock();
		try {
			for (Map.Entry<Cluster,Boolean> entry: clusterMap.entrySet()) {
				Cluster cluster = entry.getKey();
				clusterMap.remove(cluster);
				cluster.close();
			}
		} catch (Exception e) {
			Log.error(e.getMessage());
			
		} finally {
			lock.unlock();
		}
	}*/
}
