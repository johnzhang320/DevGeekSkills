package com.solr.example;
import java.io.IOException;  
import java.util.Iterator;

import org.apache.solr.client.solrj.SolrClient; 
import org.apache.solr.client.solrj.SolrServerException; 
import org.apache.solr.client.solrj.impl.HttpSolrClient; 
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrInputDocument; 
import org.apache.solr.client.solrj.SolrQuery; 
import org.apache.solr.client.solrj.response.QueryResponse; 
import org.apache.solr.common.SolrDocumentList; 

public class RetrievingData {
	public static void main(String args[]) throws SolrServerException, IOException  { 
	      //Preparing the Solr client 
	      String urlString = "http://localhost:8983/solr/my_core"; 
	      SolrClient Solr = new HttpSolrClient.Builder(urlString).build();  
	      
	      //Preparing Solr query 
	      SolrQuery query = new SolrQuery();  
	      query.setQuery("*:*");  
	   
	      //Adding the field to be retrieved 
	      query.addField("*");  
	   
	      //Executing the query 
	      QueryResponse queryResponse = Solr.query(query);  
	   
	      //Storing the results of the query 
	      SolrDocumentList docs = queryResponse.getResults();    
	      System.out.println(docs); 
	      
	      System.out.println(docs.get(0)); 
	      System.out.println(docs.get(1)); 
	      System.out.println(docs.get(2));   
	      
	      
	      System.out.println("-------------------------------------");
	      Iterator<SolrDocument> itr = docs.iterator();
	      while (itr.hasNext()) {
	    	  SolrDocument sdoc = itr.next();
	    	  System.out.println(sdoc);
	      }
	      //Saving the operations 
	      Solr.commit();         
	   } 
}
