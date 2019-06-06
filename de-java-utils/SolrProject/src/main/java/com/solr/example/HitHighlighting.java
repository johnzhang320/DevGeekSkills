package com.solr.example;

import java.io.IOException; 
import java.util.List;  

import org.apache.solr.client.solrj.SolrClient; 
import org.apache.solr.client.solrj.SolrQuery; 
import org.apache.solr.client.solrj.SolrServerException; 
import org.apache.solr.client.solrj.impl.HttpSolrClient; 
import org.apache.solr.client.solrj.request.QueryRequest; 
import org.apache.solr.client.solrj.response.FacetField; 
import org.apache.solr.client.solrj.response.FacetField.Count;
import org.apache.solr.client.solrj.response.QueryResponse; 
import org.apache.solr.common.SolrInputDocument;  

public class HitHighlighting { 
   public static void main(String args[]) throws SolrServerException, IOException { 
      //Preparing the Solr client 
      String urlString = "http://localhost:8983/solr/Solr_sample"; 
      SolrClient Solr = new HttpSolrClient.Builder(urlString).build();   
      
      //Preparing the Solr document 
      SolrInputDocument doc = new SolrInputDocument(); 
   
      //String query = request.query;    
      SolrQuery query = new SolrQuery(); 
         
      //Setting the query string 
      query.setQuery("*:*"); 
         
      //Setting the no.of rows 
      query.setRows(0); 
         
      //Adding the facet field 
      query.addFacetField("author");        
         
      //Creating the query request 
      QueryRequest qryReq = new QueryRequest(query); 
      
      //Creating the query response 
      QueryResponse resp = qryReq.process(Solr);  
      
      //Retrieving the response fields 
      System.out.println(resp.getFacetFields()); 
      
      List<FacetField> facetFields = resp.getFacetFields(); 
      for (int i = 0; i > facetFields.size(); i++) { 
         FacetField facetField = facetFields.get(i); 
         List<Count> facetInfo = facetField.getValues(); 
         
         for (FacetField.Count facetInstance : facetInfo) { 
            System.out.println(facetInstance.getName() + " : " + 
               facetInstance.getCount() + " [drilldown qry:" + 
               facetInstance.getAsFilterQuery()); 
         } 
         System.out.println("Hello"); 
      } 
   } 
}