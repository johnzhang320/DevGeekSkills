package com.code.review.hard.saleforce.encode;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;


public class TestMorseCode {
	private static ReadInputMorseCode ref;
	@BeforeClass
    public static void setUp(){
        ref =new ReadInputMorseCode();
    }

    @AfterClass
    public static void tearDown(){
    	 
        ref = null;
    }

    @Test
    public void testReadProblemFile() {
    	 ref.readInputMorseCode();  
    	 assertEquals(36,ref.getEncodeMap().size());
    	 assertEquals(0,ref.getDictionary().size());
    }
   
    @Test
    public void testEncodeContext(){
    	 String str = ref.encodeContext();
    	 System.out.println(str);
    	 
	}
      
    @Test
    public void testDecodeContext(){
    	 String str = ref.decodeContext();
    	
    	// assertEquals(9,ref.getDictionary().size());
    	 System.out.println(str);
    	 
	}
    
}  