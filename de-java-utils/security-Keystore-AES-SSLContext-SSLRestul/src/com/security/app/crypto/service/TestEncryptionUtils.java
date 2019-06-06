package com.security.app.crypto.service;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;


public class TestEncryptionUtils {
	private static EncryptionUtils su;
	
	@BeforeClass
    public static void setUp(){
        su = EncryptionUtils.getInstance();
    }
	@AfterClass
    public static void tearDown(){
        su = null;
    } 
	@Test
    public void testEncryptionAndDecryption(){
		String message = "Hello, Emily!"; 
		String cipher="";
		try {
			cipher = su.encrypt(message);
			System.out.println("Original message="+message+"\nEncrypted Cipher="+cipher);
			Thread.sleep(1000);
			message = su.decrypt(cipher);
			System.out.println("Original Cipher="+cipher+"\nDecrypted Message="+message);
		} catch (Exception e) {}
		
	}
    
}
