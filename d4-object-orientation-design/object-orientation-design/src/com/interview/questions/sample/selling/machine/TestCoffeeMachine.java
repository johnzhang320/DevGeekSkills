package com.interview.questions.sample.selling.machine;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;


public class TestCoffeeMachine {
	private static SellingMachine vm;
	@BeforeClass
    public static void setUp(){
        vm = SellingMachineFactory.getSellingMachineFactory("coffeeMachine");
    }

    @AfterClass
    public static void tearDown(){
    	vm.reset();
        vm = null;
    }

    @Test
    public void testBuyCoffeeWithExactPrice() {
        //select item, price in cents
        long price = vm.getItemPrice(Coffee.CUPPUCCINO);
        //price should be Coke's price      
        System.out.println("Price = " +price);
        assertEquals(Coffee.CUPPUCCINO.getPrice(), price);
        //25 cents paid              
        vm.insertCoin(Coin.QUARTER);           
        vm.insertCoin(Coin.DOLLAR);          
       
        
        Bucket<Product, List<Coin>> bucket = vm.collectItemChange();
        Product item = bucket.getFirst();
        List<Coin> change = bucket.getSecond();
        System.out.println("item name = " +item.getName()+",change.isEmpty="+change.isEmpty());
        //should be Coke
        assertEquals(Coffee.CUPPUCCINO, item);
        //there should not be any change                              
        assertTrue(change.isEmpty());                              
    }
   
    @Test
    public void testBuyItemWithMorePrice(){
        long price = vm.getItemPrice(Coffee.MOCHA);
        assertEquals(Coffee.MOCHA.getPrice(), price);
       
       // vm.insertCoin(Coin.DOLLAR);       
        vm.insertCoin(Coin.QUARTER);      
       
        Bucket<Product, List<Coin>> bucket = vm.collectItemChange();
        Product item = bucket.getFirst();
        List<Coin> change = bucket.getSecond();
       
        //should be Coke
        assertEquals(Coffee.MOCHA, item);
        //there should not be any change                                     
        assertTrue(!change.isEmpty());        
        //comparing change                             
        assertEquals(100 - Coffee.MOCHA.getPrice(), vm.getTotalChange(change));  
     
	 }
      

}  