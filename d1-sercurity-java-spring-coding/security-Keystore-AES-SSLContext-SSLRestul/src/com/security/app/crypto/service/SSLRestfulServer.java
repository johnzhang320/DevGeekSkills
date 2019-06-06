package com.security.app.crypto.service;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.Logger;


import com.mailfrontier.msgcenter.app.log.Logger;


@Path("/jsonServices")
public class SSLRestfulServer {
	public static Logger Log =Logger.getLogger(SSLRestfulServer.class.getName());
	

	@POST
	@Path("/ssl_resful_client")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deliverAllQueuedMessage(NewMessageAuthVO mdVO,@Context HttpServletResponse response) {
		
		/**
		 *  Set no cache
		 */
		// Set standard HTTP/1.1 no-cache headers.
		response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");

		// Set IE extended HTTP/1.1 no-cache headers (use addHeader).
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");

		// Set standard HTTP/1.0 no-cache header.
		
		response.setHeader("Pragma", "no-cache");	 // HTTP 1.0.
		 
		response.setHeader("Expires", "-1");  // Proxies.
		
		EncryptionUtils handler= new EncryptionUtils(mdVO.getSeedStr4IV());
		
		/**
		 *  Apply jzhang invented Symmetric Key Authentication Cryptography , random number plus timestamp, 
		 *  check whether random number is repeated if the timestamp is most currently or check if timestamp 
		 *  is too old.  
		 *  Also check whether different Cluster CC call this CC  
		 */
		AuthenticateManager authMgr = new AuthenticateManager();
		
		String authCheck = authMgr.ccUserAuthentication(mdVO);		
		
		if (!handler.AUTH_PASSED.equalsIgnoreCase(authCheck)) {
			Log.error("Error: Received POJO Object failed authentication because "+authCheck);				
			return Response.status(200).entity(handler.AUTH_FAILED).build();
			
		} 
		if (null==mdVO.getCommandType() || ""==mdVO.getCommandType()) {					 
			Log.error("Error: No Command Type Setting!");	
			return Response.status(200).entity(Constants.ERR_NO_COMMAND_TYPE).build();
		}
	 
		String commandType = null;
		try {
			commandType = handler.decrypt(mdVO.getCommandType());
		} catch (Exception e) {
			Log.error("Error: No Command Type Decrypted Failed!");
			return Response.status(200).entity(Constants.ERR_NO_COMMAND_TYPE).build();
		}
		String output = null; 
		if (Constants.CMD_DELIVER_ALL_QUEUED_MESSAGE.equalsIgnoreCase(commandType)) {
			
			 			
				if (Constants.ALLOW_MLFWORR_API_WORK) {
					
					Long start = System.currentTimeMillis();
					
					Log.info("Deliver all queued message for this host " );
					
					//MlfWorkr.deliverAllPMTAQueuedMtaMessages();;
					
					Long end = System.currentTimeMillis();
					
					Log.info("Deliver all queued message for this host, took "+(end-start)+" millis seconds " );
 				}
 			 
		} else {
			Log.error("Error:  Command Type did not matched!");
			return Response.status(200).entity(Constants.ERR_NO_COMMAND_TYPE).build();
		}
		  
		output="SucceedOndelivering";
		Log.debug("Deliver All queued message for this host \n"+output);
		return Response.status(200).entity(output).build();
	}
 
 
	
	public static String getLocalHost() {
		
	    InetAddress ip;
	    
        String localHostname=""; 
        
	    try 
	    {
	       	ip = InetAddress.getLocalHost();
	       	
	        String hostname = ip.getHostName();
	        String host[] = hostname.split("\\.");
	        
	        if (host.length>0) {
	        	localHostname = host[0];
	        } else {
	        	localHostname = hostname;
	        }
	        
	    } catch (UnknownHostException e) {
	    	
	       	Log.error("Failure to get local hostname",e);
				 
	    }
	    Log.info("WS Server localhost name is "+localHostname);
		return localHostname;
	}
	
	public String usrAuthentication(MessageAuthVO mdVO,EncryptionUtils handler) {
	
		String result = handler.AUTH_PASSED;
		
		try {

			 result = handler.userAuthentication(mdVO);			 
			
			 Log.info("Server Auth result="+result);
			 
		} catch (Exception e) {
			
			 result = handler.AUTH_FAILED;
		}
		return result;
	}
}
