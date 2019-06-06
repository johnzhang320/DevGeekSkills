package com.prismoskills.solrj;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
 
//import org.apache.solr.client.solrj.SolrServer;
//import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.SolrClient; 
import org.apache.solr.client.solrj.SolrServerException; 
import org.apache.solr.client.solrj.impl.HttpSolrClient; 

public final class SolrClientFactory 
{
	 
	    
    Map <String, SolrClient> urlToServer = new ConcurrentHashMap <String, SolrClient> ();
    static SolrClientFactory instance = new SolrClientFactory ();
    
    public static SolrClientFactory getInstance()
    {
        return instance;
    }
    
    private SolrClientFactory ()
    {
    }
    
    public SolrClient createServer (String solrURL)
    {
        if (urlToServer.containsKey(solrURL))
            return urlToServer.get(solrURL);
        
        /*
        HttpSolrServer is thread-safe and if you are using the following constructor,
        you *MUST* re-use the same instance for all requests.  If instances are created on
        the fly, it can cause a connection leak. The recommended practice is to keep a
        static instance of HttpSolrServer per solr server url and share it for all requests.
        See https://issues.apache.org/jira/browse/SOLR-861 for more details
      */
        SolrClient server =null;
        try {
        	server = new HttpSolrClient.Builder(solrURL).build();   
        	urlToServer.put(solrURL, server);
        } catch (Exception e) {
        	System.out.println(e.getMessage());
        }
      return server;
    }
}