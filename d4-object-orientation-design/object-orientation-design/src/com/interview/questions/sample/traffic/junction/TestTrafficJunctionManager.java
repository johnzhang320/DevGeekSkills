package com.interview.questions.sample.traffic.junction;

import java.util.Timer;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestTrafficJunctionManager {
	private TrafficQueue vqueue[] = new TrafficQueue[8];
	
	private ExecutorService executor;
	private static final int THREAD_POOL_SIZE=20; 
	
	
	public TestTrafficJunctionManager() {
		
		 executor = Executors.newFixedThreadPool(25);
		 vqueue[0] =new  TrafficQueue(new Vechicle(TrafficSides.EAST)); 
		 vqueue[1] =new  TrafficQueue(new Vechicle(TrafficSides.WEST)); 
		 vqueue[2] =new  TrafficQueue(new Vechicle(TrafficSides.NORTH)); 
		 vqueue[3] =new  TrafficQueue(new Vechicle(TrafficSides.SOUTH)); 
		 
		 vqueue[4] = new TrafficQueue(new Pedestrain(TrafficSides.EAST));
		 vqueue[5] = new TrafficQueue(new Pedestrain(TrafficSides.WEST));
		 vqueue[6] = new TrafficQueue(new Pedestrain(TrafficSides.NORTH));
		 vqueue[7] = new TrafficQueue(new Pedestrain(TrafficSides.SOUTH));
		 
	}
	
	 
	public void setCommingTrafficThread() {
		for (int i=0; i<8; i++) {
			Runnable thread =new  CommingTrafficThread(vqueue[i]);
			executor.execute(thread);
		}
		 
	}
	public void setPassingTrafficThread(Directions direction) {
		Runnable thread[] = new Runnable[8];
		
		thread[0] = new HorizantalTrafficThread(direction, vqueue[0], TrafficSides.EAST);
		thread[1] = new HorizantalTrafficThread(direction, vqueue[1], TrafficSides.WEST); 
		thread[2] = new VerticalTrafficThread(direction, vqueue[2], TrafficSides.NORTH);
		thread[3] = new VerticalTrafficThread(direction, vqueue[3], TrafficSides.SOUTH); 
		
		thread[4] = new HorizantalTrafficThread(direction, vqueue[4], TrafficSides.EAST);
		thread[5] = new HorizantalTrafficThread(direction, vqueue[5], TrafficSides.WEST); 		
		thread[6] = new VerticalTrafficThread(direction, vqueue[6], TrafficSides.NORTH);
		thread[7] = new VerticalTrafficThread(direction, vqueue[7], TrafficSides.SOUTH); 
		for (int i=0;i<8;i++ ) {
			executor.execute(thread[i]);
		}
	}
	public static void main(String args[]) {
		Directions direction = new Directions(false);
		TestTrafficJunctionManager ref = new TestTrafficJunctionManager ();
	 
		Runnable thread =new  CommingTrafficThread(new Vechicle(TrafficSides.EAST));
		ExecutorService executor = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
		executor.execute(thread);
		ref.setCommingTrafficThread();
		ref.setPassingTrafficThread(direction);
		 
		Timer timer = new Timer(); 
		
		TrafficJunctionThread task = new TrafficJunctionThread(direction);		 
		timer.scheduleAtFixedRate(task,  0, direction.getHorTime()); 
		 
	}
}
