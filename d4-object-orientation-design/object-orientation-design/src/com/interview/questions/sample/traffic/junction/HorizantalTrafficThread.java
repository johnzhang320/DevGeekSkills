package com.interview.questions.sample.traffic.junction;

public class HorizantalTrafficThread implements Runnable {
	
	private Directions direction; 
	private TrafficQueue tqueue;
	private TrafficSides sides;
	
	 
	public HorizantalTrafficThread(Directions direction, TrafficQueue tqueue, TrafficSides sides) {
		super();
		this.direction = direction;
		this.tqueue = tqueue; 
		this.sides = sides;
	}


	public void run() {
		while (true) {
			synchronized(direction) {
				// waiting for the Horizantal sign
				while (!direction.isHorizantal()) {
					try {
						System.out.println(tqueue.getTraffic().getTrafficType()+" is waiting for Horizatal light");
						direction.wait();
					} catch (InterruptedException e) {}
				}
			}
			System.out.println(" Already turn on  Horizatal light");
			while (direction.isHorizantal()) {
				if (tqueue.isEmpty()) {
					System.out.println(sides.getSide()+" no comming!");
					try {
						Thread.sleep(20000);
					} catch (InterruptedException e) {}
					continue;
				}
				
				
				try {
					
					Traffic traffic = tqueue.pollTraffic();
					System.out.println(traffic.getTrafficType()+" is passing Horizantal Junction to "+sides.getSide());
					Thread.sleep(traffic.getPassingSpeed());
				} catch (InterruptedException e) {}
				continue;
			}
		}
	}
	
}
