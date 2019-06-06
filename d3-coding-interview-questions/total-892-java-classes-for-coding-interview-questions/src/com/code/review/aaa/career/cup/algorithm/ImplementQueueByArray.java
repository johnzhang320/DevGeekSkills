package com.code.review.aaa.career.cup.algorithm;

public class ImplementQueueByArray {
	/**
	 * (1) Create Array and initial size Capacity is 10
	 * (2) define two pointer front=0 and rear =0, len = A.length;
	 * (3) when add data from queue, check if rear==len, if yes, return queue is full, if not, return A[rear++]=data
	 * (3) when delete data from queue, check front==rear+1, if yes, return queue is empty, if not, return A[front] front++ 
	 * @param args
	 */
	private int A[] = new int[10];
	private int front = 0;
	private int rear = 0;
	public void enqueue(int data) throws Exception {
		if (rear==A.length) {
			throw new Exception("queue is full");
		} else {
			A[rear++] = data;
		}
		
	}
	public int dequeue() throws Exception {
		if (front == rear) {
			throw new Exception("queue is empty");
		} else {
			int result = A[front];
			front++;
			return result; 
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
