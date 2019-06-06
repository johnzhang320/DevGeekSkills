package com.interview.questions.sample.traffic.junction;

public class CommingTrafficThread implements Runnable {
	private TrafficQueue tqueue;
	private Traffic traffic;
	
	public CommingTrafficThread(TrafficQueue tqueue) {
		super();
		this.tqueue = tqueue;
		this.traffic = this.tqueue.getTraffic();
	}
	
	public CommingTrafficThread(Traffic traffic) {
		this.tqueue = new TrafficQueue(traffic);
		this.traffic = traffic;
	}
 
	public void run() {
		while (true) {
		this.tqueue.addTraffic(this.traffic);
			try {
				 
				System.out.println(traffic.getTrafficType()+" is comming from "+ traffic.getSides());				 
				Thread.sleep(traffic.getCommingFrequent());
			} catch (InterruptedException e) {}
		}
	}


	public TrafficQueue getTqueue() {
		return tqueue;
	}


	public void setTqueue(TrafficQueue tqueue) {
		this.tqueue = tqueue;
	}
	
}
