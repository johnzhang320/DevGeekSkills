package com.solr.utils;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
 


import java.util.logging.Logger;


//import org.apache.solr.client.solrj.SolrServer;
//import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.SolrClient; 
import org.apache.solr.client.solrj.SolrServerException; 
import org.apache.solr.client.solrj.impl.HttpSolrClient; 
import org.apache.solr.client.solrj.request.CoreAdminRequest;
import org.apache.solr.client.solrj.response.CoreAdminResponse;

public final class SolrClientFactory 
{
	Logger LOG = Logger.getLogger(SolrClientFactory.class.getName()); 
	    
    Map <String, HttpSolrClient> urlToServer = new ConcurrentHashMap <String, HttpSolrClient> ();
    static SolrClientFactory instance = new SolrClientFactory ();
    
    public static SolrClientFactory getInstance()
    {
        return instance;
    }
    
    private SolrClientFactory ()
    {
    }
    
    public HttpSolrClient getClient(String solrURL) {
    	String myURL[] = solrURL.split("/");
    	String coreName = null;
    	String baseURL = null;
    	for (int i=0; i<myURL.length;i++) {
    		if (myURL[i].equalsIgnoreCase("#")) {
    			if (i+1<myURL.length) {
    				coreName = myURL[i+1];
    				System.out.println( myURL[i+1]);
    			}
    		}
    	}
    	baseURL = solrURL.replace("/"+coreName, "");
    	System.out.println(baseURL);
    	return getClient(baseURL,coreName,coreName);
    }
    
    public HttpSolrClient getClient (
    		                          String baseURL,   // solr server URL and port such as http://localhost:8983/solr
    		                          String coreName,   // coreName
    		                          String dataDir // regularly it is same as coreName
    		                         )
    {
        
        
        HttpSolrClient httpSolrClient =null;
        try {
        	
        	httpSolrClient = new HttpSolrClient.Builder(baseURL+"/"+coreName).build();   
        	// ensure core has been created if it not exists
        	createCoreIfNeeded(coreName,dataDir, httpSolrClient);
        	 
        	 
        } catch (Exception e) {
        	System.out.println(e.getMessage());
        }
      return httpSolrClient;
    }
    /**
     * Create a core if it does not already exists. Returns true if a new core was created, false otherwise.
     */
    public void createCoreIfNeeded(String coreName, String dataDir, SolrClient solrClient)  
    {
        
        try
        {
            // SolrJ provides no direct method to check if a core exists, but getStatus will
            // return an empty list for any core that doesn't.
            CoreAdminResponse statusResponse = CoreAdminRequest.getStatus(coreName, solrClient);
            boolean coreExists = statusResponse.getCoreStatus(coreName).size() > 0;
            if(!coreExists)
            {
                // Create the core
                LOG.info("Creating Solr core: " + coreName);
                CoreAdminRequest.Create create = new CoreAdminRequest.Create();
                create.setCoreName(coreName);
                create.setInstanceDir(".");
                create.setDataDir(dataDir);
                create.process(solrClient);
            } else {
            	System.out.println("Solr Core "+coreName+" already exists");
            }
        }
        catch ( Exception e)
        {
            e.printStackTrace();
        }
        return ;
    }
    public static void main(String args[]) {
     
    	HttpSolrClient httpSolrClient = new HttpSolrClient.Builder("http://localhost:8983/solr").build();  
    	SolrClientFactory.getInstance().createCoreIfNeeded("my_core2", "my_core2", httpSolrClient) ;
    }
}