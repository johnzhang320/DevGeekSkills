package com.code.review.aaa.walmart.code;

public class ArrayStack<T> {
	private static final int CAP=10;
	private T[] A = (T[]) new Object[CAP];
	private int size=0;
	private int top=-1;
	public boolean isEmpty() {
		if (size==0) {
			System.out.println("Stack underflow!");
			return true;
		}
		return false;
	}
	public boolean isFull() {
		if (size==CAP) {
			System.out.println("Stack overflow");
			return true;
		}
		return false;
	}
	public boolean push(T value) {
		if (isFull()) {
			return false;
		}
		size++;
		A[++top]=value;
		return true;
	}
	public T pop() {
		if (isEmpty()) {
			return null;
		}
		size--;
		T tmp = A[top];
		A[top]=null;
		top--;
		return tmp;
	}
	public T peek() {
		if (isEmpty()) {
			return null;
		}
 		return A[top];
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayStack<Integer> ref = new ArrayStack<Integer>();
		for (int i=0;i<12;i++) {
			ref.push(i);
			System.out.println("push "+i);
		}
		for (int i=0;i<12;i++) {
			System.out.println("pop "+ref.pop());
		}
	}
}
