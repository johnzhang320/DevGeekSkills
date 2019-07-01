package com.set.sslconnection.to.google.facebook.by.truststore;
  

import javax.net.ssl.HttpsURLConnection;

import org.apache.log4j.Logger;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.keystore.truststore.manager.GatherServerCertChain;
import com.keystore.truststore.manager.utils.DoKeytool;
import com.keystore.truststore.manager.utils.FindResourceFilePath;
import com.keystore.truststore.manager.utils.GetSSLConnectionBySSLContext;
import com.keystore.truststore.manager.utils.Utils;
/*
 * Note:
 * 1. If first time run this project , please check run configuration --> delete all items under Junit 
 * 2. Make sure that JRE is java 1.8
 * 3. Junit 5 order by sequence as test presents
 */
@RunWith(JUnitPlatform.class)
@FixMethodOrder()  // put BeforeEach and AfterEach, running test case by test method's occurring order
public class TestSSLConnectToFaceBookByGatheredTruststore {
	static Logger Log = Logger.getLogger(TestSSLConnectToFaceBookByGatheredTruststore.class);
	
	private static GatherServerCertChain handler = null;
	static DoKeytool dokey = null;
	private static final String FACE_BOOK_LINK="https://www.facebook.com";
	private static GetSSLConnectionBySSLContext getsslconnect;
	private static String result="nothing";
	private static String WORkING_DIR = null;
	private static int testCase=1;
	@BeforeAll
	public static void setUp() {
		WORkING_DIR = FindResourceFilePath.getInstance().getResourcePath();
		handler = new GatherServerCertChain(
				 FACE_BOOK_LINK, 												//String https_url,
				"facebookTrustCertChain", 								    //String trustCertChainFile,
				 WORkING_DIR, 		//String trustStoreWorkDir,
				"facebook_cert_chain" );       	 							//String aliasName) 
		
		getsslconnect = new GetSSLConnectionBySSLContext(
				FACE_BOOK_LINK, 
				null,
				null,
				 WORkING_DIR+"facebookTrustCertChain.jks",
				"abc1234"
		);
		
		dokey = new DoKeytool();
				
		Log.info("Completed Setup , result="+result);
		testCase=1;
	}
	@BeforeEach 
	void before() {
		System.out.println("\nRunning Case "+ testCase);
	}
	@AfterEach 
	void after() {
		System.out.println("Completed Case "+ testCase+" and result="+result);
		testCase++;
	}
	@Test
	public void establishSSConnectionToFacebook() {
 
		Log.info("TestCase 1 gather Facebook Truststore contained Certificate Chain ");
		handler.collectCMServerCertChain(); 
		
		
		 
		Log.info("\nTestCase 2 establishSSConnectionToFacebook() by GET without risking by browser! ");
		   HttpsURLConnection conn =getsslconnect.getSSLConnection();
		   Log.info("Result connection="+conn.toString());
		   if (conn!=null) {
			   Log.info("Succeed to Connect to Facebook ");
			   try {
				   System.out.println("Because:");   
				   System.out.println("\nResponse Code : " + conn.getResponseCode());
				   System.out.println("Cipher Suite : " + conn.getCipherSuite());
				   conn.connect();
				   System.out.println("\nWe can see request data as following:");
				   System.out.println("Request header fields:" + conn.getHeaderFields());
				   System.out.println("Request header Context-Type:" + conn.getContentType());
				   System.out.println("Request header Accept:" + conn.getRequestProperty("Accept"));
				   System.out.println("Request Method:" + conn.getRequestMethod());
				   System.out.println("Response Message:" + conn.getResponseMessage());
				   System.out.println("\n");
			   } catch (Exception e) {
				   Log.error(e.fillInStackTrace());
			   } finally {
				   conn.disconnect();
			   }
			
		   }
		
		   Log.info("\nTestCase 3 establishSSConnectionToFacebook() by POST without risking by browser! ");
		    conn =getsslconnect.getSSLConnection("POST");
		   Log.info("Result connection="+conn.toString());
		   if (conn!=null) {
			   Log.info("Succeed to Connect to Facebook ");
			   try {
				   System.out.println("Because:");   
				   System.out.println("\nResponse Code : " + conn.getResponseCode());
				   System.out.println("Cipher Suite : " + conn.getCipherSuite());
				   conn.connect();
				   System.out.println("\nWe can see request data as following:");
				   System.out.println("Request header fields:" + conn.getHeaderFields());
				   System.out.println("Request header Context-Type:" + conn.getContentType());
				   System.out.println("Request header Accept:" + conn.getRequestProperty("Accept"));
				   System.out.println("Request Method:" + conn.getRequestMethod());
				   System.out.println("Response Message:" + conn.getResponseMessage());
				   System.out.println("\n");
			   } catch (Exception e) {
				   Log.error(e.fillInStackTrace());
			   } finally {
				   conn.disconnect();
			   }
			
		   }
	}
	 
	
}
