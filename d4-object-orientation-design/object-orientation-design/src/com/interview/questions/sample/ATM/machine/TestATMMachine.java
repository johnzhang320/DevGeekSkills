package com.interview.questions.sample.ATM.machine;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;


public class TestATMMachine {
	private static AccountManager vm;
	@BeforeClass
    public static void setUp(){
        vm = AcoountFactory.getAccount("WellsfargoSunnyvale");
    }

    @AfterClass
    public static void tearDown(){
    	vm.reset();
        vm = null;
    }

    @Test
    public void testBuyCoffeeWithExactPrice() {
                        
    }
   
    @Test
    public void testBuyItemWithMorePrice(){
        
     
	 }
      

}  