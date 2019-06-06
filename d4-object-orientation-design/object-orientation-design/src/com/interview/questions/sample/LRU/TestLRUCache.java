package com.interview.questions.sample.LRU;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;




public class TestLRUCache {
	int key[] = {100,31,23,22,212,33,45,55};
	int value[] = {1001,1002,1003,1004,1005,1006};
	private static LRUCache su;
	@BeforeClass
    public static void setUp(){
        su = new LRUCache(5);
    }
	 
	@Test
    public void testSetWithinCapacity(){
		for (int i=0;i<5;i++) {
			su.set(key[i], value[i]);
		}
		assertEquals(5,su.map.size());
		su.display();
		
	}
	@Test
    public void testSetOverCapacity(){
		for (int i=5;i<6;i++) {
			su.set(key[i], value[i]);
		}
		assertEquals(5,su.map.size());
 	}
	@Test
    public void testGet(){
		int value = su.get(31);
		assertEquals(1002,value);
	    su.display();
 	}
	
    @AfterClass
    public static void tearDown(){
    	su.map.clear();
        su = null;
    }

    
  
   
   


 

 
 
	
	 

}
