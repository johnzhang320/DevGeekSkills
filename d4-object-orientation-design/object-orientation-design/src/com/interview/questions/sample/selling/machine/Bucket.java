package com.interview.questions.sample.selling.machine;

import java.util.ArrayList;
import java.util.List;
/**
 * A parameterized utility class to hold two different object.
 * Such as Bucket pattern is good to apple as pair or Entry class which parameterized utility class 
 */
 
public class Bucket<E1, E2> {
	private E1 first;
	private E2 second;
	 
	public Bucket(E1 first, E2 second) {
		super();
		this.first = first;
		this.second = second;
	}
	public E1 getFirst() {
		return first;
	}
	public void setFirst(E1 first) {
		this.first = first;
	}
	public E2 getSecond() {
		return second;
	}
	public void setSecond(E2 second) {
		this.second = second;
	}
	 
	
}
