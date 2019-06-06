package com.interview.questions.sample.parkinglots;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.*;
import org.junit.Test;
import static org.junit.Assert.*;



public class TestParkingLot {
	private static ParkingLot pl;
	@BeforeClass
	public static void setUp() {
		  pl = new Runch99ParkingLot();
		
	}
	@AfterClass
	public static void tearDown() {
		pl = null;
	}
	@Test
	public void TestThreeVichiclesTakenAndUnPack() {
		String token1 = pl.parking(VechicleLevel.MOTORCYCLE);
 		assertNotNull(token1);
 		//assertEquals("S0 Motorcycle",token1);
 		String token2 = pl.parking(VechicleLevel.CAR);
 		assertNotNull(token2);
 		//assertEquals("C0 Car",token2);
 		String token3 = pl.parking(VechicleLevel.BUS);
 		assertNotNull(token3);
 		//assertEquals("L0 Bus",token3);
 		pl.display();
 		pl.unparking(token1);
 		pl.display();
	}
	@Test
	public void TestParkingLotSlotFullyException() {
		
		String token = pl.parking(VechicleLevel.CAR);
 		assertNotNull(token);
		System.out.println("Token = "+token);
		//assertTrue(pl.unparking(token));
		token = pl.parking(VechicleLevel.CAR);
 		assertNotNull(token);
		System.out.println("Token = "+token);
		
		//assertTrue(pl.unparking(token));
		token = pl.parking(VechicleLevel.CAR);
 		assertNotNull(token);
 		
		System.out.println("Token = "+token);
		
		token = pl.parking(VechicleLevel.CAR);
 		assertNotNull(token);
 		
		System.out.println("Token = "+token);
		
		token = pl.parking(VechicleLevel.CAR);
 		assertNotNull(token);
 		
		System.out.println("Token = "+token);
		
		token = pl.parking(VechicleLevel.CAR);
 		assertNotNull(token);
 		
		System.out.println("Token = "+token);
		
		token = pl.parking(VechicleLevel.CAR);
 		assertNotNull(token);
 		
		System.out.println("Token = "+token);
		//assertTrue(pl.unparking(token));   // unparking 
		
		token = pl.parking(VechicleLevel.CAR);
 		assertNotNull(token);
 		
		System.out.println("Token = "+token);
		
		token = pl.parking(VechicleLevel.CAR);
 		assertNotNull(token);
 		
		System.out.println("Token = "+token);  // not found available slot
		
	//	assertTrue(pl.unparking(token));
		
	//	pl.display();
		
		

		 
 		
	}
	@Test
	public void TestReset() {
		 
		pl.display();
	}
}
