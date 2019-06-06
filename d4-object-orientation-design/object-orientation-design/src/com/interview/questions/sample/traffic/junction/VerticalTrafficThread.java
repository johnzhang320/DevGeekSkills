package com.interview.questions.sample.traffic.junction;

public class VerticalTrafficThread implements Runnable {
	
	private Directions direction; 
	private TrafficQueue tqueue;
	private TrafficSides sides;
	
	 
	public VerticalTrafficThread(Directions direction, TrafficQueue tqueue, TrafficSides sides) {
		super();
		this.direction = direction;
		this.tqueue = tqueue; 
		this.sides = sides;
	}


	public void run() {
		while (true) {
			synchronized(direction) {
				// waiting for the Horizantal sign
				while (direction.isHorizantal()) {
					try {
						System.out.println(tqueue.getTraffic().getTrafficType()+" is Waiting for Vertical light");
						direction.wait();
					} catch (InterruptedException e) {}
				}
			}
			System.out.println(" Already turn on  Vertical light");
			while (!direction.isHorizantal()) {
				if (null!=tqueue && tqueue.isEmpty()) {
					System.out.println(sides.getSide()+" no comming!");
					try {
						Thread.sleep(20000);
					} catch (InterruptedException e) {}
					continue;
				}
				Traffic traffic = tqueue.pollTraffic();
				System.out.println(traffic.getTrafficType()+" is passing Vertical Junction to "+sides.getSide());
			}
		}
	}
	
}
