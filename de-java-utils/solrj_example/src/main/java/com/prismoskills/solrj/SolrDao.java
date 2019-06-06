package com.prismoskills.solrj;


import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.request.UpdateRequest;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;

import com.prismoskills.solrj.SolrClientFactory;

public class SolrDao <T>
{

   SolrClient server = null;
   
   public SolrDao (String solrURL)
   {
       server = (SolrClient) SolrClientFactory.getInstance().createServer(solrURL);
       configureSolr (server);
   }
   
   public void put (T dao)
   {
       put (createSingletonSet (dao));
   }
   
   public void put (Collection<T> dao)
   {
       try 
       {
           UpdateResponse rsp = server.addBeans (dao);
           System.out.println ("Added documents to solr. Time taken = " + rsp.getElapsedTime() + ". " + rsp.toString());
       }
       catch (SolrServerException e)
       {
           e.printStackTrace();
       }
       catch (IOException e)
       {
           e.printStackTrace();
       }
   }
   
   public void putDoc (SolrInputDocument doc)
   {
       putDoc (createSingletonSet(doc));
   }
   
   public void putDoc (Collection<SolrInputDocument> docs)
   {
       try 
       {
           long startTime = System.currentTimeMillis();
           UpdateRequest req = new UpdateRequest();
           req.setAction( UpdateRequest.ACTION.COMMIT, false, false );
           req.add (docs);
           UpdateResponse rsp = req.process( server );
           System.out.print ("Added documents to solr. Time taken = " + rsp.getElapsedTime() + ". " + rsp.toString());
           long endTime = System.currentTimeMillis();
           System.out.println (" , time-taken=" + ((double)(endTime-startTime))/1000.00 + " seconds");
       }
       catch (SolrServerException e)
       {
           e.printStackTrace();
       }
       catch (IOException e)
       {
           e.printStackTrace();
       }
   }
   
   public QueryResponse readAll () 
   {
       SolrQuery query = new SolrQuery();
       query.setQuery( "*:*" );
       //query.addSortField( "price", SolrQuery.ORDER.asc );
       QueryResponse rsp = null;
       try 
       {
           rsp = server.query( query );
       }
       catch (SolrServerException e) 
       {
           e.printStackTrace();
       } catch (IOException e) {
    	   e.printStackTrace();
       }
       return rsp;
   }
   
   public SolrDocumentList readAllDocs ()
   {
       SolrQuery query = new SolrQuery();
       query.setQuery( "*:*" );
       //query.addSortField( "price", SolrQuery.ORDER.asc );
       QueryResponse rsp = null;
       try {
           rsp = server.query( query );
       } catch (SolrServerException e) 
       {
           e.printStackTrace();
           return null;
       } catch (IOException e) {
    	   e.printStackTrace();
       }
       SolrDocumentList docs = rsp.getResults();
       return docs;
   }
   
   private void configureSolr(SolrClient server) 
   {
       //server.setMaxRetries(1); // defaults to 0.  > 1 not recommended.
       //server.setConnectionTimeout(5000); // 5 seconds to establish TCP
       // The following settings are provided here for completeness.
       // They will not normally be required, and should only be used 
       // after consulting javadocs to know whether they are truly required.
      // server.setSoTimeout(1000);  // socket read timeout
     //  server.setDefaultMaxConnectionsPerHost(100);
      // server.setMaxTotalConnections(100);
     //  server.setFollowRedirects(false);  // defaults to false
       // allowCompression defaults to false.
       // Server side must support gzip or deflate for this to have any effect.
     //  server.setAllowCompression(false);
   }
   
   private <U> Collection<U> createSingletonSet(U dao) 
   {
       if (dao == null)
           return Collections.emptySet();
       return Collections.singleton(dao);
   }

}