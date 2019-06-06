package com.security.app.crypto.service;
 
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration; 



/**
 *  PMTAQueueClient apply POST method with Json object to request PMTAQueueServer
 *  (1) For HTTP protocol, we apply Authentication Entity plus Message, that is client and 
 *  server shared same private symmetric key(SK), client use AES with SK to encrypt the
 *  message to Esk(m) and use SHA256 to digest message to hash1, then fetch the seed for
 *  Initialized Vector for Cipher Block Chain and then send these three kind of data in 
 *  MessageAuthVO to Server, which decrypt the Esk(m) to m with shared SK adn Seed, then
 *  use SHA256 to digest the m to hash2, then compare hash1 to hash2, if it's same, means
 *  user who call this client has been authenticated.
 *  
 *  (2) For HTTPS protocol, we use RA's keystore file and server.xml of tomcat to create sslContext
 *  instance to establish encrypted SSL channel to talk to RA    
 *    
 */
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONStringer;

import com.mailfrontier.msgcenter.app.log.Logger;

/**
 * For HTTP, encrypt commandType and hostname, send user authenticated message to server
 * For HTTPS, using tomcat java keystore and trust store to establish the HTTPS connection
 * 
 * For mta_status.xml
 * valid method is MTAStatusRACountsHTTPClient(String URL,String commandType, String hostname,MTAStatusRAsCounts mtaCounts )
 * Calling web service server, read RA data into three Json Strings: totalSection, inboundSection and outboundSection 
 * 
 * For mta_queuebydetailvmta.xml
 * Calling web service server, read RA data into Json String 
 * 
 * 
 * @author jzhang
 *
 */
public class SSLRestfulClient  {
	 
	 public static Logger Log =Logger.getLogger(SSLRestfulClient.class.getName());
	 private  final static String DIGEST_MESSAGE_STRING = "sonicwall40%dsgsonicwall40345234214%ds12345678";
	 /**
	  * SSLRestfulClient()
	  * Send information to Authenticate user by SymmetricKey and commandType and hostname, then 
	 
	  * User authenticate by symmetric key, validate commandType and hostnameType, then
	  * @param URL 			 --	     http://localhost:8080/services/jsonServices/ssl_restful_client"
	  *                              for	Constants.CMD_DELIVER_ALL_QUEUED_MESSAGE 	
	  * @param commandType   --      Constants.CMD_DELIVER_ALL_QUEUED_MESSAGE
	  * @param hostname      -- 	 nqhostname localhost or domain name of web server
                             
	  * @return Constants.SUCCEED_DELIVER_ALL_QUEUED_MESSAGE if succeed on deliver all queued message for request RA 
	  */ 
	
	 public String SSLRestfulClient(String URL,String commandType, String hostname)  {
		 if (null==URL) {
			 return null;
		 }
		 if (null==commandType || ""==commandType) {
			 Log.error("\nError: No CommandType!");
			 return null;
		 }
		 if (null==hostname || ""==hostname) {
			 
			 Log.error("\nError: No Hostname!");
			 return null;
		 }
		 String output = null;
		 Client client = null;
		 String protocol = URL.substring(0,6).toLowerCase(); 
	
		 try {
			 if (protocol.indexOf("https")!=-1) {
					 
				 EmptySSLContextManager ssl = new EmptySSLContextManager(hostname);
					 
				 SSLContext sslContext = ssl.getSSLContext();				 
					
				 ClientConfig clientConfig = ssl.getDefaultClientConfig(sslContext);
					 
				 clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
					 
				 client = Client.create(clientConfig);
					 
				 Log.info("Succeed to create HTTPS Client!");
			 } else {
					 
				 ClientConfig clientConfig = new DefaultClientConfig();
					 
				 clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
					 
				 client = Client.create(clientConfig);
					 
				 Log.info("Succeed to create HTTP Client!");
			 }
			 
			 /**
			  *  Apply jzhang invented Symmetric Key Authentication Cryptography , random number plus timestamp as dynamic 
			  *  authentication message and limited by time limit  
			  */
			 AuthenticateManager authMgr = new AuthenticateManager();
				 
			 Object MDVO =authMgr.generateNewMessageAuthVO (commandType, hostname);
				 
			// Object MDVO = new MessageAuthVO(enText,digestMsg,handler.getseedStr4IV(),command, host); 			
			
			 WebResource webResource = client.resource(URL);
			 // POST
			 ClientResponse response = webResource.accept("application/json").type("application/json").post(ClientResponse.class, MDVO);

			 if (response.getStatus() != 200) {
				 return ("Failed : HTTP error code : " + response.getStatus());
			 }

			 output = response.getEntity(String.class);
			 if (Constants.SUCCEED_DELIVER_ALL_QUEUED_MESSAGE.equalsIgnoreCase(output)) {
				 Log.info ("Succeed on delivering queued message for URL:"+URL);
			 } else {
				 Log.info ("Failed delivering queued message for URL:"+URL);
			 }
			 
		 } catch (Exception e) {
			 Log.error("Failed to connect to Web Service Server "+hostname+", Error on "+e.getMessage());
			 e.printStackTrace();
		 }
		
		 return output;
	 }
	 
}