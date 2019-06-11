package com.keystore.truststore.manager;
/**
 *  Author:john.zhang320@gmail.com
 *  Created Date: 01/31/2018
 */
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;

import org.apache.log4j.Logger;

import com.keystore.truststore.manager.utils.DoKeytool;
import com.keystore.truststore.manager.utils.ExecCmd;
import com.keystore.truststore.manager.utils.FindResourceFilePath;
import com.sslcontext.manager.SSLContextManager;
 

public class GatherServerCertChain {
	static Logger Log = Logger.getLogger(GatherServerCertChain.class);
   /**
    *  truststore.jks should contain the certificate chain of the server be trusted, if unconditionally trust a server
    *  put null for JKS_TRUSTSTORE_PATH, KEY_PASSWORD , this is bad practice for production but for code testing only
    */
	private static final String JKS_TRUSTSTORE_PATH=null;  // if unconditionally trust server, put null , normally for test
	private static final String KEY_PASSWORD=null;
	private static final String SSL_CLIENT_CERT_WORK_DIR=FindResourceFilePath.getInstance().getResourcePath();
	private static ExecCmd runCmd = new ExecCmd();
	private String https_url;
	private String fullPathCer;
	private String fullPathPem;
	private String fullPathJks;
	private String aliasName;
	private String trustCertChainFile;
	 
	public GatherServerCertChain(String https_url,String trustCertChainFile,String trustStoreWorkDir,String aliasName) {
		this.fullPathCer = trustStoreWorkDir+trustCertChainFile+".cer";
		this.fullPathPem = trustStoreWorkDir+trustCertChainFile+".pem";
		this.fullPathJks = trustStoreWorkDir+trustCertChainFile+".jks";	
		this.aliasName=aliasName;
		File file = new File(fullPathCer);
		if (file.exists()) {
			file.delete();
		}
		file = new File(fullPathPem);
		if (file.exists()) {
			file.delete();
		}
		file = new File(fullPathJks);
		if (file.exists()) {
			file.delete();
		}
		this.https_url =https_url;
		this.trustCertChainFile = trustCertChainFile;
		 
	}
	public GatherServerCertChain() {}
	
	public void collectCMServerCertChain() {
		
			
		   /**
		    *  truststore.jks should contain the certificate chain of the server be trusted, if unconditionally trust a server
		    *  put null for JKS_TRUSTSTORE_PATH, KEY_PASSWORD , this is bad practice for production but for code testing only
		    */
		   SSLContextManager ssl = new SSLContextManager(
				   null, 
				   null,
				   JKS_TRUSTSTORE_PATH,
				   KEY_PASSWORD 
			);
		   SSLContext ssl_ctx = ssl.getSSLContext();
		   URL url;
		   try {
			   HttpsURLConnection.setDefaultSSLSocketFactory(ssl_ctx.getSocketFactory());

		 	    url = new URL(https_url);
		 	    HttpsURLConnection con = (HttpsURLConnection)url.openConnection();

		 	    // Guard against "bad hostname" errors during handshake.
	             con.setHostnameVerifier(new HostnameVerifier() {
	                 public boolean verify(String host, SSLSession sess) {
	                	 return true;
	                   //  if (host.equals("localhost")) return true;
	                   //  else return false;
	                 }
	             });
		         
		         
		         con.setRequestMethod("GET");
		         con.setRequestProperty("Content-Type", "application/json");
		         con.setRequestProperty("Accept", "application/json");
		         con.setDoOutput(true);
		 		 con.setDoInput(true);
		         
		 	    //dumpl all cert info
		 	    print_https_cert(con);

		 	   

		 	 } catch (MalformedURLException e) {
		 		e.printStackTrace();
		 	 } catch (IOException e) {
		 		e.printStackTrace();
		 	 } 
		        
	   }
	/**
	 * Try to gather any truststore from any CA URL
	 * Suppose mutual authentication , if there is not mutual authenticate, pass local keystore path/password the null
	 * 
	 */
	
	public void collectCMServerCertChain(String trustCAUrl, String localKeystorePath,String localKeystorePasswd) {
		
		
		   /**
		    *  truststore.jks should contain the certificate chain of the server be trusted, if unconditionally trust a server
		    *  put null for JKS_TRUSTSTORE_PATH, KEY_PASSWORD , this is bad practice for production but for code testing only
		    */
		   SSLContextManager ssl = new SSLContextManager(
				   null, 
				   null,
				   localKeystorePath,
				   localKeystorePasswd 
			);
		   SSLContext ssl_ctx = ssl.getSSLContext();
		   URL url;
		   try {
			   
			    HttpsURLConnection.setDefaultSSLSocketFactory(ssl_ctx.getSocketFactory());
			    Log.info("created SocketFactory for "+trustCAUrl);
		 	    url = new URL(trustCAUrl);
		 	    HttpsURLConnection con = (HttpsURLConnection)url.openConnection();
		 	    Log.info("created HttpsURLConnection for "+trustCAUrl);
		 	    // Guard against "bad hostname" errors during handshake.
	             con.setHostnameVerifier(new HostnameVerifier() {
	                 public boolean verify(String host, SSLSession sess) {
	                	 return true;
	                   //  if (host.equals("localhost")) return true;
	                   //  else return false;
	                 }
	             });
	             Log.info("created HostnameVerifier for "+trustCAUrl); 
		         
		         con.setRequestMethod("GET");
		        // con.setRequestProperty("Content-Type", "application/json");
		        // con.setRequestProperty("Accept", "application/json");
		         con.setDoOutput(true);
		 		 con.setDoInput(true);
		 		 Log.info("set header for"+trustCAUrl); 
		 	    //dumpl all cert info
		 	    print_https_cert(con);

		 	   

		 	 } catch (MalformedURLException e) {
		 		e.printStackTrace();
		 	 } catch (IOException e) {
		 		e.printStackTrace();
		 	 } 
		        
	   } 

	   private void print_https_cert(HttpsURLConnection con){
		   Log.info("begin");
	      if(con!=null){
	   
		    try {
	
			 	System.out.println("Response Code : " + con.getResponseCode());
			 	System.out.println("Cipher Suite : " + con.getCipherSuite());
			 	System.out.println("\n");
			
			 	Certificate[] certs = con.getServerCertificates();
			 	File file = new File(fullPathCer);
			 	if (file.exists()) {
			 		file.delete();
			 	}
			 	FileOutputStream os = new FileOutputStream(file);
				 	
	
			 	for(Certificate cert : certs){
				 	  System.out.println("Cert Type : " + cert.getType());
				 	  System.out.println("Cert Hash Code : " + cert.hashCode());
				 	  System.out.println("Cert Public Key Algorithm : " + cert.getPublicKey().getAlgorithm());
				 	  System.out.println("Cert Public Key Format : " + cert.getPublicKey().getFormat());
				 	  System.out.println("Cert encode: \n " + cert.getEncoded().toString());
				 	 if (cert instanceof X509Certificate) {
				            X509Certificate x509cert = (X509Certificate) cert;
				            System.out.println("X509Certificate name"+x509cert.getSubjectDN().getName());
				            System.out.println(x509cert.getEncoded());
				            os.write(x509cert.getEncoded());
				 	 }
			 	  
			 	  
				 	 System.out.println("\n");
			 	}
			 	os.close();
			 	file = new File(fullPathCer);
			 	if (!file.exists()) {
			 		return;
			 	}  
			 	String command="openssl x509 -inform der -in "+trustCertChainFile+".cer"+" -out "+trustCertChainFile+".pem";
				int p=runCmd.runCmd(command, SSL_CLIENT_CERT_WORK_DIR); 
				if (p==0) {
					Log.info("Succeed Converting CER to PEM");
				} 
					
				//keytool -delete -alias 'Test Apple Corporate Root CA' -keystore truststore.jks -storepass abc1234 -keypass abc1234
				//keytool -import -v -trustcacerts -alias 'Test Apple Corporate Root CA' -file localhost.apple.com.issuer.pem -keystore truststore.jks -storepass abc1234 -keypass abc1234
			
				command = " -genkey -keyalg RSA -alias "+ aliasName+
						  " -keystore "+ fullPathJks+
				          " -dname CN=unknown "+
				          " -storepass abc1234 -keypass abc1234";
		
				Log.info("keytool "+command);
				DoKeytool.execute(command,false);
		
				command = " -delete -alias "+ aliasName+
						 " -keystore "+ fullPathJks+
						 " -storepass abc1234 -keypass abc1234";
				Log.info("keytool "+command);
				DoKeytool.execute(command,false);
								 
				command = " -import -v -trustcacerts -noprompt -alias "+ aliasName+
						  " -file "+fullPathPem+
						  " -keystore "+ fullPathJks+
						  " -storepass abc1234 -keypass abc1234";
			
				Log.info("keytool "+command);
				DoKeytool.execute(command,false);
				
				System.out.println("\n******************************** Created Trustore as following *******************************************************************************");
				System.out.println("\n"+fullPathJks+"\n");
				System.out.println("********************************** Created Trustore as above ********************************************************************************");
				System.out.println("\n");
		      } catch (Exception e) {
		    	 System.out.println("e.getMessage()="+e.getMessage());
		      }  
	      
	   }
   }
 
}
