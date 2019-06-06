package com.interview.questions.sample.traffic.junction;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class TrafficQueue {
	private Traffic traffic;
	public Queue<Traffic> myQueue = new LinkedBlockingQueue<Traffic>();
	
	public TrafficQueue(Traffic traffic) {
		super();
		this.traffic = traffic;		
		myQueue.add(traffic);
		
		
	} 
	public synchronized void addTraffic(Traffic traffic) {
		myQueue.add(traffic);	 
	}
	public boolean isEmpty() {
		return myQueue.isEmpty();
	}
	public synchronized Traffic pollTraffic() {
		try {
			if (!myQueue.isEmpty()) {
		 
				return myQueue.poll();
			} else {
				throw new NoTrafficException(" No any traffic on this side of queue! ");
			}
		} catch (NoTrafficException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public synchronized Traffic peek() {
		try {
			if (!myQueue.isEmpty()) {		 
				return myQueue.peek();
			} else {
				throw new NoTrafficException(" No any traffic on this side of queue! ");
			}
		} catch (NoTrafficException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	
	public Traffic getTraffic() {
		return traffic;
	}
	public void setTraffic(Traffic traffic) {
		this.traffic = traffic;
	}
	
}
 