package com.interview.questions.airport;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestAirport {
	private static Airport ap;
	@BeforeClass
	public static void setUp() {
		ap = AirportFactory.createFactory("SFO");
	}
	@AfterClass
	public static void tearDown() {
		ap=null;
	}
	@Test
	public void testAirport() {
		// Test Airport initialized
		assertTrue(!ap.getRunwayList().isEmpty());     
		ap.TakeRunway(Flight.CA101);
		ap.TakeRunway(Flight.UA130);
		ap.TakeRunway(Flight.UD402);
		ap.TakeRunway(Flight.CA101);	 
		ap.TakeRunway(Flight.UN650);
		ap.TakeRunway(Flight.CQ200);
		ap.TakeRunway(Flight.TC1900);
		ap.TakeRunway(Flight.UP122);
	 
		
		assertEquals(ap.getRunwayMap().size(),5);
		ap.releaseRunway(Flight.CA101);
		assertEquals(ap.getRunwayMap().size(),4);
		
	}
 
}
