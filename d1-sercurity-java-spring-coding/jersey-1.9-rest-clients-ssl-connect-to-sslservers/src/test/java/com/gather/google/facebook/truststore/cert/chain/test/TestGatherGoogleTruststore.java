package com.gather.google.facebook.truststore.cert.chain.test;
  

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
import com.keystore.truststore.manager.utils.Utils;
/*
 * Note:
 * 1. If first time run this project , please check run configuration --> delete all items under Junit 
 * 2. Make sure that JRE is java 1.8
 * 3. Junit 5 order by sequence as test presents
 */
@RunWith(JUnitPlatform.class)
@FixMethodOrder()  // put BeforeEach and AfterEach, running test case by test method's occurring order
public class TestGatherGoogleTruststore {
	static Logger Log = Logger.getLogger(TestGatherGoogleTruststore.class);
	
	private static GatherServerCertChain handler = null;
	static DoKeytool dokey = null;
	private static final String GOOGLE_LINK="https://www.google.com";
	private static String result="nothing";
	private static String WORkING_DIR = null;
	private static int testCase=1;
	@BeforeAll
	public static void setUp() {
		WORkING_DIR = FindResourceFilePath.getInstance().getResourcePath();
		handler = new GatherServerCertChain(
				 GOOGLE_LINK, 												//String https_url,
				"googleTrustCertChain", 								//String trustCertChainFile,
				 WORkING_DIR, 		//String trustStoreWorkDir,
				"google_cert_chain" );       	 							//String aliasName) 
		
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
	public void test1_collectCMServerCertChainTest() {
 
		Log.info("TestCase 1 collectCMServerCertChainTest() ");
		handler.collectCMServerCertChain(); 
	
		Log.info("TestCase 2 showGoogleTrustCertInPemFormat() ");
		String pem = Utils.readFileExactly(WORkING_DIR+"googleTrustCertChain.pem");
		Log.info("googleTrustCertChain.pem shown below");
		System.out.println(pem);
		
		Log.info("TestCase 2 showGoogleTrustCertInJKSFormat() ");
		String keystorefile=WORkING_DIR+("googleTrustCertChain.jks");
		String command = " -list "+
                 " -v "+
                 " -keystore "+keystorefile+
                 " -storepass abc1234";
		 Log.info(command);
		 result=dokey.execute(command,false);  // pass diaplay result
	     
	     Log.info("Complete displayKeystoreTest ");
	}
	 
	
}
