package com.solr.example;
import java.io.IOException;  

import org.apache.solr.client.solrj.SolrClient; 
import org.apache.solr.client.solrj.SolrServerException; 
import org.apache.solr.client.solrj.impl.HttpSolrClient; 
import org.apache.solr.common.SolrInputDocument;  
public class DeletingAllDocuments {
	 public static void main(String args[]) throws SolrServerException, IOException {
	      //Preparing the Solr client 
	      String urlString = "http://localhost:8983/solr/my_core"; 
	      SolrClient Solr = new HttpSolrClient.Builder(urlString).build();   
	      
	      //Preparing the Solr document 
	      SolrInputDocument doc = new SolrInputDocument();   
	          
	      //Deleting the documents from Solr 
	      Solr.deleteByQuery("*");        
	         
	      //Saving the document 
	      Solr.commit(); 
	      System.out.println("Documents deleted"); 
	   } 
}
