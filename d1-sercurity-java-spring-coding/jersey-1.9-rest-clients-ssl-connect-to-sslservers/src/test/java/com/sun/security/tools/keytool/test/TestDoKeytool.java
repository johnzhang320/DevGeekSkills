package com.sun.security.tools.keytool.test;
  

import org.apache.log4j.Logger;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.keystore.truststore.manager.utils.DoKeytool;
import com.keystore.truststore.manager.utils.FindResourceFilePath;
/*
 * Note:
 * 1. If first time run this project , please check run configuration --> delete all items under Junit 
 * 2. Make sure that JRE is java 1.8
 * 3. Junit 5 order by sequence as test presents
 */
@RunWith(JUnitPlatform.class)
@FixMethodOrder()  // put BeforeEach and AfterEach, running test case by test method's occurring order
public class TestDoKeytool {
	static Logger Log = Logger.getLogger(TestDoKeytool.class);
	static DoKeytool dokey = null;
	private static String result="nothing";
	private static int testCase=1;
	@BeforeAll
	public static void setUp() {
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
	public void findResourceFilePathTest() {
 
		Log.info("run1 findResourceFilePathTest ");
		Log.info("mykeystore location="+FindResourceFilePath.getInstance().getResourceFile("mykeystore.jks"));
	}
		
	@Test
	public void getResourceFilePathTest() {
		Log.info("run2 getResourceFilePathTest2 ");
		Log.info("mykeystore location="+FindResourceFilePath.getInstance().getResourceFile("mykeystore.jks"));
	}
	@Test
	public void keytoolKeystoreAccessTest() {
		Log.info("run3 keytoolKeystoreAccessTest ");
		String keystorefile=FindResourceFilePath.getInstance().getResourceFile("mykeystore.jks");
		String command = " -list "+
                 " -v "+
                 " -keystore "+keystorefile+
                 " -storepass abc1234";
		 Log.info(command);
		 dokey.execute(command,false);   // false means command does not display result
	
	}
	
	@Test
	public void displayKeystoreTest() {
		Log.info("run4 displayKeystoreTest ");
		String keystorefile=FindResourceFilePath.getInstance().getResourceFile("mykeystore.jks");
		String command = " -list "+
                 " -v "+
                 " -keystore "+keystorefile+
                 " -storepass abc1234";
		 Log.info(command);
		 result=dokey.execute(command,false);  // pass diaplay result
	     
	     Log.info("Complete displayKeystoreTest ");
	 
	
	}
	
}
