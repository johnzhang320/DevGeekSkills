package com.interview.questions.sample.shorten.URL;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeSet;

public class ShortenURL {
	// using PriorityQueue to save sequence ID, ensure queue.peek() already maximum seq id
	// new unique id should be queue.peek()+1; O(1) 
	private PriorityQueue<Long> queue = new PriorityQueue<Long>(Collections.reverseOrder());
	// save unique Long associated with original URL 
	private Map<Long, URLRecord> URLDatabase = new HashMap<Long,URLRecord>();
	
 
	
	public ShortenURL() {
		initialize();
	}
	public synchronized void initialize() {
		Long id = 192345L;
		if (!URLDatabase.containsKey(id)) {
			queue.add(id);
			//URLRecord(String uRL, String shortenURL, Long seqID) 
			URLDatabase.put(id,new URLRecord("https://google.com",Utils.encode(id),id));
		}
	}
	/**
	 * Check if URL exists in Database, exception and return exists one
	 * 
	 * @param URL
	 * @return
	 */
	public synchronized String shortenOneURL(String URL)   {
		String shortenURL = null;
		Long uniqueId = queue.peek()+1;  // fetch maximum sequence id with O(1) and no remove
		try {
			if (URLDatabase.containsKey(uniqueId)) {
				throw new DuplicatedURLShortenException(uniqueId+ " sequence ID has been used ");
			} else {
				  // obtain max+1 
				shortenURL = Utils.encode(uniqueId);
				System.out.println("shorten url="+shortenURL+",uniqueId="+uniqueId);
				URLRecord ref = new URLRecord(URL,shortenURL,uniqueId);
				URLDatabase.put(uniqueId, ref);
				queue.add(uniqueId);
			}
		} catch (DuplicatedURLShortenException e ){
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return shortenURL;
	}
	/**
	 * Check if shortenURL is correct or exists and return original URL
	 * @param shortenURL
	 * @return original URL
	 */
	public synchronized String acceptAccess(String shortenURL)  {
		Long uniqueId = Utils.decode(shortenURL);
		System.out.println("Found unique id="+uniqueId+" for shortenURL "+shortenURL+",URLDatabase.size()="+URLDatabase.size());
		String retVal=null;
		try {
			if (URLDatabase.containsKey(uniqueId)) {
				URLRecord ref = URLDatabase.get(uniqueId);
				System.out.println("found org Url="+ref.getURL());
				retVal = ref.getURL();
			} else {
				throw new NotFoundShortenURLException("Not found shorten URL "+shortenURL+" illegal access!");
			}
		} catch (NotFoundShortenURLException e) {
			System.out.println(e.getMessage());
			//e.printStackTrace();
		}
		return retVal;
	}
	/**
	 *  if deleted default is false, if it is true, just delete all duplicated record
	 *  Found if same URL request multiple shorten URL
	 */
	public void auditingAndHouseKeep(boolean deleted) {
		Map<String, Integer> map = new HashMap<String,Integer>();
		Iterator<Long> itr = URLDatabase.keySet().iterator();
		while (itr.hasNext()) {
			Long key = itr.next();
			URLRecord ref = URLDatabase.get(key);
			String URL = ref.getURL();
			if (map.containsKey(URL)) {
				map.put(URL, map.get(URL)+1);
				if (deleted) {
					synchronized(URLDatabase) {
						itr.remove();  // using iterator delete map to avoid concurrent exception 
						queue.remove(key);
					}
				}
			} else {
				map.put(URL,1);
			}
		}
		if (!deleted) {
			Iterator<String> it = map.keySet().iterator();
			while (it.hasNext()) {
				String key = it.next();
				int count = map.get(key);
				if (count>1) {
					System.out.println("Original URL "+key+" has "+ count+" shortenURLs");
					
				}
			}
		}
	}
	public void reset() {
		URLDatabase.clear();
		queue.clear();
	}
}
