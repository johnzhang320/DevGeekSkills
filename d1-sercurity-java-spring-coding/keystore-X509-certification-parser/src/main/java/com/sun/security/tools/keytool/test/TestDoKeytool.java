package com.sun.security.tools.keytool.test;

import org.apache.log4j.Logger;

import com.keystore.x509.certificate.utils.DoKeytool;
import com.keystore.x509.certificate.utils.WorkHome;

import junit.framework.TestCase;

public class TestDoKeytool extends TestCase {
	static Logger Log = Logger.getLogger(TestDoKeytool.class);
	DoKeytool dokey = null;
	public void setUp() {
		dokey = new DoKeytool();
	}
	public void testKeytoolPassword() {
		 String command = " -list "+
                 " -v "+
                 " -keystore /Users/intelliswift/temp/applepay_qa_environment/keystores/applepay_qa1/poc.keystore"+
                 " -storepass abc1234";
		 Log.info(command);
		  dokey.execute(command,true);
		 
	}
	
}
