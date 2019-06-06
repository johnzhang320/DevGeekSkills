package com.keystore.x509.certificate.model;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import com.keystore.x509.certificate.utils.Utils;
 
public class KeystoreMap {
 	
	static Logger LOG = Logger.getLogger(KeystoreMap.class);
	// Key could be fingerprint of certificagte
	private static Map<String,List<KeystoreVO>> keystoreMap = new ConcurrentHashMap<String,List<KeystoreVO>>();
	private static long UpdateTimestamp =0; 
	
	public synchronized boolean put(String fingerPrint,KeystoreVO ksvo) {
		if (!Utils.isValidKeystoreVO(ksvo)) {
			LOG.info("Error: You try to save invalid KeystoreVO to KeystoreMap! ");
			return false;
		}
		List<KeystoreVO> ksList = null;
		if (keystoreMap.containsKey(fingerPrint)) {
			 ksList = keystoreMap.get(fingerPrint);
			 if (!ksList.contains(ksvo)) {   // implemented equals in keystoreVO, same fingerPrint, keystore and truststore , means equal
				 ksList.add(ksvo);
				 keystoreMap.put(fingerPrint, ksList);
			 } 
		} else {
			ksList = new ArrayList<KeystoreVO>();
			ksList.add(ksvo);
			keystoreMap.put(fingerPrint, ksList);
		}
		UpdateTimestamp = System.currentTimeMillis();
		
		return true;
	}
	public synchronized List<KeystoreVO> get(String fingerPrint) {
		List<KeystoreVO> ksList = null;
		if (keystoreMap.containsKey(fingerPrint)) {
			 ksList = keystoreMap.get(fingerPrint);
 		} 
		return ksList;
 	}
	public synchronized void remove(String fingerPrint) {
		if (keystoreMap.containsKey(fingerPrint)) {
			keystoreMap.remove(fingerPrint);
			UpdateTimestamp = System.currentTimeMillis();
		}
	}
	public static Map<String, List<KeystoreVO>> getKeystoreMap() {
		return keystoreMap;
	}
	public static void setKeystoreMap(Map<String, List<KeystoreVO>> keystoreMap) {
		KeystoreMap.keystoreMap = keystoreMap;
	}
	public void display() {
		Iterator<String> itr = keystoreMap.keySet().iterator();
		while (itr.hasNext()) {
			String key = itr.next();
			List<KeystoreVO> list = keystoreMap.get(key);
			for (KeystoreVO vo:list) {
				System.out.println(vo.toString());
			}
		}
	}
	public static long getUpdateTimestamp() {
		return UpdateTimestamp;
	}
	public static void setUpdateTimestamp(long updateTimestamp) {
		UpdateTimestamp = updateTimestamp;
	}
	
}
