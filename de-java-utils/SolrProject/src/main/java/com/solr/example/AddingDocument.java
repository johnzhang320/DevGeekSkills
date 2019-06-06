package com.solr.example;

import java.io.IOException;  

import org.apache.solr.client.solrj.SolrClient; 
import org.apache.solr.client.solrj.SolrServerException; 
import org.apache.solr.client.solrj.impl.HttpSolrClient; 
import org.apache.solr.common.SolrInputDocument; 

public class AddingDocument { 
   public static void main(String args[]) throws Exception { 
      //Preparing the Solr client 
      String urlString = "http://localhost:8983/solr/my_core"; 
      SolrClient Solr = new HttpSolrClient.Builder(urlString).build();   
      
      //Preparing the Solr document 
      SolrInputDocument doc = new SolrInputDocument(); 
   
      //Adding fields to the document 
      doc.addField("id", "003"); 
      doc.addField("name", "Rajaman"); 
      doc.addField("age","34"); 
      doc.addField("addr","vishakapatnam"); 
         
      //Adding the document to Solr 
      Solr.add(doc);         
         
      //Saving the changes 
      Solr.commit(); 
      System.out.println("Documents added"); 
   } 
}
