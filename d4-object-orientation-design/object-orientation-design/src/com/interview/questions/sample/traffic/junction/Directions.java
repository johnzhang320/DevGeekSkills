package com.interview.questions.sample.traffic.junction;

public class Directions {
	private boolean Horizantal=true;
	private int HorTime = 8000;
	private int VerTime = 5000;
	public Directions(boolean horizantal) {
		super();
		Horizantal = horizantal;
	}

	public Directions(boolean horizantal, int horTime, int verTime) {
		super();
		Horizantal = horizantal;
		HorTime = horTime;
		VerTime = verTime;
	}

	public boolean isHorizantal() {
		return Horizantal;
	}

	public void setHorizantal(boolean horizantal) {
		Horizantal = horizantal;
	}

	public int getHorTime() {
		return HorTime;
	}

	public void setHorTime(int horTime) {
		HorTime = horTime;
	}

	public int getVerTime() {
		return VerTime;
	}

	public void setVerTime(int verTime) {
		VerTime = verTime;
	}
 	
}
