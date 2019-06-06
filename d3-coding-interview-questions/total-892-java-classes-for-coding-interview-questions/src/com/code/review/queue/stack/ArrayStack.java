package com.code.review.queue.stack;

public class ArrayStack<T> {
	private static final int CAP=100;
	private T[] arr = (T[]) new Object[CAP];
	private int size=0;
	private int top=-1;
	
	public boolean push(T val) {
		if (isFull()) {
			return false;
		}
		size++;
		arr[++top] = val;
		
		return true;
		
	}
	public T pop() {
		if (isEmpty()) {
			return null;
		}
		T tmp = arr[top];
		arr[top] = null; // release object
		top--;
		size--;
		return tmp;
	}
	public T peek() {
		if (isEmpty()) {
			return null;
		} 
		return arr[top];
	}
	public boolean isFull() {
		if (size==CAP) {
			System.out.println("Stack Overflow");
			return true;
		} 
		return false;
	}
	public boolean isEmpty() {
		if (size==0) {
			System.out.println("Stack underflow");
			return true;
		}
		return false;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
