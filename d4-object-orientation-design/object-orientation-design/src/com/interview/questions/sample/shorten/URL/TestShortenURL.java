package com.interview.questions.sample.shorten.URL;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;


public class TestShortenURL {
	private static ShortenURL su;
	@BeforeClass
    public static void setUp(){
        su = new ShortenURL();
    }
	 
	@Test
    public void testEncodeDecode(){
		Long id =53462L;
		String surl = Utils.encode(id);     
	    Long durl = Utils.decode(surl);
	    assertEquals(durl, id);
	}
	
    @AfterClass
    public static void tearDown(){
        su = null;
    }

    @Test
    public void testShortenURLRequest() {
        String myURL = "https://mortgageloan.com";
        String shURL = su.shortenOneURL(myURL);
        assertEquals("2ef.5a",shURL); 
        myURL = "https://mortgageloan.com";
        shURL = su.shortenOneURL(myURL);
        
    }
   
    @Test
    public void testAcceptAccess(){
    	 
    	String originalUrl = su.acceptAccess("2ef.5a");
    	assertEquals("https://mortgageloan.com",originalUrl);
    }  
    @Test
    public void testAcceptAccessException(){
    	String originalUrl = su.acceptAccess("2ef.51");
    	assertNull(originalUrl);
    	 
    }  
 
    
    @Test
    public void testAuditing(){
        
       su.auditingAndHouseKeep(false);  
       
       su.auditingAndHouseKeep(true);   
       
    }
   
  
   
   


 

 
 
	
	 

}
