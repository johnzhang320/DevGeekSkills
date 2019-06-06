package com.interview.questions.sample.traffic.junction;

import java.util.Timer;
import java.util.TimerTask;

public class TrafficJunctionThread extends TimerTask {
	private Directions direction;

	public TrafficJunctionThread(Directions direction) {
		super();
		this.direction = direction;
	} 
	public void run() {
 		try {
			synchronized(direction) {
				if (!direction.isHorizantal()) {
					System.out.println("Swithing to Horizantal light");
					direction.setHorizantal(true);
					direction.notifyAll();
				} else if (direction.isHorizantal()) {
					System.out.println("Swithing to Vertical light");
					direction.setHorizantal(false);
					direction.notifyAll();
				}
			}
			 
		} catch (Exception e) {}
 		
	}
	public static void main(String args[]) {
		Timer timer = new Timer(); 
		Directions direction = new Directions(false);
		TrafficJunctionThread task = new TrafficJunctionThread(direction);		 
		timer.scheduleAtFixedRate(task,  0, direction.getHorTime());
		 
	}
}
