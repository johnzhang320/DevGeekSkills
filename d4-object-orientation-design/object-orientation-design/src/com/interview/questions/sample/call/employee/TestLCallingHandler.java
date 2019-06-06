package com.interview.questions.sample.call.employee;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;




public class TestLCallingHandler {
	 
	private static CallingHandler su;
	@BeforeClass
    public static void setUp(){
        su = new CallingHandler();
    }
	 
	@Test
    public void DisplayInitialize(){
		 
		su.displayStats();
	}
	@Test
    public void testCallingHandler(){
		String caller[] = {"John Smith","Carl Max","Eric Bush","Donald Trump","Hillary Clinton","Winston Churchill","Franklin Barak"};
		EmpRank rank[] = {EmpRank.FRESHER, EmpRank.FRESHER,EmpRank.FRESHER,EmpRank.FRESHER,EmpRank.TL,EmpRank.PM,EmpRank.PM,EmpRank.TL};
		Call call[] = new Call[caller.length];
		Employee emps[] = new Employee[caller.length];
		for (int i=0; i < caller.length;i++) {
			call[i] = new Call(rank[i],caller[i]);
			emps[i]=su.incomingCallHandle(call[i]);
		}
		su.displayStats(); 
 	}
	 
    
	@Test
    public void testDisconnected(){
		Call calling = new Call(EmpRank.FRESHER,"Carl Max");
		boolean dis=su.disconnetedOneCalling(calling);
		assertFalse(dis);
		su.displayStats(); 
 	}
	 
   
	 
	@Test
    public void testRefreshWaitingQueue(){
	 
		boolean dis=su.handleWaitingCalling();
		assertTrue(dis);
		su.displayStats(); 
 	}
		 


 

 
 
	
	 

}
