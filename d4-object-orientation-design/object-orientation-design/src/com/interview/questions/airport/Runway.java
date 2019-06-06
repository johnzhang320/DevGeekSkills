package com.interview.questions.airport;

 

public class Runway implements Comparable<Runway>{
	private String runwayNo;
	private int width;
	private boolean occupied=false;
	
	public Runway(String runwayNo, int width, boolean occupied) {
		super();
		this.runwayNo = runwayNo;
		this.width = width;
		this.occupied = occupied;
	}
	@Override
	public boolean equals(Object obj) {
		return ((Runway) obj).runwayNo == this.runwayNo;
 	}
	@Override 
	public int hashCode() {
		int sum =0;
		char chars[] = runwayNo.toCharArray();
		for (int i=0;i<chars.length;i++) {
			sum = sum+chars[i];
		}
		return sum*53;
	}
	/**
	 *  because Comparator<Runway>, so compare must apply supertype: Runway
	 */
	@Override 
	public int compareTo(Runway obj) {
		int w1 = this.width;
		int w2 = ((Runway) obj).width;
		if (w1<w2) {
			return -1;
		} else if (w1>w2) {
			return 1;
		}
		return 0;
	}
	 

	public String getRunwayNo() {
		return runwayNo;
	}
	public void setRunwayNo(String runwayNo) {
		this.runwayNo = runwayNo;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public boolean isOccupied() {
		return occupied;
	}
	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}
	public String toString() {
		return "Runway No:"+ runwayNo+",width:"+width+",occupied:"+occupied;
	}
}
