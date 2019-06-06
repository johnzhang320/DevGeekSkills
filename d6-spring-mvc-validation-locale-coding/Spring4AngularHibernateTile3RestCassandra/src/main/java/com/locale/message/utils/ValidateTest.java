package com.locale.message.utils;

import javax.servlet.http.HttpServletRequest;

import junit.framework.TestCase;

import org.springframework.validation.Errors;
import org.apache.log4j.Logger;
 
/*import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
*/
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ValidateTest  extends TestCase {
	 private static Logger Log = Logger.getLogger(ValidateTest.class);
	/**
	 * It’s important to understand that Mockito’s @Mock objects are not real instances, 
	 * they are just bare-bones of instance created using Class of type. But their main 
	 * capability is that they can remember all the interactions [operations performed] on them. 
	 */
 
    
	ValidateUtils ref = null; 
	
	public void setUp(){
	 
		ref =new  ValidateUtils();
	}
   
	public void testIsXSSPair() {
		Log.info("testIsXSSPair(John <scrip>alert(\"Hacked\")</script>) ");
		assertTrue(ref.findXSSTagPair("John <scrip>alert(\"Hacked\")</script>"));
		Log.info("testIsXSSPair(John <h1>alert(\"Hacked\")</h1>) ");
		assertTrue(ref.findXSSTagPair("John <h1>alert(\"Hacked\")</h1>"));
		Log.info("testIsXSSPair(John <h1>alert(\"Hacked\")</h1>) ");
		assertTrue(ref.findXSSTagPair("John <label>alert(\"Hacked\")</label>"));
		Log.info("testIsXSSPair(John Zhang) ");
		assertFalse(ref.findXSSTagPair("John Zhang"));
	}
	public void testRLTrim() {
		String str = "                     John Zhang                 ";
		Log.info("TestRLTRIM(         John            ) ");
		assertEquals(10,ref.rltrim(str).length());		 
		assertEquals(ref.rltrim(str),"John Zhang");
	}
	public void testIPv4() {
		Log.info("testValidIPAddress(ip) ");
		String ip1="192.168.15.22";
		assertTrue(ref.isValidSimpleIP(ip1));
		String ip2="192.168.15.888";
		assertFalse(ref.isValidSimpleIP(ip2));
	}
	
	public void testVaildIP() {
		Log.info("testValidIPAddress(ip) ");
		String ip1="192.168.15.22";
		assertTrue(ref.isValidIPAddress(ip1));
		String ip2="192.168.15.888";
		assertFalse(ref.isValidIPAddress(ip2));
		String ip3="2001:0:9d38:90d7:34e0:2e8:e77d:7d81"; // ipv6
		assertTrue(ref.isValidIPAddress(ip3));		
		String ip4="192.168.15.88/24";
		assertTrue(ref.isValidIPAddress(ip4));
		ip4="192.168.15.88/32";
		assertTrue(ref.isValidIPAddress(ip4));
		ip4="192.168.15.88/16";
		assertTrue(ref.isValidIPAddress(ip4));
		ip4="192.168.15.88/8";
		assertTrue(ref.isValidIPAddress(ip4));
		ip4="192.168.15.88/1";
		assertFalse(ref.isValidIPAddress(ip4));
		String ip5="192.168.15:88/24";
		assertFalse(ref.isValidIPAddress(ip5));		
		String ip6="2001:0:9d38:90d7:34e0:2e8:e77d:7d81/32";
		assertFalse(ref.isValidIPAddress(ip5));
	}
	public void testVaildCIDIP() {
		Log.info("testValidCIDRIPAddress(ip) ");
		 
		String ip1="192.168.15.72";
		String ip2="192.168.15.70/24";
		assertTrue(ref.checkIPCIDRRange(ip1, ip2));
		
		ip1="192.168.16.32";
		ip2="192.168.15.32/16";
		assertTrue(ref.checkIPCIDRRange(ip1, ip2));
		
		ip1="192.168.16.156";
		ip2="192.168.16.155/24";
		assertTrue(ref.checkIPCIDRRange(ip1, ip2));		
		
		ip1="192.168.16.444";
		ip2="192.168.16.444/24";
		assertFalse(ref.checkIPCIDRRange(ip1, ip2));		
		
		
		ip1="192.168.16.32";
		ip2="192.168.15.32/24";
		assertFalse(ref.checkIPCIDRRange(ip1, ip2));
		
		ip1="192.168.16.32";
		ip2="192.168.15.32/4";
		assertFalse(ref.checkIPCIDRRange(ip1, ip2));
		
		ip1="192.168.16.32";
		ip2="192.168.15.32/33";
		assertFalse(ref.checkIPCIDRRange(ip1, ip2));
		
		
		
		ip1="192.168.16.156";
		ip2="192.168.16.153/32";
		assertFalse(ref.checkIPCIDRRange(ip1, ip2));		
		
		
		ip1="2001:0:9d38:90d7:34e0:2e8:e77d:7d81"; // ipv6
		ip2="2001:0:9d38:90d7:34e0:2e8:e77d:7d81/24"; // ipv6
		assertFalse(ref.checkIPCIDRRange(ip1, ip2));
		 
	}
	public void testIsValidEmailString() {
		Log.info("testIsValidEmailString(string str) ");
		 
		String fieldValue="john.zhang320@gmail.com";
		 
		assertTrue(ref.isValidEmailString(fieldValue));
		
		fieldValue="john.zhang320@gmail.com.cn";
		 
		assertTrue(ref.isValidEmailString(fieldValue));
		
		fieldValue="john.zhang320aol.com.cn";		 
		assertFalse(ref.isValidEmailString(fieldValue));
		
		fieldValue="john.zhang320@aol.comcn";		 
		assertTrue(ref.isValidEmailString(fieldValue));
		
		fieldValue="john.mi.zhang320@aol.org.zh";		 
		assertTrue(ref.isValidEmailString(fieldValue));
		 
	}
	public void testIsValidPhoneString() {
		Log.info("testIsValidPhoneString(string str) ");
		 
		String fieldValue="4082347798";		 
		assertTrue(ref.isValidPhoneString(fieldValue));
		fieldValue="40823477981";		 
		assertFalse(ref.isValidPhoneString(fieldValue));
		fieldValue="(408)234-7798";		 
		assertTrue(ref.isValidPhoneString(fieldValue));
		fieldValue="(408)234-77198";		 
		assertFalse(ref.isValidPhoneString(fieldValue));		
		fieldValue="408-234-7798";		 
		assertTrue(ref.isValidPhoneString(fieldValue));
		fieldValue="408-2344-7798";		 
		assertFalse(ref.isValidPhoneString(fieldValue));
		fieldValue="(408)2344-7798";			
		assertFalse(ref.isValidPhoneString(fieldValue));
		fieldValue="40823447798";		 
		assertFalse(ref.isValidPhoneString(fieldValue));
	}
}
