package com.interview.questions.sample.selling.machine;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;


public class TestVendorMachine {
	private static SellingMachine vm;
	@BeforeClass
    public static void setUp(){
        vm = SellingMachineFactory.getSellingMachineFactory("vendorMachine");
    }

    @AfterClass
    public static void tearDown(){
        vm = null;
    }

    @Test
    public void testBuyDrinkWithExactPrice() {
        //select item, price in cents
        long price = vm.getItemPrice(Drink.COKE);
        //price should be Coke's price      
        System.out.println("Price = " +price);
        assertEquals(Drink.COKE.getPrice(), price);
        //25 cents paid              
        vm.insertCoin(Coin.QUARTER);                           
       
        
        Bucket<Product, List<Coin>> bucket = vm.collectItemChange();
        Product item = bucket.getFirst();
        List<Coin> change = bucket.getSecond();
        System.out.println("item name = " +item.getName()+",change.isEmpty="+change.isEmpty());
        //should be Coke
        assertEquals(Drink.COKE, item);
        //there should not be any change                              
        assertTrue(change.isEmpty());                              
    }
   
    @Test
    public void testBuyItemWithMorePrice(){
        long price = vm.getItemPrice(Drink.SODA);
        assertEquals(Drink.SODA.getPrice(), price);
       
        vm.insertCoin(Coin.QUARTER);       
        vm.insertCoin(Coin.QUARTER);      
       
        Bucket<Product, List<Coin>> bucket = vm.collectItemChange();
        Product item = bucket.getFirst();
        List<Coin> change = bucket.getSecond();
       
        //should be Coke
        assertEquals(Drink.SODA, item);
        //there should not be any change                                     
        assertTrue(!change.isEmpty());        
        //comparing change                             
        assertEquals(50 - Drink.SODA.getPrice(), vm.getTotalChange(change));  
       
    }  

    @Test
    public void testRefund(){
        long price = vm.getItemPrice(Drink.PEPSI);
        assertEquals(Drink.PEPSI.getPrice(), price);       
        vm.insertCoin(Coin.DIME);
        vm.insertCoin(Coin.NICKLE);
        vm.insertCoin(Coin.PENNY);
        vm.insertCoin(Coin.QUARTER);
        
      //  assertEquals(41, vm.getTotalChange(vm.refund()));       
    }
   
    @Test(expected=SoldOutException.class)
    public void testSoldOut(){
        for (int i = 0; i < 5; i++) {
            vm.getItemPrice(Drink.COKE);
            vm.insertCoin(Coin.QUARTER);
            vm.collectItemChange();
        }
     
    }
   
    @Test(expected=NotSufficientChangeException.class)
    public void testNotSufficientChangeException(){
        for (int i = 0; i < 5; i++) {
            vm.getItemPrice(Drink.SODA);
            vm.insertCoin(Coin.QUARTER);
            vm.insertCoin(Coin.QUARTER);
            vm.collectItemChange();
           
            vm.getItemPrice(Drink.PEPSI);
            vm.insertCoin(Coin.QUARTER);
            vm.insertCoin(Coin.QUARTER);
            vm.collectItemChange();
        }
    }
   
   
    @Test(expected=SoldOutException.class)
    public void testReset(){
       SellingMachine vmachine = SellingMachineFactory.getSellingMachineFactory("vendorMachine");
        vmachine.reset();
       
        vmachine.getItemPrice(Drink.COKE);
       
    }
   
   


 

 
 
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	//	TestVendorMachine ref = new TestVendorMachine();
	//	ref.setUp();
	//	ref.testBuyDrinkWithExactPrice();
	}

}
